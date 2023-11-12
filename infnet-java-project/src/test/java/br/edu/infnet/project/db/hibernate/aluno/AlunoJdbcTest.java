package br.edu.infnet.project.db.hibernate.aluno;

import java.sql.*;

import static java.lang.System.out;

public class AlunoJdbcTest {

    public static void testeAluno() {

        Connection connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/infnet_project", "root", "root1234");
            Statement statement;
            statement = connection.createStatement();

            listaAlunos(statement);
            alteraAluno(statement);
            listaAlunos(statement);
            incluiAluno(statement);
            listaAlunos(statement);

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void listaAlunos(Statement statement) throws SQLException {
        String sqlCommand = "SELECT * FROM infnet_project.Aluno a;";
        ResultSet resultSet = statement.executeQuery(sqlCommand);

        int idAluno, numMatricula, numSalaAula;
        String nomeAluno;
        boolean ativo;

        while (resultSet.next()) {
            idAluno = resultSet.getInt("IdAluno");
            nomeAluno = resultSet.getString("NomeAluno");
            numMatricula = resultSet.getInt("NumMatricula");
            numSalaAula = resultSet.getInt("NumSalaAula");
            ativo = resultSet.getBoolean("Ativo");

            out.printf(
                "IdAluno: %s, Nome do Aluno: %s, Número de Matrícula: %d, Número da Sala: %d, Ativo: %b%n",
                idAluno,
                nomeAluno,
                numMatricula,
                numSalaAula,
                ativo
            );
        }
        //
        resultSet.close();
        out.println();
    }

    private static void alteraAluno(Statement statement) throws SQLException {
        String sqlCommand = "UPDATE infnet_project.Aluno SET NomeAluno = 'André Matos', NumMatricula = 789, NumSalaAula = 1 , Ativo = true WHERE IdAluno = 1";
        statement.executeUpdate(sqlCommand);
    }

    private static void incluiAluno(Statement statement) throws SQLException {
        String sqlCommand = "INSERT INTO infnet_project.Aluno (NomeAluno, NumMatricula, NumSalaAula, Ativo) VALUES ('Aluno XPTO', 1234, 1, true)";
        statement.executeUpdate(sqlCommand);
    }
}
