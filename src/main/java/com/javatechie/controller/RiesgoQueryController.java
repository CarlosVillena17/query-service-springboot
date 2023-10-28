package com.javatechie.controller;

import com.javatechie.dto.RiesgoEvent;
import com.javatechie.entity.Riesgo;
import com.javatechie.service.RiesgoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/products")
@RestController
public class RiesgoQueryController {

    @Autowired
    private RiesgoQueryService queryService;

    @GetMapping
    public List<Riesgo> fetchAllProducts(){
        return queryService.getProducts();
    }


}
