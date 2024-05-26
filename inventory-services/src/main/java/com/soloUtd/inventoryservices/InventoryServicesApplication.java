package com.soloUtd.inventoryservices;

import com.soloUtd.inventoryservices.Model.Inventory;
import com.soloUtd.inventoryservices.Repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class InventoryServicesApplication {

	private static final Logger log = LoggerFactory.getLogger(InventoryServicesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InventoryServicesApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory1 = new Inventory();
			inventory1.setId(1L);
			inventory1.setSkuCode("iphone-13");
			inventory1.setQuantity(5);
			inventoryRepository.save(inventory1);
			Inventory inventory2 = new Inventory();
			inventory2.setId(2L);
			inventory2.setSkuCode("iphone13-red");
			inventory2.setQuantity(12);
			inventoryRepository.save(inventory2);
			log.info("Inventory 1: " + inventory1);
			log.info("Inventory 2: " + inventory2);
		};

	}
}
