package com.biz.pmti.dbp.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.biz.pmti.dbp.R;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;
import utils.DBPBase;

/**
 * Created by ian.blanco on 11/7/2017.
 */

public class FragmentTransactionTwo extends BaseFragment {

    @BindView(R.id.spn_birformnumber)
    Spinner mSpnBirformnumber;

    @BindView(R.id.spn_taxtype)
    Spinner mSpnTaxtype;

    @BindView(R.id.spn_accountcode)
    Spinner mSpnAccountcode;

    @BindView(R.id.tv_periodcovered)
    TextView mTvPeriodcovered;

    @BindView(R.id.spn_quarter)
    Spinner mSpnQuarter;

    @BindView(R.id.et_assessment)
    EditText mEtAssessment;

    @BindView(R.id.tv_duedate)
    TextView mTvDuedate;

    @BindView(R.id.tvBirFormNumber)
    TextView mTvBirFormNumber;

    Unbinder unbinder;

    private long periodCovered = 0;
    private long dueDate = 0;
    private String[] QTR = new String[]{"1", "2", "3", "4"};
    private ArrayList<String> mBirFormNumbers = new ArrayList<>();
    private ArrayList<String> mTaxTypes = new ArrayList<>();
    private ArrayList<String> mAccountCodes = new ArrayList<>();


    public static FragmentTransactionTwo newInstance() {
        FragmentTransactionTwo fragmentTransactionTwo = new FragmentTransactionTwo();
        return fragmentTransactionTwo;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.isVisible()) {
            mTvBirFormNumber.requestFocus();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactiontwo, container, false);
        unbinder = ButterKnife.bind(this, view);

        initialize();

        return view;
    }

    private void initialize() {

        mBirFormNumbers = new ArrayList<>();
        mTaxTypes = new ArrayList<>();
        mAccountCodes = new ArrayList<>();

        mBirFormNumbers.add(0, "-SELECT BIR FORM NUMBER-");
        mBirFormNumbers.add("1660");
        mBirFormNumbers.add("1011A");
        mBirFormNumbers.add("1940B");

        mTaxTypes.add(0, "-SELECT TAX TYPE-");
        mTaxTypes.add("AC");
        mTaxTypes.add("DE");
        mTaxTypes.add("FG");
        mTaxTypes.add("HK");
        mTaxTypes.add("LO");

        mAccountCodes.add(0, "-SELECT ACCOUNTCODES-");
        mAccountCodes.add("ACCOUNT 1");
        mAccountCodes.add("ACCOUNT 2");
        mAccountCodes.add("ACCOUNT 3");
        mAccountCodes.add("ACCOUNT 4");


        mSpnBirformnumber.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, mBirFormNumbers));
        mSpnBirformnumber.setSelection(0);

        mSpnQuarter.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, QTR));
        mSpnQuarter.setSelection(0);

        mSpnTaxtype.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, mTaxTypes));
        mSpnTaxtype.setSelection(0);

        mSpnAccountcode.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, mAccountCodes));
        mSpnAccountcode.setSelection(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @OnClick({R.id.tv_periodcovered, R.id.tv_duedate})
    public void onViewClicked(final View v) {
        switch (v.getId()) {
            case R.id.tv_periodcovered:

                try{
                    Calendar c = Calendar.getInstance();
                    if (periodCovered != 0)
                        c.setTimeInMillis(periodCovered);
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog dpd = new DatePickerDialog(getContext(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    periodCovered = DBPBase.convertToMills(year, monthOfYear, dayOfMonth);
                                    ((TextView) v).setText(DBPBase.formatDate(periodCovered));
                                }
                            }, year, month, day);
                    // dpd.getDatePicker().setMaxDate(c.getTimeInMillis());
                    dpd.show();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
                break;
            case R.id.tv_duedate:

                //new DialogDatePicker().show(getChildFragmentManager(),"DATEPICKER");
                try {
                    Calendar c = Calendar.getInstance();
                    if (dueDate != 0)
                        c.setTimeInMillis(dueDate);
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog dpd = new DatePickerDialog(getContext(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    dueDate = DBPBase.convertToMills(year, monthOfYear, dayOfMonth);
                                    ((TextView) v).setText(DBPBase.formatDate(dueDate));
                                }
                            }, year, month, day);
                    //dpd.getDatePicker().setMaxDate(c.getTimeInMillis());
                    dpd.show();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }


                break;
        }
    }


    @OnItemSelected(R.id.spn_birformnumber)
    void BFNSchanged(final int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    @OnItemSelected(R.id.spn_taxtype)
    void TAXTYPEchanged(final int position) {


    }

    @OnItemSelected(R.id.spn_accountcode)
    void ACCTDESCchanged(View v, int position) {
        Log.e("tag", "ACCTDESCchanged : " + position);

    }




    @Override
    public boolean isValid() {
        if (mSpnBirformnumber.getSelectedItemPosition() == 0) {
//            showMessagePrompt(transtype,"Invalid BIR Form Number!");
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Invalid BIR Form Number!");
            return false;
        }

        if (mSpnTaxtype.getSelectedItemPosition() == 0) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Invalid Tax-Type Code!");
            return false;
        }

        if (mSpnAccountcode.getSelectedItemPosition()== 0) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Invalid Account Code!");
            return false;
        }

        if (periodCovered == 0) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Invalid Period Covered!");
            return false;
        }

        if (dueDate == 0) {
            DBPBase.dialogCustom(getContext(), getActivity(), "ERROR Prompt", "Invalid Due Date!");
            return false;
        }

        parent.mTransactionModel.setBirFormNumber(mBirFormNumbers.get(mSpnBirformnumber.getSelectedItemPosition()));
        parent.mTransactionModel.setTaxType(mTaxTypes.get(mSpnTaxtype.getSelectedItemPosition()));
        parent.mTransactionModel.setAccountCode(mAccountCodes.get(mSpnAccountcode.getSelectedItemPosition()));
        parent.mTransactionModel.setPeriodCovered(periodCovered);
        parent.mTransactionModel.setQuarter(QTR[mSpnQuarter.getSelectedItemPosition()]);
        parent.mTransactionModel.setAssessment(mEtAssessment.getText().toString());
        parent.mTransactionModel.setDueDate(dueDate);
        return true;
    }
}
