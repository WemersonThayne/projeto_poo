package br.edu.ifpb.entidades;

public class Produto{

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + codProduto;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + quantideAtual;
		long temp;
		temp = Double.doubleToLongBits(valorUnitario);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (codProduto != other.codProduto)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (quantideAtual != other.quantideAtual)
			return false;
		if (Double.doubleToLongBits(valorUnitario) != Double.doubleToLongBits(other.valorUnitario))
			return false;
		return true;
	}

	
	
}
