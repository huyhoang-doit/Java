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
public class WarehousedProduct {
    private Product product ;
    private String manufacturingDate;
    private String expirationDate;
    private int quantitySX;

    //Constructor
    public WarehousedProduct() {
        
    }

    public WarehousedProduct(Product product, String manufacturingDate, String expirationDate, int quantitySX) {
        this.product = product;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.quantitySX = quantitySX;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getQuantitySX() {
        return quantitySX;
    }

    //Getter and setter
    public void setQuantitySX(int quantitySX) {
        this.quantitySX = quantitySX;
    }

    // toString
    @Override
    public String toString() {
        return product.getProductCode() + ", " + product.getProductName() + ", " + product.getGroup() + ", " + manufacturingDate + ", " + expirationDate + ", " + quantitySX;
    }
    
    public void showLineInfoProduct() {
        System.out.printf("|%-5s|%-25s|%-15s|%-10s|%-10s|%10d|\n", product.getProductCode(), product.getProductName(), product.getGroup(), manufacturingDate, expirationDate, quantitySX);
    }
}
