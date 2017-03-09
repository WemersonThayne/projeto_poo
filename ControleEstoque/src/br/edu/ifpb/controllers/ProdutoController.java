package br.edu.ifpb.controllers;

import java.util.List;

import br.edu.ifpb.dao.ProdutoDAO;
import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class ProdutoController {

	public int creat(Produto prod) throws ControleEstoqueSqlException {		
		return ProdutoDAO.getInstance().creat( prod);
	}
	
	public int update(Produto prod) throws ControleEstoqueSqlException {		
		return ProdutoDAO.getInstance().update(prod);
	}
	
	
	public List<Produto> listarTodos() throws ControleEstoqueSqlException {
		return ProdutoDAO.getInstance().listarTodos();
	}
}
