package br.edu.infnet.project.db.queries.abs.table;

import java.util.List;

public class TableParameters {

    private String parameters = "";
    private List<String> columns;
    private List<String> attributes;
    private List<String> definitions;
    List<List<String>> allParameters;

    public TableParameters() {
    }

    public TableParameters(List<String> columns, List<String> attributes) {
        this.columns = columns;
        this.attributes = attributes;
    }

    public TableParameters(List<String> columns, List<String> attributes, List<String> definitions) {
        this.columns = columns;
        this.attributes = attributes;
        this.definitions = definitions;
    }

    public TableParameters(List<List<String>> allParameters) {
        this.allParameters = allParameters;
    }

    public String createWithColumnsAndAttributes() throws Exception {
        if (!validateParameters()) {
            System.err.println("Error: Parameters do not exist or are wrong, please correct!");
        } else {
            for (int i = 0; i < this.columns.size(); i++) {
                this.parameters = this.parameters
                    .concat(this.columns.get(i))
                    .concat(" ")
                    .concat(this.attributes.get(i))
                    .concat(", ");
            }
        }
        return this.parameters.substring(0, this.parameters.length() - 2);
    }

    public String createWithParameters() {
        if (!validateParameters()) {
            System.err.println("Error: Parameters do not exist or are wrong, please correct!");
        } else {
            if (this.columns.size() == this.attributes.size()) {
                for (int i = 0; i < this.columns.size(); i++) {
                    this.parameters = this.parameters
                        .concat(this.columns.get(i))
                        .concat(" ")
                        .concat(this.attributes.get(i))
                        .concat(" ")
                        .concat(this.definitions.get(i))
                        .concat(", ");
                }
            }
        }
        return this.parameters.substring(0, this.parameters.length() - 2);
    }

    public String createWithAllParameters() {
        if (!validateAllParameters()) {
            System.err.println("Error: Parameters do not exist or are wrong, please correct!");
        } else {
            for (int i = 0; i < this.allParameters.get(0).size(); i++) {
                this.parameters = this.parameters
                    .concat(this.allParameters.get(0).get(i))
                    .concat(" ")
                    .concat(this.allParameters.get(1).get(i))
                    .concat(" ")
                    .concat(this.allParameters.get(2).get(i))
                    .concat(", ");
            }
        }
        return this.parameters.substring(0, this.parameters.length() - 2);
    }

    private boolean validateParameters() {
        return (this.columns == null || this.attributes == null)
            || (this.columns.isEmpty() || this.attributes.isEmpty())
            || (this.columns.size() == this.attributes.size());
    }

    private boolean validateAllParameters() {
        return (this.allParameters == null || this.allParameters.get(0).isEmpty()
            || this.allParameters.get(0).size() == this.allParameters.get(1).size());
    }
}
