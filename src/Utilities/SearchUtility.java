package Utilities;

import java.text.NumberFormat;

public class SearchUtility {

    public static boolean isStringNumeric(String searchText){
        if (searchText == null) {
            return false;
        }

        try {
            Integer.parseInt(searchText);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
