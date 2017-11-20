package utils.customview;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by alvin.tibor on 2/29/2016.
 */
public class MoneyEditText2 extends android.support.v7.widget.AppCompatEditText {

    public static final String PESO_CURRENCY = "\u20B1";
//    public static final String CURRENCY = PESO_CURRENCY;
    public static final String ZERO_AMOUNT = PESO_CURRENCY + "0.00";
    private static NumberFormat canadaEnglish = NumberFormat
            .getCurrencyInstance(Locale.CANADA);

    private static final String DEFAULT_STRING = ZERO_AMOUNT;

    public MoneyEditText2(Context context) {
        super(context);
    }

    public MoneyEditText2(Context context, AttributeSet attrs) {
        super(context, attrs);
        setText(DEFAULT_STRING);
        addTextChangedListener(onTextChangeListener);
    }

    public MoneyEditText2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private TextWatcher onTextChangeListener = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String formatted = "";

            removeTextChangedListener(this);
            String cleanString = s.toString().replaceAll("[\u20B1,.]", "");

            //Log.ic_news("PMTI ET", "cleanString > " + cleanString);
            BigDecimal parsed = new BigDecimal(cleanString.equals("") ? "0.00"
                    : cleanString).setScale(2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);

            formatted = canadaEnglish.format(parsed)
                    .replaceAll("\\$", PESO_CURRENCY);

            //Log.ic_news("PMTI ET",  "formatted > " + formatted);
            setText(formatted.replace("\\$", PESO_CURRENCY));
            setSelection(formatted.length());
            addTextChangedListener(this);

        }
    };

    boolean textFocus = true;

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        CharSequence text = getText();
        if(textFocus)
            if(selStart!=text.length() || selEnd != text.length()){
                setSelection(text.length(),text.length());
                textFocus = false;
            }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);

        new Handler(getContext().getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                textFocus = true;
            }
        }, 50);

    }

    public static double toDouble(String amount) {
        //DecimalFormat formatter = new DecimalFormat("#,###.00");
        return Double.parseDouble(amount.replace(PESO_CURRENCY,"").replace(",",""));
    }

    public static String format(double amount) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return PESO_CURRENCY+formatter.format(amount);
    }

}
