package com.zinnaworks.daily.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zinnaworks.daily.constant.Const;
import com.zinnaworks.daily.vo.DailyVO;
public class OracleDailyDao extends DailyDao{

	private Connection conn;
	private Statement stat;

	public OracleDailyDao() {
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PW);
			stat = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean insertDaily(DailyVO info) {
		// TODO Auto-generated method stub
		try {
			String query = "INSERT INTO DAILY VALUES('" + info.getName() + "','" + info.getEmail() + "','"
					+ info.getDetails() + "'," + info.getAge() + ")";
			stat.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteDaily(DailyVO info) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM DAILY WHERE NAME ='" + info.getName() + "' and DETAILS = '" + info.getDetails()
				+ "' and EMAIL ='" + info.getEmail() + "'and AGE =" + info.getAge() + "";
		try {
			stat.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateDaily(DailyVO info) {
		// TODO Auto-generated method stub
		String query = "UPDATE DAILY SET DETAILS = '" + info.getDetails() + "' WHERE NAME = '" + info.getName()
				+ "'and EMAIL = '" + info.getEmail() + "'";
		try {
			stat.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Object> searchDaily(DailyVO info) {
		List<Object> list = new ArrayList<Object>();
		String query = "SELECT * FROM DAILY WHERE NAME = '" +info.getName() +"'" ;
		try {
			ResultSet rs = stat.executeQuery(query);
			
			while(rs.next()){
				String name = rs.getString(1);
				String email = rs.getString(2);
				String details = rs.getString(3);
				String age = rs.getString(4);
				
				DailyVO rsInfo = new DailyVO();
				rsInfo.setName(name);
				rsInfo.setDetails(details);
				rsInfo.setEmail(email);
				rsInfo.setAge(age);
				
				list.add(rsInfo);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Object> listAll() {
		List<Object> list = new ArrayList<Object>();
		String query = "SELECT * FROM DAILY" ;
		try {
			ResultSet rs = stat.executeQuery(query);
			
			while(rs.next()){
				String name = rs.getString(1);
				String email = rs.getString(2);
				String details = rs.getString(3);
				String age = rs.getString(4);
				
				DailyVO rsInfo = new DailyVO();
				rsInfo.setName(name);
				rsInfo.setDetails(details);
				rsInfo.setEmail(email);
				rsInfo.setAge(age);
				
				list.add(rsInfo);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		try {
			conn.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
