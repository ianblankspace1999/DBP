package com.biz.pmti.dbp.models.modeofpayments;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pmti.ldonios.lib.utils.DateUtils;

/**
 * Created by pmti-04 on 11/16/2016.
 */

public class PaidTypeSaro implements Parcelable {
    @JsonProperty("ptsaroId") 								private long ptsaroId 			= 1;
    @JsonProperty("pmodeCode") 								private String pmodeCode 		= "";
    @JsonProperty("tpayReceiptNo") 							private String tpayReceiptNo 	= "";
    @JsonProperty("tpaySigAlgo") 							private String tpaySigAlgo 		= "";
    @JsonProperty("ptsaroNo") 								private String ptsaroNo 			= "";
    @JsonProperty("ptsaroDate") 							private long ptsaroDate 			= 0;
    @JsonProperty("ptsaroAmount") 							private double ptsaroAmount 		= 0;
    @JsonProperty("dateCreated") 							private long dateCreated 		= 0;
    @JsonProperty("ptsaroStatus") 							private String ptsaroStatus 		= "";


    public long getPtsaroId() {
        return ptsaroId;
    }

    public void setPtsaroId(long ptsaroId) {
        this.ptsaroId = ptsaroId;
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

    public String getPtsaroNo() {
        return ptsaroNo;
    }

    public void setPtsaroNo(String ptsaroNo) {
        this.ptsaroNo = ptsaroNo;
    }

    public long getPtsaroDate() {
        return ptsaroDate;
    }

    public void setPtsaroDate(long ptsaroDate) {
        this.ptsaroDate = ptsaroDate;
    }
    @JsonIgnore
    public void setPtsaroDate(String ptsaroDate) {
        this.ptsaroDate = DateUtils.simpledateToMillis(ptsaroDate, DateUtils.DateFormat.SIMPLE_DATE_5_FORMAT);
    }

    public double getPtsaroAmount() {
        return ptsaroAmount;
    }

    public void setPtsaroAmount(double ptsaroAmount) {
        this.ptsaroAmount = ptsaroAmount;
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

    public String getPtsaroStatus() {
        return ptsaroStatus;
    }

    public void setPtsaroStatus(String ptsaroStatus) {
        this.ptsaroStatus = ptsaroStatus;
    }

    public static Creator<PaidTypeSaro> getCreator() {
        return CREATOR;
    }

    protected PaidTypeSaro(Parcel in) {
        ptsaroId = in.readLong();
        pmodeCode = in.readString();
        tpayReceiptNo = in.readString();
        tpaySigAlgo = in.readString();
        ptsaroNo = in.readString();
        ptsaroDate = in.readLong();
        ptsaroAmount = in.readDouble();
        dateCreated = in.readLong();
        ptsaroStatus = in.readString();
    }

    public PaidTypeSaro() {
        // TODO Auto-generated constructor stub
    }

    public PaidTypeSaro(long ptsaroId, String pmodeCode, String tpayReceiptNo,
                        String tpaySigAlgo, String ptsaroNo, long ptsaroDate, double ptsaroAmount,
                        String dateCreated, String ptsaroStatus) {

        this.ptsaroId = ptsaroId;
        this.pmodeCode = pmodeCode;
        this.tpayReceiptNo = tpayReceiptNo;
        this.tpaySigAlgo = tpaySigAlgo;
        this.ptsaroNo = ptsaroNo;
        this.ptsaroDate = ptsaroDate;
        this.ptsaroAmount = ptsaroAmount;
        this.dateCreated = dateCreated!=null?DateUtils.simpledateToMillis(dateCreated, DateUtils.DateFormat.SIMPLE_DATE_5_FORMAT):0;
        this.ptsaroStatus = ptsaroStatus;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(ptsaroId);
        dest.writeString(pmodeCode);
        dest.writeString(tpayReceiptNo);
        dest.writeString(tpaySigAlgo);
        dest.writeString(ptsaroNo);
        dest.writeLong(ptsaroDate);
        dest.writeDouble(ptsaroAmount);
        dest.writeLong(dateCreated);
        dest.writeString(ptsaroStatus);
    }

    @SuppressWarnings("unused")
    public static final Creator<PaidTypeSaro> CREATOR = new Creator<PaidTypeSaro>() {
        @Override
        public PaidTypeSaro createFromParcel(Parcel in) {
            return new PaidTypeSaro(in);
        }

        @Override
        public PaidTypeSaro[] newArray(int size) {
            return new PaidTypeSaro[size];
        }
    };
}
