
package storemanagement;

import Enum.ReceptType;
import Model.Product;
import Model.Recept;
import Model.StoreManager;
import Util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lvhho
 */
public class Controller {

    public static StoreManager LoadData(StoreManager sm) {
        sm.setProducts(LoadProduct());
        sm.setRecepts(LoadRecept());
        return sm;
    }

    public static void SaveData(StoreManager sm) {
        saveProduct(sm.getProducts());
        saveRecept(sm.getRecepts());
    }

    public static List<Product> LoadProduct() {
        List<Product> Products = new ArrayList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader("src//Data//Product.dat"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line into its components based on the comma separator
                String[] components = line.split(",");
                if (components.length == 6) {
                    String productID = components[0];
                    String productName = components[1];
                    double price = Double.parseDouble(components[2]);
                    int quantity = Integer.parseInt(components[3]);
                    Date manufacter_date = Util.dateParse(components[4]);
                    Date expire_date = Util.dateParse(components[5]);

                    // Process the data as needed
                    Products.add(new Product(productID, productName, price, quantity, manufacter_date, expire_date));
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        return Products;
    }

    public static List<Recept> addRecept(List<Recept> Recepts, String receptID, String type, String productID, int quantity) {
        boolean hasRecept = false;
        for (int i = 0; i > Recepts.size(); i++) {
            if (Recepts.get(i).getReceptID().equals(receptID)) {
                Recept r = Recepts.get(i);
                Map<String, Integer> productMap = r.getProductMap();
                productMap.put(productID, quantity);
                r.setProductMap(productMap);
                Recepts.set(i, r);
                hasRecept = true;
                break;
            }
        }

        if (!hasRecept) {
            Map<String, Integer> productMap = new HashMap<>();
            productMap.put(productID, quantity);
            Recepts.add(new Recept(receptID, ReceptType.valueOf(type), productMap));
        }
        return Recepts;
    }

    public static List<Recept> LoadRecept() {
        List<Recept> Recepts = new ArrayList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader("src//Data//Recept.dat"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line into its components based on the comma separator
                String[] components = line.split(",");
                if (components.length == 4) {
                    String receptID = components[0];
                    String type = components[1];
                    String productID = components[2];
                    int quantity = Integer.parseInt(components[3]);

                    // Process the data as needed
                    Recepts = addRecept(Recepts, receptID, type, productID, quantity);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        return Recepts;
    }

    public static void saveProduct(List<Product> products) {
        // Write the data to the file
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("src//Data//Product.dat", false))) {
            for (Product p : products) {
                writer.write(p.toString());
                writer.newLine(); // Add a new line for each entry
            }
            System.out.println("Save Data to " + "Product.dat");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static void saveRecept(List<Recept> recepts) {
        // Write the data to the file
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("src//Data//Recept.dat", false))) {
            for (Recept r : recepts) {
                writer.write(r.toString());
                writer.newLine(); // Add a new line for each entry
            }
            System.out.println("Save Data to " + "Recept.dat");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static String inputNewProductID(List<Product> products) {
        String id = Util.inputString("input product id");
        for (Product p : products) {
            if (p.getProductID().equals(id)) {
                System.err.println("Product already existed");
                return inputNewProductID(products);
            }
        }
        return id;
    }

    public static String inputProductID(List<Product> products) {
        boolean hasID = false;
        String id = Util.inputString("input product id");
        for (Product p : products) {
            if (p.getProductID().equals(id)) {
                hasID = true;
                break;
            }
        }

        if (!hasID) {
            System.err.println("Product doesnt existed");
            return inputProductID(products);
        }
        return id;
    }

    public static StoreManager addProduct(StoreManager sm) {
        String id = inputNewProductID(sm.getProducts());
        String name = Util.inputString("input product name");
        double price = Util.inputDouble("input product price", 0, 1000);
        int quantity = Util.inputInt("input product inventory quantity", 0, 100);
        Date manu_date = Util.inputDate("input manufacter date (dd-MM-yyyy)");
        Date ex_date = Util.inputDate("input expired date (dd-MM-yyyy)");
        List<Product> products = sm.getProducts();
        products.add(new Product(id, name, price, quantity, manu_date, ex_date));
        sm.setProducts(products);
        return sm;
    }

    public static StoreManager updateProduct(StoreManager sm) {
        String id = inputProductID(sm.getProducts());
        String name = Util.inputString("input product name");
        double price = Util.inputDouble("input product price", 0, 1000);
        int quantity = Util.inputInt("input product inventory quantity", 0, 100);
        Date manu_date = Util.inputDate("input manufacter date (dd-MM-yyyy)");
        Date ex_date = Util.inputDate("input expired date (dd-MM-yyyy)");
        List<Product> products = sm.getProducts();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID().equals(id)) {
                products.set(i, new Product(id, name, price, quantity, manu_date, ex_date));
                break;
            }
        }
        sm.setProducts(products);
        return sm;
    }

    public static StoreManager deleteProduct(StoreManager sm) {
        String id = inputProductID(sm.getProducts());
        List<Product> products = sm.getProducts();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID().equals(id)) {
                products.remove(i);
                break;
            }
        }
        sm.setProducts(products);
        return sm;
    }

    public static void showProducts(StoreManager sm) {
        List<Product> products = sm.getProducts();
        System.out.println("=================================PRODUCT LIST===================================");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s%n", "ID", "Name", "Price", "Inventory", "ManufacterDate", "ExpriedDate");
        for (Product p : products) {
            System.out.printf("%-20s %-20s %-20f %-20d %-20s %-20s%n", p.getProductID(), p.getProductName(), p.getUnitPrice(),
                    p.getInventoryQuantity(), Util.formatDateToString(p.getManufacter_date()), Util.formatDateToString(p.getExpiration_date()));
        }
    }

    public static String inputNewRecepttID(List<Recept> recepts) {
        String id = Util.inputString("input recept id");
        for (Recept r : recepts) {
            if (r.getReceptID().equals(id)) {
                System.err.println("Recept already existed");
                return inputNewRecepttID(recepts);
            }
        }
        return id;
    }

    public static String inputReceptID(List<Recept> recepts) {
        boolean hasID = false;
        String id = Util.inputString("input recept id");
        for (Recept r : recepts) {
            if (r.getReceptID().equals(id)) {
                hasID = true;
            }
        }

        if (!hasID) {
            System.err.println("Recept doesnt existed");
            return inputReceptID(recepts);
        }
        return id;
    }

    public static ReceptType inputType() {
        int type = Util.inputInt("input type \n 1.Import \n 2.Export \n", 1, 2);
        return ReceptType.valueOf(type);
    }

    public static boolean checkQuantity(String id, int quantity, StoreManager sm) {
        List<Product> products = sm.getProducts();

        for (Product p : products) {
            if (p.getProductID().equals(id)) {
                if (p.getInventoryQuantity() >= quantity) {
                    return true;
                } else {
                    System.err.println("not enough " + p.getProductName() + " in inventory");
                }
                break;
            }
        }
        return false;
    }

    public static StoreManager handleQuantity(String id, int quantity, StoreManager sm, ReceptType type) {
        List<Product> products = sm.getProducts();

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getProductID().equals(id)) {

                if (type.equals(ReceptType.EXPORT)) {
                    if (p.getInventoryQuantity() >= quantity) {
                        p.setInventoryQuantity(p.getInventoryQuantity() - quantity);
                    }
                }

                if (type.equals(ReceptType.IMPORT)) {
                    p.setInventoryQuantity(p.getInventoryQuantity() + quantity);
                }

                products.set(i, p);
                break;
            }
        }
        
        sm.setProducts(products);
        return sm;
    }

    public static StoreManager addRecept(StoreManager sm, ReceptType type) {
        List<Recept> recepts = sm.getRecepts();
        String id = inputNewRecepttID(recepts);
        boolean yn = true;
        Map<String, Integer> productMap = new HashMap<>();
        do {
            String pid = inputProductID(sm.getProducts());
            int quantity = Util.inputInt("input quantity", 1, 100);

            switch (type) {
                case IMPORT:
                    productMap.put(pid, quantity);
                    sm = handleQuantity(pid, quantity, sm, type);
                    break;

                case EXPORT:
                    if (checkQuantity(pid, quantity, sm)) {
                        productMap.put(pid, quantity);
                        sm = handleQuantity(pid, quantity, sm, type);
                    }
                    break;
            }

            yn = Util.inputYN("do you want to continue y/n");
        } while (yn);
        recepts.add(new Recept(id, type, productMap));
        sm.setRecepts(recepts);
        return sm;
    }

    public static void showExProducts(StoreManager sm) {
        Date datenow = new Date();
        List<Product> products = sm.getProducts();
        System.out.println("=================================EXPIRED PRODUCT LIST===================================");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s%n", "ID", "Name", "Price", "Inventory", "manufacterDate", "expriedDate");
        for (Product p : products) {
            if (datenow.after(p.getExpiration_date())) {
                System.out.printf("%-20s %-20s %-20f %-20d %-20s %-20s%n", p.getProductID(), p.getProductName(), p.getUnitPrice(),
                        p.getInventoryQuantity(), Util.formatDateToString(p.getManufacter_date()), Util.formatDateToString(p.getExpiration_date()));
            }

        }
    }

    public static void showSellProducts(StoreManager sm) {
        Date datenow = new Date();
        List<Product> products = sm.getProducts();
        System.out.println("=================================SELLING PRODUCT LIST===================================");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s%n", "ID", "Name", "Price", "Inventory", "manufacterDate", "expriedDate");
        for (Product p : products) {
            if (datenow.before(p.getExpiration_date()) && p.getUnitPrice() > 0) {
                System.out.printf("%-20s %-20s %-20f %-20d %-20s %-20s%n", p.getProductID(), p.getProductName(), p.getUnitPrice(),
                        p.getInventoryQuantity(), Util.formatDateToString(p.getManufacter_date()), Util.formatDateToString(p.getExpiration_date()));
            }

        }
    }

    public static void showOutStockProducts(StoreManager sm) {
        Date datenow = new Date();
        List<Product> products = sm.getProducts();
        System.out.println("=================================OUT OF STOCK PRODUCT LIST===================================");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s%n", "ID", "Name", "Price", "Inventory", "manufacterDate", "expriedDate");
        for (Product p : products) {
            if (p.getInventoryQuantity()== 0) {
                System.out.printf("%-20s %-20s %-20f %-20d %-20s %-20s%n", p.getProductID(), p.getProductName(), p.getUnitPrice(),
                        p.getInventoryQuantity(), Util.formatDateToString(p.getManufacter_date()), Util.formatDateToString(p.getExpiration_date()));
            }

        }
    }

    public static void showRecepts(StoreManager sm) {
        List<Product> products = sm.getProducts();
        List<Recept> recepts = sm.getRecepts();
        String id = inputProductID(products);
        System.out.println("=================================RECEPT LIST===================================");
        System.out.printf("%-20s %-20s %-20s %-20s%n", "ID", "Type", "Product", "Quantity");
        for (Recept r : recepts) {
            for (Map.Entry<String, Integer> entry : r.getProductMap().entrySet()) {
                if (entry.getKey().equals(id)) {
                    System.out.printf("%-20s %-20s %-20s %-20d%n", r.getReceptID(), r.getType(), getProductById(products, id).getProductName(), entry.getValue());
                }
            }
        }
    }

    public static Product getProductById(List<Product> products, String id) {
        for (Product p : products) {
            if (p.getProductID().equals(id)) {
                return p;
            }
        }
        return null;
    }
}
