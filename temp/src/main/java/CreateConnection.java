

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateConnection {
	public static Connection getConnectionObj() {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tiffin_service_db","root","ucs22m1018");
			return con;
		}
		catch(Exception e) {
			System.out.println(e);
			return con;
		}
		
	}
	public static void main(String args[]) {
		Connection con=CreateConnection.getConnectionObj();
		if(con!=null) {
			System.out.println("Success");
		}
	}
}
