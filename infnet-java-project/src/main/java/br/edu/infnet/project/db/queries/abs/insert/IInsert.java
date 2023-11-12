package br.edu.infnet.project.db.queries.abs.insert;

import java.util.List;

public interface IInsert {

    String insert(String fromTable, List<String> columns, List<String> values);
}