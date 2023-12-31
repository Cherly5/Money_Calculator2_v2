package software.ulpgc.moneycalculator.model;

public record Currency(String code, double value) {
    @Override
    public String toString() {
        return code;
    }
}
