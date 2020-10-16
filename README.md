 ### Short description
+ Template of online shop, made with Java with basic funcionalities, such as:
+ products : add, delete, save to cart
+ order : complete, delete
+ shopping cart : add order, delete order, view all
+ roles : admin, user
 ### Technologies used
+ Web: Tomcat, Java Servlets, JSP
+ Database: MySQL, JDBS
+ Maven 
+ IntelliJ IDEA
 ### Security implementation
+ password hashing
+ web filters for admin and user
+ authentication and authorization implementations
 ### Project configration 
+ Start Tomcat on your machine : Deployment - war_exploded, context address - "/"
+ Project implements MySQL as RDBMS for the project, SQL code is located in init_db.sql
+ Configure local connection to MySQL in the /com/internet/shop/util/ConnectionUtil.java
+ Start the project, press "Inject" on the main page to add default admin and users
 
