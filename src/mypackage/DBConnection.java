package mypackage;

import java.sql.Connection;

import java.sql.DriverManager;

import javax.swing.JOptionPane;
public class DBConnection {
public static Connection ConnectDB(){
Connection conn=null;
try{
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zakaria","root","");
return conn;
}catch(Exception ex){
JOptionPane.showMessageDialog(null, ex);
}
return null;
}
}
