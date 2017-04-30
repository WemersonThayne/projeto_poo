package br.edu.ifpb.controllers;

import java.util.List;

import br.edu.ifpb.dao.SaidaProdutoDAO;
import br.edu.ifpb.entidades.SaidaProduto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class SaidaProdutoController {
	public int creat(SaidaProduto saida) throws ControleEstoqueSqlException {
		return SaidaProdutoDAO.getInstance().creat(saida);
	}

	public List<SaidaProduto> listarTodos() throws ControleEstoqueSqlException {
		return SaidaProdutoDAO.getInstance().listarTodos();
	}
}
