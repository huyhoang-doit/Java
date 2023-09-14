/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lvhho
 */
public class Product {
    private String productCode;
    private String productName;
    private String manufactureDate;
    private String expritionDate;
    private int quantity;
    private String group;
    
    // Constructor

    public Product() {
    }

    public Product(String productCode, String productName, String manufactureDate, String expritionDate, int quantity, String group) {
        this.productCode = productCode;
        this.productName = productName;
        this.manufactureDate = manufactureDate;
        this.expritionDate = expritionDate;
        this.quantity = quantity;
        this.group = group;
    }
    
    // Getter
    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public String getExpritionDate() {
        return expritionDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getGroup() {
        return group;
    }

    // Setter

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public void setExpritionDate(String expritionDate) {
        this.expritionDate = expritionDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return productCode + ", " + productName + ", " + manufactureDate + ", "+  expritionDate +", " + quantity + ", " + group;
    }

    
    
    
    
    public void showLineInfoProduct() {
        System.out.printf("|%-5s|%-25s|%-15s|%-10s|%-10s|%10d|\n", productCode, productName, group, manufactureDate, expritionDate, quantity);
    }
    
    
}
