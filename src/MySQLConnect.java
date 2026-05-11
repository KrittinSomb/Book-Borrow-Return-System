import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MySQLConnect {

   private Connection conn = null;
   private Statement stmt = null;

   public MySQLConnect() {
      Properties prop = new Properties();

      try (FileInputStream fis = new FileInputStream("db.properties")) {
         // 1. โหลดไฟล์ Properties
         prop.load(fis);

         // 2. โหลด JDBC Driver
         Class.forName("com.mysql.cj.jdbc.Driver");

         // 3. ดึงค่าจากไฟล์มาใช้ใน DriverManager
         conn = DriverManager.getConnection(
               prop.getProperty("db.url"),
               prop.getProperty("db.user"),
               prop.getProperty("db.password"));

         stmt = conn.createStatement();
         System.out.println("เชื่อมต่อฐานข้อมูลสำเร็จ!");

      } catch (IOException e) {
         System.err.println("ไม่พบไฟล์ db.properties");
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         System.err.println("ไม่พบ JDBC Driver");
         e.printStackTrace();
      } catch (SQLException e) {
         System.err.println("การเชื่อมต่อฐานข้อมูลผิดพลาด");
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
