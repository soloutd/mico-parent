package com.soloUtd.inventoryservices.Dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest implements Serializable {

    private String skuCode;
    private int quantity;

}

