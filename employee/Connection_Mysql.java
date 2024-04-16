package employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_Mysql {
	private String url = "jdbc:mysql://localhost:3306/SinhVien";
    private String user = "root";
    private String password = "2604";
    private Connection connection;
    public Connection_Mysql() {
        try {
            // Attempt to establish a connection when an instance is created
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // Handle any potential SQL exceptions here
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    
}
}
