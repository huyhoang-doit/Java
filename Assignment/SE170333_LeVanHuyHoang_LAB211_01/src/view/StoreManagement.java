/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.DataController;
import controller.ProductFunction;
import controller.WareHouseFunciton;
import java.util.Scanner;
import tool.CheckNumber;

/**
 *
 * @author lvhho
 */
public class StoreManagement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        Menu menu = new Menu();
        ProductFunction productFunction = new ProductFunction();
        WareHouseFunciton wareHouseFunction = new WareHouseFunciton();
        
        do {
            menu.printMainMenu();
            choice = CheckNumber.getAnInteger("Enter your choice: ", "Your selection is not valid, choose from 1 to 5!", 1, 5);
            switch (choice) {

                // <1> Management Product 
                case 1: {
                    int choiceCase1;
                    menu.printProductManagementMenu();
                    choiceCase1 = CheckNumber.getAnInteger("Enter your choice: ", "Your selection is not valid, choose from 1 to 4!", 1, 4);
                    switch (choiceCase1) {

                        // Add new product
                        case 1: {
                            int addProductChoice;
                            do {
                                menu.printAddNewProductOptions();
                                addProductChoice = CheckNumber.getAnInteger("Your choice: ", "Your selection is not valid, choose 1 or 2", 1, 2);
                                switch (addProductChoice) {

                                    // Agree to add new product
                                    case 1: {
                                        productFunction.addProduct();
                                        break;
                                    }

                                    // Not agree to add new product
                                    case 2: {
                                        break;
                                    }
                                }
                            } while (addProductChoice != 2);
                            break;
                        }

                        // Update new information for product in list
                        case 2: {
                            int updateProductChoice;
                            do {
                                menu.printUpdateOptions();
                                updateProductChoice = CheckNumber.getAnInteger("Your choice: ", "Your selection is not valid, choose 1 or 2", 1, 2);
                                switch (updateProductChoice) {

                                    // Agree update information
                                    case 1: {
                                        productFunction.updateProduct();
                                        break;
                                    }

                                    // Not agree update information
                                    case 2: {
                                        break;
                                    }
                                }
                            } while (updateProductChoice != 2);
                            break;
                        }

                        // Delete product from list products
                        case 3: {
                            break;
                        }

                        // Show list product to screen
                        case 4: {
                            productFunction.printListOfProduct();
                            break;
                        }
                    }
                    break;
                }

                // <2> Management Warehouse
                case 2: {
                    int choiceCase2;
                    menu.printWarehouseManagementMenu();
                    choiceCase2 = CheckNumber.getAnInteger("Your choice: ", "Your selection is not valid, choose 1 or 2 !", 1, 2);
                    switch (choiceCase2) {

                        //  Create an import receipt
                        case 1: {
                            wareHouseFunction.CreateImportReceipt(productFunction.listProduct, productFunction.listCodeProduct);
                            break;
                        }

                        // Create an export receipt
                        case 2: {
                            wareHouseFunction.CreateExportReceipt(productFunction.listProduct, productFunction.listCodeProduct);
                            break;
                        }
                    }
                    break;
                }

                // <3> Report
                case 3: {
                    int choiceCase3;
                    menu.printReportMenu();
                    choiceCase3 = CheckNumber.getAnInteger("Enter your choice: ", "Your selection is not valid, choose from 1 to 4!", 1, 4);
                    switch(choiceCase3) {
                        // Report: list products have expired: danh sach san pham het hang su dung
                        case 1: {
                            productFunction.printListProductsExpired();
                            break;
                        }
                        // Report: list products selling: san pham dang ban
                        case 2: {
                            productFunction.printListCurrentSell();
                            break;
                        }
                        // Report: list product on running out of stock
                        case 3: {
                            productFunction.sortProductQuantity();
                            break;
                        }
                        // Report: import/ export of product
                        case 4: {
                            break;
                        }
                    }
                    break;
                }

                // <4> Save data to file
                case 4: {
                    DataController.saveListProductsToFile(productFunction.listProduct);
                    DataController.saveListReceiptsToFile(wareHouseFunction.listAllReceipts);
                    System.out.println("Store data to files successfully!");
                    break;
                }

                // <5> Exit system
                case 5: {
                    System.out.println("Thank you for using the system, goodbye >v<");
                    break;
                }
            }

        } while (choice != 5);
    }
}
