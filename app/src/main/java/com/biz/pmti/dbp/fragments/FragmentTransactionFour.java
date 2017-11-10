package com.biz.pmti.dbp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.biz.pmti.dbp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ian.blanco on 11/8/2017.
 */

public class FragmentTransactionFour extends BaseFragment {


    @BindView(R.id.textView16)
    TextView mTextView16;

    @BindView(R.id.spn_mannerofpayment)
    Spinner mSpnMannerofpayment;

    @BindView(R.id.spn_typeofpayment)
    Spinner mSpnTypeofpayment;

    @BindView(R.id.spn_modeofpayment)
    Spinner mSpnModeofpayment;

    @BindView(R.id.paymentcontainer)
    LinearLayout mPaymentcontainer;

    Unbinder unbinder;

    public static FragmentTransactionFour newInstance() {
        FragmentTransactionFour fragmentTransactionFour = new FragmentTransactionFour();
        return fragmentTransactionFour;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactionfour, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
