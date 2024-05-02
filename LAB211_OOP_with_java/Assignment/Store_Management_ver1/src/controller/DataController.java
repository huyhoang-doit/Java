/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import model.Product;
import model.WareHouse;

/**
 *
 * @author lvhho
 */
public class DataController {

    //<1> Save list product to file "product.dat"
    public static void saveListProductsToFile(HashMap<String, Product> listProduct) {
        if (listProduct.isEmpty()) {
            System.out.println(">>>> LIST OF PRODUCTS AT THE STORE IS EMPTY!!!");
        } else {
            try {
                FileWriter fw = new FileWriter("src\\output\\product.dat");
                BufferedWriter bf = new BufferedWriter(fw);
                for (Product product : listProduct.values()) {
                    bf.write(product.toString());
                    bf.newLine();
                }
                bf.close();
                fw.close();
            } catch (Exception e) {
            }
            System.out.println("Product list saved successfully");
        }
    }

    //<2> Save list receipt to file "wareHouse.dat"
    public static void saveListReceiptsToFile(ArrayList<WareHouse> listReceipt) {
        if (listReceipt.isEmpty()) {
            System.out.println(">>>> LIST OF RECEIPTS AT THE STORE IS EMPTY!!");
        } else {
            try {
                FileWriter fw = new FileWriter("src\\output\\wareHouse.dat");
                BufferedWriter bf = new BufferedWriter(fw);
                for (WareHouse receipt : listReceipt) {
                    bf.write(receipt.toString());
                    bf.newLine();
                }
                bf.close();
                fw.close();
            } catch (Exception e) {
            }
            System.out.println("Receipt list saved successfully");
        }
    }
}
