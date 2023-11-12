package br.edu.infnet.project.db.validator;

import br.edu.infnet.project.db.command.execution.CommandExecutor;
import br.edu.infnet.project.db.conn.DatabaseConn;
import br.edu.infnet.project.db.queries.QueryManager;

import java.sql.Connection;
import java.util.List;

import static java.lang.System.out;
import static java.util.Arrays.asList;

public class TableCreatorTest {

    private final QueryManager queryManager = new QueryManager();

    public void createSimpleTable() {
        // Configurando conexão...
        DatabaseConn.ConnParameters.setConnArgs(
            asList(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/db",
                "root",
                "root1234"
            )
        );

        // Fecha e abre uma instância de conexão com o banco de dados...
        Connection conn = DatabaseConn.restartConn();
        CommandExecutor commandExecutor = new CommandExecutor(conn);

        out.println(DatabaseConn.getConnStatus());

        // Usa uma base de dados...
        commandExecutor.executeCommand(this.queryManager.useDatabase("DatabaseTest"));

        // Cria uma tabela...
        List<List<String>> withParameters = List.of(
            List.of("Id", "Title", "StartDate", "DueDate"),
            List.of("INT", "VARCHAR(255)", "DATE", "DATE"),
            List.of("PRIMARY KEY", "NOT NULL", "NOT NULL", "NOT NULL")
        );
        commandExecutor.executeCommand(
            new QueryManager()
                .createTable(
                    "Task",
                    withParameters
                )
            //
        );

        // Fecha a conexão novamente.
        DatabaseConn.closeConn();
        out.println(DatabaseConn.getConnStatus());
    }

    public static void main(String[] args) {
        new TableCreatorTest().createSimpleTable();
    }
}

// Cria uma tabela...
/* Exemplo.:
    String tableComm =
        new QueryManager().createTable(
            "Task",
            new TableParameters(
                List.of("Id", "Title", "StartDate", "DueDate"),
                List.of("INT", "VARCHAR(255)", "DATE", "DATE"),
                List.of("PRIMARY KEY", "NOT NULL", "NOT NULL", "NOT NULL")
            ).createWithAllParameters()
        );
    //
*/