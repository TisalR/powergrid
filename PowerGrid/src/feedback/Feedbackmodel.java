package feedback;
import java.sql.*;

public class Feedbackmodel {
 //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/power_grid", "root", "");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertItem(String issName, String status, String date, String desc, String location)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " insert into Issue (`issueName`,`issueStatus`,`Date`,`Decsription`,`Location`)" + " values (?, ?, ?, ?, ?)";
 
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setString(1, issName);
 preparedStmt.setString(2, status);
 preparedStmt.setString(3, date);
 preparedStmt.setString(4, desc);
 preparedStmt.setString(5, location);
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newItems = readItems();
 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
 }
 catch (Exception e)
 {
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
	 System.err.println(e.getMessage());
 }
 return output;
 }
public String readItems()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Issue id</th><th>Issue name</th>" + "<th>Issue Status</th>" + "<th>Date</th>" + "<th>Description</th>" + "<th>Location</th>" + "<th>Update</th><th>Remove</th></tr>";

 String query = "select * from feedback";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String Issue_id = Integer.toString(rs.getInt("Issue_id"));
 String Issue_name = rs.getString("Issue_name");
 String Issue_Status = rs.getString("Issue_Status");
 String Date = rs.getString("Date");
 String Description = rs.getString("Description");
 String Location = rs.getString("Location");
 // Add into the html table
 output += "<tr><td>" + Issue_id + "</td>";
 output += "<td>" + Issue_name + "</td>";
 output += "<td>" + Issue_Status + "</td>";
 output += "<td>" + Date + "</td>";
 output += "<td>" + Description + "</td>";
 output += "<td>" + Location + "</td>";
 // buttons
 output += "<td><input name='btnUpdate' "
 + " type='button' value='Update' class=' btnUpdate btn btn-secondary'"
 + "data-itemid='" + Issue_id + "'></td>"
 + "<td><input name='btnRemove' type='button' value='Remove' "
 + "class='btnRemove btn btn-danger' data-itemid='" + Issue_id + "'></td></tr>";


 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the items.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updateItem(String ID, String issName, String status, String date, String desc, String location)

 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for updating."; }
 // create a prepared statement
 String query = "UPDATE issue SET Issue_Name=?,Issue_Status=?,Date=?,Description=?,Location=? WHERE Issue_id=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setString(1, issName);
 preparedStmt.setString(2, issName);
 preparedStmt.setString(3, date);
 preparedStmt.setString(4, date);
 preparedStmt.setString(5, location);
 preparedStmt.setInt(6, Integer.parseInt(ID));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newItems = readItems();
 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
 }
 catch (Exception e)
 {
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
	 System.err.println(e.getMessage());
 }
 return output;
 }
public String deleteItem(String Issue_ID)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for deleting."; }
 // create a prepared statement
 String query = "delete from feedback where Issue_id=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(Issue_ID));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newItems = readItems();
 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
 }
 catch (Exception e)
 {
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
	 System.err.println(e.getMessage());
 }
 return output;
 }
}