package com.example.ukol_kurzovni_listky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.ukol_kurzovni_listky.GetDataFromAPI.sendApiRequest;

@SpringBootApplication
public class UkolKurzovniListkyApplication {
    public static void main(String[] args) {
        SpringApplication.run(GetDataFromAPI.class, args);
        sendApiRequest();
    }
}