package com.soloUtd.productservices.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "product")
public class Product {

    @Id()
    private String id;
    @NonNull
    private String name;
    private String description;
    private double price;

}
