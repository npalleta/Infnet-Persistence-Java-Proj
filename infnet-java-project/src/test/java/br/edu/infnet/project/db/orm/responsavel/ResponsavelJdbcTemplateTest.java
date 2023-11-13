package br.edu.infnet.project.db.orm.responsavel;

import br.edu.infnet.project.db.model.domain.Aluno;
import br.edu.infnet.project.db.model.domain.Responsavel;
import br.edu.infnet.project.db.repository.ResponsavelRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

import static java.lang.System.out;

public class ResponsavelJdbcTemplateTest {

    private static JdbcTemplate jdbcTemplate;

    public static void testeResponsavel() throws SQLException {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        jdbcTemplate = new JdbcTemplate();
        ResponsavelRepository responsavelRepository = context.getBean(ResponsavelRepository.class);

        // Lista todos os responsáveis
        listaSalas(responsavelRepository);

        // Busca um responsável por ID
        out.println("\nTestando: buscando um responsável");
        Responsavel Responsavel = responsavelRepository.buscarPorId(1);
        out.println(Responsavel);

        // Salva um responsável
        out.println("\nTestando: salvando um responsável");
        Responsavel novoResponsavel = new Responsavel(1, new Aluno(), "Luca Turilli", "Pai");
        responsavelRepository.salvar(novoResponsavel, 1);
        listaSalas(responsavelRepository);

        // Atualiza um responsável
        out.println("\nTestando: atualizando dados de um responsável");
        novoResponsavel.setIdResponsavel(4);
        novoResponsavel.setNomeResponsavel("Leandro Karnal");
        novoResponsavel.setParentesco("Avô");
        responsavelRepository.atualizar(novoResponsavel);
        listaSalas(responsavelRepository);

        // Exclusão de responsável
        out.println("\nTestando: excluindo um responsável");
        responsavelRepository.excluir(novoResponsavel.getIdResponsavel());
        out.printf("IdResponsavel: %d%n", novoResponsavel.getIdResponsavel());
        listaSalas(responsavelRepository);
    }

    private static void listaSalas(ResponsavelRepository responsavelRepository) throws SQLException {
        out.println("\nTestando: listando todos os responsáveis");
        responsavelRepository.listarTodos().forEach(out::println);
    }
}
