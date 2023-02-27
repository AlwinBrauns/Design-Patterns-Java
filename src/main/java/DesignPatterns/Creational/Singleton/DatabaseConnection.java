package DesignPatterns.Creational.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseConnection {
    private Connection connection;

    // ------ SINGLETON PATTERN ---------------
    private static DatabaseConnection instance;
    private static String driver = "com.mysql.cj.jdbc.Driver";
    public static String host = "localhost";

    private DatabaseConnection() {}

    public static synchronized DatabaseConnection getInstance() {
        if(instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }
    // ----------------------------------------

    public void loadDriver() {
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (Exception exception) {

        }
    }

    public boolean connectWithDB(String dbName, String user, String password) {
        var couldConnect = false;
        if(connection != null) return couldConnect;
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://"+ host + "/"+ dbName + "?" +
                "user=" + user + "&password=" + password + ""
            );
            couldConnect = true;
        } catch (SQLException exception) {
            printSQLException(exception);
        }
        return couldConnect;
    }

    public void disconnectFromDB() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException exception) {
                printSQLException(exception);
            }
        }
    }

    public String executeQuery(String query) {
        if(connection == null) return null;
        String result = "";
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch(SQLException exception) {
            printSQLException(exception);
        } finally {
            if (resultSet != null) {
                try {
                    while(resultSet.next()) {
                        int columnCount = resultSet.getMetaData().getColumnCount();
                        if(resultSet.isFirst()) {
                            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                                result += resultSet.getMetaData().getColumnLabel(columnIndex);
                                result += getColumnEnding(columnIndex, columnCount);
                            }
                        }
                        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                            result += resultSet.getString(columnIndex);
                            result += getColumnEnding(columnIndex, columnCount);
                        }
                    }
                    resultSet.close();
                } catch (SQLException exception) { 
                    printSQLException(exception);
                }
        
                resultSet = null;
            }
        
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) { 
                    printSQLException(exception);
                }
        
                statement = null;
            }
        }
        return result;
    }

    private String getColumnEnding(int columnIndex, int columnCount) {
        if(columnIndex==columnCount) {
            return System.lineSeparator();
        }else {
            return ",";
        }
    }

    private void printSQLException(SQLException exception) {
        System.out.println("SQLException: " + exception.getMessage());
        System.out.println("SQLState: " + exception.getSQLState());
        System.out.println("VendorError: " + exception.getErrorCode());
    }
}
