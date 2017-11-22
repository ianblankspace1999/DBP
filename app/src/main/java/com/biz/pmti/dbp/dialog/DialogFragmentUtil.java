package com.biz.pmti.dbp.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import utils.DBPBase;

/**
 * Created by ian.blanco on 11/22/2017.
 */

public class DialogFragmentUtil {


    public static void showDatePicker(final Calendar c, Context context) {

        DatePickerDialog dpd = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        long mills = DBPBase.convertToMills(year, monthOfYear, dayOfMonth);
                        c.setTimeInMillis(mills);
//                        onDateChanged(c);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());

        dpd.show();

    }

    public static void showMessagePrompt(String title, String message, FragmentManager fragmentManager) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        DialogFragment newFragment = new DialogErro();
        newFragment.setArguments(args);
        newFragment.show(fragmentManager, "dialog");
    }

    public static void showToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
