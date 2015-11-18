package com.triveni.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.triveni.beans.RegistrationBean;
import com.triveni.utility.JDBCHelper;

public class ProfileDAO {
	Connection connection = JDBCHelper.connect();
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public int updateProfile(String triveniId, RegistrationBean registrationBean) {
		String query = "update person set gender = ?, address_1 = ?, address_2 = ?, phone = ?, city = ?, zip = ?, state = ?, country = ?, permanent_contact=? where triveni_id = ?";
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, registrationBean.getGender());
			pstmt.setString(2, registrationBean.getAddress1());
			pstmt.setString(3, registrationBean.getAddress2());
			pstmt.setString(4, registrationBean.getPhnNumber());
			pstmt.setString(5, registrationBean.getCity());
			pstmt.setString(6, registrationBean.getZip());
			pstmt.setString(7, registrationBean.getState());
			pstmt.setString(8, registrationBean.getCountry());
			pstmt.setString(10, triveniId);
			pstmt.setString(9, registrationBean.getPermanentContact());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int changePassword(String triveniId, String currentPassword, String newPassword) {
		String query = "update login set password = ?, temp_password = ?, login_status = ? where triveni_id = ?";
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, newPassword);
			pstmt.setString(3, "active");
			pstmt.setString(4, triveniId);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int uploadItenary(String triveniId, String arrivalDate, String arrivalTime, String phone, String extraInfo, String fileStoragePath, String accommodationRequired) {
		String query = "insert into itenary (triveni_id,arrival_date,arrival_time,phone,extra_info,url,acc_req) values(?,?,?,?,?,?,?)";
		String startDate = arrivalDate;
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		java.util.Date arrivalDate1 = null;
		java.sql.Date newArrivalDate = null;
		try {
			arrivalDate1 = sdf.parse(startDate);
			newArrivalDate = new java.sql.Date(arrivalDate1.getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, triveniId);
			pstmt.setString(2, arrivalDate);
			pstmt.setString(3, arrivalTime);
			pstmt.setString(4, phone);
			pstmt.setString(5, extraInfo);
			pstmt.setString(6, fileStoragePath);
			pstmt.setString(7, accommodationRequired);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public int forgotPassword(String email, String tempPassword) {
		String query = "update login set temp_password = ? where email = ?";
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, tempPassword);
			pstmt.setString(2, email);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String fetchId(String email) {
		String query = "select triveni_id from login where email = ?";
		String triveniId = "";
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while(rs.next()){
				triveniId = rs.getString("triveni_id");
			}
			return triveniId;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
