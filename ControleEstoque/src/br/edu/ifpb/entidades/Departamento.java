package br.edu.ifpb.entidades;

public class Departamento {

	private int codDepartamento;
	private String descricao;
	
	public Departamento(){}

	public int getCodDepartamento() {
		return codDepartamento;
	}

	public void setCodDepartamento(int codDepartamento) {
		this.codDepartamento = codDepartamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao ;
	}
	
}
