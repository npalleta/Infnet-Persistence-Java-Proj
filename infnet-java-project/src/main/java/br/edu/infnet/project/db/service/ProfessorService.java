package br.edu.infnet.project.db.service;

import br.edu.infnet.project.db.model.domain.Professor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProfessorService extends TemplateService {

    private final SessionFactory sessionFactory;

    public ProfessorService() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        this.sessionFactory = config.buildSessionFactory();
    }

    @Override
    public List<Professor> buscarTodos() {
        Session sessao = sessionFactory.openSession();
        List<Professor> Professors = sessao.createQuery("FROM Professor", Professor.class).list();
        sessao.close();
        return Professors;
    }

    @Override
    public Professor buscarPorId(int idProfessor) {
        Session session = sessionFactory.openSession();
        Professor Professor = session.get(Professor.class, idProfessor);
        session.close();
        return Professor;
    }

    @Override
    public Professor salvar(Object entity) {
        Session sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        sessao.persist(entity);
        sessao.getTransaction().commit();
        sessao.close();
        return (Professor) entity;
    }

    @Override
    public Professor atualizar(Object entity) {
        Session sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        sessao.merge(entity);
        sessao.getTransaction().commit();
        sessao.close();
        return (Professor) entity;
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
