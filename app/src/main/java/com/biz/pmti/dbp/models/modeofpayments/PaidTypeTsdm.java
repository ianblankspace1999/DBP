package com.biz.pmti.dbp.models.modeofpayments;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pmti.ldonios.lib.utils.DateUtils;

/**
 * Created by pmti-04 on 11/16/2016.
 */

public class PaidTypeTsdm implements Parcelable {
    @JsonProperty("pttsdmId") 									private long pttsdmId = 1;
    @JsonProperty("pmodeCode") 									private String pmodeCode = "";
    @JsonProperty("tpayReceiptNo") 								private String tpayReceiptNo = "";
    @JsonProperty("tpaySigAlgo") 								private String tpaySigAlgo = "";
    @JsonProperty("pttsdmNo") 									private String pttsdmNo = "";
    @JsonProperty("pttsdmDate") 								private long pttsdmDate = 0;
    @JsonProperty("pttsacNo") 									private String pttsacNo = "";
    @JsonProperty("pttsacDate") 								private long pttsacDate = 0;
    @JsonProperty("pttsdmAmount") 								private double pttsdmAmount = 0;
    @JsonProperty("dateCreated") 								private long dateCreated = 0;
    @JsonProperty("pttsdmStatus") 								private String pttsdmStatus = "";

    public long getPttsdmId() {
        return pttsdmId;
    }

    public void setPttsdmId(long pttsdmId) {
        this.pttsdmId = pttsdmId;
    }

    public String getPmodeCode() {
        return pmodeCode;
    }

    public void setPmodeCode(String pmodeCode) {
        this.pmodeCode = pmodeCode;
    }

    public String getTpayReceiptNo() {
        return tpayReceiptNo;
    }

    public void setTpayReceiptNo(String tpayReceiptNo) {
        this.tpayReceiptNo = tpayReceiptNo;
    }

    public String getTpaySigAlgo() {
        return tpaySigAlgo;
    }

    public void setTpaySigAlgo(String tpaySigAlgo) {
        this.tpaySigAlgo = tpaySigAlgo;
    }

    public String getPttsdmNo() {
        return pttsdmNo;
    }

    public void setPttsdmNo(String pttsdmNo) {
        this.pttsdmNo = pttsdmNo;
    }

    public long getPttsdmDate() {
        return pttsdmDate;
    }

    public void setPttsdmDate(long pttsdmDate) {
        this.pttsdmDate = pttsdmDate;
    }
    @JsonIgnore
    public void setPttsdmDate(String pttsdmDate) {
        this.pttsdmDate = DateUtils.simpledateToMillis(pttsdmDate, DateUtils.DateFormat.SIMPLE_DATE_5_FORMAT);
    }

    public String getPttsacNo() {
        return pttsacNo;
    }

    public void setPttsacNo(String pttsacNo) {
        this.pttsacNo = pttsacNo;
    }

    public long getPttsacDate() {
        return pttsacDate;
    }

    public void setPttsacDate(long pttsacDate) {
        this.pttsacDate = pttsacDate;
    }
    @JsonIgnore
    public void setPttsacDate(String pttsacDate) {
        this.pttsacDate = DateUtils.simpledateToMillis(pttsacDate, DateUtils.DateFormat.SIMPLE_DATE_5_FORMAT);
    }

    public double getPttsdmAmount() {
        return pttsdmAmount;
    }

    public void setPttsdmAmount(double pttsdmAmount) {
        this.pttsdmAmount = pttsdmAmount;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }
    @JsonIgnore
    public void setDateCreated(String dateCreated) {
        this.dateCreated = DateUtils.simpledateToMillis(dateCreated, DateUtils.DateFormat.SIMPLE_DATE_5_FORMAT);
    }

    public String getPttsdmStatus() {
        return pttsdmStatus;
    }

    public void setPttsdmStatus(String pttsdmStatus) {
        this.pttsdmStatus = pttsdmStatus;
    }

    public static Creator<PaidTypeTsdm> getCreator() {
        return CREATOR;
    }

    protected PaidTypeTsdm(Parcel in) {
        pttsdmId = in.readLong();
        pmodeCode = in.readString();
        tpayReceiptNo = in.readString();
        tpaySigAlgo = in.readString();
        pttsdmNo = in.readString();
        pttsdmDate = in.readLong();
        pttsacNo = in.readString();
        pttsacDate = in.readLong();
        pttsdmAmount = in.readDouble();
        dateCreated = in.readLong();
        pttsdmStatus = in.readString();
    }

    public PaidTypeTsdm() {

    }

    public PaidTypeTsdm(long pttsdmId, String pmodeCode, String tpayReceiptNo,
                       String tpaySigAlgo, String pttsdmNo, String pttsdmDate, String pttsacNo,
                       String pttsacDate, double pttsdmAmount, String dateCreated, String pttsdmStatus) {
        this.pttsdmId = pttsdmId;
        this.pmodeCode = pmodeCode;
        this.tpayReceiptNo = tpayReceiptNo;
        this.tpaySigAlgo = tpaySigAlgo;
        this.pttsdmNo = pttsdmNo;
        this.pttsdmDate = pttsdmDate!=null ? DateUtils.simpledateToMillis(pttsdmDate, DateUtils.DateFormat.SIMPLE_DATE_5_FORMAT):0;
        this.pttsacNo = pttsacNo;
        this.pttsacDate = pttsacDate!=null ? DateUtils.simpledateToMillis(pttsacDate, DateUtils.DateFormat.SIMPLE_DATE_5_FORMAT):0;
        this.pttsdmAmount = pttsdmAmount;
        this.dateCreated = dateCreated!=null ? DateUtils.simpledateToMillis(dateCreated, DateUtils.DateFormat.SIMPLE_DATE_5_FORMAT):0;
        this.pttsdmStatus = pttsdmStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(pttsdmId);
        dest.writeString(pmodeCode);
        dest.writeString(tpayReceiptNo);
        dest.writeString(tpaySigAlgo);
        dest.writeString(pttsdmNo);
        dest.writeLong(pttsdmDate);
        dest.writeString(pttsacNo);
        dest.writeLong(pttsacDate);
        dest.writeDouble(pttsdmAmount);
        dest.writeLong(dateCreated);
        dest.writeString(pttsdmStatus);
    }

    @SuppressWarnings("unused")
    public static final Creator<PaidTypeTsdm> CREATOR = new Creator<PaidTypeTsdm>() {
        @Override
        public PaidTypeTsdm createFromParcel(Parcel in) {
            return new PaidTypeTsdm(in);
        }

        @Override
        public PaidTypeTsdm[] newArray(int size) {
            return new PaidTypeTsdm[size];
        }
    };
}
