package br.edu.ifpb.controllers;

import br.edu.ifpb.dao.FornecedorDAO;
import br.edu.ifpb.entidades.Fornecedor;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class FornecedorController {

	public int creat(Fornecedor fornecedor) throws ControleEstoqueSqlException {		
		return FornecedorDAO.getInstance().creat( fornecedor);
	}
	
	public Fornecedor verificaLogin(Fornecedor funcionario) throws ControleEstoqueSqlException {
		return FornecedorDAO.getInstance().verificarLogin(funcionario);
	}
	
	public int update(Fornecedor funcionario) throws ControleEstoqueSqlException {		
		return FornecedorDAO.getInstance().update(funcionario);
	}
}
