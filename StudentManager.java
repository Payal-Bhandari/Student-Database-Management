package com.student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StudentManager {
	    Date dateOfAdmission;
	    Date startDate;
	    Date endDate;
	
	public void EnrollStudent(String studentName,String address,Long contactNumber,int courseId,int feesPaid,Date dateOfAdmission,Date startDate,Date endDate)
	{
		DbConnect db=new DbConnect();
	    Connection con=null;
	    con=db.connect();
	    int count=0;
	    try {
	    	 CourseIdValidator cv=new CourseIdValidator();
	    	 Boolean status=cv.validate(courseId);
	    	if(status)
	    	{ 
	    	  
	    	    String queryforcount="Select max(regid) from student1";
	    		Statement smt=con.createStatement();
				ResultSet rs1=smt.executeQuery(queryforcount);
				if(!rs1.next()) {
				count=1;	
				}
				else {
				
				count=rs1.getInt(1);
				++count;
				}
				
			

	          SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy");
	          String doa=df.format(dateOfAdmission);
	          String sdate=df.format(startDate);
			  String edate=df.format(endDate);
	          
	          String insertquery="INSERT INTO student1 VALUES (" +count+ ",'" +studentName+ "','" +address+ "'," +contactNumber+ ","+courseId+","+feesPaid+ ",'"+doa+ "','"+sdate+ "','"+edate+"')";
	          Statement st1=con.createStatement();
	          int rows=st1.executeUpdate(insertquery);
	          if(rows>0) {
		       System.out.println("INSERTION SUCCESS");
	        }
          }
	    	else
	    	{
	    		System.out.println("Course Id is not Valid !");
	    	}
	    }
           catch(SQLException e) {
	      e.printStackTrace();
          }	
	
	}
	public ArrayList<Student> viewStudentDetails(int courseId)
	{
		ArrayList<Student> list=new ArrayList();
        
			CourseIdValidator cv=new CourseIdValidator();
	        Boolean flag=cv.validate(courseId);
	       if(flag)
	       {
		      StudentCourseService scs=new StudentCourseService();
		      list=scs.fetchStudentDetails(courseId);
	       } 
		   
	       else
	       {
	    	   System.out.println("Enter Valid course Id !");
	       } 
		return list;
		
	}

	public Student viewStudentDetail(int regId)
	{

		DbConnect db=new DbConnect();
	    Connection conn=null;
	    Student objstudent=new Student();
		try
		{
			conn=db.connect();
    		String sql2="SELECT * from student1 where regid='"+regId+"'";
    		Statement smt=conn.createStatement();
	        ResultSet rs=smt.executeQuery(sql2);
	        while(rs.next())
		   {
	    	 
	    	 objstudent.setRegId(rs.getInt(1));
	    	 objstudent.setStudentName(rs.getString(2));
	    	 objstudent.setAddress(rs.getString(3));
	    	 objstudent.setContactNumber(rs.getLong(4));
	    	 objstudent.setCourseId(rs.getInt(5));
	    	 objstudent.setFeesPaid(rs.getInt(6));
	    	 String doa=rs.getString(7);
	    	 SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	    		try {
	    			dateOfAdmission=df.parse(doa);
	    		} catch (java.text.ParseException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    	 objstudent.setDateOfAdmission(dateOfAdmission);
	    	 String sdate=rs.getString(8);
	    	 SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
	    		try {
	    			startDate=df1.parse(sdate);
	    		} catch (java.text.ParseException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    	 objstudent.setStartDate(startDate);
	    	 String edate=rs.getString(9);
	    	 SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
	    		try {
	    			endDate=df1.parse(edate);
	    		} catch (java.text.ParseException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    	 objstudent.setEndDate(endDate);
	    
	       }
		}catch(SQLException e)
		  {
			  e.getMessage();
		  }
		return objstudent;
	}
}
	
