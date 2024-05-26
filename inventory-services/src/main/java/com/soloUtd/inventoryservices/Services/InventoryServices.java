package com.soloUtd.inventoryservices.Services;

import com.soloUtd.inventoryservices.Dto.InventoryResponse;
import com.soloUtd.inventoryservices.Repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServices {

    private final InventoryRepository inventoryRepository;

    public List<InventoryResponse> checkInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream().map(inventory ->
                    InventoryResponse.builder()
                            .skucode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                ).toList();

    }

}
