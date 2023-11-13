package br.edu.infnet.project.db.repository;

import br.edu.infnet.project.db.model.domain.Professor;
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

public class ProfessorRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProfessorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Professor> listarTodos() throws SQLException {
        String sqlCommand = "SELECT IdProfessor, NomeProfessor, NumSalaAula, Materia FROM Professor;";
        return jdbcTemplate
            .query(sqlCommand, new RowMapper<Professor>() {
                @Override
                public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Professor(
                        rs.getInt("IdProfessor"),
                        rs.getString("NomeProfessor"),
                        rs.getInt("NumSalaAula"),
                        rs.getString("Materia")
                    );
                }
            }
        );
    }

    public Professor buscarPorId(int idProfessor) throws SQLException {
        String sqlCommand = "SELECT IdProfessor, NomeProfessor, NumSalaAula, Materia FROM Professor WHERE IdProfessor = ?";
        return jdbcTemplate
            .queryForObject(sqlCommand, new Object[]{idProfessor}, new RowMapper<Professor>() {
                @Override
                public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Professor(
                        rs.getInt("IdProfessor"),
                        rs.getString("NomeProfessor"),
                        rs.getInt("NumSalaAula"),
                        rs.getString("Materia")
                    );
                }
            }
        );
    }

    public void salvar(Professor professor) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sqlCommand = "INSERT INTO Professor (NomeProfessor, NumSalaAula, Materia) VALUES (?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, professor.getNomeProfessor());
            pst.setInt(2, professor.getNumSalaAula());
            pst.setString(3, professor.getMateria());
            return pst;
        }, keyHolder);
        professor.setIdProfessor(Objects.requireNonNull(keyHolder.getKey()).intValue());
    }

    public void atualizar(Professor professor) throws SQLException {
        String sqlCommand = "UPDATE Professor SET NomeProfessor = ?, NumSalaAula = ?, Materia = ? WHERE IdProfessor = ?";
        jdbcTemplate.update(
            sqlCommand,
            professor.getIdProfessor(),
            professor.getNomeProfessor(),
            professor.getNumSalaAula(),
            professor.getMateria()
        );
    }

    public void excluir(int idProfessor) throws SQLException {
        String sqlCommand = "DELETE FROM Professor WHERE IdProfessor = ?";
        jdbcTemplate.update(sqlCommand, idProfessor);
    }
}
