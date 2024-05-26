package com.soloUtd.orderservices.Dto;

import com.soloUtd.orderservices.Model.Order;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class OrderLineItemDto {

    private long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
