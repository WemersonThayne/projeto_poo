package br.edu.ifpb.exceptions;

public class ControleEstoqueException  extends Exception{

	/**
	 * Exceptions do sistema.
	 */
	private static final long serialVersionUID = 1L;
	
	public ControleEstoqueException (String mensege){
		super(mensege);
	}

}
