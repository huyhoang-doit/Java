
package storemanagement;

import Enum.MenuItem;
import Util.Util;
import java.awt.List;

/**
 *
 * @author lvhho
 */
public class Menu {

    public static final MenuItem[] mainMenu = {
        MenuItem.PRODUCT_MANAGEMENT,
        MenuItem.WARE_HOUSE_MANAGEMENT,
        MenuItem.REPORT,
        MenuItem.SAVE_LOAD,
        MenuItem.EXIT
    };
    
    public static final MenuItem[] pMenu= {
        MenuItem.ADD_PRODUCT,
        MenuItem.UPDATE_PRODUCT,
        MenuItem.DELETE_PRODUCT,
        MenuItem.SHOW_PRODUCT,
        MenuItem.BACK
    };
    
    public static final MenuItem[] whMenu = {
        MenuItem.ADD_IMPORT_RECEPT,
        MenuItem.ADD_EXPORT_RECEPT,
        MenuItem.BACK
    };
    
    public static final MenuItem[] rMenu = {
        MenuItem.EXPIRED_PRODUCT,
        MenuItem.SELLING_PRODUCT,
        MenuItem.OUT_STOCK_PRODUCT,
        MenuItem.RECEPTS,
        MenuItem.BACK
    };
    
    public static MenuItem Display(MenuItem[] MenuOptions) {
        System.out.println("===== Store Management Menu =====");
        for(int i = 0; i < MenuOptions.length; i++){
            System.out.println((i+1) +". "+MenuOptions[i].getLabel());
        }
        System.out.println("===============================");

        int choice = Util.inputInt("Please enter your choice", 1, MenuOptions.length);
        
        return MenuOptions[choice-1];
    }
}
