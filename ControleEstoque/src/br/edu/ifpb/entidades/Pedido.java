package br.edu.ifpb.entidades;

import java.sql.Date;

public class Pedido {

	private int codPedido;
	private Date dataPedido;
	private String hashPedido;
	
	public Pedido(){}
	public int getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public String getHashPedido() {
		return hashPedido;
	}
	public void setHashPedido(String hashPedido) {
		this.hashPedido = hashPedido;
	}
	
	@Override
	public String toString() {
		return "Pedido [codPedido=" + codPedido + ", dataPedido=" + dataPedido + ", hashPedido=" + hashPedido + "]";
	}
	
}
