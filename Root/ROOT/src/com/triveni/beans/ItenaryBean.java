package com.triveni.beans;

import java.sql.Date;

public class ItenaryBean {

	private String triveniId;
	private String arrivalDate;
	private String arrivalTime;
	private String phonenumber;
	private String extraInfo;
	private String url;
	private String accommodationRequired;
	public String getTriveniId() {
		return triveniId;
	}
	public void setTriveniId(String triveniId) {
		this.triveniId = triveniId;
	}
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String date) {
		this.arrivalDate = date;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAccommodationRequired() {
		return accommodationRequired;
	}
	public void setAccommodationRequired(String accommodationRequired) {
		this.accommodationRequired = accommodationRequired;
	}
}
