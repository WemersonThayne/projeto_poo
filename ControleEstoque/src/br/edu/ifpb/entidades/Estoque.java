package br.edu.ifpb.entidades;

import java.util.Map;

public class Estoque {
	
	private Map<ItemEstoque,Produto> itens;
	
	public Map<ItemEstoque,Produto> getItens() {
		return itens;
	}

	public void setItens(Map<ItemEstoque, Produto> itens) {
		this.itens = itens;
	}


	@Override
	public String toString() {
		return "Estoque [itens=" + itens + "]";
	}
	
}
