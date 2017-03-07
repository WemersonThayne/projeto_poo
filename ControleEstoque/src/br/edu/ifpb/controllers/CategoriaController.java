package br.edu.ifpb.controllers;

import java.util.List;

import br.edu.ifpb.dao.CategoriaDAO;
import br.edu.ifpb.entidades.CategoriaProduto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class CategoriaController {

	public List<CategoriaProduto> listarTodos() throws ControleEstoqueSqlException {
		return CategoriaDAO.getInstance().selectAll();
	}
}
