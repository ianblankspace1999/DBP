package com.biz.pmti.dbp.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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


public class DialogPaymentMoneyOrder extends BaseDialogFragment {


    private static final String TAG = "DialogPaymentMoneyOrder";

    @BindView(R.id.et_order_number)
    EditText etOrderNumber;

    @BindView(R.id.tv_order_date)
    TextView tvOrderDate;

    @BindView(R.id.textView14)
    TextView mTextView14;

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
        parent = (FragmentTransactionFour) getParentFragment();

        transaction = (MainActivity) parent.getActivity();
        if (savedInstanceState != null)
            moDate = savedInstanceState.getLong("moDate");

        setup("Money Order", R.layout.dialog_payment_moneyorder);
//        list = revenueCollection.getPaidTypeMo();
        if (list == null)
            list = new ArrayList<>();


            model = new PaidTypeMo();

        model.setPmodeCode("MONEY ORDER");

        if (moDate != 0) {
            moCalendar.setTimeInMillis(moDate);
            tvOrderDate.setText(DBPBase.formatDate(moCalendar.getTimeInMillis()));
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("moDate", moDate);
    }

    @OnClick(R.id.tv_order_date)
    void showDate() {
        DialogFragmentUtil.showDatePicker(moCalendar, getContext());
    }

    @Override
    protected void submit(AlertDialog diag) {


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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_payment_moneyorder, null);
        unbinder = ButterKnife.bind(this, view);

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
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
