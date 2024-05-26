package com.soloUtd.productservices.Service;


import com.soloUtd.productservices.Dto.ProductRequest;
import com.soloUtd.productservices.Dto.ProductResponse;
import com.soloUtd.productservices.Model.Product;
import com.soloUtd.productservices.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServices {


    private static final Logger log = LoggerFactory.getLogger(ProductServices.class);
    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {

        Product product = Product.builder().
                name(productRequest.getName()).
                description(productRequest.getDescription()).
                price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product created: {}", product.getId());

    }

    public List<ProductResponse> getAllPro() {
        List<Product> products = productRepository.findAll();

        return  products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {

        return ProductResponse.builder().id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice()).build();
    }

    public ProductResponse getPro(String id) throws Exception {
        try{
            Product product = productRepository.findById(id).get();
            log.info("Product found: {}", product.getId());
           return  ProductResponse.builder().id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .build();
        }catch (Exception e){
            log.error("Product not found: {}", id);
                 throw new Exception(e.getMessage());
        }

    }


}
