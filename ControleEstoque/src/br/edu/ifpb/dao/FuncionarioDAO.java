package br.edu.ifpb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.edu.ifpb.entidades.Departamento;
import br.edu.ifpb.entidades.Funcionario;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.ConnectionFactory;

public class FuncionarioDAO implements DAOInterface<Funcionario> {

	private final String INSERT = "INSERT INTO FUNCIONARIO (NOME, CPF, ENDERECO, DATANASCIMENTO,TELEFONE, EMAIL, LOGIN, SENHA, IDDEPARTAMENTO) VALUES (?,?,?,?,?,?,?,?,?)";
	private final String LISTBYLOGIN = "SELECT ID, NOME, CPF, ENDERECO, DATANASCIMENTO,TELEFONE, EMAIL, LOGIN, SENHA, IDDEPARTAMENTO FROM FUNCIONARIO WHERE LOGIN=? AND SENHA=?";
	private final String UPDATE = "UPDATE FUNCIONARIO SET NOME=?, CPF=?, ENDERECO=?, DATANASCIMENTO=?, TELEFONE=?, EMAIL=?, LOGIN=?, SENHA=?, IDDEPARTAMENTO=? WHERE ID=?";
	private final String LISTBYID = "SELECT * FROM FORNECEDOR WHERE id=?";

	/*
	 * private final String UPDATE =
	 * "UPDATE CONTATO SET NOME=?, TELEFONE=?, EMAIL=? WHERE ID=?"; private
	 * final String DELETE = "DELETE FROM CONTATO WHERE ID =?"; private final
	 * String LIST = "SELECT * FROM CONTATO";
	 */

	private static FuncionarioDAO instance;

	public static FuncionarioDAO getInstance() {
		if (instance == null) {
			instance = new FuncionarioDAO();
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;

	public FuncionarioDAO(){}

	@Override
	public int creat(Funcionario pessoa) throws ControleEstoqueSqlException {

		int chave = 0;

		if (pessoa != null) {

			connection = null;

			try {

				connection = (Connection) new ConnectionFactory().getConnection();

				PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(INSERT);

				pstm.setString(1, pessoa.getNome());
				pstm.setString(2, pessoa.getCpf());
				pstm.setString(3, pessoa.getEndereco());
				pstm.setString(4, pessoa.getDataNascimento());
				pstm.setString(5, pessoa.getTelefone());
				pstm.setString(6, pessoa.getEmail());
				pstm.setString(7, pessoa.getLogin());
				pstm.setString(8, pessoa.getSenha());
				pstm.setInt(9, pessoa.getDepartamento().getCodDepartamento());

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

	public Funcionario verificarLogin(Funcionario funcionario) throws ControleEstoqueSqlException {

		Funcionario funcioarioConsulta = null;
		connection = null;
		if (funcionario != null) {

			try {

				connection = (Connection) new ConnectionFactory().getConnection();

				// prepared statement para consulta do login no banco
				PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(LISTBYLOGIN);
				pstm.setString(1, funcionario.getLogin());
				pstm.setString(2, funcionario.getSenha());

				ResultSet rs = pstm.executeQuery();
				if (rs.next()) {

					funcioarioConsulta = new Funcionario();
					funcioarioConsulta.setCodPessoa(rs.getInt("id"));
					funcioarioConsulta.setLogin(rs.getString("login"));
					funcioarioConsulta.setNome(rs.getString("nome"));
					funcioarioConsulta.setEmail(rs.getString("email"));
					funcioarioConsulta.setTelefone(rs.getString("telefone"));
					funcioarioConsulta.setCpf(rs.getString("cpf"));
					funcioarioConsulta.setEndereco(rs.getString("endereco"));
					funcioarioConsulta.setDataNascimento(rs.getString("dataNascimento"));
					funcioarioConsulta.setSenha(rs.getString("senha"));
					funcioarioConsulta.setDepartamento(new Departamento());
				}

				pstm.close();
				rs.close();
				connection.close();
			} catch (SQLException sqle) {
				throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
			}
		}
		return funcioarioConsulta;
	}

	@Override
	public int update(Funcionario funci) throws ControleEstoqueSqlException {

		connection = null;
		int chave = 0;
		try {

			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(UPDATE);
			
			pstm.setString(1, funci.getNome());
			pstm.setString(2, funci.getCpf());
			pstm.setString(3, funci.getEndereco());
			pstm.setString(4, funci.getDataNascimento());
			pstm.setString(5, funci.getTelefone());
			pstm.setString(6, funci.getEmail());
			pstm.setString(7, funci.getLogin());
			pstm.setString(8, funci.getSenha());
			pstm.setInt(9,    funci.getDepartamento().getCodDepartamento());
			pstm.execute();
			chave = Statement.RETURN_GENERATED_KEYS;
			pstm.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}
		
		return chave;
	}
	

	public Funcionario consultaByID(int id) throws ControleEstoqueSqlException {

		Funcionario funcionarioConsulta = null;
		connection = null;

			try {

				connection = (Connection) new ConnectionFactory().getConnection();

				// prepared statement para consulta do login no banco
				PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(LISTBYID);
				pstm.setInt(1,id);


				ResultSet rs = pstm.executeQuery();
				if (rs.next()) {

					funcionarioConsulta = new Funcionario();
					funcionarioConsulta.setCodPessoa(rs.getInt("id"));
					funcionarioConsulta.setLogin(rs.getString("login"));
					funcionarioConsulta.setNome(rs.getString("nome"));
					funcionarioConsulta.setEmail(rs.getString("email"));
					funcionarioConsulta.setTelefone(rs.getString("telefone"));
					funcionarioConsulta.setCpf(rs.getString("cpf"));
					funcionarioConsulta.setEndereco(rs.getString("endereco"));
					funcionarioConsulta.setDataNascimento(rs.getString("dataNascimento"));
					funcionarioConsulta.setSenha(rs.getString("senha"));
				}

				pstm.close();
				rs.close();
				connection.close();
			} catch (SQLException sqle) {
				throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
			}
		return funcionarioConsulta;
	}
	

	@Override
	public List<Funcionario> listarTodos() throws ControleEstoqueSqlException {
		return null;
	}

	@Override
	public int delete(Funcionario t) throws ControleEstoqueSqlException {
		return 0;
	}
}