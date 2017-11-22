package com.biz.pmti.dbp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Data;

/**
 * Created by ian.blanco on 11/22/2017.
 */
@Data
public class TransactionModel implements Serializable {

    @SerializedName("tin_number")
    @Expose
    private String tinNumber;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("bir_form_number")
    @Expose
    private String birFormNumber;

    @SerializedName("tax_type")
    @Expose
    private String taxType;

    @SerializedName("account_code")
    @Expose
    private String accountCode;

    @SerializedName("period_covered")
    @Expose
    private String periodCovered;

    @SerializedName("quarter")
    @Expose
    private String quarter;

    @SerializedName("assessment")
    @Expose
    private String assessment;

    @SerializedName("basic_tax")
    @Expose
    private String basicTax;

    @SerializedName("sur_charge")
    @Expose
    private String surCharge;

    @SerializedName("interest")
    @Expose
    private String interest;

    @SerializedName("total_amount_paid")
    @Expose
    private String totalAmountPaid;

    @SerializedName("penalties")
    @Expose
    private String penalties;

    @SerializedName("total_amount_due")
    @Expose
    private String totalAmountDue;

    @SerializedName("data")
    @Expose
    private ArrayList<Payment> paymentsCollection;

    @SerializedName("remarks")
    @Expose
    private String remakrs;


}