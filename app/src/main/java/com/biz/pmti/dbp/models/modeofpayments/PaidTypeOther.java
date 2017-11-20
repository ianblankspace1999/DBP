package com.biz.pmti.dbp.models.modeofpayments;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pmti.ldonios.lib.utils.DateUtils;
import pmti.ldonios.lib.utils.DateUtils.DateFormat;

public class PaidTypeOther implements Parcelable {
	@JsonProperty("ptmoId") 								private long ptoId 				= 1;
	@JsonProperty("pmodeCode") 								private String pmodeCode 		= "";
	@JsonProperty("tpayReceiptNo") 							private String tpayReceiptNo 	= "";
	@JsonProperty("tpaySigAlgo") 							private String tpaySigAlgo 		= "";
	@JsonProperty("ptoDate") 								private long ptoDate 			= 0;
	@JsonProperty("ptoAmount") 								private double ptoAmount 		= 0;
	@JsonProperty("ptoRemarks") 							private String ptoRemarks 		= "";
	@JsonProperty("dateCreated") 							private long dateCreated 		= 0;
	@JsonProperty("ptoStatus") 								private String ptoStatus 		= "";

    public long getPtoId() {
		return ptoId;
	}

	public void setPtoId(long ptoId) {
		this.ptoId = ptoId;
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

	public long getPtoDate() {
		return ptoDate;
	}

	public void setPtoDate(long ptoDate) {
		this.ptoDate = ptoDate;
	}
	@JsonIgnore
	public void setPtoDate(String ptoDate) {
		this.ptoDate = DateUtils.simpledateToMillis(ptoDate, DateFormat.SIMPLE_DATE_5_FORMAT);
	}

	public double getPtoAmount() {
		return ptoAmount;
	}

	public void setPtoAmount(double ptoAmount) {
		this.ptoAmount = ptoAmount;
	}

	public String getPtoRemarks() {
		return ptoRemarks;
	}

	public void setPtoRemarks(String ptoRemarks) {
		this.ptoRemarks = ptoRemarks;
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

	public String getPtoStatus() {
		return ptoStatus;
	}

	public void setPtoStatus(String ptoStatus) {
		this.ptoStatus = ptoStatus;
	}

	public static Creator<PaidTypeOther> getCreator() {
		return CREATOR;
	}

	protected PaidTypeOther(Parcel in) {
        ptoId = in.readLong();
        pmodeCode = in.readString();
        tpayReceiptNo = in.readString();
        tpaySigAlgo = in.readString();
        ptoDate = in.readLong();
        ptoAmount = in.readDouble();
        ptoRemarks = in.readString();
        dateCreated = in.readLong();
        ptoStatus = in.readString();
    }

    public PaidTypeOther() {
		
	}

	public PaidTypeOther(long ptoId, String pmodeCode, String tpayReceiptNo,
			String tpaySigAlgo, String ptoDate, double ptoAmount, String ptoRemarks,
			String dateCreated, String ptoStatus) {
		
		this.ptoId = ptoId;
        this.pmodeCode = pmodeCode;
        this.tpayReceiptNo = tpayReceiptNo;
        this.tpaySigAlgo = tpaySigAlgo;
        this.ptoDate = ptoDate!=null?DateUtils.simpledateToMillis(ptoDate, DateFormat.SIMPLE_DATE_5_FORMAT):0;
        this.ptoAmount = ptoAmount;
        this.ptoRemarks = ptoRemarks;
        this.dateCreated = dateCreated!=null?DateUtils.simpledateToMillis(dateCreated, DateFormat.SIMPLE_DATE_5_FORMAT):0;
        this.ptoStatus = ptoStatus;
	}

	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(ptoId);
        dest.writeString(pmodeCode);
        dest.writeString(tpayReceiptNo);
        dest.writeString(tpaySigAlgo);
        dest.writeLong(ptoDate);
        dest.writeDouble(ptoAmount);
        dest.writeString(ptoRemarks);
        dest.writeLong(dateCreated);
        dest.writeString(ptoStatus);
    }

    @SuppressWarnings("unused")
    public static final Creator<PaidTypeOther> CREATOR = new Creator<PaidTypeOther>() {
        @Override
        public PaidTypeOther createFromParcel(Parcel in) {
            return new PaidTypeOther(in);
        }

        @Override
        public PaidTypeOther[] newArray(int size) {
            return new PaidTypeOther[size];
        }
    };
}
