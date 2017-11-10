package utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.biz.pmti.dbp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ian.blanco on 11/8/2017.
 */

public class DBPBase {

    public static AlertDialog.Builder dialogPicker(Context context, String title, String[] items, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setItems(items, onClickListener);

        return builder;
    }


    public static void dialogWithOnClickCancelable(Context context, String title, DialogInterface.OnClickListener onClickListener) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(title);
            builder.setPositiveButton("OK", onClickListener);
            builder.create().show();
        } catch (Exception e) {
//            No Context available
        }
    }

    public static void dialogWithOnClick(Context context, String title, DialogInterface.OnClickListener onClickListener) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(title);
            builder.setCancelable(false);
            builder.setPositiveButton("OK", onClickListener);
            builder.create().show();
        } catch (Exception e) {
//            No Context available
        }
    }

    public static void dialogWithOnCLickCancelable(Context context, String title, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onCancel) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setMessage(title);
            builder.setPositiveButton("Yes", onClickListener);
            builder.setNegativeButton("No", onCancel);
            builder.create().show();
        } catch (Exception e) {
//            No Context available
        }
    }

    public static void dialogCustom(Context context, Activity activity, String title, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
         AlertDialog alertDialog;
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_error, null);
        TextView tvTitle = (TextView) dialogView.findViewById(R.id.title);
        TextView tvMessage = (TextView) dialogView.findViewById(R.id.message);
        Button btnOk = (Button) dialogView.findViewById(R.id.ok);
        tvTitle.setText(title);
        tvMessage.setText(message);
        builder.setView(dialogView);
        alertDialog = builder.create();
        alertDialog.show();

        final AlertDialog finalAlertDialog = alertDialog;
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalAlertDialog.dismiss();
            }
        });

    }

    public static String formatDate(long mills){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date d = new Date(mills);
        return sdf.format(d);
    }


    public static String formatDate(int year,int monthOfYear,int dayOfMonth){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Calendar c = Calendar.getInstance();
        c.set(year, monthOfYear, dayOfMonth);
        return formatDate(c.getTimeInMillis());
    }

    public static String formatDateYear(long mills){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date(mills);
        return sdf.format(d);
    }



    public static long convertToMills(int year,int monthOfYear,int dayOfMonth){
        Calendar c = Calendar.getInstance();
        c.set(year, monthOfYear, dayOfMonth);
        return c.getTimeInMillis();
    }


}
