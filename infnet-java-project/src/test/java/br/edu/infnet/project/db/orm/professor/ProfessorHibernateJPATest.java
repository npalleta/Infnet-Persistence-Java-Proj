package br.edu.infnet.project.db.orm.professor;

import br.edu.infnet.project.db.model.domain.Professor;
import br.edu.infnet.project.db.service.ProfessorService;

import java.util.List;

import static java.lang.System.out;

public class ProfessorHibernateJPATest {

    public static void testeProfessor(ProfessorService professorService) {
        out.printf("Início do teste - %s%n%n", professorService.getClass());
        listaProfessores("Estado inicial", professorService);
        buscaProfessorPorId(1, professorService);

        // Cria um novo professor
        Professor professor = new Professor();
        professor.setNomeProfessor("Michael Angelo Batio");
        professor.setNumSalaAula(1);
        professor.setMateria("Geografia");

        // Salva um novo professor
        professorService.salvar(professor);
        listaProfessores("Novo professor", professorService);

        // Atualiza um professor
        // professor.setIdProfessor(2);
        professor.setNomeProfessor("Leo Fender");
        professor.setNumSalaAula(1);
        professor.setMateria("Educação Física");
        professorService.atualizar(professor);
        listaProfessores("Atualiza dados do professor", professorService);

        // Deleta um professor
        professorService.excluir(professor);
        listaProfessores("Exclusão do professor", professorService);

        out.printf("Fim do teste - %s%n%n", professorService.getClass());
    }

    private static void listaProfessores(String mensagem, ProfessorService professorService) {
        out.println(mensagem);
        // Lista todos os professores
        List<Professor> professores = professorService.buscarTodos();
        professores.forEach(professor ->
            out.printf(
                "IdProfessor: %d, NomeProfessor: %s, NumSalaAula: %d, Materia: %s%n",
                professor.getIdProfessor(),
                professor.getNomeProfessor(),
                professor.getNumSalaAula(),
                professor.getMateria()
            )
        );
    }

    private static void buscaProfessorPorId(int idProfessor, ProfessorService professorService) {
        out.printf("Buscando o id do professor número: %d%n", idProfessor);
        Professor professor = professorService.buscarPorId(idProfessor);
        out.printf(
            "IdProfessor: %d, NomeProfessor: %s, NumSalaAula: %d, Materia: %s%n",
            professor.getIdProfessor(),
            professor.getNomeProfessor(),
            professor.getNumSalaAula(),
            professor.getMateria()
        );
    }
}
