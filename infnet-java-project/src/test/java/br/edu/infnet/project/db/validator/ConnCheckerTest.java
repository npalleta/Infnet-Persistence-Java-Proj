package br.edu.infnet.project.db.validator;

import br.edu.infnet.project.db.conn.DatabaseConn;
import java.sql.Connection;

import static java.lang.System.out;
import static java.util.Arrays.asList;

public class ConnCheckerTest {

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
        DatabaseConn.closeConn();
        out.println(DatabaseConn.getConnStatus());
    }
}
