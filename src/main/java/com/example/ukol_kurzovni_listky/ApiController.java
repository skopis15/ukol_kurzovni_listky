package com.example.ukol_kurzovni_listky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rates")
public class ApiController {

    @Autowired
    private ExchangeRateRepository repository;

    @Autowired
    private GetDataFromAPI getDataFromAPI;


    @CrossOrigin(origins = "*")
    @GetMapping
    public List<ExchangeRateDto> getAllExchangeRates(@RequestParam(value = "usedb", defaultValue = "true") boolean usedb) {
        if(usedb){
            List<ExchangeRateDto> exchangeRateDtoList = (List<ExchangeRateDto>) repository.findAll();
            if (exchangeRateDtoList.isEmpty()){
                return getDataFromAPI.sendApiRequest();
            }else {
                return exchangeRateDtoList;
            }
        }else{
           return getDataFromAPI.sendApiRequest();

        }
    }

}
