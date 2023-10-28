package com.javatechie.service;

import com.javatechie.dto.RiesgoEvent;
import com.javatechie.entity.Riesgo;
import com.javatechie.repository.RiesgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RiesgoQueryService {

    @Autowired
    private RiesgoRepository repository;

    public List<Riesgo> getProducts() {
        return repository.findAll();
    }

    @KafkaListener(topics = "product-event-topic",groupId = "product-event-group")
    public void processProductEvents(RiesgoEvent productEvent) {
        Riesgo product = productEvent.getProduct();
        if (productEvent.getEventType().equals("CreateProduct")) {
            repository.save(product);
        }
        if (productEvent.getEventType().equals("UpdateProduct")) {
            Riesgo existingProduct = repository.findById(product.getId()).get();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDescription(product.getDescription());
            repository.save(existingProduct);
        }
    }
}
