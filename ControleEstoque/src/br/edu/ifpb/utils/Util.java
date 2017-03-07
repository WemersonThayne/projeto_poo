package br.edu.ifpb.utils;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.edu.ifpb.entidades.Departamento;

public  class  Util {

	public static String 	VALOR_INSERIDOS_INVALIDOS = "Valores Inválidos!";
	public static String 	LOGIN_MENSAGEM = "Bem Vindo(a): ";
	public static String 	LOGIN_MENSAGEM_INVALIDO = "Usuário não Cadastrado: ";
	
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

	
	public static boolean validarCamposComboTipoUsuario(JComboBox<String> comboBoxTipoUsuario){
		if(comboBoxTipoUsuario.getSelectedIndex() == 1){
			return false;
		}
		return true;
	}
	
	public static boolean validarCamposComboDepartamento(JComboBox<Departamento> comboBoxDepartamentos){
		if(comboBoxDepartamentos.getSelectedIndex() == 0){
			return false;
		}
		return true;
	}
}
