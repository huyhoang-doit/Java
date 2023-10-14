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
public class ImportReceipt extends WareHouse {
    
    // Constructor
    public ImportReceipt() {
    }

    public ImportReceipt(String code, String time, HashMap<String, Product> listProduct, ArrayList<Integer> listQuantity) {
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

    public HashMap<String, Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(HashMap<String, Product> listProduct) {
        this.listProduct = listProduct;
    }

    public ArrayList<Integer> getListQuantity() {
        return listQuantity;
    }

    public void setListQuantity(ArrayList<Integer> listQuantity) {
        this.listQuantity = listQuantity;
    }

    @Override
    public String toString() {
        return code + "," + time;
    }
    
    
    

    // Method print information of receipt
    public void printInforOfReceipt() {
        int count = 0;
        System.out.println("+-------------------------------------------------+");
        System.out.println("|>>> IMPORT RECEIPT");
        System.out.println("|Imp: " + code + "          Time: " + time + " |");
        System.out.printf("|%-4s|%-7s|%-25s|%-10s|\n", "STT", "ID_CODE", "PRODUCT NAME", "QUANTITY");
        for (String IDCode : listProduct.keySet()) {
            System.out.printf("|%-4d|%-7s|%-25s|%-10d|\n", count + 1, IDCode, listProduct.get(IDCode).getProductName(), listQuantity.get(count));
        }
        System.out.println("+-------------------------------------------------+");
    }

}
