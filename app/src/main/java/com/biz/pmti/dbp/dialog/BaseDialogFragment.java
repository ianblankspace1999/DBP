package com.biz.pmti.dbp.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;

import com.biz.pmti.dbp.activities.MainActivity;
import com.biz.pmti.dbp.fragments.FragmentTransactionFour;

import java.util.Calendar;

import butterknife.ButterKnife;
import utils.DBPBase;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by ian.blanco on 11/22/2017.
 */

public class BaseDialogFragment extends DialogFragment {

    private AlertDialog diag;
    protected FragmentTransactionFour parentFragment;
    protected MainActivity transaction;
    private View v;
    private String title;
    DialogInterface.OnClickListener onclick = null;

    private CloseActivityBroadcast BroadcastCloseAll;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentFragment = (FragmentTransactionFour) getParentFragment();
        transaction = (MainActivity) parentFragment.getActivity();

        BroadcastCloseAll = new CloseActivityBroadcast();
        IntentFilter intentFilter = new IntentFilter("baseActivity");
        parentFragment.getActivity().registerReceiver(BroadcastCloseAll, intentFilter);
    }

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
//            updateListUI();
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
//            updateListUI();
        }
    };

    public void onDestroy() {
        super.onDestroy();
        parentFragment.getActivity().unregisterReceiver(BroadcastCloseAll);
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


//        if(isEditMode)
//            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Delete", onclick);

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", onclick);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", onclick);


        return alertDialog;
    }


    protected void submit(AlertDialog diag) {
    }

    protected void delete() {
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

    protected void onDateChanged(Calendar c) {

    }

    protected void hideSoftKeyboard() {
        if (getActivity().getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) parentFragment.getActivity().getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(parentFragment.getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }


    public class CloseActivityBroadcast extends BroadcastReceiver {

        public void onReceive(Context arg0, Intent intent) {
            if (intent.getIntExtra("closeAll", 0) == 1)
                parentFragment.getActivity().finish();
        }
    }


    public void exit() {
        Intent intent = new Intent("baseActivity");
        intent.putExtra("closeAll", 1);
        parentFragment.getActivity().sendBroadcast(intent);
    }

}
