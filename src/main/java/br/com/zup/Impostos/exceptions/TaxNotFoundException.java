package br.com.zup.Impostos.exceptions;

public class TaxNotFoundException extends RuntimeException {
    public TaxNotFoundException(String message) {
        super(message);
    }
}
