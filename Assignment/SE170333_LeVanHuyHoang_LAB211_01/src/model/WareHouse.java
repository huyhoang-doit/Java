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
    protected String code;
    protected String time;
    protected HashMap< String, Product> listProduct;
    protected ArrayList<Integer> listQuantity;
    
    // Constructor
    public WareHouse() {
    }

    public WareHouse(String code, String time, HashMap<String, Product> listProduct, ArrayList<Integer> listQuantity) {
        this.code = code;
        this.time = time;
        this.listProduct = listProduct;
        this.listQuantity = listQuantity;
    }
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

    // Getter
    public void setListQuantity(ArrayList<Integer> listQuantity) {
        this.listQuantity = listQuantity;
    }

    @Override
    public String toString() {
        return time + "," + code;
    }

    
    
}
