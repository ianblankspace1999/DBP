package com.biz.pmti.dbp.models.modeofpayments;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pmti.ldonios.lib.utils.DateUtils;
import pmti.ldonios.lib.utils.DateUtils.DateFormat;

public class PaidTypeTdm implements Parcelable {
	@JsonProperty("ptmoId") 									private long pttdmId = 1;
	@JsonProperty("pmodeCode") 									private String pmodeCode = "";
	@JsonProperty("tpayReceiptNo") 								private String tpayReceiptNo = "";
	@JsonProperty("tpaySigAlgo") 								private String tpaySigAlgo = "";
	@JsonProperty("pttdmNo") 									private String pttdmNo = "";
	@JsonProperty("pttdmDate") 									private long pttdmDate = 0;
	@JsonProperty("pttccNo") 									private String pttccNo = "";
	@JsonProperty("pttccDate") 									private long pttccDate = 0;
	@JsonProperty("pttdmAmount") 								private double pttdmAmount = 0;
	@JsonProperty("dateCreated") 								private long dateCreated = 0;
	@JsonProperty("pttdmStatus") 								private String pttdmStatus = "";

    public long getPttdmId() {
		return pttdmId;
	}

	public void setPttdmId(long pttdmId) {
		this.pttdmId = pttdmId;
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

	public String getPttdmNo() {
		return pttdmNo;
	}

	public void setPttdmNo(String pttdmNo) {
		this.pttdmNo = pttdmNo;
	}

	public long getPttdmDate() {
		return pttdmDate;
	}

	public void setPttdmDate(long pttdmDate) {
		this.pttdmDate = pttdmDate;
	}
	@JsonIgnore
	public void setPttdmDate(String pttdmDate) {
		this.pttdmDate = DateUtils.simpledateToMillis(pttdmDate, DateFormat.SIMPLE_DATE_5_FORMAT);
	}

	public String getPttccNo() {
		return pttccNo;
	}

	public void setPttccNo(String pttccNo) {
		this.pttccNo = pttccNo;
	}

	public long getPttccDate() {
		return pttccDate;
	}

	public void setPttccDate(long pttccDate) {
		this.pttccDate = pttccDate;
	}
	@JsonIgnore
	public void setPttccDate(String pttccDate) {
		this.pttccDate = DateUtils.simpledateToMillis(pttccDate, DateFormat.SIMPLE_DATE_5_FORMAT);
	}

	public double getPttdmAmount() {
		return pttdmAmount;
	}

	public void setPttdmAmount(double pttdmAmount) {
		this.pttdmAmount = pttdmAmount;
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

	public String getPttdmStatus() {
		return pttdmStatus;
	}

	public void setPttdmStatus(String pttdmStatus) {
		this.pttdmStatus = pttdmStatus;
	}

	public static Creator<PaidTypeTdm> getCreator() {
		return CREATOR;
	}

	protected PaidTypeTdm(Parcel in) {
        pttdmId = in.readLong();
        pmodeCode = in.readString();
        tpayReceiptNo = in.readString();
        tpaySigAlgo = in.readString();
        pttdmNo = in.readString();
        pttdmDate = in.readLong();
        pttccNo = in.readString();
        pttccDate = in.readLong();
        pttdmAmount = in.readDouble();
        dateCreated = in.readLong();
        pttdmStatus = in.readString();
    }

    public PaidTypeTdm() {
		
	}

	public PaidTypeTdm(long pttdmId, String pmodeCode, String tpayReceiptNo,
			String tpaySigAlgo, String pttdmNo, String pttdmDate, String pttccNo,
			String pttccDate, double pttdmAmount, String dateCreated, String pttdmStatus) {
		  	this.pttdmId = pttdmId;
	        this.pmodeCode = pmodeCode;
	        this.tpayReceiptNo = tpayReceiptNo;
	        this.tpaySigAlgo = tpaySigAlgo;
	        this.pttdmNo = pttdmNo;
	        this.pttdmDate = pttdmDate!=null ? DateUtils.simpledateToMillis(pttdmDate, DateFormat.SIMPLE_DATE_5_FORMAT):0;
	        this.pttccNo = pttccNo;
	        this.pttccDate = pttccDate!=null ? DateUtils.simpledateToMillis(pttccDate, DateFormat.SIMPLE_DATE_5_FORMAT):0;
	        this.pttdmAmount = pttdmAmount;
	        this.dateCreated = dateCreated!=null ? DateUtils.simpledateToMillis(dateCreated, DateFormat.SIMPLE_DATE_5_FORMAT):0;
	        this.pttdmStatus = pttdmStatus;
	}

	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(pttdmId);
        dest.writeString(pmodeCode);
        dest.writeString(tpayReceiptNo);
        dest.writeString(tpaySigAlgo);
        dest.writeString(pttdmNo);
        dest.writeLong(pttdmDate);
        dest.writeString(pttccNo);
        dest.writeLong(pttccDate);
        dest.writeDouble(pttdmAmount);
        dest.writeLong(dateCreated);
        dest.writeString(pttdmStatus);
    }

    @SuppressWarnings("unused")
    public static final Creator<PaidTypeTdm> CREATOR = new Creator<PaidTypeTdm>() {
        @Override
        public PaidTypeTdm createFromParcel(Parcel in) {
            return new PaidTypeTdm(in);
        }

        @Override
        public PaidTypeTdm[] newArray(int size) {
            return new PaidTypeTdm[size];
        }
    };
}
