package com.triveni.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.triveni.beans.RegistrationBean;
import com.triveni.utility.JDBCHelper;

public class RegistrationDAO {
	Connection connection = JDBCHelper.connect();
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public int registerUSer(RegistrationBean registrationBean) {

		String query = "insert into registration (firstname,lastname,email,gender,address_1,address_2,phone,city,zip,state,country,registration_status,permanent_contact,term,year)"
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, registrationBean.getFirstname());
			pstmt.setString(2, registrationBean.getLastname());
			pstmt.setString(3, registrationBean.getEmail());
			pstmt.setString(4, registrationBean.getGender());
			pstmt.setString(5, registrationBean.getAddress1());
			pstmt.setString(6, registrationBean.getAddress2());
			pstmt.setString(7, registrationBean.getPhnNumber());
			pstmt.setString(8, registrationBean.getCity());
			pstmt.setString(9, registrationBean.getZip());
			pstmt.setString(10, registrationBean.getState());
			pstmt.setString(11, registrationBean.getCountry());
			pstmt.setString(12, "pending");
			pstmt.setString(13, registrationBean.getPermanentContact());
			pstmt.setString(14, registrationBean.getTerm());
			pstmt.setString(15, registrationBean.getYear());
			if (pstmt.executeUpdate() != 0) {
				pstmt = null;
				query = "select triveni_id from registration where email=?";
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, registrationBean.getEmail());
				rs = pstmt.executeQuery();
				System.out.println("select triveni_id from registration where email=" + registrationBean.getEmail());
				int triveniId;
				while (rs.next()) {
					triveniId = rs.getInt("triveni_id");
					query = "insert into login (triveni_id,temp_password,login_status,email) values (?,?,?,?)";
					pstmt = connection.prepareStatement(query);
					pstmt.setInt(1, triveniId);
					pstmt.setString(2, registrationBean.getTempPassowrd());
					pstmt.setString(3, "inactive");
					pstmt.setString(4, registrationBean.getEmail());
					System.out.println(query);
					if (pstmt.executeUpdate() != 0) {
						query="insert into person (triveni_id,firstname,lastname,email,gender,address_1,address_2,phone,city,zip,state,country,role,permanent_contact)"
								+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						pstmt = connection.prepareStatement(query);
						pstmt.setInt(1, triveniId);
						pstmt.setString(2, registrationBean.getFirstname());
						pstmt.setString(3, registrationBean.getLastname());
						pstmt.setString(4, registrationBean.getEmail());
						pstmt.setString(5, registrationBean.getGender());
						pstmt.setString(6, registrationBean.getAddress1());
						pstmt.setString(7, registrationBean.getAddress2());
						pstmt.setString(8, registrationBean.getPhnNumber());
						pstmt.setString(9, registrationBean.getCity());
						pstmt.setString(10, registrationBean.getZip());
						pstmt.setString(11, registrationBean.getState());
						pstmt.setString(12, registrationBean.getCountry());
						pstmt.setString(13, "student");
						pstmt.setString(14, registrationBean.getPermanentContact());
						pstmt.executeUpdate();
						return triveniId;
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

}
