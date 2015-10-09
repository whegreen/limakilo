package id.limakilo.www.bawang.util.common;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by walesadanto on 10/8/15.
 */
public class TextFormatter {

    public static String capitalize(String word){
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    public static String decimalFormat(Double number){
        Locale locale  = new Locale("id", "ID");
        String pattern = "###,###.##";

        DecimalFormat decimalFormat = (DecimalFormat)
                NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);

        return decimalFormat.format(number);
    }

}
