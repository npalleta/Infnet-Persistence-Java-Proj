package br.edu.infnet.project.db.validator;

import br.edu.infnet.project.db.conn.DatabaseConn;
import br.edu.infnet.project.db.queries.DatabaseCreator;

import java.sql.Connection;

import static java.lang.System.out;
import static java.util.Arrays.*;

public class DatabaseCreatorTest {

    public static void main(String[] args) {
        DatabaseConn.ConnParameters.setConnArgs(
            asList(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/db",
                "root",
                "root1234"
            )
        );
        Connection conn = DatabaseConn.restartConn();
        out.println(DatabaseConn.getConnStatus());
        new DatabaseCreator(conn).createDatabase("DatabaseTest");
        DatabaseConn.closeConn();
        out.println(DatabaseConn.getConnStatus());
    }
}
