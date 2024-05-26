package com.soloUtd.orderservices.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class InventoryResponse {

    private String skucode;
    private boolean isInStock;
}
