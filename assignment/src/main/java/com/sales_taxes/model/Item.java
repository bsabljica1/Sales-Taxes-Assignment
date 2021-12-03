package com.sales_taxes.model;

public class Item {
    
    private String name;
    private Double price;
    private int quantity;
    private Boolean imported;
    private int type;
    private Double afterTax;

    public Item(String name, Double price, int quantity, Boolean imported, int type) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imported = imported;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Boolean getImported() {
        return this.imported;
    }

    public int getType() {
        return this.type;
    }

    public Double getAfterTax() {
        return this.afterTax;
    }

    public void setAfterTax(Double afterTax) {
        this.afterTax = afterTax;        
    }

    public double calculateItemTax() {

        double importTax = 0;
        double otherTax = 0;

        if (imported) {
            importTax = price * quantity * 0.05;
        }

        if (type == 4) {
            otherTax = price * quantity * 0.1;
        }


        afterTax = importTax + otherTax;

        afterTax = Math.round(afterTax*100.0)/100.0;

        return afterTax;

    }

    public double getTaxedPrice() {

        double taxedPrice = price + calculateItemTax();
        taxedPrice = Math.round(taxedPrice*100.0)/100.0;
        return taxedPrice;

    }


    
}
