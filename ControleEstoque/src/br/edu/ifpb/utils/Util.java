package br.edu.ifpb.utils;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public  class  Util {

	public static String 	VALOR_INSERIDOS_INVALIDOS = "Valores Inválidos!";
	public static String 	LOGIN_MENSAGEM = "Bem Vindo(a): ";
	
	public static boolean validarCampos(JTextField campo){
		if(campo.getText().length() == 0){
			return false;
		}
		return true;
	}
	
	public static boolean validarCamposSenha(JPasswordField campo){
		if(campo.getPassword().length == 0){
			return false;
		}
		return true;
	}
	
}
