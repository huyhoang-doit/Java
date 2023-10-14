
package Enum;

/**
 *
 * @author lvhho
 */
public enum ReceptType {
    IMPORT,
    EXPORT;

    public static ReceptType valueOf(int type) {
        switch (type) {
            case 1:
                return IMPORT;
            case 2:
                return EXPORT;
        }
        return null;
    }
    
    
}
