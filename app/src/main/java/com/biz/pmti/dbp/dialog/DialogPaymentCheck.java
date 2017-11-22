package com.biz.pmti.dbp.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.biz.pmti.dbp.R;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypeCheck;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import utils.AmountFormatter;
import utils.DBPBase;
import utils.customview.MoneyEditText2;
import utils.customview.NumberTextWatcherForThousand;

/**
 * Created by juanalfonzocarlos.le on 3/1/2016.
 */
public class DialogPaymentCheck extends BaseDialogFragment{

    private static final String TAG = "DialogPaymentCheck";

    @BindView(R.id.pb_load)
    ProgressBar pbLoad;
    @BindView(R.id.spn_bankcode)
    AutoCompleteTextView spnBankCode;
    @BindView(R.id.et_bankdesc)
    EditText etBankDesc;
    @BindView(R.id.et_checknumber)
    EditText etCheckNumber;
    @BindView(R.id.et_checkamount)
    EditText etCheckAmount;
    @BindView(R.id.tv_checkdate)
    TextView tvCheckDate;

    String bankselection;

    double total = 0;


    Unbinder unbinder;
    private ArrayList<String> bankCodes = new ArrayList<>();
    private ArrayList<PaidTypeCheck> list = new ArrayList<>();
    private PaidTypeCheck model = null;

    private long checkDate = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            checkDate = savedInstanceState.getLong("checkDate", 0);
        }

        setup("Check", R.layout.dialog_payment_check);

        spnBankCode.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        spnBankCode.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, bankCodes));
        spnBankCode.setThreshold(1);



        spnBankCode.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                spnBankCode.showDropDown();
                return false;
            }
        });

        etCheckNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Calendar c = Calendar.getInstance();
                    if (checkDate != 0)
                        c.setTimeInMillis(checkDate);
                    DialogFragmentUtil.showDatePicker(c, getContext());
                }
                return false;
            }
        });

        etCheckNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    String result = s.toString().replaceAll("--", "-");
                    if (!s.toString().equals(result)) {
                        etCheckNumber.setText(result);
                        etCheckNumber.setSelection(result.length());
                        // alert the user
                    }
                } catch (IndexOutOfBoundsException e) {

                }

            }
        });



        if (list == null)
            list = new ArrayList<>();


            model = new PaidTypeCheck();

        if (checkDate != 0) {
            tvCheckDate.setText(DBPBase.formatDate(checkDate));
        }

        model.setPmodeCode("CHECK");

        etCheckAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                double doubleValue = 0;
                if (hasFocus == false) {
                    try {
                        DecimalFormat formatter = new DecimalFormat("#,###,###.00");
                        doubleValue = Double.parseDouble(etCheckAmount.getText().toString().replace(",", "").replace(AmountFormatter.PESO_CURRENCY, ""));
                        etCheckAmount.setText(AmountFormatter.PESO_CURRENCY + formatter.format(doubleValue));

                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });

        etCheckAmount.addTextChangedListener(new NumberTextWatcherForThousand(etCheckAmount));


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("checkDate", checkDate);
    }

    @OnClick(R.id.tv_checkdate)
    void datePicker() {
        Calendar c = Calendar.getInstance();
//        if (checkDate != 0)
            c.setTimeInMillis(checkDate);
        DialogFragmentUtil.showDatePicker(c, getContext());
    }

    @Override
    protected void onDateChanged(Calendar c) {
        checkDate = c.getTimeInMillis();
        Log.i("ianian", "date result: " + checkDate);
        tvCheckDate.setText(DBPBase.formatDate(checkDate));
    }

    @Override
    protected void submit(AlertDialog diag) {

        if (!etCheckAmount.getText().toString().equals("")) {
            try {
                total = Double.parseDouble(etCheckAmount.getText().toString().replace(",", "").replace(AmountFormatter.PESO_CURRENCY, ""));
            } catch (NumberFormatException ex) {
                total = 0;
            }
        }
        if (/*bankselection.isEmpty() || */etBankDesc.length() <= 0 || etCheckNumber.length() <= 0 || tvCheckDate.length() <= 0 || total <= 0) {
            DialogFragmentUtil.showToast("Please fill all fields!", getContext());
            return;
        }


        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            Date date1 = sdf.parse(DBPBase.formatDate(checkDate));
            Date date2 = sdf.parse(DBPBase.formatDate(new Date().getTime()));


            if (date1.after(date2)) {
                DialogFragmentUtil.showMessagePrompt("E-receipt", "The system do not allow post dated check!", getFragmentManager());
                return;
            }

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -5);
            Date result = sdf.parse(DBPBase.formatDate(cal.getTimeInMillis()));

            Log.e("FIVE", "-- " + result + " --- " + date1);


        } catch (ParseException e) {
            e.printStackTrace();
        }



        model.setDbcCode(bankselection);
        model.setDbcAddress(etBankDesc.getText().toString());
        model.setPtchkNumber(etCheckNumber.getText().toString());
        Log.i("--------", "ian---- " + checkDate);
        model.setPtchkDate(checkDate);
        model.setPtchkAmount(total);

        Log.i("--------", "ian---- save" + model.getPtchkDate());

        if (!list.contains(model)) {
            list.add(model);
        }


        diag.dismiss();


//        }


    }



    @Override
    protected void delete() {

    }

    @OnClick(R.id.et_bankdesc)
    void onclickDbcCode() {
        // etBankDesc.showDropDown();
    }


    //spnBankCode.setSelection(findIndex(model.getDbcCode()));
       /* if (service.isBound())
            try {
                service.getService().getTableDraweeBankCodeDBC_DESC(bankCodes.get(position), new IMrcosServiceReplyHandler.Stub() {
                    @Override
                    public void getEncryptedData(byte[] encryptedData, byte[] encryptedKey, byte[] iv) throws RemoteException {
                        List<String> list = (List<String>) Utils.decryptCommonObj(encryptedData, encryptedKey, iv);
                        if(list.size()>0)
                            etBankDesc.setText(""+list.get(0));
                    }

                    @Override
                    public void onRequestInterruptedListener(int interruption) throws RemoteException {

                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }*/


   /*@OnItemSelected(R.id.spn_bankcode)
    void onBankCodeSelected(int position){
        if (service.isBound())
            try {
                service.getService().getTableDraweeBankCodeDBC_DESC(bankCodes.get(position), new IMrcosServiceReplyHandler.Stub() {
                    @Override
                    public void getEncryptedData(byte[] encryptedData, byte[] encryptedKey, byte[] iv) throws RemoteException {
                        List<String> list = (List<String>) Utils.decryptCommonObj(encryptedData, encryptedKey, iv);
                        if(list.size()>0)
                            etBankDesc.setText(""+list.get(0));
                    }

                    @Override
                    public void onRequestInterruptedListener(int interruption) throws RemoteException {

                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
    }*/


    private int findIndex(String dbcCode) {
        for (String code : bankCodes) {
            if (code.equalsIgnoreCase(dbcCode))
                return bankCodes.indexOf(code);
        }
        return -1;
    }

    protected void showSoftKeyboard(EditText input) {
        input.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);

    }

    protected void hideSoftKeyboard(EditText input) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);

    }


}
