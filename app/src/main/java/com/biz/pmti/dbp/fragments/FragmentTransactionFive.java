package com.biz.pmti.dbp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.biz.pmti.dbp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ian.blanco on 11/8/2017.
 */

public class FragmentTransactionFive extends BaseFragment {

    @BindView(R.id.etRemarks)
    EditText mEtRemarks;

    Unbinder unbinder;

    public static FragmentTransactionFive newInstance() {
        FragmentTransactionFive fragmentTransactionFive = new FragmentTransactionFive();
        return fragmentTransactionFive;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactionfive, container, false);
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
        parent.mTransactionModel.setRemakrs(mEtRemarks.getText().toString());
        return true;
    }
}
