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
public class DialogPaymentCheck extends SuperPaymentDialog {

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
    private List<String> bankCodes = null;
    private List<PaidTypeCheck> list = null;
    private PaidTypeCheck model = null;

    private long checkDate = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            checkDate = savedInstanceState.getLong("checkDate", 0);
        }

//        service = ((BaseApplication) getActivity().getApplicationContext()).service;

        setup("Check", R.layout.dialog_payment_check);
//        bankCodes = parentFragment.getBankCodes();

        spnBankCode.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        spnBankCode.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, bankCodes));
        spnBankCode.setThreshold(1);
//        spnBankCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selection = (String) parent.getItemAtPosition(position);
//                bankselection = selection;
//                Log.i("tag", "iainian pumasok" + selection);
//
//                if (service.isBound())
//                    try {
//                        service.getService().getTableDraweeBankCodeDBC_DESC(selection, new IMrcosServiceReplyHandler.Stub() {
//                            @Override
//                            public void getEncryptedData(byte[] encryptedData, byte[] encryptedKey, byte[] iv) throws RemoteException {
//
//                                List<String> list = (List<String>) Utils.decryptCommonObj(encryptedData, encryptedKey, iv);
//                                Log.i("tag", "iainian pumasok size : " + list.size());
//                                Log.i("tag", "iainian pumasok to string : " + list.toString());
//                                if (list.size() > 0)
//                                    etBankDesc.setText("" + list.get(0));
//                                hideSoftKeyboard(spnBankCode);
//                            }
//
//                            @Override
//                            public void onRequestInterruptedListener(int interruption) throws RemoteException {
//
//                            }
//                        });
//                    } catch (RemoteException e) {
//                        e.printStackTrace();
//                    }
//
//            }
//        });

//        spnBankCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                    spnBankCode.showDropDown();
//                }
//            }
//        });

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
                    showDatePicker(c);
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
        /*spnBankCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selection = (String)parent.getItemAtPosition(position);
                Log.e("etBAnk jac","position"+selection);

                *//*if (service.isBound())
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
                 }*//*
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        /*etBankDesc.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, bankCodes));
        etBankDesc.setThreshold(1);
        etBankDesc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //spnBankCode.setSelection(position);
               // spnBankCode.
            }
        });
*/
//        list = revenueCollection.getPaidTypeCheck();


        if (list == null)
            list = new ArrayList<>();

        if (isEditMode) {
            Log.e("position", "" + "--" + list.toString());

//            model = list.get(position);
            model = (PaidTypeCheck) fragmentTransaction.mPaymentCollection.get(position);
            // spnBankCode.setSelection(findIndex(model.getDbcCode()));
            spnBankCode.setText(model.getDbcCode());
            etBankDesc.setText(model.getDbcAddress());
            etCheckNumber.setText(model.getPtchkNumber());
            checkDate = model.getPtchkDate();
            Log.i("tag", "-------iaaaan: " + model.getPtchkDate());
            etCheckAmount.setText(MoneyEditText2.format(model.getPtchkAmount()));
            tvCheckDate.setText(model.getPtchkDateStr());
        } else
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
        if (checkDate != 0)
            c.setTimeInMillis(checkDate);
        showDatePicker(c);
    }

    @Override
    protected void onDateChanged(Calendar c) {
        checkDate = c.getTimeInMillis();
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
            showToast("Please fill all fields!");
            return;
        }


        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            Date date1 = sdf.parse(DBPBase.formatDate(checkDate));
            Date date2 = sdf.parse(DBPBase.formatDate(new Date().getTime()));


            if (date1.after(date2)) {
                showMessagePrompt("E-receipt", "The system do not allow post dated check!");
                return;
            }

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -5);
            Date result = sdf.parse(DBPBase.formatDate(cal.getTimeInMillis()));

            Log.e("FIVE", "-- " + result + " --- " + date1);

//            if (!fragmentTransaction.mTitle.equals("Update Deposit")) {
//                if (date1.before(result)) {
//                    showMessagePrompt("E-receipt", "Check date must not be more than 5 months ago!");
//                    return;
//                }
//            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


//        for (int i = 0; i < list.size(); i++) {
//            PaidTypeCheck ptc = list.get(i);
//
//            if (position == i) {
//                Log.i("position", "position: " + ptc.getPtchkNumber());
//                Log.i("position", "positionsss: " + etCheckNumber.getText().toString());
//                if (!ptc.getPtchkNumber().equals(etCheckNumber.getText().toString()) && !ptc.getDbcAddress().equals(etBankDesc.getText().toString())) {
//                    if (!TextUtils.isEmpty(ptc.getPtchkNumber()) && !TextUtils.isEmpty(ptc.getDbcCode())) {
//                        if (ptc.getPtchkNumber().equals(etCheckNumber.getText().toString()) && ptc.getDbcAddress().equals(etBankDesc.getText().toString())) {
//                            showMessagePrompt("E-receipt", "Bank details is already exist!");
//                            return;
//
//                        }
//                    }
//                }
//
//            } else {
//                Log.i("position", "positions: " + position);
//                if (!TextUtils.isEmpty(ptc.getPtchkNumber()) && !TextUtils.isEmpty(ptc.getDbcCode())) {
//                    if (ptc.getPtchkNumber().equals(etCheckNumber.getText().toString()) && ptc.getDbcAddress().equals(etBankDesc.getText().toString())) {
//                        showMessagePrompt("E-receipt", "Bank details is already exist!");
//                        return;
//
//                    }
//                }
//            }
//
//        }
        for (int i = 0; i < fragmentTransaction.mPaymentCollection.size(); i++) {
            try {
                PaidTypeCheck paidTypeCheck = (PaidTypeCheck) fragmentTransaction.mPaymentCollection.get(i);
                if (paidTypeCheck.getPtchkNumber().equals(etCheckNumber.getText().toString()) &&
                        paidTypeCheck.getDbcAddress().equals(etBankDesc.getText().toString()) &&
                        position != i) {
                    showMessagePrompt("E-receipt", "Bank details is already exist!");
                    return;
                }

            } catch (ClassCastException ex) {
//                Do nothing
            }
        }


//        if (NetworkUtils.isConnected(getActivity()) && !fragmentTransaction.mTitle.equals("Update Deposit")) {
//
//            ServiceInterface si = BaseRestClient.getClient2();
//            pbLoad.setVisibility(View.VISIBLE);
//            Observable<List<String>> call = si.hasCheck(spnBankCode.getText().toString(), etCheckNumber.getText().toString());
//            call.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<List<String>>() {
//                        @Override
//                        public void onCompleted() {
//                            Log.e("COMPLETE", "----- COMPLETE");
//                            pbLoad.setVisibility(View.GONE);
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            Log.e("ERRRROOOOOOR", "----- " + e.toString());
//                            pbLoad.setVisibility(View.GONE);
//                            hasDuplicate(diag, total);// base only on the api response
//
//
//                        }
//
//
//                        @Override
//                        public void onNext(List<String> response) {
//                            hasDuplicate(response, diag, total);
//
//                        }
//                    });
//
//
//        } else {

        model.setDbcCode(bankselection);
        model.setDbcAddress(etBankDesc.getText().toString());
        model.setPtchkNumber(etCheckNumber.getText().toString());
        Log.i("--------", "ian---- " + checkDate);
        model.setPtchkDate(checkDate);
        model.setPtchkAmount(total);

        Log.i("--------", "ian---- save" + model.getPtchkDate());

        if (!list.contains(model)) {
            list.add(model);
            fragmentTransaction.mPaymentCollection.add(model);
        }

//            for (PaidTypeCheck paidTypeCheck : list) {
//                if (paidTypeCheck.getPtchkNumber().equals(model.getPtchkNumber())
//                        && paidTypeCheck.getDbcAddress().equals(model.getDbcAddress())) {
//                    list.add(model);
//                }
//            }


//            revenueCollection.setPaidTypeCheck(list);
//            revenueCollection.setCheckFlag("Y");

        diag.dismiss();


//        }


    }

    private void hasDuplicate(List<String> response, AlertDialog diag, double total) {

        if (response.size() != 0) {
            showMessagePrompt("E-receipt", "Bank details already exist!");
            return;
        }
        model.setDbcCode(bankselection);
        model.setDbcAddress(etBankDesc.getText().toString());
        model.setPtchkNumber(etCheckNumber.getText().toString());
        Log.i("--------", "ian---- " + checkDate);
        model.setPtchkDate(checkDate);
        model.setPtchkAmount(total);
        Log.i("--------", "ian---- save" + model.getPtchkDate());

        if (!list.contains(model))
            list.add(model);

//        revenueCollection.setPaidTypeCheck(list);
//        revenueCollection.setCheckFlag("Y");

        diag.dismiss();


    }

    private void hasDuplicate(AlertDialog diag, double total) {
        Log.e("HERE", "-- " + total);

        model.setDbcCode(bankselection);
        model.setDbcAddress(etBankDesc.getText().toString());
        model.setPtchkNumber(etCheckNumber.getText().toString());
        Log.i("--------", "ian---- " + checkDate);
        model.setPtchkDate(checkDate);
        model.setPtchkAmount(total);

        Log.i("--------", "ian---- save" + model.getPtchkDate());
        if (!list.contains(model)) {
            list.add(model);
            fragmentTransaction.mPaymentCollection.add(model);
        }

//        revenueCollection.setPaidTypeCheck(list);
//        revenueCollection.setCheckFlag("Y");

        parentFragment.updatePaymentMode();
        diag.dismiss();


    }


    @Override
    protected void delete() {
        list.remove(model);
        fragmentTransaction.mPaymentCollection.remove(model);
//        revenueCollection.setPaidTypeCheck(list);
//        revenueCollection.setCheckFlag("N");
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
