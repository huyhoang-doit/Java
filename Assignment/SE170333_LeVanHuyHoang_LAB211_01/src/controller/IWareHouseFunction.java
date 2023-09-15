/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import model.Product;

/**
 *
 * @author lvhho
 */
public interface IWareHouseFunction {
     public void CreateImportReceipt(HashMap<String, Product> listProduct, ArrayList<String> listCodeProduct);
     public void CreateExportReceipt(HashMap<String, Product> listProduct, ArrayList<String> listCodeProduct);
     public void PrintInforReceiptByProductCode();
}
