package vn.funix.FX21678.asm03.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class Utils {

    public static String getDivider(){
        return "+----------+-------------------------+---------+";
    }

    public static String getDateTime(){
        return String.valueOf(LocalDateTime.now());
    }

    public static String formatBalance(double amount){
        Locale locale = new Locale("vi", "VI");
        String pattern = "###,###,###,###";
        DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        dcf.applyPattern(pattern);

        return dcf.format(amount)+"đ";
    }
}
