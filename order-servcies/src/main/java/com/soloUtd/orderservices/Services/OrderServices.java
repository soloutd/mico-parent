package com.soloUtd.orderservices.Services;


import com.soloUtd.orderservices.Config.WebConfig;
import com.soloUtd.orderservices.Dto.InventoryResponse;
import com.soloUtd.orderservices.Dto.OrderLineItemDto;
import com.soloUtd.orderservices.Dto.RequestOrder;
import com.soloUtd.orderservices.Model.Order;
import com.soloUtd.orderservices.Model.OrderLineItem;
import com.soloUtd.orderservices.Repository.OrderRepository;
import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServices {


    private static final Logger log = LoggerFactory.getLogger(OrderServices.class);
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;


   /* public OrderServices(OrderRepository orderRepository, WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082/inventory").build();
    }*/


    public void placeOrder(RequestOrder requestOrder) throws InvocationTargetException {
        Order order = createOrder(requestOrder);

        List<String> skuCodes = order.getOrderLineItemList()
                .stream().map(OrderLineItem::getSkuCode).toList();
        // call inventory services and place order is present.

        /*webClientBuilder webC = webClientBuilder.clientConnector(new ReactorClientHttpConnector(
                HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE)
        ));*/
      /*  WebClient webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(
                                HttpClient.create()
                                        .resolver(DefaultAddressResolverGroup.INSTANCE)
                        )
                ).build();*/

        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://localhost:8082/api/inventory/sku",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build(skuCodes))
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        response ->
                                switch (response.hashCode()) {
                                    case 400 -> Mono.error(new Exception("-----bad request made"));
                                    case 401, 403 -> Mono.error(new Exception("auth error"));
                                    case 404 -> Mono.error(new Exception("-----Maybe not an error?"));
                                    case 500 -> Mono.error(new Exception("-----server error"));
                                    default -> Mono.error(new Exception("-----something went wrong"));
                                }
                )
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);



        if (allProductsInStock) {
            orderRepository.save(order);
        }else {
            throw new IllegalArgumentException("-----bad request: Product not in stock!!!");
        }
    }



    public Order createOrder(RequestOrder requestOrder) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = requestOrder.getOrderLineItemDtoList()
                .stream().map(this::mapDTO).toList();
        order.setOrderLineItemList(orderLineItems);

        return order;
    }

    private OrderLineItem mapDTO(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        return orderLineItem;
    }
}
