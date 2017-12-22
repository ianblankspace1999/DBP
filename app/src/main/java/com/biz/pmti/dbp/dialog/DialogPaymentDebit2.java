package com.biz.pmti.dbp.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.biz.pmti.dbp.models.CardDetails;
import com.cloudpos.jniinterface.MSRInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.Util;
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


    private static final int MSG_ID_SHOW_MESSAGE = 0;
    private Handler handler;
    private CardDetails cardDetails;

    private OnButtonClickListener mOnPositiveClickListener;
    private OnButtonClickListener mOnNegativeClickListener;


    public DialogPaymentDebit2() {
        cardDetails = new CardDetails();
        cardDetails.setPan("xxx");
        cardDetails.setCardHolderName("Card Holder Name");
        cardDetails.setExpiryDate("0000");
        cardDetails.setServiceCode("12345");
    }

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler(new Handler.Callback() {

            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_ID_SHOW_MESSAGE:
                        displayCardDetails(msg.obj.toString());
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        executeMSReader();

    }

    private void displayCardDetails(String message) {
            mEtBankName.setText(cardDetails.getCardHolderName());
            etCardNumber.setText(cardDetails.getPan());

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

    private void executeMSReader() {
        new Thread() {

            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(5000);
                        getParentFragment().getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                boolean retry = true;
                                while (retry) {
                                    retry = false;
                                    int MSRopenresult = MSRInterface.open();
                                    if (MSRopenresult >= 0) {
                                        try {
                                            MSRInterface.waitForSwipe(50000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if (MSRInterface.eventID == MSRInterface.CONTACTLESS_CARD_EVENT_FOUND_CARD) {
                                            if (!parseTrackData())
                                                retry = true;
                                            else
                                                handler.obtainMessage(MSG_ID_SHOW_MESSAGE, "Magnetic Stripe Card Details Reading Successful").sendToTarget();
                                        } else
                                            retry = true;
                                        MSRInterface.close();
                                    } else
                                        handler.obtainMessage(MSG_ID_SHOW_MESSAGE, "Code:" + Integer.toHexString(MSRopenresult) + " (Device Open failed)").sendToTarget();
                                }
                            }
                        });

                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private boolean parseTrackData() {
        String Info = "Please Swipe Again!";
        int result, trackNo = 0;
        result = MSRInterface.getTrackError(trackNo);
        if (result < 0) {
            handler.obtainMessage(MSG_ID_SHOW_MESSAGE, Info).sendToTarget();
            return false;
        }
        result = MSRInterface.getTrackDataLength(trackNo);
        if (result < 0) {
            handler.obtainMessage(MSG_ID_SHOW_MESSAGE, Info).sendToTarget();
            return false;
        }
        byte[] stripInfo = new byte[result];
        result = MSRInterface.getTrackData(trackNo, stripInfo, stripInfo.length);
        if (result < 0) {
            handler.obtainMessage(MSG_ID_SHOW_MESSAGE, Info).sendToTarget();
            return false;
        }
        cardDetails.setPan(Util.getStripInfoPAN(stripInfo));
        cardDetails.setCardHolderName(Util.getStripInfoDetails(stripInfo, 1, 2));
        cardDetails.setExpiryDate(Util.getStripInfoDetails(stripInfo, 2, -1, 0, 4));
        cardDetails.setServiceCode(Util.getStripInfoDetails(stripInfo, 2, -1, 5, 3));
        return true;
    }

}
