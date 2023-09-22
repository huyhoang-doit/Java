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
import model.WarehousedProduct;
import tool.CheckNumber;
import tool.CheckRule;
import tool.CheckDateTime;
import view.Menu;

/**
 *
 * @author lvhho
 */
public class WareHouseFunciton implements IWareHouseFunction {

    private Scanner sc = new Scanner(System.in);
    private Menu menu = new Menu();
    private String time = CheckDateTime.getTime();

    private HashMap< String, Integer> listQuantityNotExpired = new HashMap<>();
    public HashMap<String, Product> listProductAppear = new HashMap<>();
    public ArrayList<WarehousedProduct> listProductsInWareHouse = new ArrayList<>();

    public ArrayList<WareHouse> listAllReceipts = new ArrayList<>();
    private ArrayList<ImportReceipt> listImportReceipts = new ArrayList<>();
    private ArrayList<ExportReceipt> listExportReceipts = new ArrayList<>();
    private String importReceiptCode = "0000000";
    private String exportReceiptCode = "0000000";

    @Override
    //<1> Create an Import Receipt 
    public void CreateImportReceipt(HashMap<String, Product> listProduct, ArrayList<String> listCodeProduct) {
        int quantity, choice;
        Integer reverseInt;
        boolean stop = false;
        Product product;
        ImportReceipt imReceipt = new ImportReceipt();

        ArrayList<Integer> listQuantity = new ArrayList<>();
        String codeReceipt, time, productCode, manDate, exDate;
        ArrayList<WarehousedProduct> listProductsImport = new ArrayList<>();

        if (listProduct.isEmpty() && listCodeProduct.isEmpty()) {
            System.err.println(">>>> LIST OF PRODUCTS AT THE STORE IS EMPTY!!!");
        } else {
            // Create ID receipt
            reverseInt = Integer.parseInt(importReceiptCode);
            String tmp = String.format("%07d", reverseInt);
            reverseInt++;
            codeReceipt = "IM" + tmp;
            importReceiptCode = reverseInt.toString();
            time = this.time;

            do {
                System.out.println("Enter product information on this import receipt:");
                System.out.print("Product code: ");
                productCode = CheckRule.productCodeValid();
                if (listCodeProduct.contains(productCode)) {
                    product = listProduct.get(productCode);

                    // Add product to list productAppear in receipt
                    listProductAppear.put(productCode, product);

                    //Input productSX info
                    WarehousedProduct productSX = new WarehousedProduct();
                    productSX.setProduct(product);

                    // Enter manDate and calculate exDate
                    System.out.print("Input date of manufacture <dd-MM-yyyy>: ");
                    manDate = CheckDateTime.getManuFactureDate();
                    productSX.setManufacturingDate(manDate);
                    exDate = CheckDateTime.getExpritionDate(manDate, product.getExpiry());
                    productSX.setExpirationDate(exDate);

                    // Enter quantity of this productSX
                    System.out.print("Quantity: ");
                    quantity = CheckNumber.getAnInteger();
                    productSX.setQuantitySX(quantity);

                    // Check expired by exDate
                    // Purpose: Only use quantity not expired in Export receipt 
                    boolean CheckExpired = CheckDateTime.isExpired(exDate);
                    if (!CheckExpired) {
                        if (listQuantityNotExpired.isEmpty() || !listQuantityNotExpired.containsKey(productCode)) {
                            listQuantityNotExpired.put(productCode, quantity);
                        } else {
                            int newQuantity = listQuantityNotExpired.get(productCode) + quantity;
                            listQuantityNotExpired.put(productCode, newQuantity);
                        }
                    }
                    // Update quantity of product in warehouse
                    product.setTotalQuantity(product.getTotalQuantity() + quantity);

                    // Add quantity to listQuantity of this receipt
                    listQuantity.add(quantity);

                    // Add productSX to listProduct of this receipt
                    listProductsImport.add(productSX);

                    // Add productSX to warehouse
                    listProductsInWareHouse.add(productSX);
                    System.out.println("This product has been added to the receipt");
                    menu.printAddProductToReceiptChoices();
                    choice = CheckNumber.getAnInteger("Your choice: ", "Illegal, try again", 1, 2);
                    switch (choice) {
                        case 1: {
                            break;
                        }
                        case 2: {
                            // New receipt create
                            imReceipt = new ImportReceipt(codeReceipt, time, listProductsImport, listQuantity);
                            imReceipt.printInforOfReceipt();
                            stop = true;
                            break;
                        }
                    }
                } else {
                    System.err.println("Product does not exist!!!");
                    menu.printAddProductToReceiptChoices();
                    choice = CheckNumber.getAnInteger("Your choice: ", "Illegal, try again", 1, 2);
                    switch (choice) {
                        case 1: {
                            break;
                        }
                        case 2: {
                            if (listProductsImport.isEmpty()) {
                                System.err.println("No products were entered on the receipt");
                                System.err.println("Cancel creating import receipt");
                            } else {
                                // New receipt create
                                imReceipt = new ImportReceipt(codeReceipt, time, listProductsImport, listQuantity);
                                imReceipt.printInforOfReceipt();
                            }
                            stop = true;
                            break;
                        }
                    }
                }
            } while (stop == false);

            // Add new receipt to list
            listImportReceipts.add(imReceipt);
            listAllReceipts.add(imReceipt);
        }
    }

    @Override
    //<2> Create an Export Receipt 
    public void CreateExportReceipt(HashMap<String, Product> listProduct, ArrayList<String> listCodeProduct) {

        int quantity, choice;
        Integer reverseInt;
        String codeReceipt, time, productCode;
        Product product;

        ExportReceipt exReceipt = new ExportReceipt();
        boolean stop = false;
        ArrayList<Integer> listQuantity = new ArrayList<>();
        ArrayList<WarehousedProduct> listProductsExport = new ArrayList<>();

        if (listProduct.isEmpty() || listCodeProduct.isEmpty() || listQuantityNotExpired.isEmpty()) {
            System.err.println(">>>> LIST OF PRODUCTS AT THE STORE IS EMPTY!!!");
        } else {
            // Create ID receipt
            reverseInt = Integer.parseInt(exportReceiptCode);
            String tmp = String.format("%07d", reverseInt);
            reverseInt++;
            codeReceipt = "EX" + tmp;
            exportReceiptCode = reverseInt.toString();
            time = this.time;

            System.out.println("Enter product information on this export receipt:");
            do {
                System.out.print("Product code: ");
                productCode = CheckRule.productCodeValid();
                // Find product by listProductCode and exist in listQuantityNotExpired
                if (listCodeProduct.contains(productCode) && listQuantityNotExpired.containsKey(productCode)) {
                    product = listProduct.get(productCode);
                    int check = 0;
                    // Find product sx in warehouse by product
                    for (WarehousedProduct warehousedProduct : listProductsInWareHouse) {
                        if (warehousedProduct.getProduct().equals(product)) {
                            WarehousedProduct productSX = new WarehousedProduct();
                            productSX = warehousedProduct;

                            //Only export product not expired
                            int quantityValid = listQuantityNotExpired.get(productCode);
                            System.out.print("Quantity ( < " + quantityValid + " ): ");
                            quantity = CheckRule.checkQuantityInStore(quantityValid);

                            //Update total quantity and not expired quantity of product
                            product.setTotalQuantity(product.getTotalQuantity() - quantity);
                            listQuantityNotExpired.put(productCode, quantityValid - quantity);

                            // Add product info and quantity to listProduct of this receipt
                            listQuantity.add(quantity);
                            listProductsExport.add(productSX);
                            System.out.println("This product has been added to the receipt");
                            check = 1;
                        }
                    }
                    if (check == 0) {
                        System.out.println("Quantity valid of " + product + " = 0");
                    }
                    menu.printAddProductToReceiptChoices();
                    choice = CheckNumber.getAnInteger("Your choice: ", "Illegal, try again", 1, 2);
                    switch (choice) {
                        case 1: {
                            break;
                        }
                        case 2: {
                            // New receipt create
                            exReceipt = new ExportReceipt(codeReceipt, time, listProductsExport, listQuantity);
                            exReceipt.printInforOfReceipt();
                            stop = true;
                            break;
                        }
                    }
                } else {
                    System.err.println("Product does not exist or expired!!!");
                    menu.printAddProductToReceiptChoices();
                    choice = CheckNumber.getAnInteger("Your choice: ", "Illegal, try again", 1, 2);
                    switch (choice) {
                        case 1: {
                            break;
                        }
                        case 2: {
                            if (listProductsExport.isEmpty()) {
                                System.err.println("No products were entered on the receipt");
                                System.err.println("Cancel creating import receipt");
                            } else {
                                // New receipt create
                                exReceipt = new ExportReceipt(codeReceipt, time, listProductsExport, listQuantity);
                                exReceipt.printInforOfReceipt();
                            }
                            stop = true;
                            break;
                        }
                    }
                }

            } while (stop == false);
            // Add receipt to list
            listExportReceipts.add(exReceipt);
            listAllReceipts.add(exReceipt);
        }
    }

    @Override
    // <3> Report: show receipt containing products, productCode entered by user
    public void printInforReceiptByProductCode(ArrayList<String> listCodeProduct) {
        String code;

        System.out.print("Import/export information of product code: <Code:*****>: ");
        code = CheckRule.productCodeValid();
        if (listCodeProduct.contains(code)) {
            
            for (WareHouse receipt : listAllReceipts) {
                for (WarehousedProduct warehousedProduct : receipt.listProduct) {
                    if (warehousedProduct.getProduct().getProductCode().equals(code)) {
                        if (receipt instanceof ImportReceipt) {
                            ((ImportReceipt) receipt).printInforOfReceipt();
                        } else {
                            ((ExportReceipt) receipt).printInforOfReceipt();
                        }
                    }
                }
            }
        } else {
            System.out.println("Product does not exist!");
        }
    }

    // ORTHER METHOD
    @Override
    // <4> Report: List product that have expired : hết hạn sử dụng
    public void printListProductsExpired() {
        ArrayList<WarehousedProduct> listExpired = new ArrayList<>();
        if (listProductsInWareHouse.isEmpty()) {
            System.err.println(">>>> LIST OF PRODUCTS AT THE STORE IS EMPTY!!!");
        } else {
            boolean check;

            // Add expired products to the listExpired
            for (WarehousedProduct warehousedProduct : listProductsInWareHouse) {
                String expritionDate = warehousedProduct.getExpirationDate();
                check = CheckDateTime.isExpired(expritionDate);
                if (check) {
                    listExpired.add(warehousedProduct);
                }
            }
            ////////
            // Print listExpired
            System.out.println("+--------------------------------------------------------------------------------+");
            System.out.println(">>>> LIST PRODUCTS EXPIRED");
            System.out.printf("|%-5s|%-25s|%-15s|%-10s|%-10s|%-10s|\n", "CODE", "PRODUCT-NAME", "GROUP", "MAN_DATE", "EXP_DATE", "QUANTITY");
            System.out.println("+--------------------------------------------------------------------------------+");
            if (listExpired.isEmpty()) {
                System.out.println("|                                     NULL                                       |");
                System.out.println("+--------------------------------------------------------------------------------+");
            } else {
                for (WarehousedProduct product : listExpired) {
                    product.showLineInfoProduct();
                }
                System.out.println("+--------------------------------------------------------------------------------+");
            }
        }
    }

    @Override
    //<5> Print list of products store selling now ( quantity > 0 and not exprired )
    public void printListCurrentSell() {
        ArrayList<WarehousedProduct> listCelling = new ArrayList<>();

        if (listProductsInWareHouse.isEmpty()) {
            System.err.println(">>>> LIST OF PRODUCTS AT THE STORE IS EMPTY!!!");
        } else {
            boolean check;
            // Create celling list
            for (WarehousedProduct product : listProductsInWareHouse) {
                String expritionDate = product.getExpirationDate();
                check = CheckDateTime.isExpired(expritionDate);
                if (!check && product.getQuantitySX() > 0) {
                    listCelling.add(product);
                }
            }

            // Print celling list
            System.out.println("+--------------------------------------------------------------------------------+");
            System.out.println(">>>> LIST PRODUCTS STORE SELLING");
            System.out.printf("|%-5s|%-25s|%-15s|%-10s|%-10s|%-10s|\n", "CODE", "PRODUCT-NAME", "GROUP", "MAN_DATE", "EXP_DATE", "QUANTITY");
            System.out.println("+--------------------------------------------------------------------------------+");
            if (listCelling.isEmpty()) {
                System.out.println("|                                     NULL                                       |");
                System.out.println("+--------------------------------------------------------------------------------+");
            } else {
                for (WarehousedProduct product : listCelling) {
                    product.showLineInfoProduct();
                }
                System.out.println("+--------------------------------------------------------------------------------+");
            }
        }
    }
}
