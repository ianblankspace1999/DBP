package com.biz.pmti.dbp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.biz.pmti.dbp.R;
import com.biz.pmti.dbp.activities.MainActivity;
import com.biz.pmti.dbp.dialog.DialogErro;
import com.biz.pmti.dbp.dialog.DialogPaymentCash;
import com.biz.pmti.dbp.dialog.DialogPaymentCheck;
import com.biz.pmti.dbp.dialog.DialogPaymentMoneyOrder;
import com.biz.pmti.dbp.models.Payment;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypeCash;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypeCheck;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypeCredit;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypeDc;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypeDebit;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypeMo;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypeOther;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypePera;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypeSaro;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypeTdm;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypeTra;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypeTsac;
import com.biz.pmti.dbp.models.modeofpayments.PaidTypeTsdm;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import butterknife.Unbinder;
import lombok.core.Main;

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
    private MainActivity parent;

    private ArrayList<String> mMannerOfPaymentStr = new ArrayList<>();
    private ArrayList<String> mTypeOfPaymentStr = new ArrayList<>();
    private ArrayList<String> mModeOfPaymentStr = new ArrayList<>();

    public static FragmentTransactionFour newInstance() {
        FragmentTransactionFour fragmentTransactionFour = new FragmentTransactionFour();
        return fragmentTransactionFour;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parent = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_transactionfour, container, false);
        unbinder = ButterKnife.bind(this, view);
        initUI();
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnItemSelected(R.id.spn_mannerofpayment)
    void onMannerOfPayment(int position) {
        if (position == 0) {
            mSpnTypeofpayment.setEnabled(false);
            mSpnTypeofpayment.setSelection(0);
            return;
        }

        mSpnTypeofpayment.setEnabled(true);
//        if (position >= 0)
//            setMannerOfPayment(mannerOfPayment.get(position));
    }

    @OnItemSelected(R.id.spn_modeofpayment)
    void onModeOfPayment(int position) {
        if (position == 0)
            return;

//        addPayment();
//        spn_modeOfPayment.setSelection(0);
    }

    @OnItemSelected(R.id.spn_typeofpayment)
    void onTypeOfPayment(int position) {
        if (position == 0) {
            mSpnModeofpayment.setEnabled(false);
            mSpnModeofpayment.setSelection(0);
            return;
        }
        mSpnModeofpayment.setEnabled(true);

//        if (position >= 0)
//            setTypeOfPayment(typeOfPayment.get(position));
    }

    public void onCancel() {
        mSpnModeofpayment.performClick();
    }

    private void initUI() {
        setDummyData();
        mSpnTypeofpayment.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, mTypeOfPaymentStr));
        mSpnMannerofpayment.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, mMannerOfPaymentStr));
        mSpnModeofpayment.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, mModeOfPaymentStr));
    }


    List<Payment> payments;

    public void updatePaymentMode() {

        payments = new ArrayList<>();
        mPaymentcontainer.removeAllViews();


        for (int i = 0; i < parent.mPaymentCollection.size(); i++) {
            Object obj = parent.mPaymentCollection.get(i);

            if (obj instanceof PaidTypeCash) {
                PaidTypeCash paidTypeCash = (PaidTypeCash) obj;
                payments.add(new Payment("CASH", paidTypeCash.getPtcashAmount(), ""));
            }

            if (obj instanceof PaidTypeCheck) {
                PaidTypeCheck paidTypeCheck = (PaidTypeCheck) obj;
                Log.i("etoooo", "ptc:orig " + obj.toString());
                payments.add(new Payment("CHECK", paidTypeCheck.getPtchkAmount(), paidTypeCheck.getPtchkNumber(), i));
            }

            if (obj instanceof PaidTypeDebit) {
                PaidTypeDebit paidTypeDebit = (PaidTypeDebit) obj;
                payments.add(new Payment("DEBIT", paidTypeDebit.getPtdebitAmount(), ""));
            }
            if (obj instanceof PaidTypeMo) {
                PaidTypeMo paidTypeMo = (PaidTypeMo) obj;
                payments.add(new Payment("MONEY ORDER", paidTypeMo.getPtmoAmount(), paidTypeMo.getPtmoNo(), i));
            }

        }


        for (Payment payment : payments) {
            mPaymentcontainer.addView(payment.getView(getActivity()));
        }

    }


    void addPayment() {
//        String mode = modeOfPayment.get(spn_modeOfPayment.getSelectedItemPosition());
//        Log.e("JAC mode", "" + mode);
//        if (mode.equalsIgnoreCase("cash")) {
//            new DialogPaymentCash().show(getChildFragmentManager(), "DIALOG");
//        } else if (mode.equalsIgnoreCase("check")) {
//
//                    Bundle args = new Bundle();
//                    args.putString("title", "LIST OF COLLECTION");
//                    args.putString("message", "No Check list for this LC number.");
//                    DialogFragment newFragment = new DialogErro();
//                    newFragment.setArguments(args);
//                    newFragment.show(getChildFragmentManager(), "dialog");
//
//
//
//
//        } else if (mode.equalsIgnoreCase("debit")) {
//            DialogPaymentDebit2 dialogPaymentDebit2 = DialogPaymentDebit2.newInstance();
//            dialogPaymentDebit2.setPositiveButton("BACK", new DialogPaymentDebit2.OnButtonClickListener() {
//                @Override
//                public void onButtonClick() {
//                    onCancel();
//                }
//            });
//            dialogPaymentDebit2.setNegativeButton("NEXT", new DialogPaymentDebit2.OnButtonClickListener() {
//                @Override
//                public void onButtonClick() {
//                    Log.e("JAC", "click");
//                    DialogPaymentDebitNext dialogPaymentDebitNext = DialogPaymentDebitNext.newInstance();
//                    if (!dialogPaymentDebitNext.isAdded()) {
//                        dialogPaymentDebitNext.show(getChildFragmentManager(), "DIALOG");
//                    }
//                }
//            });
//
//            if (!dialogPaymentDebit2.isAdded()) {
//                dialogPaymentDebit2.show(getChildFragmentManager(), "DIALOG");
//            }
//            //new DialogPaymentDebit().show(getChildFragmentManager(), "DIALOG");
//        } else if (mode.equalsIgnoreCase("money order") || mode.equalsIgnoreCase("mo")) {
//
//                    Bundle args = new Bundle();
//                    args.putString("title", "LIST OF COLLECTION");
//                    args.putString("message", "No Money Order list for this LC number.");
//                    DialogFragment newFragment = new DialogErro();
//                    newFragment.setArguments(args);
//                    newFragment.show(getChildFragmentManager(), "dialog");
//
//        }

    }

    private void setDummyData() {

        mMannerOfPaymentStr.add("-SELECT MANNER OF PAYMENT-");
        mMannerOfPaymentStr.add("PER AUDIT/INVESTIGATION");
        mMannerOfPaymentStr.add("PER RETURN");
        mMannerOfPaymentStr.add("ACCOUNTS RECEIVABLE");


        mTypeOfPaymentStr.add("-SELECT TYPE OF PAYMENT-");
        mTypeOfPaymentStr.add("PARTIAL");
        mTypeOfPaymentStr.add("FULL");
        mTypeOfPaymentStr.add("INSTALLMENT");

        mTypeOfPaymentStr.add("-SELECT MODE OF PAYMENT-");
        mModeOfPaymentStr.add("CASH");
        mModeOfPaymentStr.add("CHECK");
        mModeOfPaymentStr.add("DEBIT");
        mModeOfPaymentStr.add("MO");
    }

}
