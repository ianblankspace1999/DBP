package com.biz.pmti.dbp.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;


import com.biz.pmti.dbp.activities.MainActivity;
import com.biz.pmti.dbp.fragments.FragmentTransactionFour;

import java.util.Calendar;

import butterknife.ButterKnife;
import utils.DBPBase;

/**
 * Created by juanalfonzocarlos.le on 4/5/2016.
 */
public class SuperPaymentDialog extends DialogFragment {

    protected MainActivity fragmentTransaction;        //parent of parent fragment
    protected FragmentTransactionFour parentFragment;      //parent fragment


    private View v;
    private String title;
    protected boolean isEditMode = false;
    private Calendar c = null;

    DialogInterface.OnClickListener onclick = null;
    protected int position = -1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentFragment = (FragmentTransactionFour) getParentFragment();
        fragmentTransaction = (MainActivity) parentFragment.getActivity();

        c = Calendar.getInstance();
//        try{
//            getArguments();
//            position = getArguments().getInt(PaymentDetail.LIST_INDEX,-1);
//            Log.e("ARGUMENTS",""+getArguments().getInt(PaymentDetail.LIST_INDEX,-1));
//            isEditMode = true;
//        }catch (NullPointerException e){
//            isEditMode = false;
//        }

    }

    protected void setup(String title, int layoutResourceID) {
        this.title = title;
        v = LayoutInflater.from(getContext()).inflate(layoutResourceID, null);
        ButterKnife.bind(this, v);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        alert.setCancelable(false)
                .setView(v)
                .setTitle(title)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        setCancelable(false);

        AlertDialog alertDialog = alert.create();


        if (isEditMode)
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Delete", onclick);

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", onclick);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", onclick);


        return alertDialog;
    }

    private AlertDialog diag;

    @Override
    public void onStart() {
        super.onStart();

        diag = (AlertDialog) getDialog();

        if (diag != null) {
            diag.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(onClickNeutral);
            diag.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(onClickNegative);
            diag.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(onClickPositive);
        }
    }

    private View.OnClickListener onClickPositive = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            submit(diag);
            updateListUI();
        }
    };


    private View.OnClickListener onClickNegative = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //cancel();
            parentFragment.onCancel();
            diag.dismiss();
        }
    };

    private View.OnClickListener onClickNeutral = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            delete();
            diag.dismiss();
            updateListUI();
        }
    };


    protected void submit(AlertDialog diag) {
    }

    protected void delete() {
    }

    private void updateListUI() {
        parentFragment.updatePaymentMode();
    }


    protected void showDatePicker(final Calendar c) {

        DatePickerDialog dpd = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        long mills = DBPBase.convertToMills(year, monthOfYear, dayOfMonth);
                        c.setTimeInMillis(mills);
                        onDateChanged(c);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());

        // dpd.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
        dpd.show();

    }

/*    protected void onDateChanged(int year,int month,int day){

    }*/

    protected void onDateChanged(Calendar c) {

    }

    protected void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected void showMessagePrompt(String title, String message) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        DialogFragment newFragment = new DialogErro();
        newFragment.setArguments(args);
        newFragment.show(getChildFragmentManager(), "dialog");
    }
}
