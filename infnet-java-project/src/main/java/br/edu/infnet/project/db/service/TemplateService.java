package br.edu.infnet.project.db.service;

import java.util.List;

public abstract class TemplateService<T> {

    public abstract List<?> buscarTodos();

    public abstract T buscarPorId(int id);

    public abstract T salvar(T entity);

    public abstract T atualizar(T entity);

    public abstract void excluir(T entity);
}
