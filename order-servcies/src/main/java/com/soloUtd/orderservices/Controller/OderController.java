package com.soloUtd.orderservices.Controller;


import com.soloUtd.orderservices.Dto.RequestOrder;
import com.soloUtd.orderservices.Services.OrderServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping(value = "/ord")
public class OderController {

    private static final Logger log = LoggerFactory.getLogger(OderController.class);
    @Autowired
    OrderServices orderServices;
    @PostMapping("/place/ord")
    public ResponseEntity<HttpStatus> placeOrd(@RequestBody RequestOrder requestOrder) throws InvocationTargetException {

        orderServices.placeOrder(requestOrder);
        log.info("-----------> Order is placed!: --> {}", requestOrder.toString());

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
