package com.student;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class StudentCourseService {
	    DbConnect db=new DbConnect();
	    Connection conn=db.connect();
	    Statement smt=null;
	    Date dateOfAdmission;
	    Date startDate;
	    Date endDate;
	    
	public void launchCourse()
	{
		 Scanner sc=new Scanner(System.in);
		 String courseName;
		 System.out.println("***ENTER THE COURSE DETAILS***");
         System.out.println("**AVAILABLE COURSES \n 1.C \n 2.JAVA \n 3.J2EE \n 4.SPRING \n 5.HIBERNATE");
         boolean status = false;
         int count =1;
         do {
         System.out.println("\t ENTER COURSE NAME :");
         courseName=sc.next();
         CourseName[] course=CourseName.values();
         
         for(CourseName c : course)
	        {
	        	String s=c.toString();
	        	if(s.equalsIgnoreCase(courseName))
	        	{  
	        	 status=true;
	        	 break;
	        	}
	        }
         if(status==false) {
        	 count++;
        	 System.out.println("Invalid course name please enter courses form available courses");
         }
         }while(! status && count==2) ;
       
         while(! status && count>2)
         {
        	 break;
         }
         
         if(count<2)
         {
         System.out.println("\t ENTER COURSE FEES :");
         int  fees=sc.nextInt();
         System.out.println("\t ENTER DURATION OF COURSE :");
         int duration=sc.nextInt();
         System.out.println("Course successfully launched ...");
		 
		CourseName[] course=CourseName.values();
		Boolean status1=false;
		int count1=0;

	    try
	    {	 	  
			  
		        	String query="Select max(courseid) from course";
		        	smt=conn.createStatement();
					ResultSet rs=smt.executeQuery(query);
					if(!rs.next()) {
					count1=1;	
					}
					else {
					
					count1=rs.getInt(1);
					++count1;
					}

		        	String sql1="insert into course values('"+count+"','"+courseName+"','"+fees+"','"+duration+"')";
		        	smt=conn.createStatement();
		  		    int rs1=smt.executeUpdate(sql1);
		  		    System.out.println("INSERTED");
		  		    System.out.println("Course Id for lsunched course is :"+count);
	    }
	  catch(SQLException e)
	  {
		  e.getMessage();
	   }
        
    }	
 }
	
	public ArrayList<Course> fetchAllCourseDetails()
	{
		ArrayList<Course> list=new ArrayList();
		
		try
		{
			CourseName[] cname=CourseName.values();
    		String sql2="select * from course";
	    	smt=conn.createStatement();
	        ResultSet rs=smt.executeQuery(sql2);
	        while(rs.next())
		   {
	        	
	    	     Course objcourse=new Course();
	    	     objcourse.setCourseId(rs.getInt(1));
	    	     String name=rs.getString(2);
	    	     for(CourseName c: cname)
	    	     {
	    	    	 if(c.toString().equalsIgnoreCase(name))
	    	    	 {
	    	    		objcourse.setCourseName(c);
	    	    	 }
		         }
	    	 objcourse.setFees(rs.getInt(3));
	    	 objcourse.setDuration(rs.getInt(4));
	    	 list.add(objcourse);
		    }
		 }
		catch(SQLException e)
		{
			e.getMessage();
		}
		return list;
	}
	
	public Course fetchCourseDetails(int courseId)
	{
		Course objcourse=new Course();
		CourseIdValidator cv=new CourseIdValidator();
		Boolean flag=cv.validate(courseId);
		if(flag)
		{
		
           try
           {
        	   CourseName[] cname=CourseName.values();
        	   String sql="SELECT * from course where courseid='"+courseId+"'";
        	   smt=conn.createStatement();
   	           ResultSet rs=smt.executeQuery(sql);
   	           while(rs.next())
 		       {
 	    	    objcourse.setCourseId(rs.getInt(1));
 	    	     String name=rs.getString(2);
 	    	     for(CourseName c: cname )
 	    	     {
 	    	    	 if(c.toString().equalsIgnoreCase(name))
 	    	    	 {
 	    	    		objcourse.setCourseName(c);
 	    	    	 }
 		         }
 	    	    objcourse.setFees(rs.getInt(3));
 	    	    objcourse.setDuration(rs.getInt(4));
 		    }
           }
           catch(SQLException e)
           {
        	   e.getMessage();
           }
		}else
		{
			System.out.println("Course Id not Valid");
		}  
		
	return objcourse;
	}
	
	public ArrayList<Student> fetchStudentDetails(int courseId)
	{
         ArrayList<Student> list=new ArrayList();
         
		try
		{
			CourseIdValidator cv=new CourseIdValidator();
	        Boolean flag=cv.validate(courseId);
	       if(flag)
	       {
    		String sql2="SELECT * from student1 where courseid="+courseId;
	    	smt=conn.createStatement();
	        ResultSet rs=smt.executeQuery(sql2);
	        System.out.println("QUERY EXECUTED !");
	        while(rs.next())
		   {
	    	 Student objstudent=new Student();
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
	 
	    	 list.add(objstudent);
	    	 System.out.println("added");
		    }
		 }
	       else
	       {
	    	   System.out.println("Enter Valid course Id !");
	       }
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
		return list;
     	}
	
	}

