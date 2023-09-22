/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author lvhho
 */
public class ExportReceipt extends WareHouse {

    public ExportReceipt() {
    }

    // Constructor
    public ExportReceipt(String code, String time, ArrayList<WarehousedProduct> listProduct, ArrayList<Integer> listQuantity) {
        super(code, time, listProduct, listQuantity);
    }

    // Getter and Setter
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<WarehousedProduct> getListProduct() {
        return listProduct;
    }

    public void setListProduct(ArrayList<WarehousedProduct> listProduct) {
        this.listProduct = listProduct;
    }

    public ArrayList<Integer> getListQuantity() {
        return listQuantity;
    }

    public void setListQuantity(ArrayList<Integer> listQuantity) {
        this.listQuantity = listQuantity;
    }

    //toString
    @Override
    public String toString() {
        return code + "," + time;
    }

    // Method print information of receipt
    public void printInforOfReceipt() {
        int count = 0;
        int total = 0;
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.printf("|>>> EXPORT RECEIPT %-68s|\n", " ");
        System.out.println("|Exp: " + code + "                                                Time: " + time + " |");
        System.out.printf("|%-4s|%-7s|%-25s|%-15s|%-10s|%-10s|%-10s|\n", "STT", "ID_CODE", "PRODUCT NAME","GROUP", "MAN_DATE", "EXP_DATE", "QUANTITY");
        System.out.println("+---------------------------------------------------------------------------------------+");
        for (WarehousedProduct product : listProduct) {
            Product infoSP = product.getProduct();
            System.out.printf("|%4d|%-7s|%-25s|%-15s|%-10s|%-10s|%10d|\n",
                    count + 1, infoSP.getProductCode(), infoSP.getProductName(), infoSP.getGroup(),
                    product.getManufacturingDate(), product.getExpirationDate(),listQuantity.get(count));
            total += listQuantity.get(count);
            count++;
        }
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.printf("|=> TOTAL QUANTITY: %68d|\n", total);
        System.out.println("+---------------------------------------------------------------------------------------+");
    }
}
