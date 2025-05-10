package br.uepa.models;

import java.math.BigDecimal;

public class Product {
    private final int id;
    private String name;
    private String brand;
    private Category category;
    private String description;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private int quantityInStock;
    private boolean isAvailable;

    public Product(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void applyDiscount(int percentage) {
        BigDecimal discountPercent = BigDecimal.valueOf(percentage).divide(BigDecimal.valueOf(100));
        this.discountPrice = price.multiply(discountPercent);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
