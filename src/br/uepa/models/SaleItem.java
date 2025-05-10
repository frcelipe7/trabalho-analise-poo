package br.uepa.models;

import java.math.BigDecimal;

public class SaleItem {
    private final int id;
    private final int saleId;
    private int productId;
    private int quantity;
    private final BigDecimal pricePerUnity;

    public SaleItem(int id, int saleId, int productId, int quantity, BigDecimal pricePerUnity) {
        this.id = id;
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
        this.pricePerUnity = pricePerUnity;
    }

    public BigDecimal getSubTotal() {
        return pricePerUnity.multiply(BigDecimal.valueOf(this.quantity));
    }
}