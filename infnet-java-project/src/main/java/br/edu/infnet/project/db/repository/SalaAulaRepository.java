package br.edu.infnet.project.db.repository;

import br.edu.infnet.project.db.model.domain.SalaAula;
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

public class SalaAulaRepository {

    private final JdbcTemplate jdbcTemplate;

    public SalaAulaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SalaAula> listarTodos() throws SQLException {
        String sqlCommand = "SELECT IdSalaAula, IdAluno, IdProfessor FROM SalaAula;";
        return jdbcTemplate
            .query(sqlCommand, new RowMapper<SalaAula>() {
                @Override
                public SalaAula mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new SalaAula(
                        rs.getInt("IdSalaAula"),
                        rs.getInt("IdAluno"),
                        rs.getInt("IdProfessor")
                    );
                }
            }
        );
    }

    public SalaAula buscarPorId(int idSalaAula) throws SQLException {
        String sqlCommand = "SELECT IdSalaAula, IdAluno, IdProfessor FROM SalaAula WHERE IdSalaAula = ?";
        return jdbcTemplate
            .queryForObject(sqlCommand, new Object[]{idSalaAula}, new RowMapper<SalaAula>() {
                @Override
                public SalaAula mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new SalaAula(
                        rs.getInt("IdSalaAula"),
                        rs.getInt("IdAluno"),
                        rs.getInt("IdProfessor")
                    );
                }
            }
        );
    }

    public void salvar(SalaAula salaAula) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sqlCommand = "INSERT INTO SalaAula (IdAluno, IdProfessor) VALUES (?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, salaAula.getIdAluno());
            pst.setInt(2, salaAula.getIdProfessor());
            return pst;
        }, keyHolder);
        salaAula.setIdSalaAula(Objects.requireNonNull(keyHolder.getKey()).intValue());
    }

    public void atualizar(SalaAula salaAula) throws SQLException {
        String sqlCommand = "UPDATE SalaAula SET IdAluno = ?, IdProfessor = ? WHERE IdSalaAula = ?";
        jdbcTemplate.update(
            sqlCommand,
            salaAula.getIdSalaAula(),
            salaAula.getIdAluno(),
            salaAula.getIdProfessor()
        );
    }

    public void excluir(int idSalaAula) throws SQLException {
        String sqlCommand = "DELETE FROM SalaAula WHERE IdSalaAula = ?";
        jdbcTemplate.update(sqlCommand, idSalaAula);
    }
}
