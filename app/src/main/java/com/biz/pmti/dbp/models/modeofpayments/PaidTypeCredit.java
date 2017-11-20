package com.biz.pmti.dbp.models.modeofpayments;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pmti.ldonios.lib.utils.DateUtils;
import pmti.ldonios.lib.utils.DateUtils.DateFormat;

public class PaidTypeCredit implements Parcelable {
	@JsonProperty("ptcreditId") 						private long ptcreditId = 1;
	@JsonProperty("pmodeCode") 							private String pmodeCode = "";
	@JsonProperty("tpayReceiptNo") 						private String tpayReceiptNo = "";
	@JsonProperty("tpaySigAlgo") 						private String tpaySigAlgo = "";
	@JsonProperty("ptcreditCardNumber") 				private String ptcreditCardNumber = "";
	@JsonProperty("ptcreditAmount") 					private double ptcreditAmount = 0;
	@JsonProperty("dateCreated") 						private long dateCreated = 0;
	@JsonProperty("ptcreditStatus") 					private String ptcreditStatus = "";

    public long getPtcreditId() {
		return ptcreditId;
	}

	public void setPtcreditId(long ptcreditId) {
		this.ptcreditId = ptcreditId;
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

	public String getPtcreditCardNumber() {
		return ptcreditCardNumber;
	}

	public void setPtcreditCardNumber(String ptcreditCardNumber) {
		this.ptcreditCardNumber = ptcreditCardNumber;
	}

	public double getPtcreditAmount() {
		return ptcreditAmount;
	}

	public void setPtcreditAmount(double ptcreditAmount) {
		this.ptcreditAmount = ptcreditAmount;
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

	public String getPtcreditStatus() {
		return ptcreditStatus;
	}

	public void setPtcreditStatus(String ptcreditStatus) {
		this.ptcreditStatus = ptcreditStatus;
	}

	public static Creator<PaidTypeCredit> getCreator() {
		return CREATOR;
	}

	protected PaidTypeCredit(Parcel in) {
        ptcreditId = in.readLong();
        pmodeCode = in.readString();
        tpayReceiptNo = in.readString();
        tpaySigAlgo = in.readString();
        ptcreditCardNumber = in.readString();
        ptcreditAmount = in.readDouble();
        dateCreated = in.readLong();
        ptcreditStatus = in.readString();
    }

    public PaidTypeCredit() {
		
	}

	public PaidTypeCredit(long ptcreditId, String pmodeCode, String tpayReceiptNo,
			String tpaySigAlgo, String ptcreditCardNumber, double ptcreditAmount, String dateCreated,
			String ptcreditStatus) {
		
		this.ptcreditId 			= ptcreditId;
		this.pmodeCode 				= pmodeCode;
		this.tpayReceiptNo 			= tpayReceiptNo;
		this.tpaySigAlgo 			= tpaySigAlgo;
		this.ptcreditCardNumber 	= ptcreditCardNumber;
		this.ptcreditAmount 		= ptcreditAmount;
		this.dateCreated 			= dateCreated!=null ? DateUtils.simpledateToMillis(dateCreated, DateFormat.SIMPLE_DATE_5_FORMAT):0;
		this.ptcreditStatus 		= ptcreditStatus;
	}

	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(ptcreditId);
        dest.writeString(pmodeCode);
        dest.writeString(tpayReceiptNo);
        dest.writeString(tpaySigAlgo);
        dest.writeString(ptcreditCardNumber);
        dest.writeDouble(ptcreditAmount);
        dest.writeLong(dateCreated);
        dest.writeString(ptcreditStatus);
    }

    @SuppressWarnings("unused")
    public static final Creator<PaidTypeCredit> CREATOR = new Creator<PaidTypeCredit>() {
        @Override
        public PaidTypeCredit createFromParcel(Parcel in) {
            return new PaidTypeCredit(in);
        }

        @Override
        public PaidTypeCredit[] newArray(int size) {
            return new PaidTypeCredit[size];
        }
    };
}
