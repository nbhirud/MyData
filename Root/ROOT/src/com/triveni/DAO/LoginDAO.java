package com.triveni.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.triveni.beans.BillBean;
import com.triveni.beans.EventBean;
import com.triveni.beans.ItenaryBean;
import com.triveni.beans.PaymentBean;
import com.triveni.beans.PhotoBean;
import com.triveni.beans.RegistrationBean;
import com.triveni.utility.JDBCHelper;

public class LoginDAO {
	Connection connection = JDBCHelper.connect();
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public String checkLogin(String triveniId, String password) {
		String query = "select * from login where triveni_id = ? and (password = ? or temp_password = ?)";
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, triveniId);
			pstmt.setString(2, password);
			pstmt.setString(3, password);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("triveni_id") == Integer.parseInt(triveniId)) {
					System.out.println(triveniId);
					return triveniId;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public RegistrationBean fetchUserDetails(String triveniId) {
		String query = "select * from person where triveni_id=?";
		RegistrationBean registrationBean = new RegistrationBean();
		rs = null;
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, triveniId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				registrationBean.setFirstname(rs.getString("firstname"));
				registrationBean.setLastname(rs.getString("lastname"));
				registrationBean.setEmail(rs.getString("email"));
				registrationBean.setGender(rs.getString("gender"));
				registrationBean.setAddress1(rs.getString("address_1"));
				registrationBean.setAddress2(rs.getString("address_2"));
				registrationBean.setPhnNumber(rs.getString("phone"));
				registrationBean.setCity(rs.getString("city"));
				registrationBean.setZip(rs.getString("zip"));
				registrationBean.setState(rs.getString("state"));
				registrationBean.setCountry(rs.getString("country"));
				registrationBean.setUserRole(rs.getString("role"));
				registrationBean.setPermanentContact(rs.getString("permanent_contact"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registrationBean;
	}

	public ItenaryBean checkItenaryDetails(String triveniId) {
		String query = "select * from itenary where triveni_id = ?";
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, triveniId);
			rs = pstmt.executeQuery();
			ItenaryBean itenaryBean = new ItenaryBean();
			while(rs.next()){
				itenaryBean.setTriveniId((rs.getInt("triveni_id")+""));
				itenaryBean.setArrivalTime(rs.getString("arrival_time"));
				itenaryBean.setArrivalDate(rs.getString("arrival_date"));
				itenaryBean.setPhonenumber(rs.getString("phone"));
				itenaryBean.setExtraInfo(rs.getString("extra_info"));
				itenaryBean.setUrl(rs.getString("url"));
				itenaryBean.setAccommodationRequired(rs.getString("acc_req"));
			}
			return itenaryBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<BillBean> fetchBillsData() {
		ArrayList<BillBean> billBeans = new ArrayList<BillBean>();;
		String query = "select * from bill order by bill_date desc";
		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BillBean billBean = new BillBean();
				billBean.setBillAmount(rs.getString("bill_amount"));
				billBean.setBillDate(rs.getDate("bill_date"));
				billBean.setEnteredDate(rs.getDate("entered_date"));
				billBean.setPurpose(rs.getString("purpose"));
				billBean.setUrl(rs.getString("url"));
				billBeans.add(billBean);
			}
			return billBeans;
		}
	 catch (SQLException e) {
		e.printStackTrace();
	}
		return billBeans;
	}

	public ArrayList<EventBean> fetchEvents() {
		ArrayList<EventBean> eventBeans = new ArrayList<EventBean>();;
		String query = "select * from events order by event_date desc";
		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()){
				EventBean eventBean = new EventBean();
				eventBean.setEventDate(rs.getString("event_date"));
				eventBean.setEventDescription(rs.getString("description"));
				eventBean.setEventId(rs.getInt("event_id"));
				eventBean.setVenue(rs.getString("venue"));
				eventBean.setEventName(rs.getString("event_name"));
				eventBean.setEventTime(rs.getString("event_time"));
				eventBeans.add(eventBean);
			}
			return eventBeans;
		}
	 catch (SQLException e) {
		e.printStackTrace();
	}
		return eventBeans;
	}

	public ArrayList<PhotoBean> fetchPhotos() {
		String query = "select * from gallery order by posted_date";
		ArrayList<PhotoBean> photoBeans = new ArrayList<PhotoBean>();
		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()){
				PhotoBean photoBean = new PhotoBean();
				photoBean.setPhotoId(rs.getString("photo_id"));
				photoBean.setPhotoName(rs.getString("name"));
				photoBean.setPostedDate(rs.getString("posted_date"));
				photoBean.setDescription(rs.getString("description"));
				photoBean.setUrl(rs.getString("url"));
				photoBeans.add(photoBean);
			}
			return photoBeans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<PaymentBean> fetchPayment(String triveniId) {
		String query = "select * from payment where triveni_id = ?";
		try {
			ArrayList<PaymentBean> paymentBeans = new ArrayList<PaymentBean>();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, triveniId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				PaymentBean paymentBean = new PaymentBean();
				paymentBean.setPaidAmount(rs.getString("paid_amount"));
				paymentBean.setPaidOn(rs.getString("paid_on"));
				paymentBeans.add(paymentBean);
			}
			return paymentBeans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
