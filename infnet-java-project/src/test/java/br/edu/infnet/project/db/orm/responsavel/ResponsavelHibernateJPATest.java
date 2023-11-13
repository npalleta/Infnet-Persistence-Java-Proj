package br.edu.infnet.project.db.orm.responsavel;

import br.edu.infnet.project.db.model.domain.Responsavel;
import br.edu.infnet.project.db.service.ResponsavelService;

import java.util.List;

import static java.lang.System.out;

public class ResponsavelHibernateJPATest {

    public static void testeResponsavel(ResponsavelService responsavelService) {
        out.printf("Início do teste - %s%n%n", responsavelService.getClass());
        listaResponsaveis("Estado inicial", responsavelService);
        buscaResponsavelPorId(1, responsavelService);

        // Cria um novo responsável
        Responsavel responsavel = new Responsavel();
        responsavel.setNomeResponsavel("Doro Pesch");
        responsavel.setIdAluno(1);
        responsavel.setParentesco("Irmã");

        // Salva um novo responsável
        responsavelService.salvar(responsavel);
        listaResponsaveis("Novo responsável", responsavelService);

        // Atualiza um responsável
        responsavel.setNomeResponsavel("Mr. Brownstone");
        responsavel.setParentesco("Tio");
        responsavelService.atualizar(responsavel);
        listaResponsaveis("Atualiza dados do responsável", responsavelService);

        // Deleta um responsável
        responsavelService.excluir(responsavel);
        listaResponsaveis("Exclusão do responsável", responsavelService);

        out.printf("Fim do teste - %s%n%n", responsavelService.getClass());
    }

    private static void listaResponsaveis(String mensagem, ResponsavelService responsavelService) {
        out.println(mensagem);
        // Lista todos os responsável
        List<Responsavel> responsaveis = responsavelService.buscarTodos();
        responsaveis.forEach(responsavel ->
            out.printf(
                "IdResponsavel: %d, NomeResponsavel: %s, Parentesco: %s%n",
                responsavel.getIdResponsavel(),
                responsavel.getIdAluno(),
                responsavel.getNomeResponsavel(),
                responsavel.getAluno().getIdAluno(),
                responsavel.getParentesco()
            )
        );
    }

    private static void buscaResponsavelPorId(int idResponsavel, ResponsavelService responsavelService) {
        out.printf("Buscando o id do responsável número: %d%n", idResponsavel);
        Responsavel responsavel = responsavelService.buscarPorId(idResponsavel);
        out.printf(
            "IdResponsavel: %d, NomeResponsavel: %s, Parentesco: %s%n",
            responsavel.getIdResponsavel(),
            responsavel.getIdAluno(),
            responsavel.getNomeResponsavel(),
            responsavel.getParentesco()
        );
    }
}
