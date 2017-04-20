package br.edu.ifpb.entidades;

public class ItemEstoque {
	
	private int idEstoque;
	private int quantideProduto;
	private int idProduto;
	public int getQuantideProduto() {
		return quantideProduto;
	}
	public void setQuantideProduto(int quantideProduto) {
		this.quantideProduto = quantideProduto;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public int getIdEstoque() {
		return idEstoque;
	}
	public void setIdEstoque(int idEstoque) {
		this.idEstoque = idEstoque;
	}
	@Override
	public String toString() {
		return "ItemEstoque [idEstoque=" + idEstoque + ", quantideProduto=" + quantideProduto + ", idProduto="
				+ idProduto + "]";
	}
	
}
