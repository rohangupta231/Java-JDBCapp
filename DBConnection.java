import java.sql.Connection;
import java.sql.DriverManager;
class DBConnection
{
	public static Connection connect()
	{
		Connection con=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql:///empdb";
			con=DriverManager.getConnection(url,"root","");
		}
		catch(Exception e)
		{
			System.out.println("Exception in connect()"+e);
		}
		return con;
	}
}