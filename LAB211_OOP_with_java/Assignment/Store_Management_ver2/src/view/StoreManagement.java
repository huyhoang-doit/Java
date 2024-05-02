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
                    ManagementProduct(productFunction, wareHouseFunction);
                    break;
                }

                // <2> Management Warehouse
                case 2: {
                    ManagementWareHouse(productFunction, wareHouseFunction);
                    break;
                }

                // <3> Report
                case 3: {
                    ReportFunction(productFunction, wareHouseFunction);
                    break;
                }

                // <4> Save data to file
                case 4: {
                    DataController.saveListProductsToFile(productFunction.listProduct);
                    DataController.saveListReceiptsToFile(wareHouseFunction.listAllReceipts);
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

    //<1> Management Product 
    public static void ManagementProduct(ProductFunction productFunction, WareHouseFunciton wareHouseFunction) {
        int choiceCase1;
        Menu menu = new Menu();
        do {
            menu.printProductManagementMenu();

            choiceCase1 = CheckNumber.getAnInteger("Enter your choice: ", "Your selection is not valid, choose from 1 to 5!", 1, 5);
            switch (choiceCase1) {

                // <1.1> Add new product
                case 1: {
                    int addProductChoice;
                    productFunction.addProduct();
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

                // <1.2> Update new information for product in list
                case 2: {
                    productFunction.updateProduct(wareHouseFunction.listProductsInWareHouse);
                    int updateProductChoice;
                    do {
                        menu.printUpdateOptions();
                        updateProductChoice = CheckNumber.getAnInteger("Your choice: ", "Your selection is not valid, choose 1 or 2", 1, 2);
                        switch (updateProductChoice) {

                            // Agree update information
                            case 1: {
                                productFunction.updateProduct(wareHouseFunction.listProductsInWareHouse);
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

                // <1.3> Delete product from list products - Only delete product not appear in receipts
                case 3: {
                    productFunction.deleteProduct(wareHouseFunction.listProductAppear);
                    break;
                }

                // <1.4> Show list product to screen
                case 4: {
                    productFunction.printListOfProduct();
                    break;
                }
                //<1.5> Quit
                case 5: {
                    break;
                }
            }
        } while (choiceCase1 != 5);

        // End of method ManagementProduct
    }

    // <2> Management Warehouse
    public static void ManagementWareHouse(ProductFunction productFunction, WareHouseFunciton wareHouseFunction) {
        int choiceCase2;
        Menu menu = new Menu();

        do {
            menu.printWarehouseManagementMenu();
            choiceCase2 = CheckNumber.getAnInteger("Your choice: ", "Your selection is not valid, choose from 1 to 3 !", 1, 3);
            switch (choiceCase2) {

                // <2.1> Create an import receipt
                case 1: {
                    wareHouseFunction.CreateImportReceipt(productFunction.listProduct, productFunction.listCodeProduct);
                    break;
                }

                // <2.2> Create an export receipt
                case 2: {
                    wareHouseFunction.CreateExportReceipt(productFunction.listProduct, productFunction.listCodeProduct);
                    break;
                }
                // <2.3> Quit
                case 3: {
                    break;
                }
            }
        } while (choiceCase2 != 3);

    }

    // <3> Report
    public static void ReportFunction(ProductFunction productFunction, WareHouseFunciton wareHouseFunction) {
        int choiceCase3;
        Menu menu = new Menu();
        do {
            menu.printReportMenu();
            choiceCase3 = CheckNumber.getAnInteger("Enter your choice: ", "Your selection is not valid, choose from 1 to 5!", 1, 5);
            switch (choiceCase3) {
                // <3.1>  Report: list products have expired: danh sach san pham het han su dung
                case 1: {
                    wareHouseFunction.printListProductsExpired();
                    break;
                }
                // <3.2> Report: list products selling: san pham dang ban
                case 2: {
                    wareHouseFunction.printListCurrentSell();
                    break;
                }
                // <3.3> Report: list product on running out of stock (quantity < 3)
                case 3: {
                    productFunction.sortProductQuantity();
                    break;
                }
                // <3.4> Report: import/ export of product, input Code -> see receipts
                case 4: {
                    wareHouseFunction.printInforReceiptByProductCode(productFunction.listCodeProduct);
                    break;
                }
                // <3.5> Quit
                case 5: {
                    break;
                }
            }
        } while (choiceCase3 != 5);
    }
}
