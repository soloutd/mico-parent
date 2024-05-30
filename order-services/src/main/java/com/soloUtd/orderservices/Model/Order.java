package com.soloUtd.orderservices.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity(name = "t_order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private String orderNumber;
    private String customerName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItem> orderLineItemList;


}
