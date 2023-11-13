package br.edu.infnet.project.db.orm.professor;

import br.edu.infnet.project.db.model.domain.Professor;
import br.edu.infnet.project.db.repository.ProfessorRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

import static java.lang.System.out;

public class ProfessorJdbcTemplateTest {

    private static JdbcTemplate jdbcTemplate;

    public static void testeProfessor() throws SQLException {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        jdbcTemplate = new JdbcTemplate();
        ProfessorRepository ProfessorRepository = context.getBean(ProfessorRepository.class);

        // Lista todos os professores
        listaSalas(ProfessorRepository);

        // Busca um professor por ID
        out.println("\nTestando: buscando um professor");
        Professor Professor = ProfessorRepository.buscarPorId(2);
        out.println(Professor);

        // Salva um professor
        out.println("\nTestando: salvando um professor");
        Professor novaProfessor = new Professor(2, "Tim Burton", 1, "História");
        ProfessorRepository.salvar(novaProfessor);
        listaSalas(ProfessorRepository);

        // Atualiza um professor
        out.println("\nTestando: atualizando dados de um professor");
        novaProfessor.setIdProfessor(4);
        novaProfessor.setNomeProfessor("Carolina Oliveira Santos");
        novaProfessor.setNumSalaAula(1);
        novaProfessor.setMateria("Estudos Sociais");
        ProfessorRepository.atualizar(novaProfessor);
        listaSalas(ProfessorRepository);

        // Exclusão de professor
        out.println("\nTestando: excluindo um professor");
        ProfessorRepository.excluir(novaProfessor.getIdProfessor());
        out.printf("IdProfessor: %d%n", novaProfessor.getIdProfessor());
        listaSalas(ProfessorRepository);
    }

    private static void listaSalas(ProfessorRepository ProfessorRepository) throws SQLException {
        out.println("\nTestando: listando todos os professores");
        ProfessorRepository.listarTodos().forEach(out::println);
    }
}
