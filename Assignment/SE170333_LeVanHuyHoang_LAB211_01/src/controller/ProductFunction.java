/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
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

    private HashMap<String, Product> listProduct = new HashMap<>();
    private ArrayList<String> listCodeProduct = new ArrayList<>();
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
        System.out.printf("|%-5s|%-25s|%-15s|%-10s|%-10s|-10s|\n", "CODE", "PRODUCT-NAME", "GROUP", "MAN_DATE", "EXP_DATE", "QUANTITY");
        System.out.println("------------------------------------------------------------");
        for (Product object : listProduct.values()) {
            object.showLineInfoProduct();
        }
    }

}
