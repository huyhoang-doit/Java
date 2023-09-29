
package Enum;

/**
 *
 * @author lvhho
 */
public enum MenuItem {
    EXIT("Exit"),
    BACK("Back"),
    SAVE_LOAD("Save and Load"),
    //
    PRODUCT_MANAGEMENT("Product Management"),
    //
    ADD_PRODUCT("Add product"),
    UPDATE_PRODUCT("Update product"),
    DELETE_PRODUCT("Delete product"),
    SHOW_PRODUCT("Show All Products"),
    //
    WARE_HOUSE_MANAGEMENT("Warehouse management"),
    //
    ADD_IMPORT_RECEPT("Create import recept"),
    ADD_EXPORT_RECEPT("Create export recept"),
    //
    REPORT("Reports"),
    //
    EXPIRED_PRODUCT("Products that have expired"),
    SELLING_PRODUCT("Products that are avaiable"),
    OUT_STOCK_PRODUCT("Products that are running out of stock"),
    RECEPTS("Show recepts of product");

    private final String label;

    public String getLabel() {
        return label;
    }


    private MenuItem(String label){
        this.label = label;
    }
}
