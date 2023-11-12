package br.edu.infnet.project.db.queries.abs.select;

public interface ISelect {

    public String select(String fromTable);

    public String select(String fromTable, String where);
}
