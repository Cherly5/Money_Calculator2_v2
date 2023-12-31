package software.ulpgc.moneycalculator.fixerws;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.CurrencyLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public class FixerCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
        try {
            return toList(loadJson());
        } catch (IOException e) {
            return emptyList();
        }
    }

    private List<Currency> toList(String json) {
        List<Currency> list = new ArrayList<>();
        Map<String, JsonElement> symbols = new Gson().fromJson(json, JsonObject.class).get("conversion_rates").getAsJsonObject().asMap();
        for (String symbol : symbols.keySet())
            list.add(new Currency(symbol, symbols.get(symbol).getAsDouble()));
        return list;
    }

    private String loadJson() throws IOException {
        URL url = new URL("https://v6.exchangerate-api.com/v6/" + FixerAPI.key + "/latest/EUR");
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }
}
