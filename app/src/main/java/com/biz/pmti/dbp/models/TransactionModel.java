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
    private long periodCovered;

    @SerializedName("quarter")
    @Expose
    private String quarter;

    @SerializedName("assessment")
    @Expose
    private String assessment;

    @SerializedName("due_date")
    @Expose
    private long dueDate;

    @SerializedName("basic_tax")
    @Expose
    private double basicTax;

    @SerializedName("sur_charge")
    @Expose
    private double surCharge;

    @SerializedName("interest")
    @Expose
    private double interest;

    @SerializedName("total_amount_paid")
    @Expose
    private double totalAmountPaid;

    @SerializedName("penalties")
    @Expose
    private double penalties;

    @SerializedName("total_amount_due")
    @Expose
    private double totalAmountDue;

    @SerializedName("data")
    @Expose
    private ArrayList<Payment> paymentsCollection;

    @SerializedName("remarks")
    @Expose
    private String remakrs;


}