package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tp_jdbc";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
        	
            Ope.AddAllData(connection, "nom1", "prenom1", 25);
            Ope.AddAllData(connection, "nom2", "prenom2", 18);
            Ope.AddAllData(connection, "nom3", "prenom3", 25);
            Ope.AddAllData(connection, "Vampire", "vvvv", 3800);
        	Ope.AddAllData(connection, "Made To ", "Be Deleted ", 0);

        	System.out.println("Student Before Updating :");
            Ope.GetAllData(connection);

            Ope.UpdateStudent(connection, 10, "prenom2","nom2",19);
                        
            System.out.println("Student After Updating :");
            Ope.GetAllData(connection);
            
            
            System.out.println("Student Before Deleting :");
            Ope.GetAllData(connection);

            Ope.DeleteStudent(connection, 12);
            
            System.out.println("Student After Deleting :");
            Ope.GetAllData(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}
