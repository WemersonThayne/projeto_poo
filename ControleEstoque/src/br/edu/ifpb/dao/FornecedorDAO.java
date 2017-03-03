package br.edu.ifpb.dao;

import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.edu.ifpb.entidades.Fornecedor;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.ConnectionFactory;

public class FornecedorDAO {

	private final String INSERT = "INSERT INTO FORNECEDOR (NOME, CPF, ENDERECO, DATANASCIMENTO,TELEFONE, EMAIL, NOMELOJA, LOGIN, SENHA) VALUES (?,?,?,?,?,?,?,?,?)";
	/*private final String UPDATE = "UPDATE CONTATO SET NOME=?, TELEFONE=?, EMAIL=? WHERE ID=?";
	private final String DELETE = "DELETE FROM CONTATO WHERE ID =?";
	private final String LIST = "SELECT * FROM CONTATO";
	private final String LISTBYID = "SELECT * FROM CONTATO WHERE ID=?";*/

	
	private static FornecedorDAO instance;

	public static FornecedorDAO getInstance() {
		if (instance == null) {
			instance = new FornecedorDAO();
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;
	
	public FornecedorDAO() {}

	public int creat(Fornecedor pessoa) throws ControleEstoqueSqlException {

		
		
		int chave = 0;
		
		if (pessoa != null) {
			
			
			 
			connection = null;
		
			try {
				
				connection = (Connection) new  ConnectionFactory().getConnection();
				
				// prepared statement para inserção
				PreparedStatement pstm = (PreparedStatement) connection
						.prepareStatement(INSERT);
			
				pstm.setString(1, pessoa.getNome());
				pstm.setString(2, pessoa.getCpf());
				pstm.setString(3, pessoa.getEndereco());
				pstm.setString(4, pessoa.getDataNascimento());
				pstm.setString(5, pessoa.getTelefone());
				pstm.setString(6, pessoa.getEmail());
				pstm.setString(7, pessoa.getNomeLoja());
				pstm.setString(8, pessoa.getLogin());
				pstm.setString(9, pessoa.getSenha());
				
				//envia para o Banco e fecha o objeto
				pstm.execute();

				chave = Statement.RETURN_GENERATED_KEYS;
				pstm.close();
				connection.close();
				
				System.out.println("Objeto inserido com sucesso:  "+chave);
			} catch (SQLException sqle) {
				throw new ControleEstoqueSqlException(sqle.getErrorCode(),
						sqle.getLocalizedMessage());
			}

		}
		return chave;
	}
}
