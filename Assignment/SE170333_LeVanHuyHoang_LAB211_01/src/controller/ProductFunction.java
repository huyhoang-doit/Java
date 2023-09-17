/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
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
public class ProductFunction implements IProductFunction {

    public HashMap<String, Product> listProduct = new HashMap<>();
    public ArrayList<Product> arrayListProduct = new ArrayList<>();
    public ArrayList<String> listCodeProduct = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private Menu menu = new Menu();

    @Override
    // <1> Add product to list
    public void addProduct() {

        String code, name, manDate = "", expDate = "", group = "";
        int quantity, groupChoice;

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
                manDate = DateTime.getDay();
                expDate = DateTime.getDay();
                break;
            }
            case 2: {
                group = "Long shelf life";
                System.out.print("Input date of manufacture <dd-MM-yyyy>: ");
                manDate = DateTime.getManuFactureDate();
                System.out.print("Input date of exprition <dd-MM-yyyy>: ");
                expDate = DateTime.getExpritionDate();
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
        System.out.println("----------------------------------------------------------------------------------");
    }

    @Override
    // <2> Update method
    public void updateProduct() {

        String code, name, manDate = "", expDate = "", group = "";
        int quantity, groupChoice;
        boolean checkCode = false;

        if (listProduct.isEmpty()) {
            System.out.println(">>>> LIST OF PRODUCTS AT THE STORE IS EMPTY!!!");
        } else {
            System.out.print("Enter the product code you want to update <Code:*****>: ");
            code = CheckRule.productCodeValid();
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
                // Find product have this 'code'
                Product productUpdate = listProduct.get(code);

                // Remove this code out of list code product
                for (int i = 0; i < listCodeProduct.size(); i++) {
                    if (listCodeProduct.get(i).equals(code)) {
                        listCodeProduct.remove(i);
                        break;
                    }
                }

                //Update new information 
                System.out.print("Product code <Code:*****>: ");
                code = CheckRule.findProductCodeNotExist(listCodeProduct);
                productUpdate.setProductCode(code);
                System.out.print("Product name: ");
                name = sc.nextLine().toUpperCase().trim();
                productUpdate.setProductName(name);
                menu.printGroupProductOptions();
                groupChoice = CheckNumber.getAnInteger("Group: ", "Illegal, try again", 1, 2);
                switch (groupChoice) {
                    case 1: {
                        group = "Daily use";
                        manDate = DateTime.getDay();
                        expDate = DateTime.getDay();
                        productUpdate.setGroup(group);
                        productUpdate.setManufactureDate(manDate);
                        productUpdate.setExpritionDate(expDate);
                        break;
                    }
                    case 2: {
                        group = "Long shelf life";
                        productUpdate.setGroup(group);
                        System.out.print("Input date of manufacture <dd-MM-yyyy>: ");
                        manDate = DateTime.getManuFactureDate();
                        productUpdate.setManufactureDate(manDate);
                        System.out.print("Input date of exprition <dd-MM-yyyy>: ");
                        expDate = DateTime.getExpritionDate();
                        productUpdate.setExpritionDate(expDate);
                        break;
                    }
                }
                System.out.print("Quantity: ");
                quantity = CheckNumber.getAnInteger();
                productUpdate.setQuantity(quantity);

                //Add new product code to the list product code
                listCodeProduct.add(code);
                System.out.println("Product update successful >^<");
                System.out.println("----------------------------------------------------------------------------------");
            }
        }
    }

    @Override
    // <3> Delele product
    public void deleteProduct(HashMap<String, Product> listProductAppear) {
        String code;
        int choice;
        
        if (listProduct.isEmpty()) {
            System.out.println(">>>> LIST OF PRODUCTS AT THE STORE IS EMPTY!!!");
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
                System.out.println("This product CANNOT be DELETED because the product information ");
                System.out.println("is already EXISTS in the import-export receipt!");
            }

        }
    }

    @Override
    // <4> List of products
    public void printListOfProduct() {
        if (listProduct.isEmpty()) {
            System.out.println(">>>> LIST OF PRODUCTS AT THE STORE IS EMPTY!!!");
        } else {
            System.out.println(">>>> LIST OF PRODUCTS AT THE STORE");
            System.out.printf("|%-5s|%-25s|%-15s|%-10s|%-10s|%-10s|\n", "CODE", "PRODUCT-NAME", "GROUP", "MAN_DATE", "EXP_DATE", "QUANTITY");
            System.out.println("----------------------------------------------------------------------------------");
            for (Product object : listProduct.values()) {
                object.showLineInfoProduct();
            }
        }
    }

    @Override
    // <5> Report: List product that have expired : hết hạn sử dụng
    public void printListProductsExpired() {
        if (listProduct.isEmpty()) {
            System.out.println(">>>> LIST OF PRODUCTS AT THE STORE IS EMPTY!!!");
        } else {
            boolean check;
            System.out.println(">>>> LIST PRODUCTS EXPIRED");
            System.out.printf("|%-5s|%-25s|%-15s|%-10s|%-10s|%-10s|\n", "CODE", "PRODUCT-NAME", "GROUP", "MAN_DATE", "EXP_DATE", "QUANTITY");
            System.out.println("----------------------------------------------------------------------------------");
            for (Product product : listProduct.values()) {
                String expritionDate = product.getExpritionDate();
                check = DateTime.isExpired(expritionDate);
                if (check) {
                    product.showLineInfoProduct();
                }
            }
        }
    }

    @Override
    //<6> Print list of products store selling now ( quantity > 0 and not exprired )
    public void printListCurrentSell() {
        if (listProduct.isEmpty()) {
            System.out.println(">>>> LIST OF PRODUCTS AT THE STORE IS EMPTY!!!");
        } else {
            boolean check;
            System.out.println(">>>> LIST PRODUCTS STORE SELLING");
            System.out.printf("|%-5s|%-25s|%-15s|%-10s|%-10s|%-10s|\n", "CODE", "PRODUCT-NAME", "GROUP", "MAN_DATE", "EXP_DATE", "QUANTITY");
            System.out.println("----------------------------------------------------------------------------------");
            for (Product product : listProduct.values()) {
                String expritionDate = product.getExpritionDate();
                check = DateTime.isExpired(expritionDate);
                if (!check && product.getQuantity() > 0) {
                    product.showLineInfoProduct();
                }
            }
        }
    }

    @Override
    //<7> Sort product quantity
    //Purpopse: to print list products are running out of stock < sort acsending of quantity> 
    public void sortProductQuantity() {
        if (listProduct.isEmpty()) {
            System.out.println(">>>> LIST OF PRODUCTS AT THE STORE IS EMPTY!!!");
        } else {
            // Create list product for ready using Collection.sort
            ArrayList<Product> listSort = new ArrayList<>();
            for (Product product : listProduct.values()) {
                // Select product has quantity less equal than 3
                if (product.getQuantity() < 3) {
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
            System.out.println("----------------------------------------------------------------------------------");
            for (Product product : listSort) {
                product.showLineInfoProduct();
            }
        }
    }

    // <8> Create ArrayList from HashMap Product
    public ArrayList<Product> getArrayListProduct() {
        ArrayList<Product> list = new ArrayList<>();
        for (Product value : listProduct.values()) {
            list.add(value);
        }
        return list;
    }
}
