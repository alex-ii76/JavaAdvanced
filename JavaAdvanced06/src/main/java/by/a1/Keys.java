package by.a1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Keys {

	public final static String SELECT_FROM_KEYS = "SELECT key, value FROM mybase.keys ORDER BY ID";
	public final static String INSERT_INTO_KEYS = "INSERT INTO mybase.keys (key, value) VALUES (?,?)";

	private String srtConnection;
	private PropConnection propConnection;

	public Keys() {
		super();
		propConnection = new PropConnection();
		this.srtConnection = String.format("jdbc:mysql://%s?user=%s&password=%s", propConnection.getHost(),
				propConnection.getUser(), propConnection.getPassword());
	}

	public void addKeys() {

		try (Scanner scanner = new Scanner(System.in);) {
			do {

				System.out.println("Enter Key");
				String key = scanner.nextLine();
				// System.out.println("key:"+key);
				System.out.println("Enter Value");
				String value = scanner.nextLine();
				// System.out.println("value: "+value);
				InsertKey(key, value);
				System.out.println("Do you want add one more key (y/n)");
				String yn = scanner.nextLine();
				if (!yn.toUpperCase().equals("Y")) {
					// System.out.println(yn.toUpperCase() == "Y");
					break;
				}
			} while (true);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void InsertKey(String key, String value) {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Driver loaded");
		} catch (Exception ex) {
			System.out.println("Error Driver loaded");
		}

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DriverManager.getConnection(this.srtConnection);
			System.out.println("Connected...");
			pstmt = conn.prepareStatement(INSERT_INTO_KEYS);
			pstmt.setString(1, key);
			pstmt.setString(2, value);
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
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				conn = null;
			}
		}
	}

	public void SelecKeys() {

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
			conn = DriverManager.getConnection(this.srtConnection);
			System.out.println("Connected...");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_FROM_KEYS);

			while (rs.next()) {
				System.out.println(rs.getString("key") + ":" + rs.getInt("value"));
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
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				conn = null;
			}
		}
	} // END SelecKeys

}
