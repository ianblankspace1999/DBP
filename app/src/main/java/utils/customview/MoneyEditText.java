package utils.customview;

import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;


import java.text.DecimalFormat;

import utils.AmountFormatter;

/**
 * Created by juanalfonzocarlos.le on 2/9/2016.
 */
public class MoneyEditText extends android.support.v7.widget.AppCompatEditText {

    private static final String TAG = "MoneyEditText";
    private boolean isDecimal = false;
    private int decimalPosition = 0;
    private int decimalIncrement = 0;
    private int dotIndex = 0;
    private int valWhole = 0;
    private int valDecimal = 0;

    private static final String PESO_CURRENCY = "\u20B1";
    //private float value = 0;

    public MoneyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

//        String text = PESO_CURRENCY+""+valWhole+"."+valDecimal;
//        dotIndex = text.indexOf(".");
//        setText(text);

        addTextChangedListener(TextChangeListener);

    }



    public MoneyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);

//        if(isDecimal){
//            int i = dotIndex+decimalIncrement;
//            if(i!=getSelectionStart());
//            try{
//                setSelection(i);
//            }catch(IndexOutOfBoundsException e){
//
//            }
//
//        }else{
//            int i = dotIndex;
//            if(i!=getSelectionStart());
//            setSelection(i);
//        }
    }
    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {

        double doubleValue = 0;
        if (focused == false) {
            try {
                DecimalFormat formatter = new DecimalFormat("#,###,###.00");
                doubleValue = Double.parseDouble(getText().toString().replace(',', '.'));
                setText(AmountFormatter.PESO_CURRENCY + formatter.format(doubleValue));
            } catch (NumberFormatException e) {
                //Error
            }

        }


    }


    private TextWatcher TextChangeListener = new TextWatcher() {
        int oldSelection = 0;
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }


        int beforeCount = 0;
        String beforeText;
        String beforeDecimal;
        int valWholeBefore = 0;
        int valDecimalBefore = 0;
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            beforeCount = count;
//            beforeText = s.toString();
//            try{
//                valWholeBefore = Integer.parseInt(getText().toString().substring(0,getText().toString().indexOf(".")).replace(PESO_CURRENCY, ""));
//            }catch(NumberFormatException e){
//                valWholeBefore = 0;
//            }
//
//            try{
//                valDecimalBefore = Integer.parseInt(getText().toString().substring(0,getText().toString().indexOf(".")+1).replace(PESO_CURRENCY, ""));
//            }catch(NumberFormatException e){
//                valDecimalBefore = 0;
//            }
//            beforeDecimal = s.toString().substring(s.toString().indexOf(".")+1);
        }

        @Override
        public void afterTextChanged(Editable s) {
            removeTextChangedListener(this);
//            String text = s.toString();
//
//
//
//            if(text.contains("..")){
//                text = text.replace("..", ".");
//                isDecimal = true;
//                //decimalPosition = strWhole.indexOf(".");
//            }else if(!text.contains(".")){
//                text = beforeText;
//                isDecimal = false;
//                decimalIncrement = 0;
//            }else if(!text.contains(PESO_CURRENCY)){
//                text = beforeText;
//            }
//
//            if(getSelectionStart()>text.indexOf(".")){
//                isDecimal = true;
//            }else{
//                isDecimal = false;
//            }
//
//
//            String strWhole = text.substring(0,text.indexOf(".")).replace(PESO_CURRENCY, "").replace(",", "");
//            if(strWhole.length()>9){
//                strWhole = beforeText.substring(0,beforeText.indexOf(".")).replace(PESO_CURRENCY, "").replace(",", "");
//            }else if(strWhole.length()==0){
//                strWhole = "0";
//            }
//
//            if(valWholeBefore == 0){
//                try{
//                    strWhole = Long.toString(Long.parseLong(strWhole));
//                }catch(NumberFormatException e){
//                    strWhole = "";
//                }
//            }
//            String strDecimal = text.substring(text.indexOf(".")+1);
//            if(isDecimal&&decimalIncrement==0){
//                strDecimal = "";
//            }else if(isDecimal && strDecimal.length()>2){
//                strDecimal = beforeDecimal;
//            }
//
//            if(strDecimal.contains(".")){
//                strDecimal = strDecimal.replace(".", "");
//                decimalIncrement-=1;
//            }
//
//            double amount = 0;
//            try{
//                amount = Double.parseDouble(strWhole);
//            }catch(NumberFormatException e){
//
//            }
//
//            DecimalFormat formatter = new DecimalFormat("#,###");
//
//            String finalText = PESO_CURRENCY+formatter.format(amount)+"."+strDecimal;
//            dotIndex = finalText.indexOf(".");
//            int pos = 0;
//
//            if(isDecimal){
//                if(strDecimal.length()==0){
//                    decimalIncrement = 1;
//                }else
//                if(text.length()>=beforeText.length()){
//                    if(decimalIncrement<3)
//                        decimalIncrement++;
//                }else{
//                    decimalIncrement--;
//                }
//                isDecimal = decimalIncrement > 0 ? true : false;
//
//                pos = finalText.indexOf(".")+decimalIncrement;
//
//            }else{
//                if(strDecimal.length()==0){
//                    finalText += "00";
//                }
//                pos = finalText.indexOf(".");
//            }
//
//            setText(finalText);
//            setSelection(pos);
//
//            //value = Float.parseFloat(finalText.replace(PESO_CURRENCY, "").replace(",", ""));
//            decval = Double.parseDouble(finalText.replace(PESO_CURRENCY,"").replace(",",""));
            if(listener!=null)
                listener.after();
            addTextChangedListener(this);
        }
    };


    double decval;

/*    public float getValue(){
        return value;
    }*/
    public double getDouble() throws NumberFormatException{
;
        return decval;
    }

    public static String format(double amount) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return PESO_CURRENCY+formatter.format(amount);
    }

    public static double toDouble(String amount) {
        //DecimalFormat formatter = new DecimalFormat("#,###.00");
        return Double.parseDouble(amount.replace(PESO_CURRENCY,"").replace(",",""));
    }


    public interface TextChangeListener{
        void after();
    }

    private TextChangeListener listener;
    public void setOnTextChangeListener(TextChangeListener listener) {
        this.listener = listener;
    }
}