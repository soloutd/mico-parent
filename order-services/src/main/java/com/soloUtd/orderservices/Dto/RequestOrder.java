package com.soloUtd.orderservices.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrder  {

    private List<OrderLineItemDto> orderLineItemDtoList;

}

