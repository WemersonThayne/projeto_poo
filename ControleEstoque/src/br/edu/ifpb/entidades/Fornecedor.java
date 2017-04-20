package br.edu.ifpb.entidades;

public class Fornecedor extends Pessoa {

	private String nomeLoja;

	public Fornecedor(){
		
	}
	public Fornecedor(String nomeLoja){
		super();
		setNomeLoja(nomeLoja);
	}
	
	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	@Override
	public String toString() {
		return getNome().toUpperCase();
	}
	
	
}
