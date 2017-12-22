package com.biz.pmti.dbp.models.modeofpayments;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pmti.ldonios.lib.utils.DateUtils;
import pmti.ldonios.lib.utils.DateUtils.DateFormat;

public class PaidTypeMo implements Parcelable {
	@JsonProperty("ptmoId") 								private long ptmoId 			= 1;	
	@JsonProperty("pmodeCode") 								private String pmodeCode 		= "";
	@JsonProperty("tpayReceiptNo") 							private String tpayReceiptNo 	= "";
	@JsonProperty("tpaySigAlgo") 							private String tpaySigAlgo 		= "";
	@JsonProperty("ptmoNo") 								private String ptmoNo 			= "";
	@JsonProperty("ptmoDate") 								private long ptmoDate 			= 0;
	@JsonProperty("ptmoAmount") 							private double ptmoAmount 		= 0;
	@JsonProperty("dateCreated") 							private long dateCreated 		= 0;
	@JsonProperty("ptmoStatus") 							private String ptmoStatus 		= "";

	private String ptchkDateStr     = "";

	public String getPtchkDateStr() {
		return ptchkDateStr;
	}

	public void setPtchkDateStr(String ptchkDateStr) {
		this.ptchkDateStr = ptchkDateStr;
	}

    public long getPtmoId() {
		return ptmoId;
	}

	public void setPtmoId(long ptmoId) {
		this.ptmoId = ptmoId;
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

	public String getPtmoNo() {
		return ptmoNo;
	}

	public void setPtmoNo(String ptmoNo) {
		this.ptmoNo = ptmoNo;
	}

	public long getPtmoDate() {
		return ptmoDate;
	}

	public void setPtmoDate(long ptmoDate) {
		this.ptmoDate = ptmoDate;
	}
	@JsonIgnore
	public void setPtmoDate(String ptmoDate) {
		this.ptmoDate = DateUtils.simpledateToMillis(ptmoDate, DateFormat.SIMPLE_DATE_5_FORMAT);
	}

	public double getPtmoAmount() {
		return ptmoAmount;
	}

	public void setPtmoAmount(double ptmoAmount) {
		this.ptmoAmount = ptmoAmount;
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

	public String getPtmoStatus() {
		return ptmoStatus;
	}

	public void setPtmoStatus(String ptmoStatus) {
		this.ptmoStatus = ptmoStatus;
	}

	public static Creator<PaidTypeMo> getCreator() {
		return CREATOR;
	}

	protected PaidTypeMo(Parcel in) {
        ptmoId = in.readLong();
        pmodeCode = in.readString();
        tpayReceiptNo = in.readString();
        tpaySigAlgo = in.readString();
        ptmoNo = in.readString();
        ptmoDate = in.readLong();
        ptmoAmount = in.readDouble();
        dateCreated = in.readLong();
        ptmoStatus = in.readString();
    }

    public PaidTypeMo() {
		// TODO Auto-generated constructor stub
	}

	public PaidTypeMo(long ptmoId, String pmodeCode, String tpayReceiptNo,
			String tpaySigAlgo, String ptmoNo, long ptmoDate, double ptmoAmount,
			String dateCreated, String ptmoStatus) {
		
		this.ptmoId = ptmoId;
		this.pmodeCode = pmodeCode;
		this.tpayReceiptNo = tpayReceiptNo;
		this.tpaySigAlgo = tpaySigAlgo;
		this.ptmoNo = ptmoNo;
		this.ptmoDate = ptmoDate;
		this.ptmoAmount = ptmoAmount;
		this.dateCreated = dateCreated!=null?DateUtils.simpledateToMillis(dateCreated, DateFormat.SIMPLE_DATE_5_FORMAT):0;
		this.ptmoStatus = ptmoStatus;
		
	}

	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(ptmoId);
        dest.writeString(pmodeCode);
        dest.writeString(tpayReceiptNo);
        dest.writeString(tpaySigAlgo);
        dest.writeString(ptmoNo);
        dest.writeLong(ptmoDate);
        dest.writeDouble(ptmoAmount);
        dest.writeLong(dateCreated);
        dest.writeString(ptmoStatus);
    }

    @SuppressWarnings("unused")
    public static final Creator<PaidTypeMo> CREATOR = new Creator<PaidTypeMo>() {
        @Override
        public PaidTypeMo createFromParcel(Parcel in) {
            return new PaidTypeMo(in);
        }

        @Override
        public PaidTypeMo[] newArray(int size) {
            return new PaidTypeMo[size];
        }
    };
}
