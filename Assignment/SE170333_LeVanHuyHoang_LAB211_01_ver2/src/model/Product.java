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
    protected String productCode;
    protected String productName;
    protected String group;
    protected int expiry; // days
    protected int totalQuantity;
    
    
    // Constructor

    public Product() {
    }

    public Product(String productCode, String productName, String group, int expiry, int totalQuantity) {
        this.productCode = productCode;
        this.productName = productName;
        this.group = group;
        this.expiry = expiry;
        this.totalQuantity = totalQuantity;
    }
    
    

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getExpiry() {
        return expiry;
    }

    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    // Getter and setter
    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    // show information
    public void showLineInfoProduct() {
        System.out.printf("|%-5s|%-25s|%-15s|%10d|\n", productCode, productName, group, totalQuantity);
    }

    @Override
    public String toString() {
        return productCode + ", " + productName + ", " + group + ", " + expiry;
    }
    
    
}
