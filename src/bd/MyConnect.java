package bd;

import java.sql.*;

public class MyConnect{
	private static String username="postgres";
	private static String password="mazoto"; // a changer
	private static String url="jdbc:postgresql://localhost:5432/gestion_vol";

	public static Connection getConnection() throws Exception {
		Connection c=null;
		Class.forName("org.postgresql.Driver");
		c=DriverManager.getConnection(url,username,password);
        c.setAutoCommit(false);
		return c;
	}
}
