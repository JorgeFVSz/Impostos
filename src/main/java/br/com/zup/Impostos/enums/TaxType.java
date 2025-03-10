package br.com.zup.Impostos.enums;

public enum TaxType {
    ICMS {
        @Override
        public double calculateTax(double baseValue, double rate) {
            // Fórmula específica para ICMS
            return baseValue * (rate / 100);
        }
    },
    ISS {
        @Override
        public double calculateTax(double baseValue, double rate) {
            // Fórmula específica para ISS
            return baseValue * (rate / 100);
        }
    },
    IPI {
        @Override
        public double calculateTax(double baseValue, double rate) {
            // Fórmula específica para IPI
            return (baseValue * (rate / 100));
        }
    };

    public abstract double calculateTax(double baseValue, double rate);
}
