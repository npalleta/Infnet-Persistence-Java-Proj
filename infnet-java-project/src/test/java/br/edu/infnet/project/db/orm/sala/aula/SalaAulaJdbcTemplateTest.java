package br.edu.infnet.project.db.orm.sala.aula;

import br.edu.infnet.project.db.model.domain.SalaAula;
import br.edu.infnet.project.db.repository.SalaAulaRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

import static java.lang.System.out;

public class SalaAulaJdbcTemplateTest {

    private static JdbcTemplate jdbcTemplate;

    public static void testeSalaAula() throws SQLException {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        jdbcTemplate = new JdbcTemplate();
        SalaAulaRepository salaAulaRepository = context.getBean(SalaAulaRepository.class);

        // Lista todas as salas
        listaSalas(salaAulaRepository);

        // Busca uma sala por ID
        out.println("\nTestando: buscando uma sala");
        SalaAula salaAula = salaAulaRepository.buscarPorId(1);
        out.println(salaAula);

        // Salva uma sala
        out.println("\nTestando: salvando uma sala");
        SalaAula novaSalaAula = new SalaAula(3, 3, 3);
        salaAulaRepository.salvar(novaSalaAula);
        listaSalas(salaAulaRepository);

        // Atualiza uma sala
        out.println("\nTestando: atualizando uma sala");
        novaSalaAula.setIdSalaAula(4);
        novaSalaAula.setIdAluno(5);
        novaSalaAula.setIdProfessor(6);
        salaAulaRepository.atualizar(novaSalaAula);
        listaSalas(salaAulaRepository);

        // Exclui uma sala
        out.println("\nTestando: excluindo uma sala");
        salaAulaRepository.excluir(novaSalaAula.getIdSalaAula());
        out.printf("IdSalaAula: %d%n", novaSalaAula.getIdSalaAula());
        listaSalas(salaAulaRepository);
    }

    private static void listaSalas(SalaAulaRepository salaAulaRepository) throws SQLException {
        out.println("\nTestando: todas as salas");
        salaAulaRepository.listarTodos().forEach(out::println);
    }
}
