package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ope{
    private static final String URL = "jdbc:mysql://localhost:3306/tp_jdbc";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
    }
    
    
    public static void GetAllData(Connection connection) throws SQLException {
        System.out.println("\nAll Students data:");
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM etudiants")) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                int age = resultSet.getInt("age");

                System.out.println("ID : " + id + ", LastName : " + nom + ", FirstName : " + prenom + ", Age : " + age);
            }}}

    public static void AddAllData(Connection connection, String nom, String prenom, int age) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO etudiants (nom, prenom, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setInt(3, age);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student added !");
            } else {
                System.out.println("Something went wrong Adding Student !");
            }}}

    public static void UpdateStudent(Connection connection, int id, String nom, String prenom, int age) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE etudiants SET nom = ?, prenom = ?, age = ? WHERE id = ?")) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student Data Updated !");
            } else {
                System.out.println("Something went wrong Updating Student !");
            }}}

    public static void DeleteStudent(Connection connection, int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM etudiants WHERE id = ?")) {
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student Deleted !");
            } else {
                System.out.println("Something went wrong when Deleting Student !");
            }}}
}