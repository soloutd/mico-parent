package com.soloUtd.productservices.Controller;


import com.soloUtd.productservices.Dto.ProductRequest;
import com.soloUtd.productservices.Dto.ProductResponse;
import com.soloUtd.productservices.Model.Product;
import com.soloUtd.productservices.Service.ProductServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prod")
@AllArgsConstructor
public class ProductController {

    private final ProductServices productServices;
    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody ProductRequest productRequest) {
        productServices.createProduct(productRequest);
    }

    @GetMapping(value = "/getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProAll(){
        return  productServices.getAllPro();
    }

    @GetMapping(value = "/getPro/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getPro(@PathVariable String id) throws Exception {
        return  productServices.getPro(id);
    }
}
