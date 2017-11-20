package com.biz.pmti.dbp.models.modeofpayments;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pmti.ldonios.lib.utils.DateUtils;
import pmti.ldonios.lib.utils.DateUtils.DateFormat;

public class PaidTypeDc implements Parcelable {
	@JsonProperty("ptdcId") 						private long ptdcId = 1;
	@JsonProperty("pmodeCode") 						private String pmodeCode = "";
	@JsonProperty("tpayReceiptNo") 					private String tpayReceiptNo = "";
	@JsonProperty("tpaySigAlgo") 					private String tpaySigAlgo = "";
	@JsonProperty("ptdcDate") 						private long ptdcDate = 0;
	@JsonProperty("ptdcAmount") 					private double ptdcAmount = 0;
	@JsonProperty("dateCreated") 					private long dateCreated = 0;
	@JsonProperty("ptdcStatus") 					private String ptdcStatus = "";

    public long getPtdcId() {
		return ptdcId;
	}

	public void setPtdcId(long ptdcId) {
		this.ptdcId = ptdcId;
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

	public long getPtdcDate() {
		return ptdcDate;
	}

	public void setPtdcDate(long ptdcDate) {
		this.ptdcDate = ptdcDate;
	}
	@JsonIgnore
	public void setPtdcDate(String ptdcDate) {
		this.ptdcDate = DateUtils.simpledateToMillis(ptdcDate, DateFormat.SIMPLE_DATE_5_FORMAT);
	}

	public double getPtdcAmount() {
		return ptdcAmount;
	}

	public void setPtdcAmount(double ptdcAmount) {
		this.ptdcAmount = ptdcAmount;
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

	public String getPtdcStatus() {
		return ptdcStatus;
	}

	public void setPtdcStatus(String ptdcStatus) {
		this.ptdcStatus = ptdcStatus;
	}

	public static Creator<PaidTypeDc> getCreator() {
		return CREATOR;
	}

	protected PaidTypeDc(Parcel in) {
        ptdcId = in.readLong();
        pmodeCode = in.readString();
        tpayReceiptNo = in.readString();
        tpaySigAlgo = in.readString();
        ptdcDate = in.readLong();
        ptdcAmount = in.readDouble();
        dateCreated = in.readLong();
        ptdcStatus = in.readString();
    }

    public PaidTypeDc() {
		
	}

	public PaidTypeDc(long ptdcId, String pmodeCode, String tpayReceiptNo,
			String tpaySigAlgo, String ptdcDate, double ptdcAmount, String dateCreated,
			String ptdcStatus) {
		
		this.ptdcId = ptdcId;
		this.pmodeCode = pmodeCode;
		this.tpayReceiptNo = tpayReceiptNo;
		this.tpaySigAlgo = tpaySigAlgo;
		this.ptdcDate = ptdcDate!=null?DateUtils.simpledateToMillis(ptdcDate, DateFormat.SIMPLE_DATE_5_FORMAT):0;
		this.ptdcAmount = ptdcAmount;
		this.dateCreated = dateCreated!=null?DateUtils.simpledateToMillis(dateCreated, DateFormat.SIMPLE_DATE_5_FORMAT):0;
		this.ptdcStatus = ptdcStatus;
		
	}

	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(ptdcId);
        dest.writeString(pmodeCode);
        dest.writeString(tpayReceiptNo);
        dest.writeString(tpaySigAlgo);
        dest.writeLong(ptdcDate);
        dest.writeDouble(ptdcAmount);
        dest.writeLong(dateCreated);
        dest.writeString(ptdcStatus);
    }

    @SuppressWarnings("unused")
    public static final Creator<PaidTypeDc> CREATOR = new Creator<PaidTypeDc>() {
        @Override
        public PaidTypeDc createFromParcel(Parcel in) {
            return new PaidTypeDc(in);
        }

        @Override
        public PaidTypeDc[] newArray(int size) {
            return new PaidTypeDc[size];
        }
    };
}
