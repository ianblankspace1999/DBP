package com.biz.pmti.dbp.models.modeofpayments;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pmti.ldonios.lib.utils.DateUtils;

/**
 * Created by pmti-04 on 11/16/2016.
 */

public class PaidTypePera implements Parcelable {
    @JsonProperty("ptperaId") 								private long ptperaId 			= 1;
    @JsonProperty("pmodeCode") 								private String pmodeCode 		= "";
    @JsonProperty("tpayReceiptNo") 							private String tpayReceiptNo 	= "";
    @JsonProperty("tpaySigAlgo") 							private String tpaySigAlgo 		= "";
    @JsonProperty("ptperaNo") 								private String ptperaNo 			= "";
    @JsonProperty("ptperaDate") 							private long ptperaDate 			= 0;
    @JsonProperty("ptperaAmount") 							private double ptperaAmount 		= 0;
    @JsonProperty("dateCreated") 							private long dateCreated 		= 0;
    @JsonProperty("ptperaStatus") 							private String ptperaStatus 		= "";


    public long getPtperaId() {
        return ptperaId;
    }

    public void setPtperaId(long ptperaId) {
        this.ptperaId = ptperaId;
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

    public String getPtperaNo() {
        return ptperaNo;
    }

    public void setPtperaNo(String ptperaNo) {
        this.ptperaNo = ptperaNo;
    }

    public long getPtperaDate() {
        return ptperaDate;
    }

    public void setPtperaDate(long ptperaDate) {
        this.ptperaDate = ptperaDate;
    }
    @JsonIgnore
    public void setPtperaDate(String ptperaDate) {
        this.ptperaDate = DateUtils.simpledateToMillis(ptperaDate, DateUtils.DateFormat.SIMPLE_DATE_5_FORMAT);
    }

    public double getPtperaAmount() {
        return ptperaAmount;
    }

    public void setPtperaAmount(double ptperaAmount) {
        this.ptperaAmount = ptperaAmount;
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

    public String getPtperaStatus() {
        return ptperaStatus;
    }

    public void setPtperaStatus(String ptperaStatus) {
        this.ptperaStatus = ptperaStatus;
    }

    public static Creator<PaidTypePera> getCreator() {
        return CREATOR;
    }

    protected PaidTypePera(Parcel in) {
        ptperaId = in.readLong();
        pmodeCode = in.readString();
        tpayReceiptNo = in.readString();
        tpaySigAlgo = in.readString();
        ptperaNo = in.readString();
        ptperaDate = in.readLong();
        ptperaAmount = in.readDouble();
        dateCreated = in.readLong();
        ptperaStatus = in.readString();
    }

    public PaidTypePera() {
        // TODO Auto-generated constructor stub
    }

    public PaidTypePera(long ptperaId, String pperadeCode, String tpayReceiptNo,
                      String tpaySigAlgo, String ptperaNo, long ptperaDate, double ptperaAmount,
                      String dateCreated, String ptperaStatus) {

        this.ptperaId = ptperaId;
        this.pmodeCode = pmodeCode;
        this.tpayReceiptNo = tpayReceiptNo;
        this.tpaySigAlgo = tpaySigAlgo;
        this.ptperaNo = ptperaNo;
        this.ptperaDate = ptperaDate;
        this.ptperaAmount = ptperaAmount;
        this.dateCreated = dateCreated!=null?DateUtils.simpledateToMillis(dateCreated, DateUtils.DateFormat.SIMPLE_DATE_5_FORMAT):0;
        this.ptperaStatus = ptperaStatus;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(ptperaId);
        dest.writeString(pmodeCode);
        dest.writeString(tpayReceiptNo);
        dest.writeString(tpaySigAlgo);
        dest.writeString(ptperaNo);
        dest.writeLong(ptperaDate);
        dest.writeDouble(ptperaAmount);
        dest.writeLong(dateCreated);
        dest.writeString(ptperaStatus);
    }

    @SuppressWarnings("unused")
    public static final Creator<PaidTypePera> CREATOR = new Creator<PaidTypePera>() {
        @Override
        public PaidTypePera createFromParcel(Parcel in) {
            return new PaidTypePera(in);
        }

        @Override
        public PaidTypePera[] newArray(int size) {
            return new PaidTypePera[size];
        }
    };
}
