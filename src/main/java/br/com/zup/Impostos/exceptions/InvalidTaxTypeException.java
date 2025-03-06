package br.com.zup.Impostos.exceptions;

public class InvalidTaxTypeException extends RuntimeException {
    public InvalidTaxTypeException(String message) {
        super(message);
    }
}
