package br.edu.ifpb.utils;

import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.ifpb.exceptions.MensagenException;

public  class  Util {

	public static String 	VALOR_INSERIDOS_INVALIDOS = "Valores Inválidos!";
	public static String 	LOGIN_MENSAGEM = "Bem Vindo(a): ";
	public static String 	LOGIN_MENSAGEM_INVALIDO = "Usuário não Cadastrado: ";
	public static String 	CADASTRO_PRD_SUCESS = "Cadastro do Produto efetuado com Sucesso.";
	public static String 	UPDATE_PRD_SUCESS = "Dados do Produto atualizado com Sucesso.";
	public static String 	DELETE_PRD_SUCESS = "Produto Excluido com Sucesso.";
	public static String 	CADASTRO_PED_SUCESS = "Cadastro do Pedido efetuado com Sucesso.";
	
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
	
	
	/*Coloca mascara nos campos*/
	public static void montarMascara(JFormattedTextField campo, String mascara) throws MensagenException{
		MaskFormatter mask;
		try {
			mask = new MaskFormatter( mascara);
			mask.install( campo );
			
		} catch (ParseException e1) {
			e1.printStackTrace();
			throw new MensagenException(e1.getMessage());
		}  
		
	}
}
