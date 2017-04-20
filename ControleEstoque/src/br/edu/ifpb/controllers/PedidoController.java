package br.edu.ifpb.controllers;

import java.util.ArrayList;

import br.edu.ifpb.dao.PedidoDAO;
import br.edu.ifpb.entidades.Pedido;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class PedidoController {

	public int creat(Pedido pedido) throws ControleEstoqueSqlException {		
		return PedidoDAO.getInstance().creat(pedido);
	}
	
	public ArrayList<Pedido> listarTodos() throws ControleEstoqueSqlException {		
		return PedidoDAO.getInstance().listarTodos();
	}
	
}
