/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author lvhho
 */
public interface IProductFunction {
    public void addProduct();
    public void updateProduct();
    public void deleteProduct();
    public void printListOfProduct();
    public void printListProductsExpired();
    public void printListCurrentSell();
    public void sortProductQuantity();
}
