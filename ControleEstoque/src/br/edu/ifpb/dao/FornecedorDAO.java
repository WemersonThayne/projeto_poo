package br.edu.ifpb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.edu.ifpb.entidades.Fornecedor;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.ConnectionFactory;

public class FornecedorDAO {

	private final String INSERT = "INSERT INTO FORNECEDOR (NOME, CPF, ENDERECO, DATANASCIMENTO,TELEFONE, EMAIL, NOMELOJA, LOGIN, SENHA) VALUES (?,?,?,?,?,?,?,?,?)";
	private final String LISTBYLOGIN = "SELECT ID, NOME, CPF, ENDERECO, DATANASCIMENTO,TELEFONE, EMAIL, LOGIN, SENHA, NOMELOJA FROM FORNECEDOR WHERE LOGIN=? AND SENHA=?";
	private final String UPDATE = "UPDATE FUNCIONARIO SET NOME=?, CPF=?, ENDERECO=?, DATANASCIMENTO=?, TELEFONE=?, EMAIL=?, LOGIN=?, SENHA=?, IDDEPARTAMENTO=? WHERE ID=?";

	private static FornecedorDAO instance;

	public static FornecedorDAO getInstance() {
		if (instance == null) {
			instance = new FornecedorDAO();
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;

	public FornecedorDAO() {
	}

	public int creat(Fornecedor pessoa) throws ControleEstoqueSqlException {

		int chave = 0;

		if (pessoa != null) {

			connection = null;

			try {

				connection = (Connection) new ConnectionFactory().getConnection();

				// prepared statement para inserção
				PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(INSERT);

				pstm.setString(1, pessoa.getNome());
				pstm.setString(2, pessoa.getCpf());
				pstm.setString(3, pessoa.getEndereco());
				pstm.setString(4, pessoa.getDataNascimento());
				pstm.setString(5, pessoa.getTelefone());
				pstm.setString(6, pessoa.getEmail());
				pstm.setString(7, pessoa.getNomeLoja());
				pstm.setString(8, pessoa.getLogin());
				pstm.setString(9, pessoa.getSenha());

				// envia para o Banco e fecha o objeto
				pstm.execute();

				chave = Statement.RETURN_GENERATED_KEYS;
				pstm.close();
				connection.close();

				System.out.println("Objeto inserido com sucesso:  " + chave);
			} catch (SQLException sqle) {
				throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
			}

		}
		return chave;
	}

	public Fornecedor verificarLogin(Fornecedor fornecedor) throws ControleEstoqueSqlException {

		Fornecedor fornecedorConsulta = null;
		connection = null;

		if (fornecedor != null) {
			try {

				connection = (Connection) new ConnectionFactory().getConnection();

				// prepared statement para consulta do login no banco
				PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(LISTBYLOGIN);
				pstm.setString(1, fornecedor.getLogin());
				pstm.setString(2, fornecedor.getSenha());

				ResultSet rs = pstm.executeQuery();
				if (rs.next()) {

					fornecedorConsulta = new Fornecedor();
					fornecedorConsulta.setCodPessoa(rs.getInt("id"));
					fornecedorConsulta.setLogin(rs.getString("login"));
					fornecedorConsulta.setNome(rs.getString("nome"));
					fornecedorConsulta.setEmail(rs.getString("email"));
					fornecedorConsulta.setTelefone(rs.getString("telefone"));
					fornecedorConsulta.setCpf(rs.getString("cpf"));
					fornecedorConsulta.setEndereco(rs.getString("endereco"));
					fornecedorConsulta.setDataNascimento(rs.getString("dataNascimento"));
					fornecedorConsulta.setSenha(rs.getString("senha"));
					fornecedorConsulta.setNomeLoja(rs.getString("nomeLoja"));
				}

				pstm.close();
				rs.close();
				connection.close();
			} catch (SQLException sqle) {
				throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
			}

		}
		return fornecedorConsulta;

	}
	
	public int update(Fornecedor fornecedor) throws ControleEstoqueSqlException {

		connection = null;
		int chave = 0;
		try {

			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(UPDATE);
			
			pstm.setString(1, fornecedor.getNome());
			pstm.setString(2, fornecedor.getCpf());
			pstm.setString(3, fornecedor.getEndereco());
			pstm.setString(4, fornecedor.getDataNascimento());
			pstm.setString(5, fornecedor.getTelefone());
			pstm.setString(6, fornecedor.getEmail());
			pstm.setString(7, fornecedor.getNomeLoja());
			pstm.setString(8, fornecedor.getLogin());
			pstm.setString(9, fornecedor.getSenha());
			
			pstm.execute();
			chave = Statement.RETURN_GENERATED_KEYS;
			pstm.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}
		
		return chave;
	}
}
