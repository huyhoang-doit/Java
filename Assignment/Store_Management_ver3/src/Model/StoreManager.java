
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lvhho
 */
public class StoreManager {
    private List<Product> Products;
    private List<Recept> Recepts;

    public StoreManager(List<Product> Products, List<Recept> Recepts) {
        this.Products = Products;
        this.Recepts = Recepts;
    }

    public StoreManager() {
        Products = new ArrayList<>();
        Recepts = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return Products;
    }

    public void setProducts(List<Product> Products) {
        this.Products = Products;
    }

    public List<Recept> getRecepts() {
        return Recepts;
    }

    public void setRecepts(List<Recept> Recepts) {
        this.Recepts = Recepts;
    }
    
    
}
