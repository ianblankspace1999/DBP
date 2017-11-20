package com.biz.pmti.dbp.models.modeofpayments;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pmti.ldonios.lib.utils.DateUtils;
import pmti.ldonios.lib.utils.DateUtils.DateFormat;

public class PaidTypeDebit implements Parcelable {
	@JsonProperty("ptdebitId") 							private long ptdebitId 			    = 1;
	@JsonProperty("pmodeCode") 							private String pmodeCode 			= "";
	@JsonProperty("tpayReceiptNo") 						private String tpayReceiptNo 		= "";
	@JsonProperty("tpaySigAlgo") 						private String tpaySigAlgo 			= "";
	@JsonProperty("ptdebitCardNumber") 					private String ptdebitCardNumber 	= "";
	@JsonProperty("ptdebitAmount") 						private double ptdebitAmount 		= 0;
	@JsonProperty("dateCreated") 						private long dateCreated 			= 0;
	@JsonProperty("ptdebitStatus") 						private String ptdebitStatus 		= "";

    public long getPtdebitId() {
		return ptdebitId;
	}

	public void setPtdebitId(long ptdebitId) {
		this.ptdebitId = ptdebitId;
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

	public String getPtdebitCardNumber() {
		return ptdebitCardNumber;
	}

	public void setPtdebitCardNumber(String ptdebitCardNumber) {
		this.ptdebitCardNumber = ptdebitCardNumber;
	}

	public double getPtdebitAmount() {
		return ptdebitAmount;
	}

	public void setPtdebitAmount(double ptdebitAmount) {
		this.ptdebitAmount = ptdebitAmount;
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

	public String getPtdebitStatus() {
		return ptdebitStatus;
	}

	public void setPtdebitStatus(String ptdebitStatus) {
		this.ptdebitStatus = ptdebitStatus;
	}

	public static Creator<PaidTypeDebit> getCreator() {
		return CREATOR;
	}

	protected PaidTypeDebit(Parcel in) {
        ptdebitId = in.readLong();
        pmodeCode = in.readString();
        tpayReceiptNo = in.readString();
        tpaySigAlgo = in.readString();
        ptdebitCardNumber = in.readString();
        ptdebitAmount = in.readDouble();
        dateCreated = in.readLong();
        ptdebitStatus = in.readString();
    }

    public PaidTypeDebit() {
		
	}

	public PaidTypeDebit(long ptdebitId, String pmodeCode, String tpayReceiptNo,
			String tpaySigAlgo, String ptdebitCardNumber, double ptdebitAmount, String dateCreated,
			String ptdebitStatus) {
		
		this.ptdebitId = ptdebitId;
        this.pmodeCode = pmodeCode;
        this.tpayReceiptNo = tpayReceiptNo;
        this.tpaySigAlgo = tpaySigAlgo;
        this.ptdebitCardNumber = ptdebitCardNumber;
        this.ptdebitAmount = ptdebitAmount;
        this.dateCreated = dateCreated!=null?DateUtils.simpledateToMillis(dateCreated, DateFormat.SIMPLE_DATE_5_FORMAT):0;
        this.ptdebitStatus = ptdebitStatus;
		
	}

	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(ptdebitId);
        dest.writeString(pmodeCode);
        dest.writeString(tpayReceiptNo);
        dest.writeString(tpaySigAlgo);
        dest.writeString(ptdebitCardNumber);
        dest.writeDouble(ptdebitAmount);
        dest.writeLong(dateCreated);
        dest.writeString(ptdebitStatus);
    }

    @SuppressWarnings("unused")
    public static final Creator<PaidTypeDebit> CREATOR = new Creator<PaidTypeDebit>() {
        @Override
        public PaidTypeDebit createFromParcel(Parcel in) {
            return new PaidTypeDebit(in);
        }

        @Override
        public PaidTypeDebit[] newArray(int size) {
            return new PaidTypeDebit[size];
        }
    };
}
