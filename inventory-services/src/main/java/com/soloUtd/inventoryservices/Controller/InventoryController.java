package com.soloUtd.inventoryservices.Controller;


import com.soloUtd.inventoryservices.Dto.InventoryResponse;
import com.soloUtd.inventoryservices.Services.InventoryServices;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/")
@RequiredArgsConstructor
public class InventoryController {


    private static final Logger log = LoggerFactory.getLogger(InventoryController.class);
    private final InventoryServices inventoryServices;


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<InventoryResponse> handleIllegalAccessException(IllegalAccessException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // impl counter for skuCode V2
    // The service need to check how many inventory left and return accordingly if it's not enough.
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "sku")
    public List<InventoryResponse> InStock(@RequestParam(value = "skuCode" ) List<String> skuCode) {
        return inventoryServices.checkInStock(skuCode);
    }

}
