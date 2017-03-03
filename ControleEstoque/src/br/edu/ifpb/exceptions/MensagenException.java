package br.edu.ifpb.exceptions;

import br.edu.ifpb.utils.Mensagens;

public class MensagenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MensagenException(String mensagem){
		super(mensagem);
		new Mensagens(mensagem);
	}
}
