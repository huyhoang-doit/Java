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
    public ArrayList<WarehousedProduct>  listProduct;
    protected ArrayList<Integer> listQuantity;
    
    // Constructor
    public WareHouse() {
    }

    public WareHouse(String code, String time, ArrayList<WarehousedProduct> listProduct, ArrayList<Integer> listQuantity) {
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

    @Override
    public String toString() {
        return time + "," + code;
    }

    
    
}
