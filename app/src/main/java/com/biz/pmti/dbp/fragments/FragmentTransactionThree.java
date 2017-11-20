package com.biz.pmti.dbp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.biz.pmti.dbp.R;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import utils.AmountFormatter;
import utils.customview.MoneyEditText;
import utils.customview.MoneyEditText2;
import utils.customview.NumberTextWatcherForThousand;


/**
 * Created by ian.blanco on 11/8/2017.
 */

public class FragmentTransactionThree extends BaseFragment implements MoneyEditText.TextChangeListener{

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

    private double dblBasicTax = 0;
    private double dblSurcharge = 0;
    private double dblInterest = 0;
    private double dblCompromise = 0;
    private double dblPenalties = 0;
    private double dblTotalAmtDue = 0;
    private double dblTotalAmtPd = 0;


    public static FragmentTransactionThree newInstance() {
        FragmentTransactionThree fragmentTransactionThree = new FragmentTransactionThree();
        return fragmentTransactionThree;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactionthree, container, false);
        unbinder = ButterKnife.bind(this, view);

        initUI();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initUI() {
        mEtBasicTax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                Toast.makeText(getActivity(),
//                        ((EditText) v).getId() + " has focus - " + hasFocus,
//                        Toast.LENGTH_LONG).show();

                double doubleValue = 0;
                if (hasFocus == false) {
                    try {
                        DecimalFormat formatter = new DecimalFormat("#,###,###.00");
                        doubleValue = Double.parseDouble(mEtBasicTax.getText().toString().replace(",", "").replace(AmountFormatter.PESO_CURRENCY, ""));
                        mEtBasicTax.setText(AmountFormatter.PESO_CURRENCY + formatter.format(doubleValue));
                        computeAll();
                    } catch (NumberFormatException e) {
                        //Error
                    }
                } else {
                    computeAll();
                }
            }
        });

        mEtBasicTax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString().trim())) {
                    dblBasicTax = 0;
                }
            }
        });

        mEtBasicTax.addTextChangedListener(new NumberTextWatcherForThousand(mEtBasicTax));

        mEtSurcharge.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                double doubleValue = 0;
                if (hasFocus == false) {
                    try {
                        DecimalFormat formatter = new DecimalFormat("#,###,###.00");
                        doubleValue = Double.parseDouble(mEtSurcharge.getText().toString().replace(",", "").replace(AmountFormatter.PESO_CURRENCY, ""));
                        mEtSurcharge.setText(AmountFormatter.PESO_CURRENCY + formatter.format(doubleValue));
                        computeAll();
                    } catch (NumberFormatException e) {
                        //Error
                    }
                } else {
                    computeAll();
                }
            }
        });

        mEtSurcharge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString().trim())) {
                    dblSurcharge = 0;
                    computeAll();
                }
            }
        });
        mEtSurcharge.addTextChangedListener(new NumberTextWatcherForThousand(mEtSurcharge));



        mEtInterest.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                double doubleValue = 0;
                if (hasFocus == false) {
                    try {
                        DecimalFormat formatter = new DecimalFormat("#,###,###.00");
                        doubleValue = Double.parseDouble(mEtInterest.getText().toString().replace(",", "").replace(AmountFormatter.PESO_CURRENCY, ""));
                        mEtInterest.setText(AmountFormatter.PESO_CURRENCY + formatter.format(doubleValue));
                        computeAll();
                    } catch (NumberFormatException e) {
                        //Error
                    }
                } else {
                    computeAll();
                }
            }
        });

        mEtInterest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString().trim())) {
                    dblInterest = 0;
                    computeAll();
                }
            }
        });

        mEtInterest.addTextChangedListener(new NumberTextWatcherForThousand(mEtInterest));


        mEtCompromise.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                double doubleValue = 0;
                if (hasFocus == false) {
                    try {
                        DecimalFormat formatter = new DecimalFormat("#,###,###.00");
                        doubleValue = Double.parseDouble(mEtCompromise.getText().toString().replace(",", "").replace(AmountFormatter.PESO_CURRENCY, ""));
                        mEtCompromise.setText(AmountFormatter.PESO_CURRENCY + formatter.format(doubleValue));
                        computeAll();
                    } catch (NumberFormatException e) {
                        //Error
                    }
                } else {
                    computeAll();
                }
            }
        });

        mEtCompromise.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString().trim())) {
                    dblCompromise = 0;
                    computeAll();
                }
            }
        });


        mEtCompromise.addTextChangedListener(new NumberTextWatcherForThousand(mEtCompromise));


        mEtTotalAmtPaid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                double doubleValue = 0;
                if (hasFocus == false) {
                    try {
                        DecimalFormat formatter = new DecimalFormat("#,###,###.00");
                        doubleValue = Double.parseDouble(mEtTotalAmtPaid.getText().toString().replace(",", "").replace(AmountFormatter.PESO_CURRENCY, ""));
                        mEtTotalAmtPaid.setText(AmountFormatter.PESO_CURRENCY + formatter.format(doubleValue));

                    } catch (NumberFormatException e) {
                        //Error
                    } catch (NullPointerException e) {

                    }
                }
            }
        });


//        etTotalAmtPaid.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (TextUtils.isEmpty(s.toString().trim())) {
//                    etTotalAmtPaid.setText("\u20B1" + "0.00");
//                }
//            }
//        });

        mEtTotalAmtPaid.addTextChangedListener(new NumberTextWatcherForThousand(mEtTotalAmtPaid));


    }


    private void computeAll() {
        if (!mEtSurcharge.getText().toString().equals("")) {
            dblSurcharge = MoneyEditText.toDouble(mEtSurcharge.getText().toString());
        }
        if (!mEtInterest.getText().toString().equals("")) {
            dblInterest = MoneyEditText.toDouble(mEtInterest.getText().toString());
        }

        if (!mEtCompromise.getText().toString().equals("")) {
            dblCompromise = MoneyEditText.toDouble(mEtCompromise.getText().toString());
        }

        if (!mEtBasicTax.getText().toString().equals("")) {
            dblBasicTax = MoneyEditText.toDouble(mEtBasicTax.getText().toString());
        }

        dblPenalties = dblSurcharge + dblInterest + dblCompromise;
        Log.e("dblPenalties", dblSurcharge + "--" + dblInterest + "--" + dblCompromise);
        if (dblPenalties == 0) {
            mEtPenalties.setText("\u20B1" + "0.00");
        } else {
            String strPenalties = MoneyEditText.format(dblPenalties);
            mEtPenalties.setText(strPenalties);
        }

        Log.e("COMPUTE", "" + dblSurcharge + " " + dblInterest + " " + dblCompromise + " penalties " + dblPenalties);

        dblTotalAmtDue = dblBasicTax + dblPenalties;

        if (dblTotalAmtDue == 0) {
            mEtTotalAmtDue.setText("\u20B1" + "0.00");
        } else {
            String strTotalAmountDue = MoneyEditText.format(dblTotalAmtDue);
            mEtTotalAmtDue.setText(strTotalAmountDue);

        }


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

    @Override
    public void after() {
        double dblPenalties = MoneyEditText2.toDouble(mEtSurcharge.getText().toString()) + MoneyEditText2.toDouble(mEtInterest.getText().toString()) + MoneyEditText2.toDouble(mEtCompromise.getText().toString());
        String strPenalties = MoneyEditText.format(dblPenalties);
        mEtPenalties.setText(strPenalties);

        double dblTotalAmountDue = MoneyEditText2.toDouble(mEtBasicTax.getText().toString()) + dblPenalties;
        String strTotalAmountDue = MoneyEditText.format(dblTotalAmountDue);
        mEtTotalAmtDue.setText(strTotalAmountDue);
    }
}
