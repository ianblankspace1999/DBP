package com.biz.pmti.dbp.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.biz.pmti.dbp.R;
import com.biz.pmti.dbp.fragments.FragmentTransactionFour;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.customview.AsteriskPasswordTransformationMethod;
import utils.customview.MoneyEditText2;

/**
 * Created by pmti-04 on 11/12/2016.
 */

public class DialogPaymentDebit2 extends DialogFragment {
    @BindView(R.id.xBtn)
    ImageView mXBtn;

    @BindView(R.id.l1)
    LinearLayout mL1;

    @BindView(R.id.tPayment)
    TextView mTPayment;

    @BindView(R.id.et_debit_amount)
    MoneyEditText2 mEtDebitAmount;

    @BindView(R.id.tBankName)
    TextView mTBankName;

    @BindView(R.id.etBankName)
    EditText mEtBankName;

    @BindView(R.id.textView10)
    TextView mTextView10;

    @BindView(R.id.etCardNumber)
    EditText etCardNumber;

    @BindView(R.id.etAccepted)
    ImageView mEtAccepted;

    @BindView(R.id.textView8)
    TextView mTextView8;

    @BindView(R.id.etSecurityCode)
    EditText mEtSecurityCode;

    @BindView(R.id.btnYes)
    Button mBtnYes;

    @BindView(R.id.btnNo)
    Button mBtnNo;
    
    private FragmentTransactionFour parentFragment;


    private OnButtonClickListener mOnPositiveClickListener;
    private OnButtonClickListener mOnNegativeClickListener;

    public static DialogPaymentDebit2 newInstance() {
        DialogPaymentDebit2 dialogPaymentDebit2 = new DialogPaymentDebit2();
        return dialogPaymentDebit2;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        parentFragment = (FragmentTransactionFour) getParentFragment();
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_debit, container, false);
        ButterKnife.bind(this, view);

        etCardNumber.setTransformationMethod(new AsteriskPasswordTransformationMethod());

        return view;
    }

    @OnClick(R.id.btnYes)
    public void xBtnYes() {
        if (mOnPositiveClickListener != null) {
            mOnPositiveClickListener.onButtonClick();
        }
        dismiss();
    }

    @OnClick(R.id.btnNo)
    public void xBtnNo() {
        if (mOnNegativeClickListener != null) {
            mOnNegativeClickListener.onButtonClick();
        }
        dismiss();
    }

    @OnClick(R.id.xBtn)
    public void xBtnOnClick() {
        parentFragment.onCancel();
        dismiss();
    }

    public void setPositiveButton(String text, OnButtonClickListener onButtonClickListener) {
        mOnPositiveClickListener = onButtonClickListener;
    }

    public void setNegativeButton(String text, OnButtonClickListener onButtonClickListener) {
        mOnNegativeClickListener = onButtonClickListener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public interface OnButtonClickListener {
        public void onButtonClick();
    }

}
