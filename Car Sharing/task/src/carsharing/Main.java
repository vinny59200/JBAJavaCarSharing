package carsharing;

import java.sql.*;
import java.util.Scanner;

public class Main {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:C:/_dev/VVJAVA/Car Sharing/Car Sharing/task/src/carsharing/db/carsharing";

    //  Database credentials
    static final String USER = "";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(true);

            //STEP 3: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS COMPANY " +
                    "(ID  IDENTITY not NULL PRIMARY KEY, " +
                    " NAME  VARCHAR(255) UNIQUE NOT NULL, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

            boolean exit = false;
            while (!exit) {
                System.out.println("1. Log in as a manager");
                System.out.println("0. Exit");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        loginAsManager(conn);
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
        System.out.println("Goodbye!");
    }


    private static void loginAsManager(Connection conn) {
        boolean back = false;
        while (!back) {
            System.out.println("1. Company list");
            System.out.println("2. Create a company");
            System.out.println("0. Back");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    listCompanies(conn);
                    break;
                case 2:
                    createCompany(conn);
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private static void createCompany(Connection conn) {
        System.out.println("Enter the company name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO COMPANY (NAME) VALUES (?)");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
//            System.out.println("Company created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void listCompanies(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM COMPANY";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next() == false) {
                System.out.println("The company list is empty!");
            } else {
                System.out.println("Company list:");
                do {
                    System.out.println(rs.getInt("id") + ". " + rs.getString("name"));
                } while (rs.next());
            }
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}