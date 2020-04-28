package com.student;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.Scanner;


public class StudentDetails {
	public static void main(String[] args) {
		 int regId;
		 String studentName;
		 String address;
	     long contactNumber;
	     int courseId;
	     int feesPaid;
	     Date dateOfAdmission=null;
	     Date startDate=null;
	     String sdate;
	     String edate;
	     Date endDate=null;
	     String courseName;
	     int duration;
	     int fees;
	     ArrayList<Course> arlist=new ArrayList();
	     ArrayList<Student>arlist1=new ArrayList();
	     Scanner sc=new Scanner(System.in);
		 int option;
		    	    
     	do
	   {
		System.out.println("\t \t \t STUDENT DATA MANAGEMENT SYSTEM  ");
		System.out.println("\t \t \t  ***MENU****");
	    System.out.println("\t \t \t1. LAUNCH NEW COURSE");
	    System.out.println("\t \t \t2. ENROLL STUDENT");
	    System.out.println("\t \t \t3. VIEW STUDENT DETAILS");
	    System.out.println("\t \t \t4. VIEW COURSE DETAILS");
	    System.out.println("\t \t \t5. EXIT");
	    
	    Student objstudent =new Student();
	    StudentManager objmanager=new StudentManager();
	    StudentCourseService objcourseservice=new StudentCourseService();
	    Course objcourse=new Course();
	    
	     option=sc.nextInt(); 
	     switch(option)
	     {
	     case 1:	  
				 objcourseservice.launchCourse();	  
					
	             break;
	             
	     case 2:
	            System.out.println("\t*** ENTER DETAILS OF STUDENT***");
	            
	            System.out.println("\t ENTER NAME OF THE STUDENT :");
	            studentName=sc.next();
	            System.out.println("\t ENTER ADDRESS OF THE STUDENT :");
	            address=sc.next();
	            System.out.println("\t ENTER CONTACT NUMBER :");
	            contactNumber=sc.nextLong();
	            System.out.println("\t ENTER COURSE ID :");
	            courseId=sc.nextInt();
	            System.out.println("\t ENTER FEES PAID :");
	            feesPaid=sc.nextInt();
	            
	            System.out.println("\t DATE OF ADMISSION IS :");
	            SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy");
	   		    dateOfAdmission=new Date();
	   		    System.out.println(df.format(dateOfAdmission));
	            System.out.println("\t ENTER START DATE :");
	            sdate=sc.next();
	            System.out.println("\t ENTER END DATE:");
	            edate=sc.next();
	            SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
	    		try {
	    			startDate=df1.parse(sdate);
	    		} catch (java.text.ParseException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
	    		try {
	    			endDate=df2.parse(edate);
	    		} catch (java.text.ParseException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		 if(dateOfAdmission.after(startDate)||startDate.before(dateOfAdmission))
	    		 {
	    			 System.out.println("Enter proper start date !!");
		    		 
	    		 }
	    		 else
	    		 {
	    			 if(startDate.after(endDate)||endDate.before(startDate)) {	
		    			 System.out.println("Enter proper dates !!");
	    			 }
		    			 else
		    			 {
		    				 objmanager.EnrollStudent(studentName,address,contactNumber,courseId,feesPaid,dateOfAdmission,startDate,endDate);
		    			 }
	    		 }
	    		 
                break;
	     case 4:System.out.println("**SELECT AN OPTION TO VIEW COURSE DETAILS :");
	            System.out.println("\t \t 1)VIEW ALL COURSE DETAILS \n\t \t 2)VIEW COURSE DETAILS OF A SPECIFIC COURSE");
	            int op=sc.nextInt();
	            if(op==1)
	            {
	            	arlist=objcourseservice.fetchAllCourseDetails();
	            	for(int i=0;i<arlist.size();i++)
	 	       	   {
	 	       		
	 	       		Object obj=arlist.get(i);
	 	       		if(obj instanceof Course)
	 	       		  {
	 	       			System.out.println("COURSE ID       :"+((Course) obj).getCourseId());
	 	       		    System.out.println("COURSE NAME     :"+((Course)obj).getCourseName());
	 	       		    System.out.println("COURSE FEES     :"+((Course) obj).getFees()); 
	 	       			System.out.println("COURSE DURATION :"+((Course) obj).getDuration());
	 	       		    System.out.println("------------------------");
	 	       		   }
	 	       		  else
	 	       		  System.out.println(obj);
	 	            	}
	            }
	            else
	            {
	    	        System.out.println("\nEnter the course Id :");
		            int id=sc.nextInt();
		    	    objcourse=objcourseservice.fetchCourseDetails(id);
		    	    if(objcourse.getCourseId()==0) {
		    	    	break;
		    	    }
		    	    else {
		    	    System.out.println("COURSE ID       :"+objcourse.getCourseId());
		    	    System.out.println("COURSE NAME     :"+objcourse.getCourseName());
		    	    System.out.println("COURSE FEES     :"+objcourse.getFees());
		    	    System.out.println("COURSE DURATION :"+objcourse.getDuration());
		    	    System.out.println("------------------------");
		    	    }
	            }
                break;	      
                
	     
	     case 3:System.out.println("\t \t***SELECT AN OPTION TO VIEW STUDENT DETAILS***");
	            System.out.println("\t \t1)VIEW STUDENT DEATILS BY REG ID \n\t \t2)VIEW STUDENT DETAILS BY COURSE ID");
	            int op1=sc.nextInt();
	            if(op1==1)
	            {
	            	
	                    System.out.println("\nEnter the reg Id :");
			            int id=sc.nextInt();
			    	    objstudent=objmanager.viewStudentDetail(id); 
			    	    System.out.println("REG ID           :"+objstudent.getRegId());
			    	    System.out.println("STUDENT NAME     :"+objstudent.getStudentName());
			    	    System.out.println("STUDENT ADDRESS  :"+objstudent.getAddress());
			    	    System.out.println("CONTACT NUMBER   :"+objstudent.getContactNumber());
			    	    System.out.println("COURSE ID        :"+objstudent.getCourseId());
			    	    System.out.println("FEES PAID        :"+objstudent.getFeesPaid());
			    	     DateFormat sf=new SimpleDateFormat("dd-MM-yyyy");
			    	     Date correctdate1=objstudent.getDateOfAdmission();
						 String datetodisplay=sf.format(correctdate1);
						 Date correctsdate2=objstudent.getStartDate();
						 String sdatetodisplay=sf.format(correctsdate2);
						 Date correctedate3=objstudent.getEndDate();
						 String edatetodisplay=sf.format(correctedate3);
						
			    	    System.out.println("DATE OF ADMISSION:"+datetodisplay);
			    	    System.out.println("START DATE       :"+sdatetodisplay);
			    	    System.out.println("END DATE         :"+edatetodisplay); 
			    	    System.out.println("------------------------");
	            }
	            else
	            {
	    	        System.out.println("Enter courseId :");
	                int id1=sc.nextInt();
	                arlist1=objmanager.viewStudentDetails(id1);
	                for(int i=0;i<arlist1.size();i++)
	       	        {
	       		      Object obj=arlist1.get(i);
	       		      if(obj instanceof Student)
	       		      {
	       			    System.out.println("REG ID           :"+((Student) obj).getRegId());
	       			    System.out.println("STUDENT NAME     :"+((Student) obj).getStudentName());
	       			    System.out.println("STUDENT ADDRESS  :"+((Student) obj).getAddress());
	       			    System.out.println("CONTACT NUMBER   :"+((Student) obj).getContactNumber());
	       			    System.out.println("COURSE ID        :"+((Student) obj).getCourseId());
	       			    System.out.println("FEES PAID        :"+((Student) obj).getFeesPaid());
	       			 
	       			 DateFormat sf1=new SimpleDateFormat("dd-MM-yyyy");
		    	     Date correctdate11=((Student)obj).getDateOfAdmission();
		    	     String datetodisplay=sf1.format(correctdate11);
		    	     System.out.println("DATE OF ADMISSION:"+datetodisplay);
		    	     
		    	     DateFormat sf2=new SimpleDateFormat("dd-MM-yyyy");
		    	     Date correctdate12=((Student)obj).getStartDate();
		    	     String sdatetodisplay=sf2.format(correctdate12);
		    	     System.out.println("START DATE       :"+sdatetodisplay);
		    	     
		    	     DateFormat sf3=new SimpleDateFormat("dd-MM-yyyy");
		    	     Date correctdate13=((Student)obj).getEndDate();
		    	     String edatetodisplay=sf3.format(correctdate13);
		    	     System.out.println("END DATE         :"+edatetodisplay);
		    	     System.out.println("------------------------");
	       		   }
	       		  else
	       		  System.out.println(obj);
	            	}
	            }
	           break;
	     case 5:break;  
	    }
	   
	} while(option!=5);  		
  }
}
