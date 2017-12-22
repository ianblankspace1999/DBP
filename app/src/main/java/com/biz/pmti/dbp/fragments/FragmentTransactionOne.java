package com.biz.pmti.dbp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.biz.pmti.dbp.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import utils.DBPBase;
import utils.customview.TINEditText;

/**
 * Created by ian.blanco on 11/7/2017.
 */

public class FragmentTransactionOne extends BaseFragment {


    @BindView(R.id.searchBtn)
    Button mSearchBtn;

    @BindView(R.id.et_Name)
    EditText mEtName;

    @BindView(R.id.et_Address)
    EditText mEtAddress;

    @BindView(R.id.et_TIN)
    TINEditText mEtTIN;

    Unbinder mUnbinder;



    private String mPrevTin = "";

    private String mStrName = "";
    private String mStrAddress = "";

    public static FragmentTransactionOne newInstance() {
        FragmentTransactionOne fragmentTransactionOne = new FragmentTransactionOne();

        return fragmentTransactionOne;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactionone, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mEtName.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mEtAddress.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        return view;
    }


    private TextWatcher provideTextWatcher(final EditText editText) {

        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                switch (editText.getId()) {

                    case R.id.et_Name:
                        editText.setText(s.toString().replaceAll("  ", " "));
                        editText.setSelection(s.toString().replaceAll("  ", " ").length());

                        break;

                    case R.id.et_Address:
                        editText.setText(s.toString().replaceAll("  ", " "));
                        editText.setSelection(s.toString().replaceAll("  ", " ").length());

                        break;

                    case R.id.et_TIN:
                        mEtName.setText("");
                        mEtAddress.setText("");
                        enableFields();
                        break;
                }
            }
        };

    }

    public void enableFields() {
        mPrevTin = "";
        mEtName.setEnabled(false);
        mEtAddress.setEnabled(false);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @OnClick(R.id.searchBtn)
    public void onViewClicked() {
        Log.i("tag","tag");
        if (mEtTIN.getText().toString().isEmpty()) {
            Log.i("tag","tagss");
//            showMessagePrompt(transType, "PLEASE ENTER TIN-BRANCH CODE");
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "PLEASE ENTER TIN-BRANCH CODE");
            // Toast.makeText(getActivity(), "PLEASE ENTER TIN-BRANCH CODE", Toast.LENGTH_SHORT).show();
            return;
        }

        String tin_number = mEtTIN.getTIN() + mEtTIN.getBranchCode();
        if (tin_number.length() < 9) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Invalid TIN number!");
            return;
        } else if (tin_number.equals("00000000000000")) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Invalid TIN number!");
            return;
        } else if (mEtTIN.getTIN().toString().equals("000000000")) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Invalid TIN number!");
            return;
        } else {
            mEtName.setEnabled(true);
            mEtAddress.setEnabled(true);

        }


    }

    @Override
    public boolean isValid() {
        if (mEtTIN.getText().toString().isEmpty()) {
//            showMessagePrompt(transType, "PLEASE ENTER TIN-BRANCH CODE");
            // Toast.makeText(getActivity(), "PLEASE ENTER TIN-BRANCH CODE", Toast.LENGTH_SHORT).show();
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "PLEASE ENTER TIN-BRANCH CODE");
            return false;
        }

        String tinnumber = mEtTIN.getTIN() + mEtTIN.getBranchCode();
        if (tinnumber.length() < 9) {
//            showMessagePrompt(transType, "Invalid TIN number!");
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "PLEASE ENTER TIN-BRANCH CODE");
            return false;
        }
        if (tinnumber.equals("00000000000000")) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Invalid TIN number!");
            return false;
        }

        if (mEtTIN.getTIN().toString().equals("000000000")) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Invalid TIN number!");
            return false;
        }

//        if (!prevTin.equals(tinnumber)) {
//            if (tin.getText().toString().length() == 17) {
//                try {
//                    searchTin();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                showMessagePrompt(transType, "INVALID TIN-BRANCH CODE FORMAT");
//                DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "PLEASE ENTER TIN-BRANCH CODE");
//                // Toast.makeText(getActivity(), "INVALID TIN-BRANCH CODE FORMAT", Toast.LENGTH_SHORT).show();
//            }
//            return false;
//        }


        if (mEtTIN.getTIN() == null || mEtTIN.getBranchCode() == null) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Invalid TIN!");
            return false;
        }

        if (mEtTIN.getTIN().length() != 9) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Invalid TIN!");
            return false;
        }

        if (mEtTIN.getBranchCode().length() != 5) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Invalid BranchCode!");
            return false;
        }

        if (mEtName.getText().toString().trim().length() <= 0) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Please enter Registered Name!");
            return false;
        }

        //address

        if (mEtAddress.getText().toString().trim().length() == 0) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Please enter Address!");
            return false;
        }

        if (tinnumber.equals("00000000000000")) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Invalid TIN number!");
            return false;
        }

        if (mEtTIN.getTIN().toString().equals("000000000")) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Invalid TIN number!");
            return false;
        }

        parent.mTransactionModel.setTinNumber(mEtTIN.getText().toString());
        parent.mTransactionModel.setName(mEtName.getText().toString());
        parent.mTransactionModel.setAddress(mEtAddress.getText().toString());

        return true;
    }
}
