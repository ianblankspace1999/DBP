package com.biz.pmti.dbp.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
public class DialogPaymentCash extends BaseDialogFragment {

    public static final String TAG = "DialogPaymentCash";
    @BindView(R.id.et_cash_amount)
    EditText etAmount;
    Unbinder unbinder;



    private double amount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup("Cash", R.layout.dialog_payment_cash);
        initUI();
    }

    @Override
    protected void submit(AlertDialog diag) {
        super.submit(diag);

        double total = 0;
        if (!etAmount.getText().toString().equals("")) {
            total = Double.parseDouble(etAmount.getText().toString().replace(",", "").replace(AmountFormatter.PESO_CURRENCY, ""));
        }

        amount = total;

        //amount = ((MoneyEditText) etAmount).getDouble();

//        if (amount > 0)
            addValue(amount);
//        else
//            delete();

        diag.dismiss();
    }

    private void initUI() {
        etAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                double doubleValue = 0;
                if (hasFocus == false) {
                    try {
                        DecimalFormat formatter = new DecimalFormat("#,###,###.00");
                        Log.i("result", "result: " + etAmount.getText().toString().replace(",", "").replace(AmountFormatter.PESO_CURRENCY, ""));
                        doubleValue = Double.parseDouble(etAmount.getText().toString().replace(",", "").replace(AmountFormatter.PESO_CURRENCY, ""));
                        etAmount.setText(AmountFormatter.PESO_CURRENCY + formatter.format(doubleValue));

                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });


        etAmount.addTextChangedListener(new NumberTextWatcherForThousand(etAmount));


    }

    protected void delete() {
        for (Object obj : transaction.mPaymentCollection) {
            if (obj instanceof PaidTypeCash) {
                transaction.mPaymentCollection.remove(obj);
                break;
            }
        }

//        transaction.revenueCollection.setCashFlag("N");
//        transaction.revenueCollection.setPaidTypeCash(null);
        parentFragment.updatePaymentMode();
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
        parentFragment.updatePaymentMode();
    }


}
