package br.edu.ifpb.controllers;

import br.edu.ifpb.dao.ProdutoDAO;
import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class ProdutoController {

	public int creat(Produto prod) throws ControleEstoqueSqlException {		
		return ProdutoDAO.getInstance().creat( prod);
	}
}
