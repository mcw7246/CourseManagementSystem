package application;

import java.sql.*;

public class UserManager
{
  Connection con = null;

  public Connection connect(){
    //trys the connection of the database
    try{
      String url = "jdbc:sqlite:C:/Users/mikay/Projects/CourseManagementSystem/src/main/java/Courses.db";

      con = DriverManager.getConnection(url);
      con.commit();
      System.out.println("Connection has been established");
    }catch(SQLException e){
      System.out.println(e.getMessage());
    }
    return con;
  }

  public boolean hasRecord(String username) throws SQLException{
    boolean exists = false;
    String sql = "SELECT 1 FROM Users where Username = ?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1, username);
    ResultSet rs = ps.executeQuery();
    if(rs.next()){
      final int count = rs.getInt(1);
      exists = true;
    }
    return exists;
  }

  public boolean passwordMatches(String username, String password) throws SQLException{
    boolean found = false;
    int rowNum = 1;
    Statement stmt = con.createStatement();
    String query = "Select * FROM Users";

    ResultSet rs = stmt.executeQuery(query);

    while(!found){
      String name = rs.getString(rowNum);
      if(name.equals(username)){
        String psw = rs.getString(2);
        if (psw.equals(password))
        {
          found = true;
          break;
        }
      }
      else{
        rowNum++;
      }
    }
    return found;
  }
}
