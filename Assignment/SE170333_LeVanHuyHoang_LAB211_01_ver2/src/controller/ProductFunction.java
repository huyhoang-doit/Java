/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
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
public class ProductFunction implements IProductFunction {

    public HashMap<String, Product> listProduct = new HashMap<>();
    public ArrayList<Product> arrayListProduct = new ArrayList<>();
    public ArrayList<String> listCodeProduct = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private Menu menu = new Menu();

    @Override
    // <1> Add product to list (Done)
    public void addProduct() {

        String code, name, group = "";
        int  groupChoice, expiry = 0;

        System.out.println("Intput information of Product:");
        System.out.print("Product code <Code:*****>: ");
        code = CheckRule.findProductCodeNotExist(listCodeProduct);
        System.out.print("Product name: ");
        name = sc.nextLine().toUpperCase().trim();
        menu.printGroupProductOptions();
        groupChoice = CheckNumber.getAnInteger("Group: ", "Illegal, try again", 1, 2);
        switch (groupChoice) {
            case 1: {
                group = "Daily use";
                expiry = 3;
                break;
            }
            case 2: {
                group = "Long shelf life";
                System.out.print("Expiry (How many days? ): ");
                expiry = CheckNumber.getAnInteger();
                break;
            }
        }

        // Product new
        Product product = new Product(code, name, group, expiry, 0);

        // Add new product to the list
        listProduct.put(code, product);

        // Add new product code to the list of codeProduct
        listCodeProduct.add(code);
        System.out.println("Add Product successful >^<");
        System.out.println("--------------------------------------------------------------");
    }

    @Override
    // <2> Update method (50%)
    public void updateProduct() {

        String code, name, group = "";
        int expiry = 0;

        if (listProduct.isEmpty()) {
            System.err.println(">>>> LIST OF PRODUCTS AT THE STORE IS EMPTY!!!");
        } else {
            System.out.print("Enter the product code you want to update <Code:*****>: ");
            code = CheckRule.productCodeValid();
            if (listCodeProduct.contains(code)) {
                System.out.println("Product " + code + " is ready to be updated");

                // Find product have this 'code'
                Product productUpdate = listProduct.get(code);

                // Remove this code out of list code product
                listCodeProduct.remove(code);

                //Update new information < code - name >
                System.out.print("Product code <Code:*****>: ");
                code = CheckRule.findProductCodeNotExist(listCodeProduct);
                productUpdate.setProductCode(code);
                System.out.print("Product name: ");
                name = sc.nextLine().toUpperCase().trim();
                productUpdate.setProductName(name);

                //Add new product code to the list product code
                listCodeProduct.add(code);
                System.out.println("Product update successful >^<");
                System.out.println("|-----------------------------------------------------------|");
            } else {
                System.err.println("Product does not exist!");
                System.out.println("|-----------------------------------------------------------|");
            }

        }
    }

    @Override
    // <3> Delele product !!!!
    public void deleteProduct(HashMap<String, Product> listProductAppear) {
        String code;
        int choice;

        if (listProduct.isEmpty()) {
            System.err.println(">>>> LIST OF PRODUCTS AT THE STORE IS EMPTY!!!");
        } else {
            // Only delete product not exist in listProdcutsAppear
            System.out.print("Enter the product code you want to delete <Code:*****>: ");
            code = CheckRule.findProductCodeExist(listCodeProduct);

            if (!listProductAppear.containsKey(code)) {
                menu.printConfirmDeleteMessage();
                choice = CheckNumber.getAnInteger("Your choice: ", "Fail, choice again: ", 1, 2);
                switch (choice) {
                    case 1:
                        // Remove code/ product from the list
                        listProduct.remove(code);
                        listCodeProduct.remove(code);
                        System.out.println("The product has been successfully deleted!");
                        break;
                    case 2:
                        System.out.println("Confirm cancellation");
                        break;
                }
            } else {
                System.err.println("This product CANNOT be DELETED because the product information ");
                System.err.println("is already EXISTS in the import-export receipt!");
            }

        }
    }

    @Override
    // <4> List of products ( Done )
    public void printListOfProduct() {
        if (listProduct.isEmpty()) {
            System.err.println(">>>> LIST OF PRODUCTS AT THE STORE IS EMPTY!!!");
        } else {
            System.out.println(">>>> LIST OF PRODUCTS AT THE STORE");
            System.out.println("+----------------------------------------------------------+");

            System.out.printf("|%-5s|%-25s|%-15s|%-10s|\n", "CODE", "PRODUCT-NAME", "GROUP", "QUANTITY");
            System.out.println("|----------------------------------------------------------|");
            for (Product object : listProduct.values()) {
                object.showLineInfoProduct();
            }
            System.out.println("+----------------------------------------------------------+");

        }
    }

    @Override
    //<5> Sort product quantity
    //Purpopse: to print list products are running out of stock < sort acsending of quantity> 
    public void sortProductQuantity() {
        if (listProduct.isEmpty()) {
            System.err.println(">>>> LIST OF PRODUCTS AT THE STORE IS EMPTY!!!");
        } else {
            // Create list product for ready using Collection.sort
            ArrayList<Product> listSort = new ArrayList<>();
            for (Product product : listProduct.values()) {
                // Select product has quantity less equal than 3
                if (product.getTotalQuantity() < 3) {
                    listSort.add(product);
                }
            }
            // Sort
            Collections.sort(listSort, new Comparator<Product>() {
                @Override
                public int compare(Product p1, Product p2) {
                    if (p1.getTotalQuantity() > p2.getTotalQuantity()) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });
            // Print list products after sort
            System.out.println(">>>> LIST PRODUCTS ARE RUNNING OUT OF STOCK");
            System.out.println("+----------------------------------------------------------+");
            System.out.printf("|%-5s|%-25s|%-15s|%-10s|\n", "CODE", "PRODUCT-NAME", "GROUP", "QUANTITY");
            System.out.println("|----------------------------------------------------------|");;
            if (listSort.isEmpty()) {
                System.out.println("|                           NULL                           |");
                System.out.println("+----------------------------------------------------------+");
            } else {
                for (Product product : listSort) {
                    product.showLineInfoProduct();
                }
                System.out.println("+----------------------------------------------------------+");
            }
        }
    }

   
}
