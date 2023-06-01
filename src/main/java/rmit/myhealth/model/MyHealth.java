package rmit.myhealth.model;

import java.sql.*;


public class MyHealth {

    private static MyHealth instance;
    private UserController userController;
    private User currentUser;

    public MyHealth() {
        this.userController = new UserController();
        this.currentUser = null;
    }

    public static MyHealth getInstance() {
        if (instance == null) {
            instance = new MyHealth();
        }
        return instance;
    }

    public Connection connectToDatabase() {
        try {
            String url = "jdbc:sqlite:myhealth.db";
            Connection connection = DriverManager.getConnection(url);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createTables(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String createUsersTableQuery = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT UNIQUE, password TEXT)";
            statement.executeUpdate(createUsersTableQuery);

            String createHealthRecordsTableQuery = "CREATE TABLE IF NOT EXISTS health_records (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, record_date TEXT, health_data TEXT)";
            statement.executeUpdate(createHealthRecordsTableQuery);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public UserController getUserController() {
        return this.userController;
    }

    public boolean login(String username, String password) {
        if (currentUser != null) {
            // A user is already logged in
            return false;
        }

        User user = userController.getUser(username);

        if (user == null || !UserAuthenticator.authenticate(user, password)) {
            // Invalid username or password
            return false;
        }

        if (dbLogin(connectToDatabase(), username, password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void createUser(String username, String password, String firstName, String lastName) {
        userController.createUser(username, password, firstName, lastName);
        registerUser(connectToDatabase(), username, password);
    }

    public void registerUser(Connection connection, String username, String password) {
        try {
            Statement statement = connection.createStatement();
            String insertUserQuery = "INSERT INTO users (username, password) VALUES ('" + username + "', '" + password + "')";
            statement.executeUpdate(insertUserQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean dbLogin(Connection connection, String username, String password) {
        try {
            Statement statement = connection.createStatement();
            String selectUserQuery = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(selectUserQuery);
            boolean success = resultSet.next();
            resultSet.close();
            statement.close();
            return success;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UserExistsInDatabase(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectToDatabase();

            // Prepare the SQL statement
            String query = "SELECT username FROM users WHERE username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if any rows are returned
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }
}