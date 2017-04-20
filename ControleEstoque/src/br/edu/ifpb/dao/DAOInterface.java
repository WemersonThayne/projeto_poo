package br.edu.ifpb.dao;

import java.util.List;

import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public interface DAOInterface <T>{

	public int creat(T t) throws ControleEstoqueSqlException;
	public int update(T t) throws ControleEstoqueSqlException;
	public List<T> listarTodos() throws ControleEstoqueSqlException;
	public int delete(T t) throws ControleEstoqueSqlException;
}
