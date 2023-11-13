package br.edu.infnet.project.db.orm.aluno;

import br.edu.infnet.project.db.model.domain.Aluno;
import br.edu.infnet.project.db.service.AlunoService;

import java.util.List;

import static java.lang.System.out;

public class AlunoHibernateJPATest {

    public static void testeAluno(AlunoService alunoService) {
        out.printf("Início do teste - %s%n%n", alunoService.getClass());
        listaAlunos("Estado inicial", alunoService);
        buscaAlunoPorId(1, alunoService);

        // Cria um novo aluno
        Aluno aluno = new Aluno();
        aluno.setNomeAluno("Teste de Aluno");
        aluno.setNumMatricula(123);
        aluno.setNumSalaAula(1);
        aluno.setAtivo(true);

        // Salva o aluno
        alunoService.salvar(aluno);
        listaAlunos("Novo aluno", alunoService);

        // Atualiza o aluno
        aluno.setNomeAluno("Eric Martin");
        alunoService.atualizar(aluno);
        listaAlunos("Atualiza o nome do aluno", alunoService);

        // Deleta o aluno
        alunoService.excluir(aluno);
        listaAlunos("Deleta aluno", alunoService);

        out.printf("Fim do teste - %s%n%n", alunoService.getClass());
    }

    private static void listaAlunos(String mensagem, AlunoService alunoService) {
        out.println(mensagem);
        // Lista todos os alunos
        List<Aluno> alunos = alunoService.buscarTodos();
        alunos.forEach(aluno ->
            out.printf(
                "IdAluno: %s, Nome do Aluno: %s, Número de Matrícula: %d, Número da Sala: %d, Ativo: %b%n",
                aluno.getIdAluno(),
                aluno.getNomeAluno(),
                aluno.getNumMatricula(),
                aluno.getNumSalaAula(),
                aluno.getAtivo()
            )
        );
    }

    private static void buscaAlunoPorId(int idAluno, AlunoService alunoService) {
        out.printf("Buscando o id de aluno número: %d%n", idAluno);
        Aluno aluno = alunoService.buscarPorId(idAluno);
        out.printf(
            "IdAluno: %s, Nome do Aluno: %s, Número de Matrícula: %d, Número da Sala: %d, Ativo: %b%n",
            aluno.getIdAluno(),
            aluno.getNomeAluno(),
            aluno.getNumMatricula(),
            aluno.getNumSalaAula(),
            aluno.getAtivo()
        );
    }
}
