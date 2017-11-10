package com.biz.pmti.dbp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.biz.pmti.dbp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ian.blanco on 11/9/2017.
 */

public class FragmentTransactionSix extends BaseFragment {

    @BindView(R.id.tvLabelManualNo)
    TextView mTvLabelManualNo;

    @BindView(R.id.tvValueManualNo)
    TextView mTvValueManualNo;

    @BindView(R.id.tvLabelCollectionDate)
    TextView mTvLabelCollectionDate;

    @BindView(R.id.tvValueCollectionDate)
    TextView mTvValueCollectionDate;

    @BindView(R.id.linearManual)
    LinearLayout mLinearManual;

    @BindView(R.id.tvLabelTIN)
    TextView mTvLabelTIN;

    @BindView(R.id.tvValueTIN)
    TextView mTvValueTIN;

    @BindView(R.id.tvName)
    TextView mTvName;

    @BindView(R.id.tvAddress)
    TextView mTvAddress;

    @BindView(R.id.tvLabelBfns)
    TextView mTvLabelBfns;

    @BindView(R.id.tvLabelTaxType)
    TextView mTvLabelTaxType;

    @BindView(R.id.tvValueTaxType)
    TextView mTvValueTaxType;

    @BindView(R.id.tvLabelPeriodCovered)
    TextView mTvLabelPeriodCovered;

    @BindView(R.id.tvValuePeriodCovered)
    TextView mTvValuePeriodCovered;

    @BindView(R.id.tvLabelDueDate)
    TextView mTvLabelDueDate;

    @BindView(R.id.tvValueDueDate)
    TextView mTvValueDueDate;

    @BindView(R.id.tvValueBfns)
    TextView mTvValueBfns;

    @BindView(R.id.tvValueAssessment)
    TextView mTvValueAssessment;

    @BindView(R.id.tvLabelAssessment)
    TextView mTvLabelAssessment;

    @BindView(R.id.tvValueBasicTax)
    TextView mTvValueBasicTax;

    @BindView(R.id.tvValueSurcharge)
    TextView mTvValueSurcharge;

    @BindView(R.id.tvValueInterest)
    TextView mTvValueInterest;

    @BindView(R.id.tvValueCompromise)
    TextView mTvValueCompromise;

    @BindView(R.id.tvValueTotalPenalty)
    TextView mTvValueTotalPenalty;

    @BindView(R.id.tvValueTotalAmountDue)
    TextView mTvValueTotalAmountDue;

    @BindView(R.id.tvValueTotalAmountPaid)
    TextView mTvValueTotalAmountPaid;

    @BindView(R.id.tvValueWordAmount)
    TextView mTvValueWordAmount;

    @BindView(R.id.tvValueMannerOfPay)
    TextView mTvValueMannerOfPay;

    @BindView(R.id.tvValueTypeOfPayment)
    TextView mTvValueTypeOfPayment;

    @BindView(R.id.textViewRemark)
    TextView mTextViewRemark;

    @BindView(R.id.linearRemark)
    LinearLayout mLinearRemark;

    @BindView(R.id.textViewRemarkTextValue)
    TextView mTextViewRemarkTextValue;

    @BindView(R.id.relativeRemarkValue)
    RelativeLayout mRelativeRemarkValue;

    @BindView(R.id.linearRemarkValue)
    LinearLayout mLinearRemarkValue;

    @BindView(R.id.relativeRemark)
    RelativeLayout mRelativeRemark;

    Unbinder unbinder;

    public static FragmentTransactionSix newInstance() {
        FragmentTransactionSix fragmentTransactionSix = new FragmentTransactionSix();
        return fragmentTransactionSix;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactionsix, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
