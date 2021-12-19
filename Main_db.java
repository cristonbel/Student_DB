//Criston Barboza
//200970019
//MCA- MIT Manipal
//Student database
import java.sql.*;
import java.util.Scanner;

public class Main_db {

	public static void main(String[] args) throws Exception{
		getconnection();
		int ch;
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			System.out.println("1.Insert 2.Delete 3.Update 4.Display All 5.Display Single 6.Exit");
			ch=sc.nextInt();
			switch(ch)
			{
				case 1: insert_record();
						break;
				case 2: delete_record();
						break;
				case 3: update_record();
						break;
				case 4: select_all();
						break;
				case 5: select_specific();
						break;
				case 6: System.out.println("Exiting....");
						System.exit(0);
				default:System.out.println("Invalid Choice");
						break;

			}
		}

	}
	public static Connection getconnection() throws Exception{
		try {
			String url="jdbc:mysql://localhost:3306/student_db";
			String uname="root";
			String pass="password";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,uname,pass);
			return con;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	static void insert_record() throws Exception{
		int sno;
		String name,dob,doj;
		Scanner sc=new Scanner(System.in);
		try {
			System.out.println("Enter the Student No");
			sno=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter the Student Name");
			name=sc.nextLine();
			System.out.println("Enter the Birth Date(Month-Date-YearFormat)");
			dob=sc.next();
			System.out.println("Enter the Joining Date(Month-Date-YearFormat)4");
			doj=sc.next();
			
			Connection con=getconnection();
			PreparedStatement statement=con.prepareStatement("INSERT INTO STUDENT(STUDENT_NO,STUDENT_NAME,DOB,DOJ) VALUES ('"+sno+"','"+name+"',str_to_date('"+dob+"','%m-%d-%Y'),str_to_date('"+doj+"','%m-%d-%Y'))");
			statement.executeUpdate();
			System.out.println("Information Inserted into Table");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	static void delete_record() throws Exception{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Student Number: ");
		int no=sc.nextInt();
		try {
				Connection con=getconnection();
				PreparedStatement statement=con.prepareStatement("DELETE FROM STUDENT WHERE STUDENT_NO = '"+no+"'");
				statement.execute();
				con.close();
				System.out.println("Record Deleted Succesfully");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	static void update_record() throws Exception{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Student Number");
		int sno=sc.nextInt();
		sc.nextLine()
		System.out.println("Enter the Student Name");
		String name=sc.nextLine();
		System.out.println("Enter the Birth Date(Month-Date-YearFormat)");
		String dob=sc.next();
		System.out.println("Enter the Joining Date(Month-Date-YearFormat)");
		String doj=sc.next();
		try {
				Connection con=getconnection();
				PreparedStatement statement=con.prepareStatement("UPDATE STUDENT SET STUDENT_NAME = '"+name+"',DOB =str_to_date('"+dob+"','%m-%d-%Y'),DOJ = str_to_date('"+doj+"','%m-%d-%Y') WHERE STUDENT_NO= '"+sno+"'");
				statement.executeUpdate();
				int rows=statement.executeUpdate();
				if(rows == 0)
					System.out.println("Record doesnot exist");
				else
					System.out.println("Information Updated");
				con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void select_all() throws Exception{
		try {
				Connection con=getconnection();
				String sql="SELECT * FROM STUDENT";
				Statement statement=con.createStatement();
				ResultSet res=statement.executeQuery(sql);
				System.out.println("Student No\tStudent Name\tDate Birth\tDate Join");
				System.out.println("----------\t------------\t----------\t---------");
				while(res.next())
				{
					int sno=res.getInt("STUDENT_NO");
					String name=res.getString("STUDENT_NAME");
					Date dob=res.getDate("DOB");
					Date doj=res.getDate("DOJ");
					System.out.println(sno+"\t\t"+name+"\t\t"+dob+"\t"+doj);
				}
				statement.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void select_specific() throws Exception{
		try {
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter the Student No: ");
				int s=sc.nextInt();
				Connection con=getconnection();
				Statement statement=con.createStatement();
				ResultSet res=statement.executeQuery("SELECT * FROM STUDENT WHERE STUDENT_NO = '"+s+"'");
				System.out.println("Student No\tStudent Name\tDate Birth\tDate Join");
				System.out.println("----------\t------------\t----------\t---------");
				
				while(res.next())
				{
					int sno=res.getInt("STUDENT_NO");
					String name=res.getString("STUDENT_NAME");
					Date dob=res.getDate("DOB");
					Date doj=res.getDate("DOJ");
					System.out.println(sno+"\t\t"+name+"\t\t"+dob+"\t"+doj);
				}
				statement.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
