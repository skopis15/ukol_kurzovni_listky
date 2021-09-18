package com.example.ukol_kurzovni_listky;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetDataFromAPI {
    @Autowired
    public ExchangeRateRepository exchangeRateRepository;


    public List<ExchangeRateDto> sendApiRequest() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v2/rates/exchangerates?web-api-key=c52a0682-4806-4903-828f-6cc66508329e";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        List<ExchangeRateDto> result = parse(response.getBody());
        exchangeRateRepository.saveAll(result);
        return result;
    }


    public List<ExchangeRateDto> parse(String responseBody) {
        try {
            JSONArray ExRatesFromAPI = new JSONArray(responseBody);
            List<ExchangeRateDto> result = new ArrayList<>();
            for (int i = 0; i < ExRatesFromAPI.length(); i++) {
                JSONObject ExchangeRate = ExRatesFromAPI.getJSONObject(i);
                String country = ExchangeRate.getString("country");
                String currency = ExchangeRate.getString("shortName");
                String currencyName = ExchangeRate.getString("name");
                Integer quantity = ExchangeRate.getInt("amount");
                Double buy = ExchangeRate.getDouble("currBuy");
                Double sell = ExchangeRate.getDouble("currSell");
                ExchangeRateDto repository = new ExchangeRateDto(country, currency, currencyName, quantity, buy, sell);
                result.add(repository);
            }
            return result;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
