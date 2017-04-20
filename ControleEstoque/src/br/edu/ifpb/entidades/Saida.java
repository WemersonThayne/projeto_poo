package br.edu.ifpb.entidades;

import java.util.Calendar;

public class Saida {

	private int codSaida;
	private Calendar dataSaida;
	private Funcionario funcionario;
	
	public Saida(){
	}	

	public Saida(int code,  Calendar data, Funcionario funcionario){
		setCodSaida(code);
		setDataSaida(data);
		setFuncionario(funcionario);
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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "Saida [codSaida=" + codSaida + ", dataSaida=" + dataSaida + ", funcionario="
				+ funcionario + "]";
	}
	
}
