package br.edu.ifpb.entidades;

public class CategoriaProduto {

	private int codCategoria;
	private String descricao;
	
	public CategoriaProduto(){}
	
	public int getCodCategoria() {
		return codCategoria;
	}
	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
	
}
