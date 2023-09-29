
package storemanagement;

import Enum.MenuItem;
import Enum.ReceptType;
import Model.StoreManager;

/**
 *
 * @author lvhho
 */
public class StoreManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        StoreManager sm = new StoreManager();
        sm = Controller.LoadData(sm);

        while (true) {
            MenuItem mi1 = Menu.Display(Menu.mainMenu);
            switch (mi1) {
                case PRODUCT_MANAGEMENT:
                    MenuItem mi2 = Menu.Display(Menu.pMenu);
                    switch (mi2) {
                        case ADD_PRODUCT:
                            sm = Controller.addProduct(sm);
                            break;
                        case UPDATE_PRODUCT:
                            sm = Controller.updateProduct(sm);
                            break;
                        case DELETE_PRODUCT:
                            sm = Controller.deleteProduct(sm);
                            break;
                        case SHOW_PRODUCT:
                            Controller.showProducts(sm);
                            break;
                    }
                    break;
                case WARE_HOUSE_MANAGEMENT:
                    MenuItem mi3 = Menu.Display(Menu.whMenu);
                    switch (mi3) {
                        case ADD_IMPORT_RECEPT:
                            sm = Controller.addRecept(sm, ReceptType.IMPORT);
                            break;
                        case ADD_EXPORT_RECEPT:
                            sm = Controller.addRecept(sm, ReceptType.EXPORT);
                            break;
                    }
                    break;
                case REPORT:
                    MenuItem mi4 = Menu.Display(Menu.rMenu);
                    switch (mi4) {
                        case EXPIRED_PRODUCT:
                            Controller.showExProducts(sm);
                            break;
                        case SELLING_PRODUCT:
                            Controller.showSellProducts(sm);
                            break;
                        case OUT_STOCK_PRODUCT:
                            Controller.showOutStockProducts(sm);
                            break;
                        case RECEPTS:
                            Controller.showRecepts(sm);
                            break;
                    }
                    break;
                case SAVE_LOAD:
                    Controller.SaveData(sm);
                    sm = Controller.LoadData(sm);
                    break;
                case EXIT:
                    System.exit(0);
            }
        }
    }

}
