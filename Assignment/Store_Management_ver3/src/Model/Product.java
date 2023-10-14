
package Model;

import Util.Util;
import java.util.Date;

/**
 *
 * @author lvhho
 */
public class Product {
    private String ProductID;
    private String ProductName;
    private double UnitPrice;
    private int InventoryQuantity;
    private Date manufacter_date;
    private Date expiration_date;

    public Product(String ProductID, String ProductName, double UnitPrice, int InventoryQuantity, Date manufacter_date, Date expiration_date) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.UnitPrice = UnitPrice;
        this.InventoryQuantity = InventoryQuantity;
        this.manufacter_date = manufacter_date;
        this.expiration_date = expiration_date;
    }
    

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public int getInventoryQuantity() {
        return InventoryQuantity;
    }

    public void setInventoryQuantity(int InventoryQuantity) {
        this.InventoryQuantity = InventoryQuantity;
    }

    public Date getManufacter_date() {
        return manufacter_date;
    }

    public void setManufacter_date(Date manufacter_date) {
        this.manufacter_date = manufacter_date;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    
    @Override
    public String toString() {
        return ProductID + "," + ProductName + "," + UnitPrice + "," + InventoryQuantity + "," + Util.formatDateToString(manufacter_date) + "," + Util.formatDateToString(expiration_date);
    }
    
    
}
