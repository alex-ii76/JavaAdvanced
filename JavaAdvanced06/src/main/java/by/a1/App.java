package by.a1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class App {
	public final static String SELECT_FROM_USERS = "SELECT name, age FROM mybase.users ORDER BY ID";
	public final static String INSERT_INTO_USERS = "INSERT INTO users (name, age) VALUES (?,?)";

	public void InsertUser(String name, int age) {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Driver loaded");
		} catch (Exception ex) {
			System.out.println("Error Driver loaded");
		}

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mybase?" + "user=root&password=");

			System.out.println("Connected...");

			pstmt = conn.prepareStatement(INSERT_INTO_USERS);
			pstmt.setString(1,name);
			pstmt.setInt(2, age);
			pstmt.execute();
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {

			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				pstmt = null;
			}
		}

	}

	public  void SelectUsers() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Driver loaded");
		} catch (Exception ex) {
			System.out.println("Error Driver loaded");
		}

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mybase?" + "user=root&password=");

			System.out.println("Connected...");

			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_FROM_USERS);

			while (rs.next()) {
				System.out.println(rs.getString("name") + ":" + rs.getInt("age"));
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
		}

	}

	public static void main(String[] args) {
	
		App app=new App();
		app.SelectUsers();
		app.InsertUser("Bob", 33);
		app.SelectUsers();
		
	}
}
