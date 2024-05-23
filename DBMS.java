package project1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextArea;

public class DBMS{
static Connection connection;
	void dbConnection(String JDBC_URL,String JDBC_USER,String JDBC_PASSWORD){
		 try {
			 connection= DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			 System.out.println("Connection Success");
		 }
		 
		 catch(Exception e) {
			 System.out.println(e);
		 }
		
	}
	
	 void CreateTable(String tableName) {
		try {
			Statement stmt = connection.createStatement();
			String createTableSql= "CREATE TABLE "+ tableName+ "(roll int PRIMARY KEY, name varchar(255), Fav_Foods varchar(255), Gender varchar(255))";
			
			stmt.execute(createTableSql);
			System.out.println("Table '"+tableName+"' created succesfully!!");
		}
		
		catch(Exception e) {
			 System.out.println(e);
		}
		
	}
	
	void insertTable(int roll, String name, String gender, String fav_Foods, String tableName) {
		try {
	       Statement stmt = connection.createStatement(); 
	    	   
	        String insertSQL = String.format("INSERT INTO %s (roll, name, Gender, Fav_Foods) VALUES (%d, '%s', '%s', '%s')", tableName,roll, name, gender, fav_Foods);
	    	   int rowsAffected = stmt.executeUpdate(insertSQL);
	    	   if (rowsAffected > 0) {
	    	      System.out.println("Data successfully inserted!"+rowsAffected);
	    	   }
	       }
	    	   
	    	    catch (Exception e) {
	    	     e.printStackTrace();
	    	   }
	}
	
	void selectTable(String tableName, JTextArea ta) {
		
		try {
			Statement stmt= connection.createStatement();
			String selectSQL = "SELECT * FROM " + tableName + " ORDER BY roll ASC";
			ResultSet rs = stmt.executeQuery(selectSQL);
			String result= "";
			
			
			while (rs.next()) {
              int roll = rs.getInt("roll");
              String name = rs.getString("name");
              String gender = rs.getString("Gender");
              String fav_foods = rs.getString("fav_foods" );
      
              result= "\n Roll: "+ roll+",\n Name: "+ name+",\n Gender: "+ gender+",\n Fav_Foods: "+fav_foods;
              
              ta.append(result+"\n");
              System.out.printf("Roll: %d, Name: %s, Gender: %s, Fav_foods: %s%n", roll, name, gender, fav_foods);
          }
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	int returnRoll(String tableName) {
		int lastRoll=0;

		try {
			Statement stmt= connection.createStatement();
			String selectSQL = "SELECT * FROM " +tableName+" order by roll ASC";
			ResultSet rs = stmt.executeQuery(selectSQL);
			
			while (rs.next()) {
	              int roll = rs.getInt("roll");
	              lastRoll= roll;
	             
			}
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return lastRoll+1;
	}

	void updateTableData(int rollno, String name, String gender, String fav_Foods, String tableName) {
		try {
			Statement stmt= connection.createStatement();
			String updateSql = String.format("UPDATE %s  SET name = '%s', gender= '%s', fav_foods= '%s'  WHERE roll= %d", tableName, name, gender, fav_Foods, rollno );
			int rowsAffected= stmt.executeUpdate(updateSql);
			
			if(rowsAffected>0) {
				System.out.println("Uptated Successfully!!");
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	void deleteTableData(String tableName, int rollno) {
		try {
			Statement stmt= connection.createStatement();
			String deleteSql= String.format("DELETE FROM %s Where roll= %d", tableName, rollno);
			int rowsAffected= stmt.executeUpdate(deleteSql);
			
			if(rowsAffected>0) {
				System.out.println("Deleted!!");
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
