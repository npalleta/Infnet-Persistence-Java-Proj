package br.edu.infnet.project.db.queries;

import br.edu.infnet.project.db.queries.abs.database.IDatabase;
import br.edu.infnet.project.db.queries.abs.table.ITable;
import br.edu.infnet.project.db.queries.abs.table.TableParameters;

import java.util.List;

import static java.lang.String.format;

public class QueryManager implements IDatabase, ITable {

    @Override
    public String createDatabase(String databaseName) {
        return format("CREATE DATABASE IF NOT EXISTS %s;", databaseName);
    }

    @Override
    public String dropDatabase(String databaseName) {
        return format("DROP DATABASE IF EXISTS %s;", databaseName);
    }

    @Override
    public String useDatabase(String databaseName) {
        return format("USE %s;", databaseName);
    }

    @Override
    public String createTable(String tableName, String parameters) {
        return format("CREATE TABLE IF NOT EXISTS %s (%s);", tableName, parameters);
    }

    @Override
    public String createTable(String tableName, List<List<String>> allParameters) {
        return format(
            "CREATE TABLE IF NOT EXISTS %s (%s);",
            tableName, new TableParameters(allParameters).createWithAllParameters()
        );
    }

    @Override
    public String dropTable(String tableName) {
        return null;
    }
}