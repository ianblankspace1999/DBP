package com.biz.pmti.dbp.models.modeofpayments;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pmti.ldonios.lib.utils.DateUtils;
import pmti.ldonios.lib.utils.DateUtils.DateFormat;

public class PaidTypeTsacext implements Parcelable {
	@JsonProperty("pttsacextId") 								private long pttsacextId 		= 1;
	@JsonProperty("pmodeCode") 									private String pmodeCode 		= "";
	@JsonProperty("tpayReceiptNo") 								private String tpayReceiptNo 	= "";
	@JsonProperty("tpaySigAlgo") 								private String tpaySigAlgo 		= "";
	@JsonProperty("pttsacNo") 									private String pttsacNo 		= "";
	@JsonProperty("saroNo") 									private String saroNo 			= "";
	@JsonProperty("saroDate") 									private long saroDate 			= 0;
	@JsonProperty("dateCreated") 								private long dateCreated 		= 0;
	@JsonProperty("pttsacextStatus") 							private String pttsacextStatus 	= "";

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

	public long getPttsacextId() {
		return pttsacextId;
	}

	public void setPttsacextId(long pttsacextId) {
		this.pttsacextId = pttsacextId;
	}

	public String getSaroNo() {
		return saroNo;
	}

	public void setSaroNo(String saroNo) {
		this.saroNo = saroNo;
	}

	public long getSaroDate() {
		return saroDate;
	}

	public void setSaroDate(long saroDate) {
		this.saroDate = saroDate;
	}

	public String getPttsacextStatus() {
		return pttsacextStatus;
	}

	public void setPttsacextStatus(String pttsacextStatus) {
		this.pttsacextStatus = pttsacextStatus;
	}

	public static Creator<PaidTypeTsacext> getCreator() {
		return CREATOR;
	}

	protected PaidTypeTsacext(Parcel in) {
        pttsacextId = in.readLong();
        pmodeCode = in.readString();
        tpayReceiptNo = in.readString();
        tpaySigAlgo = in.readString();
        pttsacNo = in.readString();
        saroNo	= in.readString();
        saroDate = in.readLong();
        dateCreated = in.readLong();
        pttsacextStatus = in.readString();
    }

    public PaidTypeTsacext() {
		
	}

	public PaidTypeTsacext(long pttsacextId, String pmodeCode, String tpayReceiptNo,
			String tpaySigAlgo, String pttsacNo,String saroNo, String saroDate,
			String dateCreated, String pttsacextStatus) {
		this.pttsacextId = pttsacextId;
		this.pmodeCode = pmodeCode;
		this.tpayReceiptNo = tpayReceiptNo;
		this.tpaySigAlgo = tpaySigAlgo;
		this.pttsacNo = pttsacNo;
		this.saroNo = saroNo;
		this.saroDate = saroDate!=null ? DateUtils.simpledateToMillis(saroDate, DateFormat.SIMPLE_DATE_5_FORMAT):0;
		this.dateCreated = dateCreated!=null ? DateUtils.simpledateToMillis(dateCreated, DateFormat.SIMPLE_DATE_5_FORMAT):0;
		this.pttsacextStatus = pttsacextStatus;
	}

	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(pttsacextId);
        dest.writeString(pmodeCode);
        dest.writeString(tpayReceiptNo);
        dest.writeString(tpaySigAlgo);
        dest.writeString(pttsacNo);
        dest.writeString(saroNo);
        dest.writeLong(saroDate);
        dest.writeLong(dateCreated);
        dest.writeString(pttsacextStatus);
    }

    @SuppressWarnings("unused")
    public static final Creator<PaidTypeTsacext> CREATOR = new Creator<PaidTypeTsacext>() {
        @Override
        public PaidTypeTsacext createFromParcel(Parcel in) {
            return new PaidTypeTsacext(in);
        }

        @Override
        public PaidTypeTsacext[] newArray(int size) {
            return new PaidTypeTsacext[size];
        }
    };
}
