package com.soloUtd.productservices.Repository;

import com.soloUtd.productservices.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
