package com.biz.pmti.dbp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.biz.pmti.dbp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import utils.customview.MoneyEditText;

/**
 * Created by ian.blanco on 11/8/2017.
 */

public class FragmentTransactionThree extends BaseFragment {

    @BindView(R.id.etBasicTax)
    EditText mEtBasicTax;

    @BindView(R.id.etSurcharge)
    EditText mEtSurcharge;

    @BindView(R.id.etInterest)
    EditText mEtInterest;

    @BindView(R.id.etCompromise)
    EditText mEtCompromise;

    @BindView(R.id.textView15)
    TextView mTextView15;

    @BindView(R.id.etTotalAmtPaid)
    EditText mEtTotalAmtPaid;

    @BindView(R.id.etPenalties)
    MoneyEditText mEtPenalties;

    @BindView(R.id.etTotalAmtDue)
    MoneyEditText mEtTotalAmtDue;

    Unbinder unbinder;

    public static FragmentTransactionThree newInstance() {
        FragmentTransactionThree fragmentTransactionThree = new FragmentTransactionThree();
        return fragmentTransactionThree;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactionthree, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public boolean isValid() {
        return true;
//        if (mEtTotalAmtPaid.getText().toString().equals("")) {
//            dblTotalAmtPd = 0;
//        } else {
//            dblTotalAmtPd = MoneyEditText.toDouble(mEtTotalAmtPaid.getText().toString());
//        }
//
//        if (dblTotalAmtPd <= 0) {
////            showMessagePrompt(trans, getResources().getString(R.string.error_total_amount_paid_enter_amount));
//            return false;
//        }
//
//        if (dblTotalAmtPd > dblTotalAmtDue) {
////            showMessagePrompt(trans, getResources().getString(R.string.error_total_amount_paid_amount_greater_than_amount_due));
//            return false;
//        }

    }
}
