package br.edu.ifpb.controllers;

import java.util.ArrayList;

import br.edu.ifpb.dao.PedidoProdutoDAO;
import br.edu.ifpb.entidades.PedidoProduto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class PedidoProdutoController {

	public int creat(PedidoProduto pedido) throws ControleEstoqueSqlException {		
		return PedidoProdutoDAO.getInstance().creat(pedido);
	}
	
	public ArrayList<PedidoProduto> listarTodos() throws ControleEstoqueSqlException {		
		return PedidoProdutoDAO.getInstance().listarTodos();
	}
	
}
