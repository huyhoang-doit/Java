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
public class WareHouseFunciton implements IWareHouseFunction{

    private Scanner sc = new Scanner(System.in);
    private Menu menu = new Menu();
    private String time = DateTime.getTime();;
    private ArrayList<ImportReceipt> listImportReceipts = new ArrayList<>();
    private ArrayList<ExportReceipt> listExportReceipts = new ArrayList<>();
    public ArrayList<WareHouse> listAllReceipts = new ArrayList<>();
    private Integer importReceiptCode = 1000000;
    private Integer exportReceiptCode = 1000000;

    @Override
    //<1> Create an Import Receipt 
    public void CreateImportReceipt(HashMap<String, Product> listProduct, ArrayList<String> listCodeProduct) {
        int  quantity, choice;
        String codeReceipt,time, productCode, reverse;
        Product product;
        boolean stop = false;
        HashMap<String, Product> listImport = new HashMap<>();
        ArrayList<Integer> listQuantity = new ArrayList<>();
        
        reverse = importReceiptCode.toString();
        codeReceipt = "IM" + reverse;
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
    
    
       @Override
    //<2> Create an Export Receipt 
    public void CreateExportReceipt(HashMap<String, Product> listProduct, ArrayList<String> listCodeProduct) {
        int quantity, choice;
        String codeReceipt, time, productCode, reverse;
        Product product;
        boolean stop = false;
        HashMap<String, Product> listExport = new HashMap<>();
        ArrayList<Integer> listQuantity = new ArrayList<>();
        
        reverse = exportReceiptCode.toString();
        codeReceipt = "EX" + reverse;
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
        listExportReceipts.add(exReceipt);
        listAllReceipts.add(exReceipt);
    }
    
    @Override
    // <3> Report: show receipt containing products, productCode entered by user
    public void PrintInforReceiptByProductCode() {
        
    };
}
