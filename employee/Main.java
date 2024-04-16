package employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        //lấy thông tin từ mysql
        
        Vector<Object> RowBang = new Vector<Object>();
        try {
        	String query = "SELECT * FROM employee.employees";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection_Mysql connection_Mysql = new Connection_Mysql();
			Connection con= connection_Mysql.getConnection();
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            	if((rs.getString(6)).equals("1")) {
               	 manager.addEmployee(new Experience(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 10, "Java"));
            	}else if((rs.getString(6)).equals("2")) {
                    manager.addEmployee(new Fresher(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), "05/2021", "Excellent", "MIT"));
            	}else if((rs.getString(6)).equals("3")) {
                    manager.addEmployee(new Intern(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), "Computer Science", "2", "Harvard"));
            	}
            }

            rs.close(); 
            stmt.close(); 
            con.close();
        } catch (Exception ed) {
            ed.printStackTrace();
        }
       
        manager.displayAllEmployees();

        manager.updateEmployee("E001", "Johnathan Doe", "987-654-3210");

        manager.deleteEmployee("F001");

        manager.displayAllEmployees();
        
        manager.saveEployeesToFile("employee.txt");

        manager.loađEmployeesFromFile("employee.txt");
    }
}
