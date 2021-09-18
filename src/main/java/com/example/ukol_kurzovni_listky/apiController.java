package com.example.ukol_kurzovni_listky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rates")
public class apiController {

    @Autowired
    public ExchangeRateRepository repository;


    @GetMapping
    public List<ExchangeRateDto> getAllExchangeRates(){
        return (List<ExchangeRateDto>) repository.findAll();
    }
}
