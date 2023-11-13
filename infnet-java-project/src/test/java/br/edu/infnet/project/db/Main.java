package br.edu.infnet.project.db;

import br.edu.infnet.project.db.model.domain.Responsavel;
import br.edu.infnet.project.db.orm.aluno.AlunoHibernateJPATest;
import br.edu.infnet.project.db.orm.aluno.AlunoJdbcTemplateTest;
import br.edu.infnet.project.db.orm.aluno.AlunoJdbcTest;
import br.edu.infnet.project.db.orm.aluno.AlunoJpqlTest;
import br.edu.infnet.project.db.orm.professor.ProfessorHibernateJPATest;
import br.edu.infnet.project.db.orm.professor.ProfessorJdbcTemplateTest;
import br.edu.infnet.project.db.orm.responsavel.ResponsavelHibernateJPATest;
import br.edu.infnet.project.db.orm.responsavel.ResponsavelJdbcTemplateTest;
import br.edu.infnet.project.db.orm.sala.aula.SalaAulaHibernateJPATest;
import br.edu.infnet.project.db.orm.sala.aula.SalaAulaJdbcTemplateTest;
import br.edu.infnet.project.db.service.AlunoService;
import br.edu.infnet.project.db.service.ProfessorService;
import br.edu.infnet.project.db.service.ResponsavelService;
import br.edu.infnet.project.db.service.SalaAulaService;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        // ------------------------------ SALA DE AULA ------------------------------
        testaSalaAula();
        // --------------------------------- ALUNO ----------------------------------
        testaAluno();
        // ------------------------------- PROFESSOR --------------------------------
        testaProfessor();
        // ------------------------------ RESPONSAVEL -------------------------------
        testaResponsavel();
    }

    public static void testaSalaAula() {
        SalaAulaService salaAulaService = new SalaAulaService();
        SalaAulaHibernateJPATest.testeSalaAula(salaAulaService);
        //
        try {
            SalaAulaJdbcTemplateTest.testeSalaAula();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testaAluno() {
        AlunoService alunoService = new AlunoService();
        AlunoHibernateJPATest.testeAluno(alunoService);
        //
        try {
            AlunoJdbcTemplateTest.testeAluno();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //
        AlunoJdbcTest.testeAluno();
        //
        AlunoJpqlTest.testeAluno();
    }

    public static void testaProfessor() {
        ProfessorService professorService = new ProfessorService();
        ProfessorHibernateJPATest.testeProfessor(professorService);
        //
        try {
            ProfessorJdbcTemplateTest.testeProfessor();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testaResponsavel() {
        ResponsavelService responsavelService = new ResponsavelService();
        ResponsavelHibernateJPATest.testeResponsavel(responsavelService);
        //
        try {
            ResponsavelJdbcTemplateTest.testeResponsavel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
