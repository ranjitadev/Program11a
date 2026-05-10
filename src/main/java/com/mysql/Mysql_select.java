/*
11a. Q1. Read all the existing records from the table coffee which is from the database test
and insert a new coffee product into it

A Java MySQL operations example
MySQL commands to be executed
use test;
CREATE TABLE IF NOT EXISTS `coffee` (`id` int(5) NOT NULL
AUTO_INCREMENT,`coffee_name` varchar(100) NOT NULL,`price` int(5), PRIMARY KEY
(`id`) );
INSERT INTO coffee values (101,&#39;Febary&#39;,120);
INSERT INTO coffee values (102,&#39;Royal&#39;,140);
INSERT INTO coffee values (103,&#39;Diamond&#39;,180);
*/
/**
* A Java MySQL SELECT and insert statement example.
* Demonstrates the use of a SQL SELECT statement against a
* MySQL database, called from a Java program.
*
*/ 

package com.mysql;
import java.sql.*;
import java.util.Properties;

public class Mysql_select
{
  public static void main(String[] args)
  {
    try
    {	
    	Connection dbConnection = null; 
    	try 
    	{ String url = "jdbc:mysql://localhost/test"; 
    	Properties info = new Properties();
    	info.put("user", "root");
    	info.put("password", "password");
    	dbConnection = DriverManager.getConnection(url, info);
    	if (dbConnection != null)
    		{
    		System.out.println("Successfully connected to MySQL database test"); 
    		} 
    	} 
    	catch (SQLException ex) 
    	{ 
    		System.out.println("An error occurred while connecting MySQL databse");
    		ex.printStackTrace();
    		}   	      
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "SELECT * FROM coffee";

      // create the java statement
      Statement st = dbConnection.createStatement();
      
      // execute the query, and get a java resultset
      ResultSet rs = st.executeQuery(query);
      
      // iterate through the java resultset
      while (rs.next())
      {
        int id = rs.getInt("id");
        String  coffee_name = rs.getString("coffee_name");
        int price= rs.getInt("price");
        
        // print the results
        System.out.format("\n%d, %s, %d", id,coffee_name,price);
      } 
      
   // inserting records
    String query1 = "insert into coffee (coffee_name, price) values ('Tajmahal', 950)";

   // Inserting record 
      PreparedStatement stmt=dbConnection.prepareStatement("insert into coffee values(?,?)");//  rds into mysql preparedstatement
    
     stmt.execute(query1);  
      	   
   stmt.close();
   
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }
  }
}


