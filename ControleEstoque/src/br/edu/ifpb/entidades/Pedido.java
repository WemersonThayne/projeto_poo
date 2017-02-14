package br.edu.ifpb.entidades;

import java.util.Calendar;
import java.util.List;

public class Pedido {

	private int codPedido;
	private int quantProdutos;
	private double valorTotal;
	private Calendar dataPedido;
	private List<Produto> produtos;
	private Fornecedor fornecedor;
	
	public Pedido(){
	}	

	public Pedido(int cod, int quantidade, double valor, Calendar data, List<Produto> produtos, Fornecedor fornecedor){
		setCodPedido(cod);
		setDataPedido(data);
		setFornecedor(fornecedor);
		setProdutos(produtos);
		setQuantProdutos(quantidade);
		setValorTotal(valor);
	}
	
	public int getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
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
	public Calendar getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Calendar dataPedido) {
		this.dataPedido = dataPedido;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	@Override
	public String toString() {
		return "Pedido [codPedido=" + codPedido + ", quantProdutos=" + quantProdutos + ", valorTotal=" + valorTotal
				+ ", dataPedido=" + dataPedido + ", produtos=" + produtos + ", fornecedor=" + fornecedor + "]";
	}
	
}
