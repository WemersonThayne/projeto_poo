package br.edu.ifpb.controllers;

import br.edu.ifpb.dao.ProdutoDAO;
import br.edu.ifpb.entidades.Estoque;
import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class ProdutoController {

	public int creat(Produto prod, int quantidade) throws ControleEstoqueSqlException {		
		return ProdutoDAO.getInstance().creat( prod, quantidade);
	}
	
	public int update(Produto prod, int quantidade) throws ControleEstoqueSqlException {		
		return ProdutoDAO.getInstance().update(prod,quantidade);
	}
	
	public Estoque readByName(String nomeProduto) throws ControleEstoqueSqlException {		
		return ProdutoDAO.getInstance().readByName(nomeProduto);
	}
	public int delete(Produto prod) throws ControleEstoqueSqlException {		
		return ProdutoDAO.getInstance().delete(prod);
	}
	
	public Estoque listarTodos() throws ControleEstoqueSqlException {
		return ProdutoDAO.getInstance().listarTodosEstoque();
	}
}
