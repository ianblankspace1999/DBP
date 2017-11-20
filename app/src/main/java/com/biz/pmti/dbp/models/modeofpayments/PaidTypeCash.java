package com.biz.pmti.dbp.models.modeofpayments;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pmti.ldonios.lib.utils.DateUtils;
import pmti.ldonios.lib.utils.DateUtils.DateFormat;

public class PaidTypeCash implements Parcelable {

	@JsonProperty("ptcashId") 						private long ptcashId 			=   1;
	@JsonProperty("pmodeCode") 						private String pmodeCode 		= "";
	@JsonProperty("tpayReceiptNo") 					private String tpayReceiptNo 	= "";
	@JsonProperty("tpaySigAlgo") 					private String tpaySigAlgo 		= "";
	@JsonProperty("ptcashAmount") 					private double ptcashAmount 	= 0;
	@JsonProperty("dateCreated") 					private long dateCreated 		= 0;
	@JsonProperty("ptcashStatus") 					private String ptcashStatus 	= "";
	
	public PaidTypeCash(long ptcashId, String pmodeCode, String tpayReceiptNo,
			String tpaySigAlgo, double ptcashAmount, String dateCreated, String ptcashStatus) {
		
		this.ptcashId = ptcashId;
		this.pmodeCode = pmodeCode;
		this.tpayReceiptNo = tpayReceiptNo;
		this.tpaySigAlgo = tpaySigAlgo;
		this.ptcashAmount = ptcashAmount;
		this.dateCreated = dateCreated != null ?DateUtils.simpledateToMillis(dateCreated, DateFormat.SIMPLE_DATE_5_FORMAT):0;
		this.ptcashStatus = ptcashStatus;
	}
	
	public PaidTypeCash(){}

    public long getPtcashId() {
		return ptcashId;
	}

	public void setPtcashId(long ptcashId) {
		this.ptcashId = ptcashId;
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

	public double getPtcashAmount() {
		return ptcashAmount;
	}

	public void setPtcashAmount(double ptcashAmount) {
		this.ptcashAmount = ptcashAmount;
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

	public String getPtcashStatus() {
		return ptcashStatus;
	}

	public void setPtcashStatus(String ptcashStatus) {
		this.ptcashStatus = ptcashStatus;
	}

	public static Creator<PaidTypeCash> getCreator() {
		return CREATOR;
	}

	protected PaidTypeCash(Parcel in) {
        ptcashId = in.readLong();
        pmodeCode = in.readString();
        tpayReceiptNo = in.readString();
        tpaySigAlgo = in.readString();
        ptcashAmount = in.readDouble();
        dateCreated = in.readLong();
        ptcashStatus = in.readString();
    }

    

	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(ptcashId);
        dest.writeString(pmodeCode);
        dest.writeString(tpayReceiptNo);
        dest.writeString(tpaySigAlgo);
        dest.writeDouble(ptcashAmount);
        dest.writeLong(dateCreated);
        dest.writeString(ptcashStatus);
    }

    @SuppressWarnings("unused")
    public static final Creator<PaidTypeCash> CREATOR = new Creator<PaidTypeCash>() {
        @Override
        public PaidTypeCash createFromParcel(Parcel in) {
            return new PaidTypeCash(in);
        }

        @Override
        public PaidTypeCash[] newArray(int size) {
            return new PaidTypeCash[size];
        }
    };
}
