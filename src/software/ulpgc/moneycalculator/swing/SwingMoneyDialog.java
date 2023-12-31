package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.CurrencyDialog;
import software.ulpgc.moneycalculator.MoneyDialog;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.Money;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {

    private JPanel panelUp = new JPanel();
    private JPanel panelDown = new JPanel();
    private final JTextField amount;
    private CurrencyDialog currencyDialog;

    public SwingMoneyDialog() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        panelUp.setLayout(new FlowLayout());
        panelDown.setLayout(new FlowLayout());
        panelDown.add(amount = createAmountField());
        add(panelUp);
        add(panelDown);
    }

    private JTextField createAmountField() {
        JTextField result = new JTextField();
        result.setColumns(8);
        return result;
    }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        add(createCurrencyDialog(currencies));
        return this;
    }

    private Component createCurrencyDialog(List<Currency> currencies) {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        dialog.define(currencies);
        this.currencyDialog = dialog;
        return dialog;
    }

    @Override
    public Money get() {
        return new Money(Long.parseLong(amount.getText()), currencyDialog.get());
    }
}
