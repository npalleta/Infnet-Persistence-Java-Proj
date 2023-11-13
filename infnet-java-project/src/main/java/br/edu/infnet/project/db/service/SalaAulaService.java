package br.edu.infnet.project.db.service;

import br.edu.infnet.project.db.model.domain.SalaAula;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class SalaAulaService extends TemplateService {

    private final SessionFactory sessionFactory;

    public SalaAulaService() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        this.sessionFactory = config.buildSessionFactory();
    }

    @Override
    public List<SalaAula> buscarTodos() {
        Session sessao = sessionFactory.openSession();
        List<SalaAula> salas = sessao.createQuery("FROM SalaAula", SalaAula.class).list();
        sessao.close();
        return salas;
    }

    @Override
    public SalaAula buscarPorId(int idSalaAula) {
        Session session = sessionFactory.openSession();
        SalaAula salaAula = session.get(SalaAula.class, idSalaAula);
        session.close();
        return salaAula;
    }

    @Override
    public SalaAula salvar(Object entity) {
        Session sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        sessao.persist(entity);
        sessao.getTransaction().commit();
        sessao.close();
        return (SalaAula) entity;
    }

    @Override
    public SalaAula atualizar(Object entity) {
        Session sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        sessao.merge(entity);
        sessao.getTransaction().commit();
        sessao.close();
        return (SalaAula) entity;
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
