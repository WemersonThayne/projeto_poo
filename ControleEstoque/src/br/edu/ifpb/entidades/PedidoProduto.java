package br.edu.ifpb.entidades;

import java.util.List;

public class PedidoProduto {

	private Pedido pedido;
	private List<Produto> produtos;
	private int quantProdutos;
	private double valorTotal;

	public PedidoProduto(){}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
		return "PedidoProduto [pedido=" + pedido + ", produtos=" + produtos + ", quantProdutos=" + quantProdutos
				+ ", valorTotal=" + valorTotal + "]";
	}
	
}
