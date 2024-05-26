package com.soloUtd.inventoryservices.Model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "t_inventory")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String skuCode;
    private Integer quantity;
}
