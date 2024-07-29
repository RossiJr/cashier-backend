package org.rossijr.cashier.exceptions;

public class InventoriesLimitReachedException extends RuntimeException{
    public InventoriesLimitReachedException(String message) {
        super(message);
    }
}
