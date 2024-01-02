package tech.csm.util;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    private static final BasicDataSource dataSource = new BasicDataSource();
    private static Connection con = null;

    static {

        // JDBC connection settings
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/tech_assess_1");
        dataSource.setUsername("root");
        dataSource.setPassword("pass@123");

        // Connection pool settings
        dataSource.setInitialSize(10);  // Initial number of connections in the pool
        dataSource.setMaxOpenPreparedStatements(20);    // Maximum number of active connections
        dataSource.setMaxIdle(10);      // Maximum number of idle connections
        dataSource.setMinIdle(5);       // Minimum number of idle connections
        dataSource.setMaxWait(5000); // Maximum time to wait for a connection from the pool (in milliseconds)
    }

    public static Connection getConnection() {
        try {
            con = dataSource.getConnection();
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection() {
        boolean closed = false;

        if (con != null) {
            try {
                con.close();
                closed = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (closed) {
            System.out.println("Connection closed successfully");
        } else {
            System.out.println("Connection still open");
        }

    }
}
