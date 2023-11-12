package br.edu.infnet.project.db;

import br.edu.infnet.project.db.hibernate.aluno.AlunoHibernateJPATest;
import br.edu.infnet.project.db.hibernate.aluno.AlunoJdbcTemplateTest;
import br.edu.infnet.project.db.hibernate.aluno.AlunoJdbcTest;
import br.edu.infnet.project.db.hibernate.aluno.AlunoJpqlTest;
import br.edu.infnet.project.db.hibernate.sala.aula.SalaAulaJdbcTemplateTest;
import br.edu.infnet.project.db.service.AlunoService;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        // ------------------------------ SALA DE AULA ------------------------------
        testaSalaAula();
        // --------------------------------- ALUNO ----------------------------------
        testaAluno();
    }

    public static void testaSalaAula() {
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
}
