package br.edu.infnet.project.db.queries.abs.database;

public interface IDatabase {

    public String createDatabase(String databaseName);

    public String dropDatabase(String databaseName);

    public String useDatabase(String databaseName);
}