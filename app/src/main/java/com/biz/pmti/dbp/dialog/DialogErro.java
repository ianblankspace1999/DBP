package com.biz.pmti.dbp.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.biz.pmti.dbp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Eli Tamondong on 11/15/2016.
 */
public class DialogErro extends DialogFragment {

    public static final String TAG = "DialogForgotPassword";

    AlertDialog alertDialog;


    Dialog dialog;
    @BindView(R.id.title)
    TextView txt_title;
    @BindView(R.id.l1)
    LinearLayout mL1;
    @BindView(R.id.message)
    TextView txt_message;
    @BindView(R.id.ok)
    Button mOk;
    Unbinder unbinder;


    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle mArgs = getArguments();
        String title = mArgs.getString("title");
        String message = mArgs.getString("message");
        View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_error, null);
        ButterKnife.bind(this, v);

        txt_title.setText(title);
        txt_message.setText(message);

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // screenManager.replaceScreen();
                //changeScreen(new frag_ReceiptType());
                // screenManager.replaceScreen();
               /* if(message.contentEquals("payment transaction has been successfully sent!")){
                    Log.e("JACCCCC succes","zzzz");
                }else{
                    dismiss();
                }*/
                //screenManager.popBackStack();

               /* if(message.contentEquals("successfully sent")){
                    screenManager.popBackStack();
                }else{
                    dismiss();
                }*/


                Log.e("DIALOG", "-----");


            }
        });
        alert.setCancelable(false);
        alert.setView(v);

        dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setCancelable(false);
        dialog = alert.create();
        //dialog.getWindow().setLayout(420,220);
        //alertDialog = alert.create();
        return dialog;
    }


    @OnClick(R.id.ok)
    void onOk() {
//         ((HomeScreen) getParentFragment()).clearScreen();
//         dismiss();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    private void changeScreen(Fragment fragment) {
//        getChildFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container, fragment)
//                .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
//                .commit();
//
//    }

}
