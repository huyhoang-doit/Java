/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import javafx.util.converter.LocalDateTimeStringConverter;
import model.Product;
import tool.CheckNumber;
import tool.CheckRule;
import tool.DateTime;
import view.Menu;

/**
 *
 * @author lvhho
 */
public class ProductFunction implements IProductFunction {

    public HashMap<String, Product> listProduct = new HashMap<>();
    public ArrayList<String> listCodeProduct = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private Menu menu = new Menu();

    @Override
    // <1> Add product to list
    public void addProduct() {

        String code, name, manDate, expDate, group = "";
        int quantity, groupChoice;

        System.out.println("Intput information of Product:");
        System.out.print("Product code <Code:*****>: ");
        code = CheckRule.findProductCodeNotExist(listCodeProduct);
        System.out.print("Product name: ");
        name = sc.nextLine().toUpperCase().trim();
        System.out.print("Input date of manufacture <dd-MM-yyyy>: ");
        manDate = DateTime.getManuFactureDate();
        System.out.print("Input date of exprition <dd-MM-yyyy>: ");
        expDate = DateTime.getExpritionDate();

        menu.printGroupProductOptions();
        groupChoice = CheckNumber.getAnInteger("Group: ", "Illegal, try again", 1, 2);
        switch (groupChoice) {
            case 1: {
                group = "Daily use";
                break;
            }
            case 2: {
                group = "Long shelf life";
                break;
            }
        }
        System.out.print("Quantity: ");
        quantity = CheckNumber.getAnInteger();
        // Product new
        Product product = new Product(code, name, manDate, expDate, quantity, group);

        // Add new product to the list
        listProduct.put(code, product);

        // Add new product code to the list of codeProduct
        listCodeProduct.add(code);
        System.out.println("Add Product successful >^<");
        System.out.println("--------------------------------------------------------------------------------");
    }

    @Override
    // <2> Update method
    public void updateProduct() {

        String code, name, manDate, expDate, group = "";
        int quantity, groupChoice;
        boolean checkCode = false;

        System.out.print("Enter the product code you want to update <Code:*****>: ");
        code = sc.nextLine().trim();
        for (String string : listCodeProduct) {
            if (code.equals(string)) {
                checkCode = true;
                break;
            }
        }
        if (checkCode == false) {
            System.out.println("Product does not exist!");
        } else {
            System.out.println("Product " + code + " is ready to be updated");
            // Remove code/ product from the list
            listProduct.remove(code);
            for (int i = 0; i < listCodeProduct.size(); i++) {
                if (listCodeProduct.get(i).equals(code)) {
                    listCodeProduct.remove(i);
                }
            }

            //Update new information 
            System.out.print("Product code <Code:*****>: ");
            code = sc.nextLine().trim();
            System.out.print("Product name: ");
            name = sc.nextLine().toUpperCase().trim();
            System.out.print("Input date of manufacture <dd-MM-yyyy>: ");
            manDate = DateTime.getManuFactureDate();
            System.out.print("Input date of exprition <dd-MM-yyyy>: ");
            expDate = DateTime.getExpritionDate();
            menu.printGroupProductOptions();
            groupChoice = CheckNumber.getAnInteger("Group: ", "Illegal, try again", 1, 2);
            switch (groupChoice) {
                case 1: {
                    group = "Daily use";
                    break;
                }
                case 2: {
                    group = "Long shelf life";
                    break;
                }
            }
            System.out.print("Quantity: ");
            quantity = CheckNumber.getAnInteger();

            //New information of product
            Product product = new Product(code, name, manDate, expDate, quantity, group);

            //Add new product to the list
            listProduct.put(code, product);

            //Add new product code to the list product code
            listCodeProduct.add(code);
            System.out.println("Product update successful >^<");
            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    @Override
    // <3> Delele product
    public void deleteProduct() {
        String code;
        int choice;

        System.out.print("Enter the product code you want to delete <Code:*****>: ");
        code = CheckRule.productCodeValid();

        menu.printConfirmDeleteMessage();
        choice = CheckNumber.getAnInteger("Your choice: ", "Fail, choice again: ", 1, 2);
        switch (choice) {
            case 1:
                // Remove code/ product from the list
                listProduct.remove(code);
                for (int i = 0; i < listCodeProduct.size(); i++) {
                    if (listCodeProduct.get(i).equals(code)) {
                        listCodeProduct.remove(i);
                    }
                }
                System.out.println("The product has been successfully deleted!");
                break;
            case 2:
                System.out.println("Confirm cancellation");
        }
    }

    @Override
    // <4> List of products
    public void printListOfProduct() {
        System.out.println(">>>> LIST OF PRODUCTS AT THE STORE");
        System.out.printf("|%-5s|%-25s|%-15s|%-10s|%-10s|%-10s|\n", "CODE", "PRODUCT-NAME", "GROUP", "MAN_DATE", "EXP_DATE", "QUANTITY");
        System.out.println("------------------------------------------------------------");
        for (Product object : listProduct.values()) {
            object.showLineInfoProduct();
        }
    }

    @Override
    // <5> Report: List product that have expired : hết hạn sử dụng
    public void printListProductsExpired() {
        // Create LocalDateTime now to compare with expritiondate of product
        LocalDateTime checker = LocalDateTime.now();

        // "string" HELP convert expritiondate to LocalDateTime
        LocalDateTimeStringConverter string = new LocalDateTimeStringConverter();

        System.out.println(">>>> LIST PRODUCTS EXPIRED");
        System.out.printf("|%-5s|%-25s|%-15s|%-10s|%-10s|%-10s|\n", "CODE", "PRODUCT-NAME", "GROUP", "MAN_DATE", "EXP_DATE", "QUANTITY");
        for (Product product : listProduct.values()) {
            String expritionDate = product.getExpritionDate();
            // Covert String to LocalDateTime
            LocalDateTime exDate = string.fromString(expritionDate);

            if (checker.isAfter(exDate)) {
                product.showLineInfoProduct();
            }
        }
    }

    @Override
    //<6> Print list of products store selling now ( quantity > 0 and not exprired
    public void printListCurrentSell() {
        // Create LocalDateTime now to compare with expritiondate of product
        LocalDateTime checker = LocalDateTime.now();

        // "string" HELP convert expritiondate to LocalDateTime
        LocalDateTimeStringConverter string = new LocalDateTimeStringConverter();

        System.out.println(">>>> LIST PRODUCTS STORE SELLING");
        System.out.printf("|%-5s|%-25s|%-15s|%-10s|%-10s|%-10s|\n", "CODE", "PRODUCT-NAME", "GROUP", "MAN_DATE", "EXP_DATE", "QUANTITY");
        for (Product product : listProduct.values()) {
            String expritionDate = product.getExpritionDate();
            // Covert String expritionDate to LocalDateTime
            LocalDateTime exDate = string.fromString(expritionDate);

            if (checker.isAfter(exDate) && product.getQuantity() > 0) {
                product.showLineInfoProduct();
            }
        }
    }

    @Override
    //<7> Sort product quantity
    //Purpopse: to print list products are running out of stock < sort acsending of quantity> 
    public void sortProductQuantity() {
        // Create list product for ready using Collection.sort
        ArrayList<Product> listSort = new ArrayList<>();
        for (Product product : listProduct.values()) {
            // Select product has quantity less equal than 3
            if(product.getQuantity() >= 3) {
                listSort.add(product);
            }
        }
        // Sort
        Collections.sort(listSort, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                if (p1.getQuantity() > p2.getQuantity()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        // Print list products after sort
        System.out.println(">>>> LIST PRODUCTS ARE RUNNING OUT OF STOCK");
        System.out.printf("|%-5s|%-25s|%-15s|%-10s|%-10s|%-10s|\n", "CODE", "PRODUCT-NAME", "GROUP", "MAN_DATE", "EXP_DATE", "QUANTITY");
        for (Product product : listSort) {
            product.showLineInfoProduct();
        }
    }

}
