/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author lvhho
 */
public class Menu {
    //Level 1
     public void printMainMenu() {
        System.out.println("+============================================================+");
        System.out.println("|                  STORE MANAGEMENT SYSTEM                   |");
        System.out.println("|            +================================+              |");
        System.out.println("+============================================================+");
        System.out.println("|       <1> Product management                               |");
        System.out.println("|       <2> Warehouse management                             |");
        System.out.println("|       <3> Report                                           |");
        System.out.println("|       <4> Store data to file                               |");
        System.out.println("|       <5> Exit system                                      |");
        System.out.println("+============================================================+");
    }
     
    public void printProductManagementMenu() {
        System.out.println("");
        System.out.println("               ---    PRODUCT MANAGEMENT      ---             ");
        System.out.println("   +---------------------------------------------------------+");
        System.out.println("   |          <1> Add a product                              |");         
        System.out.println("   |          <2> Update product information                 |");
        System.out.println("   |          <3> Delete product                             |");
        System.out.println("   |          <4> List of products                           |");
        System.out.println("   +---------------------------------------------------------+");
    }
    
    public void printWarehouseManagementMenu() {
        System.out.println("");
        System.out.println("                   --- WAREHOUSE MANAGEMENT ---               ");
        System.out.println("   +---------------------------------------------------------+");
        System.out.println("   |               <1> Create an import receipt              |");         
        System.out.println("   |               <2> Create an exprition receipt           |");
        System.out.println("   +---------------------------------------------------------+");
    }
    //Level 2
    public void printWarehouseSubMenu() {
        System.out.println("");
        System.out.println("      --- CREATE NEW IMPORT/EXPRITION RECEIPT CHOICE ---      ");
        System.out.println("   +---------------------------------------------------------+");
        System.out.println("   |               1. Create new receipt                     |");         
        System.out.println("   |               2. Exit                                   |");
        System.out.println("   +---------------------------------------------------------+");
    }
    
    public void printAddNewProductOptions() {
        System.out.println("");
        System.out.println("               --- ADD THE INFORMATION OF PROCUCT ---         ");
        System.out.println("   +---------------------------------------------------------+");
        System.out.println("   |               1. Add new Product                        |");         
        System.out.println("   |               2. Exit                                   |");
        System.out.println("   +---------------------------------------------------------+");
    }
    public void printUpdateOptions() {
        System.out.println("");
        System.out.println("             --- UPDATE THE INFORMATION OF PROCUCT ---        ");
        System.out.println("   +---------------------------------------------------------+");
        System.out.println("   |               1. Update Product                         |");         
        System.out.println("   |               2. Exit                                   |");
        System.out.println("   +---------------------------------------------------------+");
    }
    //Level 3
    public void printAddProductToReceiptChoices() {
        System.out.println("");
        System.out.println("               --- ADD NEW PROCUCT TO THE RECEIPT ---         ");
        System.out.println("   +---------------------------------------------------------+");
        System.out.println("   |               1. Add                                    |");         
        System.out.println("   |               2. Exit                                   |");
        System.out.println("   +---------------------------------------------------------+");
    }
    
    
    //Level 4
    public void printConfirmDeleteMessage() {
        System.out.println("");
        System.out.println("   +---------------------------------------------------------+");
        System.out.println("   |       You want to REMOVE this product from the list     |");
        System.out.println("   |               1. Confirm                                |");         
        System.out.println("   |               2. Cancel                                 |");
        System.out.println("   +---------------------------------------------------------+");
    }
    public void printGroupProductOptions() {
        System.out.println("");
        System.out.println("Group of product:       ");
        System.out.println("   1. Daily use         ");         
        System.out.println("   2. Long shelf life   ");
    }
}
