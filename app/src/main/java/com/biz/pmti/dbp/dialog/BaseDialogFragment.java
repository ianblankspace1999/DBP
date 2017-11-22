package com.biz.pmti.dbp.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.biz.pmti.dbp.activities.MainActivity;
import com.biz.pmti.dbp.fragments.FragmentTransactionFour;

import java.util.Calendar;

import butterknife.ButterKnife;

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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentFragment = (FragmentTransactionFour) getParentFragment();
        transaction = (MainActivity) parentFragment.getActivity();
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


    protected void onDateChanged(Calendar c){

    }

}
