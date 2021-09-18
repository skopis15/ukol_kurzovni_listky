package com.example.ukol_kurzovni_listky;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class GetDataFromAPI {
    @Autowired
    public static ExchangeRateRepository repo;



public static void sendApiRequest(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v2/rates/exchangerates?web-api-key=c52a0682-4806-4903-828f-6cc66508329e")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(GetDataFromAPI::parse)
                .join();

    }



    public static String parse(String responseBody) {
        try {
            JSONArray ExRatesFromAPI = new JSONArray(responseBody);
            for (int i = 0; i < ExRatesFromAPI.length(); i++) {
                JSONObject ExchangeRate = ExRatesFromAPI.getJSONObject(i);
                String country = ExchangeRate.getString("country");
                String currency = ExchangeRate.getString("shortName");
                String currencyName = ExchangeRate.getString("name");
                Integer quantity = ExchangeRate.getInt("amount");
                Double buy = ExchangeRate.getDouble("currBuy");
                Double sell = ExchangeRate.getDouble("currSell");
                ExchangeRateDto repository = new ExchangeRateDto(country,currency,currencyName,quantity,buy,sell);
                repo.save(repository);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
