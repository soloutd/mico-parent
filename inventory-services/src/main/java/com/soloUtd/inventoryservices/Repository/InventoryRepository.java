package com.soloUtd.inventoryservices.Repository;

import com.soloUtd.inventoryservices.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    //Optional<Inventory> findBySkuCode(List<String> skucode);

    List<Inventory> findBySkuCodeIn(List<String> skucode);
}
