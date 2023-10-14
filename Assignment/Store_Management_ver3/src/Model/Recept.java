
package Model;

import Enum.ReceptType;
import java.util.Map;

/**
 *
 * @author lvhho
 */
public class Recept {
    private String ReceptID;
    private ReceptType type;
    private Map<String, Integer> productMap;

    public Recept(String ReceptID, ReceptType type, Map<String, Integer> productMap) {
        this.ReceptID = ReceptID;
        this.type = type;
        this.productMap = productMap;
    }

    public String getReceptID() {
        return ReceptID;
    }

    public void setReceptID(String ReceptID) {
        this.ReceptID = ReceptID;
    }

    public ReceptType getType() {
        return type;
    }

    public void setType(ReceptType type) {
        this.type = type;
    }

    public Map<String, Integer> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<String, Integer> productMap) {
        this.productMap = productMap;
    }

    @Override
    public String toString() {
        String str = "";
        for (Map.Entry<String, Integer> entry : productMap.entrySet()){
            str+=ReceptID + "," + type + "," + entry.getKey() + "," + entry.getValue() + "\n";
        }
        return str;
    }
    
    
}
