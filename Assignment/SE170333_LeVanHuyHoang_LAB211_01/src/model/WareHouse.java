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
public class WareHouse {
    protected String time;
    protected HashMap< String, Product> listProduct;
    protected ArrayList<Integer> listQuantity;
    
    // Constructor
    public WareHouse() {
    }

    public WareHouse(String time, HashMap<String, Product> listProduct, ArrayList<Integer> listQuantity) {
        this.time = time;
        this.listProduct = listProduct;
        this.listQuantity = listQuantity;
    }
    
    // Getter 
    public String getTime() {
        return time;
    }

    public HashMap< String, Product> getListProduct() {
        return listProduct;
    }

    public ArrayList<Integer> getListQuantity() {
        return listQuantity;
    }
    
    // Setter 
    public void setTime(String time) {
        this.time = time;
    }

    public void setListProduct(HashMap<String, Product> listProduct) {
        this.listProduct = listProduct;
    }

    public void setListQuantity(ArrayList<Integer> listQuantity) {
        this.listQuantity = listQuantity;
    }
    
    
    // toString
    @Override
    public String toString() {
        return "WareHouse{" + "time=" + time + ", listProduct=" + listProduct + ", listQuantity=" + listQuantity + '}';
    }
    
}
