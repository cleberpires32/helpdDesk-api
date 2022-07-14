package com.cleber.helpDeskapi.service.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class Utils {

	  String CEP = formatString("12345678","#####-###");
	  String TEL = formatString("1234567890123","(##) #-####-####");
	  
    public static String formatString(String value, String pattern) {
        MaskFormatter mf;
        try {
            mf = new MaskFormatter(pattern);
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(value);
        } catch (ParseException ex) {
            return value;
        }
    }
    /*
    String CEP = "12345678";
    System.out.println(CEP.substring(0, 5) + "-" + CEP.substring(5));
    String TEL = "1234567890123";
    System.out.println("(" + TEL.substring(0, 2) + ")" + TEL.substring(2, 6) + "-" + TEL.substring(6, 10));
    */
}
