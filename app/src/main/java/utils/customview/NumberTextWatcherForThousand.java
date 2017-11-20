package utils.customview;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.StringTokenizer;

import utils.AmountFormatter;

/**
 * Created by eleazar.tamondong on 4/10/2017.
 */
public class NumberTextWatcherForThousand implements TextWatcher {
    EditText editText;
    public static final String PESO_CURRENCY = "\u20B1";

    public NumberTextWatcherForThousand(EditText editText) {
        this.editText = editText;


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        try {
            editText.removeTextChangedListener(this);
            String value = editText.getText().toString();

            if (value != null && !value.equals("")) {

                if (value.startsWith(".")) {
                    editText.setText("0.");
                }
                if (value.startsWith("0") && !value.startsWith("0.")) {
                    editText.setText("");
                }

                DecimalFormat formatter = new DecimalFormat("#,###,###");
                double v_value = Double.parseDouble(editText.getText().toString().replace(",", "").replace(AmountFormatter.PESO_CURRENCY, ""));


                if (s.equals(".") || value.contains(".")) {

                } else {
                    if (!value.equals("")) {
//                    if (str.contains(AmountFormatter.CURRENCY)){
//                        editText.setText(formatter.format(str));
//                    }else{
//                        editText.setText(AmountFormatter.CURRENCY + formatter.format(str));
//                    }
//                        String currencySymbol = Currency.getInstance(Locale.getDefault()).getSymbol();
                        editText.setText(PESO_CURRENCY + formatter.format(v_value));
                    }
                }

                editText.setSelection(editText.getText().toString().length());
            }
            editText.addTextChangedListener(this);
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
            editText.addTextChangedListener(this);
        }


    }

    public static String getDecimalFormattedString(String value) {
        StringTokenizer lst = new StringTokenizer(value, ".");
        String str1 = value;
        String str2 = "";
        if (lst.countTokens() > 1) {
            str1 = lst.nextToken();
            str2 = lst.nextToken();
        }
        String str3 = "";
        int i = 0;
        int j = -1 + str1.length();
        if (str1.charAt(-1 + str1.length()) == '.') {
            j--;
            str3 = ".";
        }
        for (int k = j; ; k--) {
            if (k < 0) {
                if (str2.length() > 0)
                    str3 = str3 + "." + str2;
                return str3;
            }
            if (i == 3) {
                str3 = "," + str3;
                i = 0;
            }
            str3 = str1.charAt(k) + str3;
            i++;
        }

    }

    public static String trimCommaOfString(String string) {
//        String returnString;
        if (string.contains(",")) {
            return string.replace(",", "");
        } else {
            return string;
        }
    }

    public static double toDouble(String amount) {
        if (amount.equals("")){
            double a = 0.0;
            return a;
        }else{
            return Double.parseDouble(amount.replace(AmountFormatter.PESO_CURRENCY, "").replace(",", ""));
        }

    }
}
