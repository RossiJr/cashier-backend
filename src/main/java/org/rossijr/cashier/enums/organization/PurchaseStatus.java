package org.rossijr.cashier.enums.organization;

public enum PurchaseStatus {
    OPEN (0, "When the purchase is opened but not paid yet"),
    PAID (1, "When the purchase is paid but not shipped yet"),
    PENDING (2, "When the purchase is paid and shipped but not received yet"),
    RECEIVED (3, "When the purchase is received"),
    PARTIALLY_RECEIVED (4, "When the purchase is partially received"),
    CONCLUDED (5, "When the purchase is concluded"),
    CANCELED (6, "When the purchase is canceled");

    private final int code;
    private final String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    PurchaseStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
