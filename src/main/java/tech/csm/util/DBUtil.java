package tech.csm.util;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    private static BasicDataSource dataSource = new BasicDataSource();
    private static Connection con;

    static {

        // JDBC connection settings
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/tech_assess_1");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        // Connection pool settings
        dataSource.setInitialSize(10);  // Initial number of connections in the pool
        dataSource.setMaxOpenPreparedStatements(20);    // Maximum number of active connections
        dataSource.setMaxIdle(10);      // Maximum number of idle connections
        dataSource.setMinIdle(5);       // Minimum number of idle connections
        dataSource.setMaxWait(5000); // Maximum time to wait for a connection from the pool (in milliseconds)
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
