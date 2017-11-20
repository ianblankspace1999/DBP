package com.biz.pmti.dbp.models.modeofpayments;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pmti.ldonios.lib.utils.DateUtils;
import pmti.ldonios.lib.utils.DateUtils.DateFormat;

public class PaidTypeTsac implements Parcelable {
	@JsonProperty("pttsacId") 										private long pttsacId 			= 1;
	@JsonProperty("pmodeCode") 										private String pmodeCode 		= "";
	@JsonProperty("tpayReceiptNo") 									private String tpayReceiptNo 	= "";
	@JsonProperty("tpaySigAlgo") 									private String tpaySigAlgo 		= "";
	@JsonProperty("pttsacNo") 										private String pttsacNo 		= "";
	@JsonProperty("pttsacDate") 									private long pttsacDate 		= 0;
	@JsonProperty("pttsacAmount") 									private double pttsacAmount 	= 0;
	@JsonProperty("dateCreated") 									private long dateCreated 		= 0;
	@JsonProperty("pttsacStatus") 									private String pttsacStatus 	= "";

    public long getPttsacId() {
		return pttsacId;
	}

	public void setPttsacId(long pttsacId) {
		this.pttsacId = pttsacId;
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

	public String getPttsacNo() {
		return pttsacNo;
	}

	public void setPttsacNo(String pttsacNo) {
		this.pttsacNo = pttsacNo;
	}

	public long getPttsacDate() {
		return pttsacDate;
	}

	public void setPttsacDate(long pttsacDate) {
		this.pttsacDate = pttsacDate;
	}
	@JsonIgnore
	public void setPttsacDate(String pttsacDate) {
		this.pttsacDate = DateUtils.simpledateToMillis(pttsacDate, DateFormat.SIMPLE_DATE_5_FORMAT);
	}

	public double getPttsacAmount() {
		return pttsacAmount;
	}

	public void setPttsacAmount(double pttsacAmount) {
		this.pttsacAmount = pttsacAmount;
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

	public String getPttsacStatus() {
		return pttsacStatus;
	}

	public void setPttsacStatus(String pttsacStatus) {
		this.pttsacStatus = pttsacStatus;
	}

	public static Creator<PaidTypeTsac> getCreator() {
		return CREATOR;
	}

	protected PaidTypeTsac(Parcel in) {
        pttsacId = in.readLong();
        pmodeCode = in.readString();
        tpayReceiptNo = in.readString();
        tpaySigAlgo = in.readString();
        pttsacNo = in.readString();
        pttsacDate = in.readLong();
        pttsacAmount = in.readDouble();
        dateCreated = in.readLong();
        pttsacStatus = in.readString();
    }

    public PaidTypeTsac() {
		
	}

	public PaidTypeTsac(long pttsacId, String pmodeCode, String tpayReceiptNo,
			String tpaySigAlgo, String pttsacNo, String pttsacDate, double pttsacAmount,
			String dateCreated, String pttsacStatus) {
		this.pttsacId = pttsacId;
		this.pmodeCode = pmodeCode;
		this.tpayReceiptNo = tpayReceiptNo;
		this.tpaySigAlgo = tpaySigAlgo;
		this.pttsacNo = pttsacNo;
		this.pttsacDate = pttsacDate!=null ? DateUtils.simpledateToMillis(pttsacDate, DateFormat.SIMPLE_DATE_5_FORMAT):0;
		this.pttsacAmount = pttsacAmount;
		this.dateCreated = dateCreated!=null ? DateUtils.simpledateToMillis(dateCreated, DateFormat.SIMPLE_DATE_5_FORMAT):0;
		this.pttsacStatus = pttsacStatus;
	}

	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(pttsacId);
        dest.writeString(pmodeCode);
        dest.writeString(tpayReceiptNo);
        dest.writeString(tpaySigAlgo);
        dest.writeString(pttsacNo);
        dest.writeLong(pttsacDate);
        dest.writeDouble(pttsacAmount);
        dest.writeLong(dateCreated);
        dest.writeString(pttsacStatus);
    }

    @SuppressWarnings("unused")
    public static final Creator<PaidTypeTsac> CREATOR = new Creator<PaidTypeTsac>() {
        @Override
        public PaidTypeTsac createFromParcel(Parcel in) {
            return new PaidTypeTsac(in);
        }

        @Override
        public PaidTypeTsac[] newArray(int size) {
            return new PaidTypeTsac[size];
        }
    };
}
