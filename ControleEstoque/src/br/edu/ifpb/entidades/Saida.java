package br.edu.ifpb.entidades;

import java.util.Calendar;
import java.util.List;

public class Saida {

	private int codSaida;
	private Calendar dataSaida;
	private List<Produto> produtos;
	private Funcionario funcionario;
	
	public Saida(){
	}	

	public Saida(int code,  Calendar data, List<Produto> produtos, Funcionario funcionario){
		setCodSaida(code);
		setDataSaida(data);
		setFuncionario(funcionario);
		setProdutos(produtos);
	}

	public int getCodSaida() {
		return codSaida;
	}

	public void setCodSaida(int codSaida) {
		this.codSaida = codSaida;
	}


	public Calendar getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Calendar dataSaida) {
		this.dataSaida = dataSaida;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "Saida [codSaida=" + codSaida + ", dataSaida=" + dataSaida + ", produtos=" + produtos + ", funcionario="
				+ funcionario + "]";
	}
	
}
