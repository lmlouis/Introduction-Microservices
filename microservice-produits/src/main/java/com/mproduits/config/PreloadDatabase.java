package com.mproduits.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mproduits.dao.ProductDao;
import com.mproduits.model.Product;

@Configuration
public class PreloadDatabase {
    private static final Logger log = LoggerFactory.getLogger(PreloadDatabase.class);

    @Autowired
    ProductDao service;

    @Bean
    CommandLineRunner initDatabase() {
        service.save(new Product(1, 
                                "LYCANDER USB Wall Chargeur", 
                                "LYCANDER USB Wall Chargeur EU Spec with 4 Ports 5A/25W Adaptive Charging Technology (EU Plug ) Marque : LYCANDER", 
                                "https://m.media-amazon.com/images/I/61L-wR5fnoL._AC_SY355_.jpg", 
                                14.53));
        service.save(new Product(2, 
                                "Jabra Elite 7 Pro", 
                                "Jabra Elite 7 Pro Écouteurs Bluetooth intra auriculaires - Écouteurs à réduction de bruit active réglable True Wireless", 
                                "https://m.media-amazon.com/images/I/51xUQWwBH0L._AC_UL320_.jpg", 
                                166.0));
        service.save(new Product(3, 
                                "LifeProof pour Apple iPhone 7 / 8 / SE", 
                                "LifeProof pour Apple iPhone 7 / 8 / SE 2e gen (2020) / SE 3e gen (2022), Coque antichoc et étanche, Série Fre, Noir", 
                                "https://m.media-amazon.com/images/I/51ILU1WLB8L._AC_SY606_.jpg", 
                                48.40));        

        return args -> {
        log.info("Initialisation Basse de données... ");
        };
    }
    
}
