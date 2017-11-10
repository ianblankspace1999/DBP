package utils.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;

import org.droidparts.widget.ClearableEditText;

/**
 * Created by juanalfonzocarlos.le on 2/9/2016.
 */
public class TINEditText extends ClearableEditText {

    public static final String TAG = "TINEditText";

    private Drawable xD;

    public TINEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(TextChangeListener);


        /* //CLEAR BUTTON STUFF
        this.xD = this.getCompoundDrawables()[2];
        if(this.xD == null) {
            this.xD = this.getResources().getDrawable(android.R.drawable.ic_notification_clear_all);
        }

        this.xD.setBounds(0, 0, this.xD.getIntrinsicWidth() - 5, this.xD.getIntrinsicHeight() - 5);

        this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], xD, this.getCompoundDrawables()[3]);*/
    }

    public TINEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




    /*@Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(getText().length(), getText().length());
        setSelection();
        //setSelection(getText().length());
    }*/


    private TextWatcher TextChangeListener = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        int old_selection = 0;


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            old_selection = getSelectionStart();
        }

        int old_len = 0;
        @Override
        public void afterTextChanged(Editable s) {





            removeTextChangedListener(this);

            if(old_len>length()){
                old_len = length();
                addTextChangedListener(this);
                return;
            }

            String text = s.toString().replace("-", "");

            char[] arr = text.toCharArray();

            StringBuilder temp = new StringBuilder();

            for(char str:arr){
                boolean isInt = false;
                try{
                    Integer.parseInt(String.valueOf(str));
                    isInt = true;
                }catch(NumberFormatException e){
                    isInt = false;
                }
                //Log.ic_so(TAG, "STR : " + str + ", INT : " + isInt);

                if(temp.length()==3||temp.length()==7||temp.length()==11){
                    temp.append("-");
                }
                if(isInt){
                    temp.append(str);
                }
                if(temp.length()==17){
                    break;
                }
            }


            setText(temp);
/*            if(old_len>temp.length()){
                setSelection(old_selection - 1);
            }else if(old_len==temp.length()){
                setSelection(old_selection);
            }else{
                setSelection(old_selection+1);
            }*/


            old_len = temp.length();
            Selection.setSelection(getText(),length());
            addTextChangedListener(this);

        }
    };


    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        //Log.ic_so(TAG,"Lpressed");

        return super.onKeyLongPress(keyCode, event);
    }

    @Nullable
    public String getTIN() {
        String[] temparr = getText().toString().split("-");
        if(temparr.length==4){
            return temparr[0]+temparr[1]+temparr[2];
        }else{
            return null;
        }

    }

    @Nullable
    public String getBranchCode() {
        String[] temparr = getText().toString().split("-");
        if(temparr.length==4){
            return temparr[3];
        }else{
            return null;
        }
    }


}
