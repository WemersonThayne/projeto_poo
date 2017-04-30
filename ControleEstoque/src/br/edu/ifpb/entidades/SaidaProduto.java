package br.edu.ifpb.entidades;

import java.util.List;

public class SaidaProduto {

	private Saida saida;
	private List<Produto> produtos;
	private int quantProdutos;
	private double valorTotal;
	public Saida getSaida() {
		return saida;
	}
	public void setSaida(Saida saida) {
		this.saida = saida;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public int getQuantProdutos() {
		return quantProdutos;
	}
	public void setQuantProdutos(int quantProdutos) {
		this.quantProdutos = quantProdutos;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	@Override
	public String toString() {
		return "SaidaProduto [saida=" + saida + ", produtos=" + produtos + ", quantProdutos=" + quantProdutos
				+ ", valorTotal=" + valorTotal + "]";
	}
	
}
