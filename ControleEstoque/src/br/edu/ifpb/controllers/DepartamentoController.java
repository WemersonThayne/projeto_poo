package br.edu.ifpb.controllers;

import java.util.List;

import br.edu.ifpb.dao.DepartamentoDAO;
import br.edu.ifpb.entidades.Departamento;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class DepartamentoController {

	public List<Departamento> listarTodos() throws ControleEstoqueSqlException {
		return DepartamentoDAO.getInstance().listarTodos();
	}
}
