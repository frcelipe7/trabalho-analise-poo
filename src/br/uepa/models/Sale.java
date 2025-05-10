package br.uepa.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sale {
    private final int id;
    private ArrayList<SaleItem> products;
    private int salesPersonId;
    private LocalDateTime timestamp;

    public Sale(int id, ArrayList<SaleItem> products, int salesPersonId) {
        this.id = id;
        this.products = products;
        this.salesPersonId = salesPersonId;
        this.timestamp = LocalDateTime.now();
    }

    public BigDecimal calculateTotal(ArrayList<SaleItem> saleItems) {
        BigDecimal total = BigDecimal.ZERO;

        for (SaleItem item : saleItems) {
            total = total.add(item.getSubTotal());
        }
        return total;
    }
}
