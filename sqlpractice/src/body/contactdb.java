package body;
import java.sql.Connection;
import java.sql.DriverManager;

public class contactdb {
	private final static String Drivername = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/";
	private final static String name = "root";
	private final static String password = "root";
	private final static String database = "olxdb";
	
	public Connection getConnection() {
		/*String hag = "S001";
		int gun = Integer.parseInt(hag.substring(1, 4));
		System.out.println("HAG " + gun + " num " + hag.length());
		*/
		try {
			
			Class.forName(Drivername);
			Connection conn =   DriverManager.getConnection(
					URL
					+ database 
					+ "?autoReconnect=true&useSSL=false", name, password);
			
			return conn;
		}
		catch (Exception e) {
			System.out.println("Fail");
			return null;
		}
	}
	public static void main(String arg[]) {
		contactdb cn = new contactdb();
		cn.getConnection();
		contactservice cs = new contactservice(cn);
	}
}	
