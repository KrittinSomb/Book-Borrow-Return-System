
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQLConnect {

private Connection conn = null;
private Statement stmt = null;
	
public 	MySQLConnect() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		}
	try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "library",
		"root", "");
		stmt = conn.createStatement();
		
		} catch (SQLException e) {
		e.printStackTrace();
		}
}
public ResultSet query(String sql) {
   ResultSet rs = null;
   try {
      rs = stmt.executeQuery(sql);
       } catch (SQLException e) {
       SQLExceptionError(e);
       }
     return rs;
}
public int update(String sql) {
     int rows = -1;
  try {
     rows = stmt.executeUpdate(sql);
   } catch (SQLException e) {
     SQLExceptionError(e);
   }
   return rows;
}

public void close() {
  try {
     if (stmt != null) {
     stmt.close();
      }
     if (conn != null) {
        conn.close();
     }
     } catch (SQLException e) {
       SQLExceptionError(e);
     }
}

private void SQLExceptionError(SQLException e) {
	// TODO Auto-generated method stub
	System.out.println("SQLException: " + e.getMessage());
	System.out.println("SQLState: " + e.getSQLState());
	System.out.println("VendorError: " + e.getErrorCode());
}
	
	
	
}

