package utils;

import android.support.annotation.NonNull;


import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by alvin.tibor on 2/24/2016.
 */
public class AmountFormatter {
    private static final int ZERO = 0;
    public static final String PESO_CURRENCY = "\u20B1";
    public static final String PHP = "Php";
    public static String CURRENCY = PESO_CURRENCY;
    public static String ZERO_AMOUNT = CURRENCY + "0.00";
    private static NumberFormat canadaEnglish = NumberFormat
            .getCurrencyInstance(Locale.CANADA);

    public static final String PESO = "\u20B1";
    public static Double AMOUNT_LIMIT = 999999999999.99;

    public static String convertTextAmountFormat(@NonNull Object object){
        return canadaEnglish.format(object)
                .replaceAll("\\$", CURRENCY);
    }

    public static String generateWordAmount(double amount){
        long amountToConvert = (long) amount;
        String translatedWholeNumber = UtilEnglishNumberToWords.convert(amountToConvert);

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        String stringConversion = nf.format(amount).toString().replaceAll(",", "");
        String translatedDecimal = "";
        String splitDecimal = stringConversion.toString();
        String[] parts = splitDecimal.split("\\.");
        String decNumber = parts[1].toString();
        int decNumberIntValue = Integer.parseInt(decNumber);
        if (decNumberIntValue != 0) {
            translatedDecimal = UtilDecimalNumberToWords.convert2(decNumberIntValue);
            translatedDecimal += " ";
        }
        return translatedWholeNumber + " PESOS " + translatedDecimal + "ONLY";
    }

}
