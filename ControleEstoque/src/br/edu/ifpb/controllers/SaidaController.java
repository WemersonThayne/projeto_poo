package br.edu.ifpb.controllers;

import java.util.ArrayList;

import br.edu.ifpb.dao.SaidaDAO;
import br.edu.ifpb.entidades.Saida;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class SaidaController {
	public int creat(Saida saida) throws ControleEstoqueSqlException {		
		return SaidaDAO.getInstance().creat(saida);
	}
	
	public ArrayList<Saida> listarTodos() throws ControleEstoqueSqlException {		
		return SaidaDAO.getInstance().listarTodos();
	}
}
