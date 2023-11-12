package br.edu.infnet.project.db.service;

import br.edu.infnet.project.db.model.domain.Aluno;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AlunoService extends TemplateService {

    private final SessionFactory sessionFactory;

    public AlunoService() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        this.sessionFactory = config.buildSessionFactory();
    }

    @Override
    public List<Aluno> buscarTodas() {
        Session sessao = sessionFactory.openSession();
        List<Aluno> alunos = sessao.createQuery("FROM Aluno", Aluno.class).list();
        sessao.close();
        return alunos;
    }

    @Override
    public Aluno buscarPorId(int idAluno) {
        Session session = sessionFactory.openSession();
        Aluno aluno = session.get(Aluno.class, idAluno);
        session.close();
        return aluno;
    }

    @Override
    public Aluno salvar(Object entity) {
        Session sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        sessao.persist(entity);
        sessao.getTransaction().commit();
        sessao.close();
        return (Aluno) entity;
    }

    @Override
    public Aluno atualizar(Object entity) {
        Session sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        sessao.merge(entity);
        sessao.getTransaction().commit();
        sessao.close();
        return (Aluno) entity;
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
