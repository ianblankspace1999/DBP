package com.biz.pmti.dbp.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.biz.pmti.dbp.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import utils.customview.MoneyEditText;

/**
 * Created by ian.blanco on 11/20/2017.
 */
@Data
public class Payment implements Serializable {

    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("amount")
    @Expose
    private double amount;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("index")
    @Expose
    private int index;

    public Payment(String paymentMode, double amount, String reference) {
        this.paymentMode = paymentMode;
        this.amount = amount;
        this.reference = reference;
    }

    public Payment(String paymentMode, double amount, String reference, int index) {
        this.paymentMode = paymentMode;
        this.amount = amount;
        this.reference = reference;
        this.index = index;
    }

    public View getView(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_payment_mode, null);
        TextView mMode = (TextView) v.findViewById(R.id.mode);
        TextView mAmount = (TextView) v.findViewById(R.id.amount) ;
        TextView mReference = (TextView) v.findViewById(R.id.reference);

        mMode.setText(paymentMode);
        mAmount.setText(MoneyEditText.format(amount));
        mReference.setText(reference);
        v.setTag(index);
        return v;
    }



}
