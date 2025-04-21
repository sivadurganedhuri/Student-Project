package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class registration {
/**
 * @param args
 */
public static void main(String[] args) {
	
	try {
		Scanner scr=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
		System.out.println("Enter Database Name : ");
		String sql="drop database " + scr.next();
		PreparedStatement pmst =conn.prepareStatement(sql);
		int i=pmst.executeUpdate();
		if(i>0)
		{
			System.err.println("Successfully Created");
		}
		else {
			System.out.println("Error");
		}
		conn.close();
		pmst.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
