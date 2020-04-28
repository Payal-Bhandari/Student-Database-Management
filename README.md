# Student-Database-Management
PROBLEM DEFINITION:
An institute wants to automate the process of Student Data Management.
They need the following functionalities to be implemented.
  1) LaunchNewCourse
  2) ErollStduent
  3) ViewCourseDetails
  4) ViewStudentDetails. 
  
 Module 1: LaunchNewCourse
 This module is useto Launch a new course. The course details for a new course must be saved in 
 database and relevant details must be uploaded. 
 
 Module 2: EnrollStudent
 This module is used to enroll student for a course. The user enters the Student details 
 which must be saved in the database and relevant details must be updated. 
 
 Module 3: ViewStudentDetails
 This module is used to display Student Details which are enrolled for a particular course by accepting the courseId. 
 
 Module 4: ViewCourseDetails
 This module is used to display Course details by accepting courseId.
 
Bean Classes:
  Create Enum CourseName with following constants: C, J2EE, JAVA, SPRING, HIBERNATE.
  Create class Course with following attributes : 
    1)courseId: integer,
    2)courseName: courseName,
    3)duration: integer,
    4)fees:integer.
 Create class Student with following attributes:
    1)regId:integer,
    2)studentName:String,
    3)address:String,
    4)contactNumber: Long,
    5)courseId:integer,
    6)feesPaid:integer,
    7)dateOfAdmission,
    8)startDate,
    9)endDate
   
Service
1)launchCourse()
2)fetchAllCourseDetails()
3)fetchCourseDetails(courseId)
4)fetchStudentDetails(courseId)
