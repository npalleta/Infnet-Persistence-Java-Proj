package br.edu.infnet.project.db.service;

import br.edu.infnet.project.db.model.domain.Responsavel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ResponsavelService extends TemplateService {

    private final SessionFactory sessionFactory;

    public ResponsavelService() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        this.sessionFactory = config.buildSessionFactory();
    }

    @Override
    public List<Responsavel> buscarTodos() {
        Session sessao = sessionFactory.openSession();
        List<Responsavel> responsaveis = sessao.createQuery("SELECT r FROM Responsavel r INNER JOIN r.aluno a ON r.aluno.idAluno = a.idAluno", Responsavel.class).list();
        sessao.close();
        return responsaveis;
    }

    @Override
    public Responsavel buscarPorId(int idResponsavel) {
        Session session = sessionFactory.openSession();
        Responsavel responsavel = session.get(Responsavel.class, idResponsavel);
        session.close();
        return responsavel;
    }

    @Override
    public Responsavel salvar(Object entity) {
        Session sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        sessao.persist(entity);
        sessao.getTransaction().commit();
        sessao.close();
        return (Responsavel) entity;
    }

    @Override
    public Responsavel atualizar(Object entity) {
        Session sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        sessao.merge(entity);
        sessao.getTransaction().commit();
        sessao.close();
        return (Responsavel) entity;
    }

    @Override
    public void excluir(Object entity) {
        Session sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        sessao.remove(entity);
        sessao.getTransaction().commit();
        sessao.close();
    }
}
