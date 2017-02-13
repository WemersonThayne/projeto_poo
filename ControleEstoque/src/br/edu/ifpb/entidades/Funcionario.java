package br.edu.ifpb.entidades;

public class Funcionario extends Pessoa{
	
	private long matricula;
	private Departamento departamento;
	
	public Funcionario(){}
	public Funcionario(long matri, Departamento dep){
		super();
		setMatricula(matri);
		setDepartamento(dep);
	}
	public long getMatricula() {
		return matricula;
	}
	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	@Override
	public String toString() {
		return super.toString()+ " Funcionario [matricula=" + matricula + ", departamento=" + departamento + "]";
	}

	
}
