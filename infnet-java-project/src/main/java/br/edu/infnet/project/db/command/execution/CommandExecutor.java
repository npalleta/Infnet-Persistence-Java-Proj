package br.edu.infnet.project.db.command.execution;

import java.sql.Connection;
import java.sql.Statement;

import static java.lang.System.out;

public class CommandExecutor {

    private final Connection conn;
    private Statement stmt = null;

    public CommandExecutor(Connection conn) {
        this.conn = conn;
    }

    public void executeCommand(String sqlCommand) {

        try {
            this.stmt = this.conn.createStatement();
            int statusCode = this.stmt.executeUpdate(sqlCommand);
            if (statusCode > 0)
                System.out.println("STATUS --> Command finished successfully!");
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            out.println("<< Exception --> Command failed! >>%n");
        }
    }
}