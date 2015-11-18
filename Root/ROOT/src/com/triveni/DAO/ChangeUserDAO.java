package com.triveni.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.triveni.beans.EventBean;
import com.triveni.beans.PhotoBean;
import com.triveni.utility.JDBCHelper;

public class ChangeUserDAO {
	Connection connection = JDBCHelper.connect();
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public int changeUser(String triveniId, String userRole) {
		String query = "update person set role = ? where triveni_id = ? and role <> 'administrator'";
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, userRole);
			pstmt.setString(2, triveniId);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int makePayment(String triveniId, String paymentDate, String paymentAmount) {
		String query = "insert into payment (triveni_id,paid_amount,paid_on,entered_on) values (?,?,?,CURDATE())";
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		java.util.Date payDate = null;
		java.sql.Date newPayDate = null;
		try {
			payDate = sdf.parse(paymentDate);
			newPayDate = new java.sql.Date(payDate.getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, triveniId);
			pstmt.setString(2, paymentAmount);
			pstmt.setDate(3, newPayDate);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int generateBill(String billAmount, String billDate, String purpose, String fileStoragePath) {
		String query = "insert into bill (bill_amount,purpose,bill_date,entered_date,url) values (?,?,?,CURDATE(),?)";
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		java.util.Date payDate = null;
		java.sql.Date newPayDate = null;
		try {
			payDate = sdf.parse(billDate);
			newPayDate = new java.sql.Date(payDate.getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, billAmount);
			pstmt.setString(2, purpose);
			pstmt.setDate(3, newPayDate);
			pstmt.setString(4, fileStoragePath);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int addEvent(EventBean eventBean) {
		String query = "insert into events (event_name,description,venue,event_date,event_time) values (?,?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		java.util.Date payDate = null;
		java.sql.Date newPayDate = null;
		try {
			payDate = sdf.parse(eventBean.getEventDate());
			newPayDate = new java.sql.Date(payDate.getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, eventBean.getEventName());
			pstmt.setString(2, eventBean.getEventDescription());
			pstmt.setString(3, eventBean.getVenue());
			pstmt.setDate(4, newPayDate);
			pstmt.setString(5, eventBean.getEventTime());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int deleteEvent(String eventId) {
		String query = "delete from events where event_id = ?";
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, eventId);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public int updateEvent(EventBean eventBean) {
		String query = "update events set event_date = ?, event_name=?, description=?,venue=?,event_time=? where event_id = ?";
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		java.util.Date payDate = null;
		java.sql.Date newPayDate = null;
		try {
			payDate = sdf.parse(eventBean.getEventDate());
			newPayDate = new java.sql.Date(payDate.getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setDate(1, newPayDate);
			pstmt.setString(2, eventBean.getEventName());
			pstmt.setString(3, eventBean.getEventDescription());
			pstmt.setString(4, eventBean.getVenue());
			pstmt.setString(5, eventBean.getEventTime());
			pstmt.setInt(6, eventBean.getEventId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int uploadPhotos(PhotoBean photoBean) {
		String query = "insert into gallery (name,description,url,posted_date) values (?,?,?,CURDATE())";
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, photoBean.getPhotoName());
			pstmt.setString(2, photoBean.getDescription());
			pstmt.setString(3, photoBean.getUrl());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int updatePhotos(PhotoBean photoBean) {
		String query = "update gallery set name = ?, description = ?, url = ?, posted_date = CURDATE()";
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, photoBean.getPhotoName());
			pstmt.setString(2, photoBean.getDescription());
			pstmt.setString(3, photoBean.getUrl());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public int deletePhoto(String photoId) {
		String query = "delete from gallery where photo_id = ?";
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, photoId);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
