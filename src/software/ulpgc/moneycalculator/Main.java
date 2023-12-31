package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.cli.CliCurrencyDialog;
import software.ulpgc.moneycalculator.cli.CliMoneyDialog;
import software.ulpgc.moneycalculator.control.ExchangeCommand;
import software.ulpgc.moneycalculator.fixerws.FixerCurrencyLoader;
import software.ulpgc.moneycalculator.fixerws.FixerExchangeRateLoader;
import software.ulpgc.moneycalculator.mock.*;
import software.ulpgc.moneycalculator.model.Currency;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FixerCurrencyLoader currencyLoader = new FixerCurrencyLoader();
        List<Currency> currencies = currencyLoader.load();
        MoneyDialog moneyDialog = new CliMoneyDialog().define(currencies);
        CurrencyDialog currencyDialog = new CliCurrencyDialog().define(currencies);
        MoneyDisplay moneyDisplay = new MockMoneyDisplay();
        ExchangeRateLoader exchangeRateLoader = new FixerExchangeRateLoader();
        ExchangeCommand exchangeCommand = new ExchangeCommand(moneyDialog, currencyDialog, exchangeRateLoader, moneyDisplay);
        exchangeCommand.execute();

    }
}
