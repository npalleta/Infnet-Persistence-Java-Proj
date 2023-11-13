package br.edu.infnet.project.db.repository;

import br.edu.infnet.project.db.model.domain.Responsavel;
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

public class ResponsavelRepository {

    private final JdbcTemplate jdbcTemplate;

    public ResponsavelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Responsavel> listarTodos() throws SQLException {
        String sqlCommand = "SELECT * FROM Responsavel;";
        return jdbcTemplate
            .query(sqlCommand, new RowMapper<Responsavel>() {
                @Override
                public Responsavel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Responsavel(
                        rs.getInt("IdResponsavel"),
                        rs.getString("NomeResponsavel"),
                        rs.getString("Parentesco")
                    );
                }
            }
        );
    }

    public Responsavel buscarPorId(int idResponsavel) throws SQLException {
        String sqlCommand = "SELECT IdResponsavel, NomeResponsavel, Parentesco FROM Responsavel WHERE IdResponsavel = ?";
        return jdbcTemplate
            .queryForObject(sqlCommand, new Object[]{idResponsavel}, new RowMapper<Responsavel>() {
                @Override
                public Responsavel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Responsavel(
                        rs.getInt("IdResponsavel"),
                        rs.getString("NomeResponsavel"),
                        rs.getString("Parentesco")
                    );
                }
            }
        );
    }

    public void salvar(Responsavel responsavel, int idAluno) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sqlCommand = "INSERT INTO Responsavel (NomeResponsavel, IdAluno, Parentesco) VALUES (?, ?, ?)";
        responsavel.getAluno().setIdAluno(idAluno);
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, responsavel.getNomeResponsavel());
            pst.setInt(2, responsavel.getAluno().getIdAluno());
            pst.setString(3, responsavel.getParentesco());
            return pst;
        }, keyHolder);
        responsavel.setIdResponsavel(Objects.requireNonNull(keyHolder.getKey()).intValue());
    }

    public void atualizar(Responsavel responsavel) throws SQLException {
        String sqlCommand = "UPDATE Responsavel SET NomeResponsavel = ?, Parentesco = ? WHERE IdResponsavel = ?";
        jdbcTemplate.update(
            sqlCommand,
            responsavel.getIdResponsavel(),
            responsavel.getNomeResponsavel(),
            responsavel.getParentesco()
        );
    }

    public void excluir(int idResponsavel) throws SQLException {
        String sqlCommand = "DELETE FROM Responsavel WHERE IdResponsavel = ?";
        jdbcTemplate.update(sqlCommand, idResponsavel);
    }
}
