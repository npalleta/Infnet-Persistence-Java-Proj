package br.edu.infnet.project.db.repository;

import br.edu.infnet.project.db.model.domain.Aluno;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class AlunoRepository {

    private final JdbcTemplate jdbcTemplate;

    public AlunoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Aluno> listarTodos() throws SQLException {
        String sqlCommand = "SELECT IdAluno, NomeAluno, NumMatricula, NumSalaAula, Ativo FROM Aluno;";
        return jdbcTemplate
            .query(sqlCommand, new RowMapper<Aluno>() {
                @Override
                public Aluno mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Aluno(
                        rs.getInt("IdAluno"),
                        rs.getString("NomeAluno"),
                        rs.getInt("NumMatricula"),
                        rs.getInt("NumSalaAula"),
                        rs.getBoolean("Ativo")
                    );
                }
            }
        );
    }

    public Aluno buscarPorId(int idAluno) throws SQLException {
        String sqlCommand = "SELECT IdAluno, NomeAluno, NumMatricula, NumSalaAula, Ativo FROM Aluno WHERE IdAluno = ?";
        return jdbcTemplate
            .queryForObject(sqlCommand, new Object[]{idAluno}, new RowMapper<Aluno>() {
                @Override
                public Aluno mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Aluno(
                        rs.getInt("IdAluno"),
                        rs.getString("NomeAluno"),
                        rs.getInt("NumMatricula"),
                        rs.getInt("NumSalaAula"),
                        rs.getBoolean("Ativo")
                    );
                }
            }
        );
    }

    public void salvar(Aluno aluno) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sqlCommand = "INSERT INTO Aluno (NomeAluno, NumMatricula, NumSalaAula, Ativo) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, aluno.getNomeAluno());
            pst.setInt(2, aluno.getNumMatricula());
            pst.setInt(3, aluno.getNumSalaAula());
            pst.setBoolean(4, aluno.getAtivo());
            return pst;
        }, keyHolder);
        aluno.setIdAluno(Objects.requireNonNull(keyHolder.getKey()).intValue());
    }

    public void atualizar(Aluno aluno) throws SQLException {
        String sqlCommand = "UPDATE Aluno SET NomeAluno = ?, NumMatricula = ?, NumSalaAula = ? , Ativo = ? WHERE IdAluno = ?";
        jdbcTemplate.update(
            sqlCommand,
            aluno.getIdAluno(),
            aluno.getNomeAluno(),
            aluno.getNumMatricula(),
            aluno.getNumSalaAula(),
            aluno.getAtivo()
        );
    }

    public void excluir(int idAluno) throws SQLException {
        String sqlCommand = "DELETE FROM Aluno WHERE IdAluno = ?";
        jdbcTemplate.update(sqlCommand, idAluno);
    }
}
