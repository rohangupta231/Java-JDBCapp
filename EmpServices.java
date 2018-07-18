import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
class EmpServices
{
	private static ArrayList<EmpBean> al;//Data Layer
	static
	{
		al=new ArrayList<EmpBean>();
	}
	public static boolean addEmployee(EmpBean objbean)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			con=DBConnection.connect();
			pstmt=con.prepareStatement("insert into empmaster (id,name,salary) values(?,?,?)");
			pstmt.setInt(1,objbean.getEmpId());
			pstmt.setString(2,objbean.getEmpName());
			pstmt.setDouble(3,objbean.getEmpSalary());
			int i=pstmt.executeUpdate();
			if(i>0)
			{
				return true;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in addEmployee()"+e);
		}
		finally
		{
			try
			{
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in finally of addEmployee()"+e);
			}
		}
		return false;
	}
	public static ArrayList getAllEmployees()
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<EmpBean>al=null;
		try
		{
			con=DBConnection.connect();
			pstmt=con.prepareStatement("select * from empmaster");
			rs=pstmt.executeQuery();
			al=new ArrayList<EmpBean>();
			while(rs.next())
			{
				EmpBean objbean=new EmpBean();
				objbean.setEmpId(rs.getInt("id"));
				objbean.setEmpName(rs.getString("name"));
				objbean.setEmpSalary(rs.getDouble("salary"));
				al.add(objbean);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in getAllEmployees()"+e);
		}
		finally
		{
			try
			{
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Finally of getAllEmployees()"+e);
			}
		}
		return al;
	}
	public static EmpBean searchById(int id)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			con=DBConnection.connect();
			pstmt=con.prepareStatement("select * from empmaster where id=?");
			pstmt.setInt(1,id);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				EmpBean objbean=new EmpBean();
				objbean.setEmpId(rs.getInt("id"));
				objbean.setEmpName(rs.getString("name"));
				objbean.setEmpSalary(rs.getDouble("salary"));
				return objbean;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in searchById()");
		}
		finally
		{
			try
			{
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Finaly of searchById()");
			}
		}
		return null;
	}
	public static boolean deleteById(int id)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			con=DBConnection.connect();
			pstmt=con.prepareStatement("delete  from empmaster where id=?");
			pstmt.setInt(1,id);
			int i=pstmt.executeUpdate();
			if(i>0)
			{
				return true;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in deleteById()");
		}
		finally
		{
			try
			{
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Finaly of deleteById()");
			}
		}
		return false;
	}
	public static boolean updateById(EmpBean objbean)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			con=DBConnection.connect();
			pstmt=con.prepareStatement("update empmaster set name=?,salary=? where id=?");
			pstmt.setString(1,objbean.getEmpName());
			pstmt.setDouble(2,objbean.getEmpSalary());
			pstmt.setInt(3,objbean.getEmpId());
			int i=pstmt.executeUpdate();
			if(i>0)
			{
				return true;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in updateById()"+e);
		}
		finally
		{
			try
			{
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in finally of updateById()");
			}
		}
		return false;
	}
}