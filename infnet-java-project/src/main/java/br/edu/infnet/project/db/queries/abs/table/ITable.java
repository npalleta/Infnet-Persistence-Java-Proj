package br.edu.infnet.project.db.queries.abs.table;

import java.util.List;

public interface ITable {

    public String createTable(String tableName, String parameters);

    public String createTable(String tableName, List<List<String>> allParameters);

    public String dropTable(String tableName);
}
