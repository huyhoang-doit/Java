/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import model.ExportReceipt;
import model.ImportReceipt;
import model.Product;
import model.WareHouse;
import tool.CheckNumber;
import tool.CheckRule;
import tool.DateTime;
import view.Menu;

/**
 *
 * @author lvhho
 */
public class WareHouseFunciton {

    private Scanner sc = new Scanner(System.in);
    private Menu menu = new Menu();
    private String time = DateTime.getTime();;
    private ArrayList<ImportReceipt> listImportReceipts = new ArrayList<>();
    private ArrayList<ExportReceipt> listExpritionReceipts = new ArrayList<>();
    private ArrayList<WareHouse> listAllReceipts = new ArrayList<>();
    private int importReceiptCode = 1000000;
    private int exportReceiptCode = 1000000;

    //<1> Create an Import Receipt 
    public void CreateImportReceipt(HashMap<String, Product> listProduct, ArrayList<String> listCodeProduct) {
        int codeReceipt, quantity, choice;
        String time, productCode;
        Product product;
        boolean stop = false;
        HashMap<String, Product> listImport = new HashMap<>();
        ArrayList<Integer> listQuantity = new ArrayList<>();
        
        codeReceipt = importReceiptCode;
        importReceiptCode++;
        time = this.time;
        
        do {
            System.out.println("Enter product information on this import receipt:");
            System.out.print("Product code: ");
            productCode = CheckRule.productCodeValid();
            boolean check = false;
            for (String string : listCodeProduct) {
                if (productCode.equalsIgnoreCase(string)) {
                    product = listProduct.get(productCode);
                    listImport.put(productCode, product);
                    System.out.print("Quantity: ");
                    quantity = CheckNumber.getAnInteger();
                    listQuantity.add(quantity);
                    check = true;
                }
                if (check == false) {
                    System.out.println("Product does not exist!!!");
                }
                menu.printAddProductToReceiptChoices();
                choice = CheckNumber.getAnInteger("Your choice: ", "Illegal, try again", 1, 2);
                switch (choice) {
                    case 1: {
                        break;
                    }
                    case 2: {
                        stop = true;
                        break;
                    }
                }
            }
        } while (stop == false);
        
        // New receipt create
        ImportReceipt imReceipt = new ImportReceipt(codeReceipt, time, listImport, listQuantity);
        
        // Add new receipt to list
        listImportReceipts.add(imReceipt);
        listAllReceipts.add(imReceipt);
    }
    
    
    
    //<2> Create an Export Receipt 
    public void CreateExportReceipt(HashMap<String, Product> listProduct, ArrayList<String> listCodeProduct) {
        int codeReceipt, quantity, choice;
        String time, productCode;
        Product product;
        boolean stop = false;
        HashMap<String, Product> listExport = new HashMap<>();
        ArrayList<Integer> listQuantity = new ArrayList<>();
        
        codeReceipt = exportReceiptCode;
        exportReceiptCode++;
        time = this.time;
        
        do {
            System.out.println("Enter product information on this export receipt:");
            System.out.print("Product code: ");
            productCode = CheckRule.productCodeValid();
            boolean check = false;
            for (String string : listCodeProduct) {
                if (productCode.equalsIgnoreCase(string)) {
                    product = listProduct.get(productCode);
                    listExport.put(productCode, product);
                    System.out.print("Quantity: ");
                    quantity = CheckNumber.getAnInteger();
                    listQuantity.add(quantity);
                    check = true;
                }
                if (check == false) {
                    System.out.println("Product does not exist!!!");
                }
                menu.printAddProductToReceiptChoices();
                choice = CheckNumber.getAnInteger("Your choice: ", "Illegal, try again", 1, 2);
                switch (choice) {
                    case 1: {
                        break;
                    }
                    case 2: {
                        stop = true;
                        break;
                    }
                }
            }
        } while (stop == false);
        
        // New receipt create
        ExportReceipt exReceipt = new ExportReceipt(codeReceipt, time, listExport, listQuantity);
        
        // Add receipt to list
        listExpritionReceipts.add(exReceipt);
        listAllReceipts.add(exReceipt);
    }
}
