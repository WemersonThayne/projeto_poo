package br.edu.ifpb.controllers;

import br.edu.ifpb.dao.ItemEstoqueDAO;
import br.edu.ifpb.entidades.ItemEstoque;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class ItemEstoqueController {

	public int update(ItemEstoque item) throws ControleEstoqueSqlException {		
		return ItemEstoqueDAO.getInstance().update(item);
	}
}
