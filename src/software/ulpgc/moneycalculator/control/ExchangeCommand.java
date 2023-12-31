package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.CurrencyDialog;
import software.ulpgc.moneycalculator.ExchangeRateLoader;
import software.ulpgc.moneycalculator.MoneyDialog;
import software.ulpgc.moneycalculator.MoneyDisplay;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;
import software.ulpgc.moneycalculator.model.Money;

import java.text.DecimalFormat;

public class ExchangeCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final ExchangeRateLoader exchangeRateLoader;
    private final MoneyDisplay moneyDisplay;

    public ExchangeCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, ExchangeRateLoader exchangeRateLoader, MoneyDisplay moneyDisplay) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.exchangeRateLoader = exchangeRateLoader;
        this.moneyDisplay = moneyDisplay;
    }

    @Override
    public void execute() {
        Money money = moneyDialog.get();
        Currency target = currencyDialog.get();
        ExchangeRate exchangeRate = exchangeRateLoader.load(money.currency(), target);
        Money result = new Money(round(money.amount() * exchangeRate.rate()), target);
        moneyDisplay.show(result);
    }

    private double round(double value) {
        double factor = Math.pow(10, 2);
        return Math.round(value * factor) / factor;
    }
}
