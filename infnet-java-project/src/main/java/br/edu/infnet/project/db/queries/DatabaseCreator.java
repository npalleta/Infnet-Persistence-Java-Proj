package br.edu.infnet.project.db.queries;

import java.sql.Connection;
import java.sql.Statement;

import static java.lang.System.out;

public class DatabaseCreator {

    private final Connection conn;

    public DatabaseCreator(Connection conn) {
        this.conn = conn;
    }

    public void createDatabase(String databaseName) {

        Statement stmt;

        try {
            stmt = this.conn.createStatement();
            int statusCode = stmt.executeUpdate(new QueryManager().createDatabase(databaseName));
            if (statusCode > 0)
                System.out.println("STATUS --> Database has been created or already exists!");
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            out.println("<< Exception --> Database has not been created! >>%n");
        }
    }
}
