package jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLData;
import java.util.Scanner;

public class Student {
	public static void main(String[] args) {
		try {
			Scanner scr=new Scanner(System.in);
			int ch;
			do {
				displaymenu();
				ch=Integer.parseInt(scr.next());
				switch (ch) {
				case 1:
					insert();
					break;
				case 2:
					update();
					break;
				case 3:
					delete();
					break;
				case 4:
					getAll();
					break;
				case 5:
					getBy();
					break;
				case 6:
					getByQuery();
					break;
				case 7:
					auth();
					break;
				case 8:
					System.exit(0);
					break;
				default:
					System.out.println("\n\tINVALID OPTION");
					System.out.println("\n\tChoose Correct One");
				}
						
				
				
			}
			while(ch>0);
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void getByQuery() {
		
		try {
			Scanner scr=new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root","root");
			System.out.print("\n\tEnter your Mysql Query\n\n\t select * from student where ");
			String sql="select * from student where " + scr.nextLine();
			PreparedStatement psmt=conn.prepareStatement(sql);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				
				System.out.println("\nRoll No : "+rs.getInt("rollno"));
				System.out.println("Name : "+rs.getString("name"));
				System.out.println("Gender : "+rs.getString("gender"));
				System.out.println("Email : "+rs.getString("email"));
				System.out.println("Password : "+rs.getString("password"));
				System.out.println("\n");
				
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	private static void auth() {
		try {
			Scanner scr=new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "root");
			System.out.println("Enter Table Name: ");
			String sql="select * from "+scr.next()+" where email=? && password=?";
			PreparedStatement psmt=conn.prepareStatement(sql);
			System.out.println("Enter Valid Email Id : ");
			psmt.setString(1, scr.next());
			System.out.println("Enter Valid Password : ");
			psmt.setString(2, scr.next());
			ResultSet rs=psmt.executeQuery();
			if(rs.next()) {
				System.out.println("\n\n\t\tAUTHENTICATION SUCCESSFUL\n\n");
				System.out.println("Roll No : "+rs.getInt("rollno"));
				System.out.println("Name : "+rs.getString("name"));
				System.out.println("Gender : "+rs.getString("gender"));
				System.out.println("Email : "+rs.getString("email"));
				System.out.println("Password : "+rs.getString("password"));
			}
			else {
				System.out.println("\n\n\t\tINVALID EMAIL OR PASSWORD !!!!\n\n");
			}
			conn.close();
			psmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void insert() {
		try {
			Scanner scr=new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "root");
			String sql="insert into student(rollno,name,gender,email,password) values(?,?,?,?,?)";
			PreparedStatement pmst=conn.prepareStatement(sql);
			System.out.println("\tEnter Student Roll No : ");
			pmst.setInt(1, scr.nextInt());
			System.out.println("\tEnter Student Name : ");
			pmst.setString(2, scr.next());
			System.out.println("\tEnter Student Gender : ");
			pmst.setString(3, scr.next());
			System.out.println("\tEnter Student Email : ");
			pmst.setString(4, scr.next());
			System.out.println("\tEnter Student Password : ");
			pmst.setString(5, scr.next());
			int i=pmst.executeUpdate();
			if(i>0) {
				System.out.println("\n\tDATA INSERTED SUCCESSSFULLY !!!!!!!\n");
			}
			else {
				System.out.println("\n\tINSERTION FAILED ??????");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private static void update() {
		try {
			Scanner scr=new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "root");
			String sql="update student set rollno=?,name=?,gender=?,email=?,password=? where rollno=? ";
			PreparedStatement pmst=conn.prepareStatement(sql);
			System.out.println("\nEnter updated Roll No : ");
			pmst.setInt(1, scr.nextInt());
			System.out.println("Enter updated Name : ");
			pmst.setString(2, scr.next());
			System.out.println("Enter updated Gender : ");
			pmst.setString(3, scr.next());
			System.out.println("Enter updated Email : ");
			pmst.setString(4, scr.next());
			System.out.println("Enter updated Password : ");
			pmst.setString(5, scr.next());
			System.out.println("Enter Existed Student Roll No : ");
			pmst.setString(6, scr.next());
			int i=pmst.executeUpdate();
			if(i>0) {
				System.out.println("\n\n\t\tSUCCESSFULLY UPDATED !!!!!!!!!!!");
			}
			else {
				System.out.println("\n\n\t\tUPDATE FAILED ????????????");
			}
			pmst.close();
			conn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private static void delete() {
		try {
			Scanner scr=new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "root");
			String sql="delete from student where rollno=? ";
			PreparedStatement pmst=conn.prepareStatement(sql);
			System.out.println("Enter Existed Student Roll No : ");
			pmst.setString(1, scr.next());
			int i=pmst.executeUpdate();
			if(i>0) {
				System.out.println("\n\n\t\tSUCCESSFULLY STUDENT DELETED !!!!!!!!");
			}
			else {
				System.out.println("\n\n\t\tDELETION FAILED ????????");
			}
			conn.close();
			pmst.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private static void getAll() {
		try {
			Scanner scr=new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "root");
			System.out.println("\nEnter the Table Name :");
			String sql="select * from "+scr.next();
			PreparedStatement pmst= conn.prepareStatement(sql);
			ResultSet rs=pmst.executeQuery();
			while(rs.next()) {
				System.out.println("Roll No : "+rs.getInt("rollno"));
				System.out.println("Name : "+rs.getString("name"));
				System.out.println("Gender : "+rs.getString("gender"));
				System.out.println("Email : "+rs.getString("email"));
				System.out.println("Password : "+rs.getString("password"));
				System.out.println("\n");
				
			}
			conn.close();
			pmst.close();
			
;		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private static void getBy() {
		try {
			Scanner scr=new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "root");
			System.out.println("enter tablename");
			String sql="select * from "+scr.next()+" where rollno=?";
			PreparedStatement pmst=conn.prepareStatement(sql);
			System.out.println("Enter Roll No :");
			pmst.setInt(1, scr.nextInt());
			ResultSet rs=pmst.executeQuery();
			while(rs.next()){
				System.out.println("id :"+rs.getInt("id"));
				System.out.println("name :"+rs.getString("name"));
				System.out.println("email :"+rs.getString("email"));
				System.out.println("password :"+rs.getString("password"));
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	}

	private static void displaymenu() {
		System.out.println("\n\n\t1. Insertion");
		System.out.println("\t2. Updation");
		System.out.println("\t3. Deletion");
		System.out.println("\t4. Get All");
		System.out.println("\t5. Get By Roll No");
		System.out.println("\t6. Get By Query");
		System.out.println("\t7. Authentication");
		System.out.println("\t8. Exit");
		System.out.println("_______________________________");
		System.out.println("\n\tChoose Correct Option");
		
	}

}
