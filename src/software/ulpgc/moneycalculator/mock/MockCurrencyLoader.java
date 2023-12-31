package software.ulpgc.moneycalculator.mock;

import software.ulpgc.moneycalculator.CurrencyLoader;
import software.ulpgc.moneycalculator.model.Currency;

import java.util.List;

public class MockCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
        return List.of(
                new Currency("USD", 1.1019),
                new Currency("EUR", 1),
                new Currency("GBP", 0.8673)
        );
    }
}
