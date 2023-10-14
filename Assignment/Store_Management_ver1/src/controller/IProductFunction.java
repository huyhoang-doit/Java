/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import model.Product;
import model.WareHouse;

/**
 *
 * @author lvhho
 */
public interface IProductFunction {
    public void addProduct();
    public void updateProduct(HashMap<String, Product> listProductAppear);
    public void deleteProduct(HashMap<String, Product> listProductAppear);
    public void printListOfProduct();
    public void printListProductsExpired();
    public void printListCurrentSell();
    public void sortProductQuantity();
}
