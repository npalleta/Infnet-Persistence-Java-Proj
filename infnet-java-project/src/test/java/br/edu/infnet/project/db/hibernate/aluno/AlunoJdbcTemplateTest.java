package br.edu.infnet.project.db.hibernate.aluno;

import br.edu.infnet.project.db.model.domain.Aluno;
import br.edu.infnet.project.db.repository.AlunoRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

import static java.lang.System.out;

public class AlunoJdbcTemplateTest {

    private static JdbcTemplate jdbcTemplate;

    public static void testeAluno() throws SQLException {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        jdbcTemplate = new JdbcTemplate();
        AlunoRepository alunoRepository = context.getBean(AlunoRepository.class);

        // Lista todos os alunos
        listaAlunos(alunoRepository);

        // Busca um aluno pelo ID
        out.println("\nTestando: buscando um aluno");
        Aluno aluno = alunoRepository.buscarPorId(1);
        out.println(aluno);

        // Salva um aluno
        out.println("\nTestando: salvando um aluno");
        Aluno novoAluno = new Aluno("Tony Mills", 456, 1, false);
        alunoRepository.salvar(novoAluno);
        listaAlunos(alunoRepository);

        // Atualiza um aluno
        out.println("\nTestando: atualizando um aluno");
        novoAluno.setNomeAluno("Klaus Meine");
        alunoRepository.atualizar(novoAluno);
        listaAlunos(alunoRepository);

        // Exclui um aluno
        out.println("\nTestando: excluindo um aluno");
        alunoRepository.excluir(novoAluno.getIdAluno());
        listaAlunos(alunoRepository);
    }

    private static void listaAlunos(AlunoRepository alunoRepository) throws SQLException {
        out.println("\nTestando: todos os alunos");
        alunoRepository.listarTodos().forEach(out::println);
    }
}
