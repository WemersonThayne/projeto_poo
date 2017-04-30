package br.edu.ifpb.entidades;

import java.sql.Date;

public class Saida {

	private int codSaida;
	private Date dataSaida;
	private Funcionario funcionario;
	private String hashSaida;
	
	public int getCodSaida() {
		return codSaida;
	}
	public void setCodSaida(int codSaida) {
		this.codSaida = codSaida;
	}
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public String getHashSaida() {
		return hashSaida;
	}
	public void setHashSaida(String hashSaida) {
		this.hashSaida = hashSaida;
	}
	@Override
	public String toString() {
		return "Saida [codSaida=" + codSaida + ", dataSaida=" + dataSaida + ", funcionario=" + funcionario
				+ ", hashSaida=" + hashSaida + "]";
	}
	
}
