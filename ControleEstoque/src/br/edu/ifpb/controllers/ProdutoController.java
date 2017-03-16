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
	
	public List<Produto> readByName(String nomeProduto) throws ControleEstoqueSqlException {		
		return ProdutoDAO.getInstance().readByName(nomeProduto);
	}
	public int delete(Produto prod) throws ControleEstoqueSqlException {		
		return ProdutoDAO.getInstance().delete(prod);
	}
	
	public List<Produto> listarTodos() throws ControleEstoqueSqlException {
		return ProdutoDAO.getInstance().listarTodos();
	}
}
