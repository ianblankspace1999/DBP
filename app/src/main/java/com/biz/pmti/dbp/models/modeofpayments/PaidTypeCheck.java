package com.biz.pmti.dbp.models.modeofpayments;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pmti.ldonios.lib.utils.DateUtils;
import pmti.ldonios.lib.utils.DateUtils.DateFormat;

public class PaidTypeCheck implements Parcelable {
	
	@JsonProperty("ptchkId") 						private long ptchkId 			= 1;
	@JsonProperty("pmodeCode") 						private String pmodeCode 		= "";
	@JsonProperty("tpayReceiptNo") 					private String tpayReceiptNo 	= "";
	@JsonProperty("tpaySigAlgo") 					private String tpaySigAlgo 		= "";
	@JsonProperty("dbcCode") 						private String dbcCode 			= "";
	@JsonProperty("dbcAddress") 					private String dbcAddress 		= "";
	@JsonProperty("ptchkNumber") 					private String ptchkNumber 		= "";
	@JsonProperty("ptchkDate") 						private long ptchkDate 		    = 0;
	@JsonProperty("ptchkAmount") 					private double ptchkAmount 		= 0;
	@JsonProperty("dateCreated") 					private long dateCreated 		= 0;
	@JsonProperty("ptchkStatus") 					private String ptchkStatus 		= "";

	private String ptchkDateStr     = "";

	public String getPtchkDateStr() {
		return ptchkDateStr;
	}

	public void setPtchkDateStr(String ptchkDateStr) {
		this.ptchkDateStr = ptchkDateStr;
	}

	public long getPtchkId() {
		return ptchkId;
	}

	public void setPtchkId(long ptchkId) {
		this.ptchkId = ptchkId;
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

	public String getDbcCode() {
		return dbcCode;
	}

	public void setDbcCode(String dbcCode) {
		this.dbcCode = dbcCode;
	}

	public String getDbcAddress() {
		return dbcAddress;
	}

	public void setDbcAddress(String dbcAddress) {
		this.dbcAddress = dbcAddress;
	}

	public String getPtchkNumber() {
		return ptchkNumber;
	}

	public void setPtchkNumber(String ptchkNumber) {
		this.ptchkNumber = ptchkNumber;
	}

	public long getPtchkDate() {
		return ptchkDate;
	}

	public void setPtchkDate(long ptchkDate) {
		this.ptchkDate = ptchkDate;
	}
	@JsonIgnore
	public void setPtchkDate(String ptchkDate) {
		this.ptchkDate = DateUtils.simpledateToMillis(ptchkDate, DateFormat.SIMPLE_DATE_5_FORMAT);
	}

	public double getPtchkAmount() {
		return ptchkAmount;
	}

	public void setPtchkAmount(double ptchkAmount) {
		this.ptchkAmount = ptchkAmount;
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

	public String getPtchkStatus() {
		return ptchkStatus;
	}

	public void setPtchkStatus(String ptchkStatus) {
		this.ptchkStatus = ptchkStatus;
	}

	public static Creator<PaidTypeCheck> getCreator() {
		return CREATOR;
	}

	protected PaidTypeCheck(Parcel in) {
        ptchkId = in.readLong();
        pmodeCode = in.readString();
        tpayReceiptNo = in.readString();
        tpaySigAlgo = in.readString();
        dbcCode = in.readString();
        dbcAddress = in.readString();
        ptchkNumber = in.readString();
        ptchkDate = in.readLong();
        ptchkAmount = in.readDouble();
        dateCreated = in.readLong();
        ptchkStatus = in.readString();
    }

    public PaidTypeCheck() {
		
	}

	public PaidTypeCheck(long ptchkId, String pmodeCode, String tpayReceiptNo,
			String tpaySigAlgo, String dbcCode, String dbcAddress, String ptchkNumber,
			String ptchkDate, double ptchkAmount, String dateCreated, String ptchkStatus) {
		
		 	this.ptchkId = ptchkId;
	        this.pmodeCode = pmodeCode;
	        this.tpayReceiptNo = tpayReceiptNo;
	        this.tpaySigAlgo = tpaySigAlgo;
	        this.dbcCode = dbcCode;
	        this.dbcAddress = dbcAddress;
	        this.ptchkNumber = ptchkNumber;
	        this.ptchkDate = ptchkDate!=null ? DateUtils.simpledateToMillis(ptchkDate, DateFormat.SIMPLE_DATE_5_FORMAT):0;
			this.ptchkDateStr = ptchkDate;
	        this.ptchkAmount = ptchkAmount;
	        this.dateCreated = dateCreated!=null ? DateUtils.simpledateToMillis(dateCreated, DateFormat.SIMPLE_DATE_5_FORMAT):0;
	        this.ptchkStatus = ptchkStatus;
	}

	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(ptchkId);
        dest.writeString(pmodeCode);
        dest.writeString(tpayReceiptNo);
        dest.writeString(tpaySigAlgo);
        dest.writeString(dbcCode);
        dest.writeString(dbcAddress);
        dest.writeString(ptchkNumber);
        dest.writeLong(ptchkDate);
        dest.writeDouble(ptchkAmount);
        dest.writeLong(dateCreated);
        dest.writeString(ptchkStatus);
    }

    @SuppressWarnings("unused")
    public static final Creator<PaidTypeCheck> CREATOR = new Creator<PaidTypeCheck>() {
        @Override
        public PaidTypeCheck createFromParcel(Parcel in) {
            return new PaidTypeCheck(in);
        }

        @Override
        public PaidTypeCheck[] newArray(int size) {
            return new PaidTypeCheck[size];
        }
    };
}
