package br.edu.ifpb.entidades;

public class Produto {

	private int codProduto;
	private String nome;
	private double valorUnitario;
	private int quantideAtual;
	private CategoriaProduto categoria;
	
	public Produto(){
	}
	
	public Produto(int cod, String nome, double valor, CategoriaProduto categoria,int quant){
		setCategoria(categoria);
		setCodProduto(cod);
		setNome(nome);
		setValorUnitario(valor);
		setQuantideAtual(quant);
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
	
	public int getQuantideAtual() {
		return quantideAtual;
	}

	public void setQuantideAtual(int quantideAtual) {
		this.quantideAtual = quantideAtual;
	}

	@Override
	public String toString() {
		return "Produto [codProduto=" + codProduto + ", nome=" + nome + ", valorUnitario=" + valorUnitario
				+ ", quantideAtual=" + quantideAtual + ", categoria=" + categoria + "]";
	}

}
