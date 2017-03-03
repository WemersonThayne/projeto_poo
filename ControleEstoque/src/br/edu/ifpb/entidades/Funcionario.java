package br.edu.ifpb.entidades;

public class Funcionario extends Pessoa{
	
	private Departamento departamento;
	
	public Funcionario(){}
	public Funcionario( Departamento dep){
		super();
		setDepartamento(dep);
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	@Override
	public String toString() {
		return super.toString()+ " Funcionario [ departamento=" + departamento + "]";
	}

	
}
