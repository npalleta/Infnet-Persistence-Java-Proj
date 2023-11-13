package br.edu.infnet.project.db.orm.aluno;

import br.edu.infnet.project.db.model.domain.Aluno;
import br.edu.infnet.project.db.model.domain.Professor;
import jakarta.persistence.*;

import java.util.List;

import static java.lang.System.out;

public class AlunoJpqlTest {

    @PersistenceContext
    private static EntityManager em;

    public static void testeAluno() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "infnet_project" );
        em = emfactory.createEntityManager();
        listaAlunosProfessor();
    }

    private static void todosAlunos() {
        out.println("\nTestando: lista todos os alunos cadastrados");
        List<Aluno> alunoList =  em.createQuery("SELECT object(a) FROM Aluno a").getResultList();
        alunoList.forEach(aluno ->
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

    private static void listaAlunosProfessor() {
        out.println("\nTestando: lista todos os alunos dos professores cadastrados");
        List<Professor> professorList = em.createQuery("SELECT object(p) FROM Professor p").getResultList();
        professorList.forEach(professor ->
            out.printf(
                "IdProfessor: %s, Nome do Professor: %s, Número da Sala: %d, Materia: %s%n",
                professor.getIdProfessor(),
                professor.getNomeProfessor(),
                professor.getNumSalaAula(),
                professor.getMateria()
            )
        );
    }
}
