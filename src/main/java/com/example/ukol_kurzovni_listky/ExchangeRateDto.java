package com.example.ukol_kurzovni_listky;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

@Data
@Entity
public class ExchangeRateDto {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private String country;
    private String currency;
    private String currencyFullName;
    private Integer quantity;
    private Double buy;
    private Double sell;
    @javax.persistence.Id
    private Integer id;


    public ExchangeRateDto(String country, String currency, String currencyFullName, Integer quantity, Double buy, Double sell){
        this.country = country;
        this.currency = currency;
        this.currencyFullName = currencyFullName;
        this.quantity = quantity;
        this.buy = buy;
        this.sell = sell;
    }

    public ExchangeRateDto() {

    }


    public String getCountry(){return country;}
    public String getCurrency(){return currency;}
    public String getCurrencyFullName(){return currencyFullName;}
    public Integer getQuantity(){return quantity;}
    public Double getBuy(){return buy;}
    public Double getSell(){return sell;}

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}


