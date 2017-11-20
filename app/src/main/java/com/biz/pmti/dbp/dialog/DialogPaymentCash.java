package com.biz.pmti.dbp.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.biz.pmti.dbp.R;
import com.biz.pmti.dbp.activities.MainActivity;
import com.biz.pmti.dbp.fragments.FragmentTransactionFour;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypeCash;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import utils.AmountFormatter;
import utils.customview.NumberTextWatcherForThousand;

/**
 * Created by juanalfonzocarlos.le on 3/1/2016.
 */
public class DialogPaymentCash extends DialogFragment {

    public static final String TAG = "DialogPaymentCash";
    @BindView(R.id.et_cash_amount)
    EditText etAmount;
    Unbinder unbinder;

    private MainActivity transaction;
    private FragmentTransactionFour parent;


    private double amount;


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        parent = (FragmentTransactionFour) getParentFragment();

//        transaction = (FragmentTransaction) parent.getParentFragment();

        View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_payment_cash, null);
        ButterKnife.bind(this, v);

//        try{
//            amount = getArguments().getDouble(PaymentDetail.AMOUNT);
//            Log.e("JAC",""+amount);
//            etAmount.setText(MoneyEditText2.format(amount));
//           // etAmount.setText(MoneyEditText.format(amount));
//        }catch (NullPointerException e){
//            Log.e(TAG, "TAG "+PaymentDetail.AMOUNT+" in getArgs is null");
//        }


//        try{
//            etAmount.setText(MoneyEditText.format(transaction.revenueCollection.getPaidTypeCash().getPtcashAmount()));
//        }catch (NullPointerException e){
//            Log.e(TAG, "FROM MODEL : NULL");
//
//        }

        etAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                double doubleValue = 0;
                if (hasFocus == false) {
                    try {
                        DecimalFormat formatter = new DecimalFormat("#,###,###.00");
                        doubleValue = Double.parseDouble(etAmount.getText().toString().replace(",", "").replace(AmountFormatter.PESO_CURRENCY, ""));
                        etAmount.setText(AmountFormatter.PESO_CURRENCY + formatter.format(doubleValue));

                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });


        etAmount.addTextChangedListener(new NumberTextWatcherForThousand(etAmount));


        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert
                .setCancelable(false)
                .setView(v)
                .setTitle("Cash")

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        parent.onCancel();
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                double total = 0;
                if (!etAmount.getText().toString().equals("")) {
                    total = Double.parseDouble(etAmount.getText().toString().replace(",", "").replace(AmountFormatter.PESO_CURRENCY, ""));
                }

                amount = total;

                //amount = ((MoneyEditText) etAmount).getDouble();

                if (amount > 0)
                    addValue(amount);
                else
                    delete();

//                        transaction.revenueCollection.setCashFlag("Y");
            }
        });

        setCancelable(false);
        AlertDialog alertDialog = alert.create();

        if (getArguments() != null)
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    delete();
                }
            });

        return alertDialog;
    }


    void delete() {
        for (Object obj : transaction.mPaymentCollection) {
            if (obj instanceof PaidTypeCash) {
                transaction.mPaymentCollection.remove(obj);
                break;
            }
        }

//        transaction.revenueCollection.setCashFlag("N");
//        transaction.revenueCollection.setPaidTypeCash(null);
        parent.updatePaymentMode();
    }

    void addValue(double amount) {
//        if(transaction.getAccountSession().isUpdateDeposit() == true){
//            transaction.revenueCollection.setPaidTypeMo(null);
//            transaction.revenueCollection.setPaidTypeCheck(null);
//        }

        for (Object obj : transaction.mPaymentCollection) {
            if (obj instanceof PaidTypeCash) {
                transaction.mPaymentCollection.remove(obj);
                break;
            }
        }

//        for (Object obj : transaction.mPaymentCollection) {
//            if (obj instanceof PaidTypeMo) {
//                transaction.mPaymentCollection.remove(obj);
//                break;
//            }
//        }
        PaidTypeCash cash = new PaidTypeCash();
        cash.setPmodeCode("CASH");
        cash.setPtcashAmount(amount);
        transaction.mPaymentCollection.add(cash);
//        transaction.revenueCollection.setPaidTypeCash(cash);
        parent.updatePaymentMode();
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
