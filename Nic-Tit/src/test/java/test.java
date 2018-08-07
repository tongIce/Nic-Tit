

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {

public static void main(String[] args) {
      String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
     String dbURL="jdbc:sqlserver://172.19.255.242:1433;DatabaseName=jwjk";//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
     String Name="zzdy";
     String Pwd="zzdy123654";
     long start = 0;
     try{
         Class.forName(driverName);
        
         System.out.println("加载驱动成功！");
     }catch(Exception e){
         e.printStackTrace();
         System.out.println("加载驱动失败！");
     }
   try
   {
   Connection conn=DriverManager.getConnection(dbURL,Name,Pwd);
   Statement st = conn.createStatement();
   String r1="select  distinct * from V_JW_XSKB where xh='162056139' and xn='2017' and xq='1' and room='Z06045'";
   System.out.println("Before");
   start=System.currentTimeMillis();
   ResultSet rs = st.executeQuery(r1);
   System.out.println(System.currentTimeMillis());
   System.out.println("jsjdjjcd");
   while (rs.next()) {
	               System.out.println(rs.getString("lessname"));
	        }
	           // 关闭资源
	         rs.close();
	         st.close();
	        conn.close();
   System.out.println("连接数据库成功");
   }catch(Exception e){
    e.printStackTrace();
      System.out.println("连接失败");
      System.out.println((System.currentTimeMillis()-start)/1000);
      }
   }

}
