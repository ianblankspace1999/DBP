package com.biz.pmti.dbp.models.modeofpayments;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pmti.ldonios.lib.utils.DateUtils;
import pmti.ldonios.lib.utils.DateUtils.DateFormat;

public class PaidTypeTra implements Parcelable {
	@JsonProperty("pttraId") 									private long pttraId 			= 1;
	@JsonProperty("pmodeCode") 									private String pmodeCode 		= "";
	@JsonProperty("tpayReceiptNo") 								private String tpayReceiptNo 	= "";
	@JsonProperty("tpaySigAlgo") 								private String tpaySigAlgo 		= "";
	@JsonProperty("pttraNo") 									private String pttraNo 			= "";
	@JsonProperty("pttraDate") 									private long pttraDate 			= 0;
	@JsonProperty("pttraAmount") 								private double pttraAmount 		= 0;
	@JsonProperty("dateCreated") 								private long dateCreated 		= 0;
	@JsonProperty("pttraStatus") 								private String pttraStatus 		= "";


    public long getPttraId() {
		return pttraId;
	}

	public void setPttraId(long pttraId) {
		this.pttraId = pttraId;
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

	public String getPttraNo() {
		return pttraNo;
	}

	public void setPttraNo(String pttraNo) {
		this.pttraNo = pttraNo;
	}

	public long getPttraDate() {
		return pttraDate;
	}

	public void setPttraDate(long pttraDate) {
		this.pttraDate = pttraDate;
	}
	@JsonIgnore
	public void setPttraDate(String pttraDate) {
		this.pttraDate = DateUtils.simpledateToMillis(pttraDate, DateFormat.SIMPLE_DATE_5_FORMAT);
	}

	public double getPttraAmount() {
		return pttraAmount;
	}

	public void setPttraAmount(double pttraAmount) {
		this.pttraAmount = pttraAmount;
	}

	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}
	@JsonIgnore
	public void setDateCreated(String dateCreated) {
		this.dateCreated = DateUtils.simpledateToMillis(dateCreated, DateFormat.SIMPLE_DATE_5_FORMAT);
	}

	public String getPttraStatus() {
		return pttraStatus;
	}

	public void setPttraStatus(String pttraStatus) {
		this.pttraStatus = pttraStatus;
	}

	public static Creator<PaidTypeTra> getCreator() {
		return CREATOR;
	}

	protected PaidTypeTra(Parcel in) {
        pttraId = in.readLong();
        pmodeCode = in.readString();
        tpayReceiptNo = in.readString();
        tpaySigAlgo = in.readString();
        pttraNo = in.readString();
        pttraDate = in.readLong();
        pttraAmount = in.readDouble();
        dateCreated = in.readLong();
        pttraStatus = in.readString();
    }

    public PaidTypeTra() {
		// TODO Auto-generated constructor stub
	}

	public PaidTypeTra(long pttraId, String pmodeCode, String tpayReceiptNo,
			String tpaySigAlgo, String pttraNo, String pttraDate, double pttraAmount,
			String dateCreated, String pttraStatus) {
		
		this.pttraId = pttraId;
        this.pmodeCode = pmodeCode;
        this.tpayReceiptNo = tpayReceiptNo;
        this.tpaySigAlgo = tpaySigAlgo;
        this.pttraNo = pttraNo;
        this.pttraDate = pttraDate!=null ? DateUtils.simpledateToMillis(pttraDate, DateFormat.SIMPLE_DATE_5_FORMAT) : 0;
        this.pttraAmount = pttraAmount;
        this.dateCreated = dateCreated!=null ? DateUtils.simpledateToMillis(dateCreated, DateFormat.SIMPLE_DATE_5_FORMAT) : 0;
        this.pttraStatus = pttraStatus;
		
	}

	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(pttraId);
        dest.writeString(pmodeCode);
        dest.writeString(tpayReceiptNo);
        dest.writeString(tpaySigAlgo);
        dest.writeString(pttraNo);
        dest.writeLong(pttraDate);
        dest.writeDouble(pttraAmount);
        dest.writeLong(dateCreated);
        dest.writeString(pttraStatus);
    }

    @SuppressWarnings("unused")
    public static final Creator<PaidTypeTra> CREATOR = new Creator<PaidTypeTra>() {
        @Override
        public PaidTypeTra createFromParcel(Parcel in) {
            return new PaidTypeTra(in);
        }

        @Override
        public PaidTypeTra[] newArray(int size) {
            return new PaidTypeTra[size];
        }
    };
}
