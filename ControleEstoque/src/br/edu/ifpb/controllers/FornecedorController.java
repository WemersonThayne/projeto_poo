package br.edu.ifpb.controllers;

import br.edu.ifpb.dao.FornecedorDAO;
import br.edu.ifpb.entidades.Fornecedor;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class FornecedorController {

	public int creat(Fornecedor fornecedor) throws ControleEstoqueSqlException {		
		return FornecedorDAO.getInstance().creat( fornecedor);
	}
}
