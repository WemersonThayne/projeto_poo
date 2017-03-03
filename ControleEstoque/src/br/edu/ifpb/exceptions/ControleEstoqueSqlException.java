package br.edu.ifpb.exceptions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ControleEstoqueSqlException  extends SQLException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2323232323L;
	private static final Map<Integer, String> erros = new HashMap<Integer, String>();
	static {
		erros.put(100, "Erro: Usu�rio n�o existe no sistema.");
		erros.put(101, "Erro: Senha inv�lida!");
		erros.put(666, "Erro: Falha convers�o da data.");
		erros.put(1062, "Chave duplicada.");
		erros.put(1052, "Consulta com coluna amb�gua.");
		erros.put(1054, "Coluna desconhecida.");
		erros.put(1136,
				"Contagem de colunas n�o confere com a contagem de valores.");
		erros.put(1146, "Tabela n�o existe.");
		erros.put(1406, "Dado muito longo para a coluna.");
		erros.put(1451, "N�o � poss�vel excluir ou atualizar uma "
				+ "linha pai: uma restri��o de chave estrangeira falhou.");
		erros.put(1452, "A restri��o de chave estrangeira falhou.");
		erros.put(1364, "Campo n�o tem um valor padr�o.");
	}

	private int errorCode;

	public ControleEstoqueSqlException(int errorCode, String localizedMessage) {

		super(erros.get(errorCode));

		setErrorCode(errorCode);

		System.err.println(ControleEstoqueSqlException.class.getName() + ">> " + errorCode + ": " + localizedMessage);

		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
