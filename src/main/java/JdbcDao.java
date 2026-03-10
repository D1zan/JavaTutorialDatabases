package com.example.tutorial;
import javafx.scene.control.Alert;

import java.sql.*;

public class JdbcDao {


    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/javafx_registration?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    private static final String INSERT_QUERY = "INSERT INTO registration (full_name, email_id, password) VALUES (?, ?, ?)";


    public void insertRecord(String fullName, String emailId, String password) throws SQLException {

        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, emailId);
            preparedStatement.setString(3, password);

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void retrieveRecords() throws SQLException {
        String SELECT_QUERY = ("SELECT * FROM registration");
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_QUERY)) {

            StringBuilder allRecords = new StringBuilder();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("full_name");
                String email = resultSet.getString("email_id");
                String password = resultSet.getString("password");

                allRecords.append("ID: ").append(id)
                        .append(" |Name: ").append(name)
                        .append(" |Email: ").append(email)
                        .append(" |Password: ").append(password)
                        .append("\n");
            }
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Records");
            infoAlert.setHeaderText("Showing all Records");

            infoAlert.setContentText(allRecords.toString());
            infoAlert.showAndWait();

        }

    }
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
//You need to separate the code
//THis is the database file, not the command file