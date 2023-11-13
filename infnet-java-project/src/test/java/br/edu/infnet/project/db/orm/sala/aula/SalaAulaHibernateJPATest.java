package br.edu.infnet.project.db.orm.sala.aula;

import br.edu.infnet.project.db.model.domain.SalaAula;
import br.edu.infnet.project.db.service.SalaAulaService;

import java.util.List;

import static java.lang.System.out;

public class SalaAulaHibernateJPATest {

    public static void testeSalaAula(SalaAulaService salaAulaService) {
        out.printf("Início do teste - %s%n%n", salaAulaService.getClass());
        listaSalas("Estado inicial", salaAulaService);
        buscaSalaPorId(1, salaAulaService);

        // Cria uma nova sala de aula
        SalaAula salaAula = new SalaAula();
        // salaAula.setIdSalaAula(1);
        salaAula.setIdAluno(2);
        salaAula.setIdProfessor(2);

        // Salva uma sala de aula
        salaAulaService.salvar(salaAula);
        listaSalas("Nova sala de aula", salaAulaService);

        // Atualiza a sala de aula
        salaAula.setIdSalaAula(10);
        salaAula.setIdAluno(10);
        salaAula.setIdProfessor(10);
        salaAulaService.atualizar(salaAula);
        listaSalas("Atualiza dados da sala de aula", salaAulaService);

        // Deleta a sala de aula
        salaAulaService.excluir(salaAula);
        listaSalas("Exclusão de sala de aula", salaAulaService);

        out.printf("Fim do teste - %s%n%n", salaAulaService.getClass());
    }

    private static void listaSalas(String mensagem, SalaAulaService salaAulaService) {
        out.println(mensagem);
        // Lista todas as salas
        List<SalaAula> salas = salaAulaService.buscarTodas();
        salas.forEach(salaAula ->
            out.printf(
                "IdSalaAula: %d, IdAluno: %d, IdProfessor: %d%n",
                salaAula.getIdSalaAula(),
                salaAula.getIdAluno(),
                salaAula.getIdProfessor()
            )
        );
    }

    private static void buscaSalaPorId(int idSalaAula, SalaAulaService salaAulaService) {
        out.printf("Buscando o id da sala de aula número: %d%n", idSalaAula);
        SalaAula salaAula = salaAulaService.buscarPorId(idSalaAula);
        out.printf(
            "IdSalaAula: %d, IdAluno: %d, IdProfessor: %d%n",
            salaAula.getIdSalaAula(),
            salaAula.getIdAluno(),
            salaAula.getIdProfessor()
        );
    }
}
