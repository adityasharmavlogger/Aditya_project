package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.CustomerBean;

public class CustomerModel {

	public Integer nextPk() throws Exception {

		int pk = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");
		PreparedStatement ps = conn.prepareStatement("select max(id) from user");

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}
		return pk + 1;
	}

	public void add(CustomerBean bean) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");
		PreparedStatement ps = conn.prepareStatement("insert into user values(?, ?, ?, ?, ?, ?)");

		ps.setInt(1, nextPk());
		ps.setString(2, bean.getName());
		ps.setString(3, bean.getAmount());
		ps.setString(4, bean.getAccoountNumber());
		ps.setDate(5, new java.sql.Date(bean.getDob().getTime()));
		ps.setString(6, bean.getCustomerId());

		int i = ps.executeUpdate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println("Data inserted = " + i);

	}

	public void update(CustomerBean bean) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

		PreparedStatement ps = conn.prepareStatement(
				"update user set name = ?, amount = ?, accountnumber = ?, dob = ?, customer = ? where id = ?");

		ps.setString(1, bean.getName());
		ps.setString(2, bean.getAmount());
		ps.setString(3, bean.getAccoountNumber());
		ps.setDate(4, new java.sql.Date(bean.getDob().getTime()));
		ps.setString(5, bean.getCustomerId());
		ps.setInt(6, bean.getId());

		int i = ps.executeUpdate();

		System.out.println("Data updated = " + i);

	}

	public void delete(int id) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

		PreparedStatement ps = conn.prepareStatement("delete from user where id = ?");

		ps.setInt(1, id);

		int i = ps.executeUpdate();

		System.out.println("Data deleted = " + i);

	}

	public CustomerBean findByPk(int pk) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

		PreparedStatement ps = conn.prepareStatement("select * from user where id = ?");

		ps.setInt(1, pk);

		ResultSet rs = ps.executeQuery();

		CustomerBean bean = null;

		while (rs.next()) {
			bean = new CustomerBean();
			bean.setId(rs.getInt(1));
			bean.setName(rs.getString(2));
			bean.setAmount(rs.getString(3));
			bean.setAccoountNumber(rs.getString(4));
			bean.setDob(rs.getDate(5));
			bean.setCustomerId(rs.getString(6));
		}
		return bean;

	}

	public CustomerBean authenticate(String loginId, String password) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

		PreparedStatement ps = conn.prepareStatement("select * from user where loginId = ? and password = ?");

		ps.setString(1, loginId);
		ps.setString(2, password);

		ResultSet rs = ps.executeQuery();

		CustomerBean bean = null;

		while (rs.next()) {
			bean = new CustomerBean();

			bean.setId(rs.getInt(1));
			bean.setName(rs.getString(2));
			bean.setAmount(rs.getString(3));
			bean.setAccoountNumber(rs.getString(4));
			bean.setDob(rs.getDate(5));
			bean.setCustomerId(rs.getString(6));

		}
		return bean;

	}

	public List search(CustomerBean bean, int pageNo, int pageSize) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

		StringBuffer sql = new StringBuffer("select * from user where 1=1");

		if (bean != null) {

			if (bean.getName() != null && bean.getName().length() > 0) {

				sql.append(" and name like '" + bean.getName() + "%'");

			}

		}

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" limit " + pageNo + ", " + pageSize);

		}

		System.out.println("SQL ====>>> " + sql);

		PreparedStatement ps = conn.prepareStatement(sql.toString());

		ResultSet rs = ps.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {
			bean = new CustomerBean();

			bean.setId(rs.getInt(1));
			bean.setName(rs.getString(2));
			bean.setAmount(rs.getString(3));
			bean.setAccoountNumber(rs.getString(4));
			bean.setDob(rs.getDate(5));
			bean.setCustomerId(rs.getString(6));

			list.add(bean);
		}
		return list;

	}

}
