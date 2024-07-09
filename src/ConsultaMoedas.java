import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ConsultaMoedas {

    public MoedasAPI consultarMoeda(String codigoMoeda) throws RuntimeException {

        URI enderecoBusca = URI.create("https://v6.exchangerate-api.com/v6/917fe8b78d177ce86efe4308/latest/" + codigoMoeda);


        HttpRequest request = HttpRequest.newBuilder()
                .uri(enderecoBusca)
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return analiseTaxaMoeda(response.body());
        } catch (Exception e) {
            throw new RuntimeException("Moeda inválida!");
        }
    }

    private MoedasAPI analiseTaxaMoeda(String json) {

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        String baseCode = jsonObject.get("base_code").getAsString();
        Map<String, Double> filtroTaxas = filterRates(jsonObject.getAsJsonObject("conversion_rates"));
        return new MoedasAPI(baseCode, filtroTaxas);
    }


    private Map<String, Double> filterRates(JsonObject conversionRatesObject) {
        Map<String, Double> taxasFiltradas = new HashMap<>();
        for (String moeda : new String[]{"USD", "EUR", "GBP", "BRL", "JPY", "CHF"}) {
            if (conversionRatesObject.has(moeda)) {
                taxasFiltradas.put(moeda, conversionRatesObject.get(moeda).getAsDouble());
            }
        }
        return taxasFiltradas;


    }

}
