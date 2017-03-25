package br.edu.ifpb.controllers;

import br.edu.ifpb.dao.FuncionarioDAO;
import br.edu.ifpb.entidades.Funcionario;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class FuncionarioController {

	public int creat(Funcionario funcionario) throws ControleEstoqueSqlException {		
		return FuncionarioDAO.getInstance().creat( funcionario);
	}
	
	public Funcionario verificaLogin(Funcionario usuario) throws ControleEstoqueSqlException {
		return FuncionarioDAO.getInstance().verificarLogin(usuario);
	}

	public int update(Funcionario funcionario) throws ControleEstoqueSqlException {		
		return FuncionarioDAO.getInstance().update(funcionario);
	}

}
