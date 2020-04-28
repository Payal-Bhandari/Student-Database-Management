package com.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
	public Connection connect()
	{
		
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("DRIVER LOADED");
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String username="hr";
			String password="hr";
		    con=DriverManager.getConnection(url, username, password);
		        if(con==null)
		        {
		    	System.out.println("CONNECTION CANNOT BE ESTABLISHED");
		        }
		       // System.out.println("CONNECTION ESTABLISHED");
		      } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//Exception details are printed by stackTrace in red color.
		     } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		     }
	      
		/*finally
		{
		try {
               if(con!=null)//properly established connection with database
               con.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		System.out.println("End of Application");
	   
	   }*/
		return con;
  } 
}
