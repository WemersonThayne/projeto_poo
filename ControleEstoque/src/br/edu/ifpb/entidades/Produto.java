package br.edu.ifpb.entidades;

public class Produto{

	private int codProduto;
	private String nome;
	private double valorUnitario;
	private CategoriaProduto categoria;
	private Fornecedor fornecedor;
	
	public Produto(){
	}
	
	public Produto(int cod, String nome, double valor, CategoriaProduto categoria, Fornecedor fornecedor){
		setCategoria(categoria);
		setCodProduto(cod);
		setNome(nome);
		setValorUnitario(valor);
	}
	
	public int getCodProduto() {
		return codProduto;
	}
	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public CategoriaProduto getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}
	


	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public String toString() {
		return "Produto [codProduto=" + codProduto + ", nome=" + nome + ", valorUnitario=" + valorUnitario
				+ ", categoria=" + categoria + ", fornecedor=" + fornecedor + "]";
	}

}
