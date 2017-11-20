package com.biz.pmti.dbp.dialog;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.biz.pmti.dbp.R;
import com.biz.pmti.dbp.activities.MainActivity;
import com.biz.pmti.dbp.fragments.FragmentTransactionFour;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypeMo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import utils.AmountFormatter;
import utils.DBPBase;
import utils.customview.MoneyEditText;
import utils.customview.NumberTextWatcherForThousand;

/**
 * Created by juanalfonzocarlos.le on 3/1/2016.
 */
public class DialogPaymentMoneyOrder extends SuperPaymentDialog {


    private static final String TAG = "DialogPaymentMoneyOrder";


    @BindView(R.id.et_order_number)
    EditText etOrderNumber;
    @BindView(R.id.tv_order_date)
    TextView tvOrderDate;
    @BindView(R.id.et_order_amount)
    EditText etOrderAmount;
    Unbinder unbinder;


    private List<PaidTypeMo> list = null;
    private PaidTypeMo model = null;

    private Calendar moCalendar = Calendar.getInstance();
    private long moDate = 0;
    double total = 0;
    private MainActivity transaction;
    private FragmentTransactionFour parent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        parent = (vpPaymentDetail3) getParentFragment();

        transaction = (MainActivity) parent.getActivity();
        if (savedInstanceState != null)
            moDate = savedInstanceState.getLong("moDate");

        setup("Money Order", R.layout.dialog_payment_moneyorder);

//        list = revenueCollection.getPaidTypeMo();
        if (list == null)
            list = new ArrayList<>();
        if (isEditMode) {
//            model = list.get(position);
            model = (PaidTypeMo) fragmentTransaction.mPaymentCollection.get(position);
            etOrderNumber.setText(model.getPtmoNo());
            moDate = model.getPtmoDate();
            //tvOrderDate.setText(AlfonzoUtils.formatDate(model.getPtmoDate()));
            etOrderAmount.setText(MoneyEditText.format(model.getPtmoAmount()));
            //paymentDate = model.getPtmoDate();
        } else
            model = new PaidTypeMo();

        model.setPmodeCode("MONEY ORDER");

        if (moDate != 0) {
            moCalendar.setTimeInMillis(moDate);
            tvOrderDate.setText(DBPBase.formatDate(moCalendar.getTimeInMillis()));
        }

        etOrderAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                double doubleValue = 0;
                if (hasFocus == false) {
                    try {
                        DecimalFormat formatter = new DecimalFormat("#,###,###.00");
                        doubleValue = Double.parseDouble(etOrderAmount.getText().toString().replace(",", "").replace(AmountFormatter.PESO_CURRENCY, ""));
                        etOrderAmount.setText(AmountFormatter.PESO_CURRENCY + formatter.format(doubleValue));

                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });

        etOrderAmount.addTextChangedListener(new NumberTextWatcherForThousand(etOrderAmount));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("moDate", moDate);
    }

    @OnClick(R.id.tv_order_date)
    void showDate() {
        showDatePicker(moCalendar);
    }

    @Override
    protected void submit(AlertDialog diag) {

        if (!etOrderAmount.getText().toString().equals("")) {
            total = Double.parseDouble(etOrderAmount.getText().toString().replace(",", "").replace(AmountFormatter.PESO_CURRENCY, ""));
        }

        if (etOrderNumber.getText().length() <= 0 || tvOrderDate.getText().length() <= 0 || total <= 0) {
            showToast("Please fill all fields!");
            return;
        }

//        for (PaidTypeMo ptm : list) {
//            Log.e("PtmNumber", ptm.getPtmoNo());
//            if (ptm.getPtmoNo().equals(etOrderNumber.getText().toString())) {
//                showMessagePrompt("E-receipt", "MO Number is already exist!");
//                return;
//
//            }
//        }

        for (int i = 0; i < fragmentTransaction.mPaymentCollection.size(); i++) {
            try {
                PaidTypeMo ptm = (PaidTypeMo) fragmentTransaction.mPaymentCollection.get(i);

                if (position == i) {

                    if (!ptm.getPtmoNo().equals(etOrderNumber.getText().toString())) {
                        if (!TextUtils.isEmpty(ptm.getPtmoNo())) {
                            if (ptm.getPtmoNo().equals(etOrderNumber.getText().toString())) {
                                showMessagePrompt("E-receipt", "MO Number is already exist!");
                                return;

                            }
                        }
                    }


                } else {
                    if (!TextUtils.isEmpty(ptm.getPtmoNo())) {
                        if (ptm.getPtmoNo().equals(etOrderNumber.getText().toString())) {
                            showMessagePrompt("E-receipt", "Reference Number is already exist!");
                            return;

                        }
                    }
                }
            } catch (ClassCastException ex) {
//                Do nothing
            }
        }
//        if (NetworkUtils.isConnected(getActivity()) && !transaction.mTitle.equals("Update Deposit")) {
//            ServiceInterface si = BaseRestClient.getClient2();
//            Observable<List<String>> call = si.hasMo(etOrderNumber.getText().toString());
//            call.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<List<String>>() {
//                        @Override
//                        public void onCompleted() {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            Log.i("error", "ERROR:" + e.toString());
//                            hasDuplicate(diag, total);
//                        }
//
//                        @Override
//                        public void onNext(List<String> response) {
//                            hasDuplicate(response, diag, total);
//                        }
//                    });
//            return;
//        }

        model.setPtmoNo(etOrderNumber.getText().toString());
        model.setPtmoDate(moDate);
        model.setPtmoAmount(total);

//        Log.e("adasdasdasdsa", "asdasd" + transaction.getAccountSession().isUpdateDeposit());
//        if (transaction.getAccountSession().isUpdateDeposit() == true) {
////            Log.e("pasok", "asdasd" + transaction.getAccountSession().isUpdateDeposit());
////            transaction.revenueCollection.setPaidTypeCash(null);
////            transaction.revenueCollection.setPaidTypeCheck(null);
//            if (!list.contains(model)) {
//                list.add(model);
//                transaction.mPaymentCollection.add(model);
//            }
////            revenueCollection.setPaidTypeMo(list);
////
//            parent.updatePaymentMode();
//
//        } else {

            Log.e("di pasok", "asdasd");
            if (!list.contains(model)) {
                list.add(model);
                transaction.mPaymentCollection.add(model);
            }
//            revenueCollection.setPaidTypeMo(list);
//        }

//        revenueCollection.setMoFlag("Y");
        diag.dismiss();
    }


    @Override
    protected void delete() {
        list.remove(model);
        transaction.mPaymentCollection.remove(model);
//        revenueCollection.setPaidTypeMo(list);
//        revenueCollection.setMoFlag("N");
    }


    @Override
    protected void onDateChanged(Calendar c) {
        moDate = c.getTimeInMillis();
        tvOrderDate.setText(DBPBase.formatDate(moDate));
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
