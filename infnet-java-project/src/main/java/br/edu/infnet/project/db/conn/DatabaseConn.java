package br.edu.infnet.project.db.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Class.forName;
import static java.lang.System.out;
import static java.util.Objects.requireNonNull;

public class DatabaseConn {

    private static final String driverName, url, username, password, errorEx;

    static {
        driverName = ConnParameters.getConnArgs().get(0);
        url = ConnParameters.getConnArgs().get(1);
        username = ConnParameters.getConnArgs().get(2);
        password = ConnParameters.getConnArgs().get(3);
        errorEx = "Driver not found or the connection was not established!";
    }

    private static String connStatus;

    public static Connection getConnection() {

        Connection conn;

        try {
            forName(DatabaseConn.driverName);
            conn = DriverManager.getConnection(DatabaseConn.url, DatabaseConn.username, DatabaseConn.password);
            DatabaseConn.connStatus = (conn != null) ? "STATUS --> Connection was successful!" : "STATUS --> Unable to connect!";
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            out.printf("<< Exception --> %s >>%n", DatabaseConn.errorEx);
            return null;
        }
    }

    public static String getConnStatus() {
        return DatabaseConn.connStatus;
    }

    public static void closeConn() {
        try {
            requireNonNull(DatabaseConn.getConnection()).close();
            DatabaseConn.connStatus = "STATUS --> Connection has been closed!";
        } catch (SQLException ex) {
            DatabaseConn.connStatus = "STATUS --> Connection cannot be closed!";
            out.printf("<< Exception --> %s >>%n", DatabaseConn.errorEx);
        }
    }

    public static Connection restartConn() {
        DatabaseConn.closeConn();
        return DatabaseConn.getConnection();
    }

    public static class ConnParameters {

        private static List<String> connArgs;

        public static List<String> getConnArgs() {
            return ConnParameters.connArgs;
        }

        public static void setConnArgs(List<String> connArgs) {
            ConnParameters.connArgs = connArgs;
        }
    }
}