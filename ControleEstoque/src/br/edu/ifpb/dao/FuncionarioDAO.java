package br.edu.ifpb.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.edu.ifpb.entidades.Funcionario;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.ConnectionFactory;
import br.edu.ifpb.utils.Mensagens;

public class FuncionarioDAO {

	private final String INSERT = "INSERT INTO FUNCIONARIO (NOME, CPF, ENDERECO, DATANASCIMENTO,TELEFONE, EMAIL, LOGIN, SENHA, IDDEPARTAMENTO) VALUES (?,?,?,?,?,?,?,?,?)";
	/*private final String UPDATE = "UPDATE CONTATO SET NOME=?, TELEFONE=?, EMAIL=? WHERE ID=?";
	private final String DELETE = "DELETE FROM CONTATO WHERE ID =?";
	private final String LIST = "SELECT * FROM CONTATO";
	private final String LISTBYID = "SELECT * FROM CONTATO WHERE ID=?";*/

	
	private static FuncionarioDAO instance;

	public static FuncionarioDAO getInstance() {
		if (instance == null) {
			instance = new FuncionarioDAO();
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;
	
	public FuncionarioDAO() {}

	public int creat(Funcionario pessoa) throws ControleEstoqueSqlException {

		
		
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
				pstm.setString(7, pessoa.getLogin());
				pstm.setString(8, pessoa.getSenha());
				pstm.setInt	  (9, pessoa.getDepartamento().getCodDepartamento());
				
				//envia para o Banco e fecha o objeto
				pstm.execute();

				chave = Statement.RETURN_GENERATED_KEYS;
				pstm.close();
				connection.close();
				new Mensagens("Cadastrado com sucesso");
				
				System.out.println("Objeto inserido com sucesso:  "+chave);
			} catch (SQLException sqle) {
				throw new ControleEstoqueSqlException(sqle.getErrorCode(),
						sqle.getLocalizedMessage());
			}

		}
		return chave;
	}

	/*public Usuario verificarLogin(Usuario usuario) {

		Usuario usuarioConsulta = null;

		try {

			String sql = "SELECT U.cd_usuario, U.nm_login, U.nm_usuario, U.nm_email,"
					+ " U.nr_telefone, U.nr_cpf, U.nm_endereco,"
					+ " U.dt_nascimento, U.tp_sexo, U.cd_tipousuario"
					+ " FROM tb_usuario as U"
					+ " WHERE U.nm_login = '"
					+ usuario.getLogin()
					+ "'"
					+ " AND U.nm_senha = '"
					+ usuario.getSenha() + "'";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				usuarioConsulta = new Usuario();
				usuarioConsulta.setId(rs.getInt("U.cd_usuario"));
				usuarioConsulta.setLogin(rs.getString("U.nm_login"));
				usuarioConsulta.setNome(rs.getString("U.nm_usuario"));
				usuarioConsulta.setEmail(rs.getString("U.nm_email"));
				usuarioConsulta.setTelefone(rs
						.getString("U.nr_telefone"));
				usuarioConsulta.setCpf(rs.getString("U.nr_cpf"));
				usuarioConsulta.setEndereco(rs.getString("nm_endereco"));
				usuarioConsulta.setNascimento(rs.getDate("U.dt_nascimento"));
				usuarioConsulta.setSexo(rs.getString("U.tp_sexo"));
				TipoUsuario tipoUsuario = new TipoUsuario();
				tipoUsuario.setId(rs.getInt("U.cd_tipousuario"));
				usuarioConsulta.setTipoUsuario(tipoUsuario);
			}

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
		return usuarioConsulta;

	}

	public ArrayList<Usuario> readById(Usuario usuario) {

		ArrayList<Usuario> users = new ArrayList<Usuario>();

		try {

			String sql = "SELECT * FROM tb_usuario as u WHERE u.cd_usuario = ?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, usuario.getId());
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				usuario.setId(rs.getInt("u.cd_usuario"));
				usuario.setLogin(rs.getString("u.nm_login"));
				usuario.setSenha(rs.getString("u.nm_senha"));
				usuario.setNome(rs.getString("u.nm_usuario"));
				usuario.setEmail(rs.getString("u.nm_email"));
				usuario.setTelefone(rs.getString("u.nr_telefone"));
				usuario.setCpf(rs.getString("u.nr_cpf"));
				usuario.setEndereco(rs.getString("u.nm_endereco"));
				usuario.setNascimento(rs.getDate("u.dt_nascimento"));
				usuario.setSexo(rs.getString("u.tp_sexo"));
				TipoUsuario tipoUsuario = new TipoUsuario();
				tipoUsuario.setId(rs.getInt("u.cd_tipousuario"));
				usuario.setTipoUsuario(tipoUsuario);

				users.add(usuario);
			}
			
			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return users;

	}

	public void update(Usuario user) {

		try {
			// Define um update com os atributos e cada valor é representado
			// por
			// ?
			String sql = "UPDATE tb_usuario SET nm_senha=?"
					+ " WHERE nm_login=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, user.getSenha());
			stmt.setString(2, user.getLogin());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

	}

	public void delete(Usuario usuario) {

		try {

			String sql = "DELETE FROM tb_usuario WHERE cd_usuario = ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, usuario.getId());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	public ArrayList<Usuario> listarTodos() throws SQLException {
		ArrayList<Usuario> users = new ArrayList<Usuario>();

		String sql = String.format("%s",
				"SELECT * FROM tb_usuario as U, tb_tipousuario as T"
						+ " WHERE U.cd_tipousuario = T.cd_tipousuario");

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			Usuario user = new Usuario();
			user.setId(rs.getInt("cd_usuario"));
			user.setLogin(rs.getString("nm_login"));
			user.setSenha(rs.getString("nm_senha"));
			user.setNome(rs.getString("nm_usuario"));
			user.setEmail(rs.getString("nm_email"));
			user.setTelefone(rs.getString("nr_telefone"));
			user.setCpf(rs.getString("nr_cpf"));
			user.setEndereco(rs.getString("nm_endereco"));
			user.setNascimento(rs.getDate("dt_nascimento"));
			user.setSexo(rs.getString("tp_sexo"));			
			TipoUsuario tipoUsuario = new TipoUsuario();
			tipoUsuario.setId(rs.getInt("cd_tipousuario"));
			tipoUsuario.setDescricao(rs.getString("nm_tipousuario"));
			user.setTipoUsuario(tipoUsuario);

			users.add(user);
		}

		stmt.close();
		rs.close();

		return users;
	}

	public List<Usuario> consultarUsuariosByNome(Usuario usuario)
			throws SQLException {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		String sql = String.format("%s '%s' %s", 
				"SELECT U.cd_usuario, U.nm_usuario, T.cd_tipousuario,"
				+ " T.nm_tipousuario"
				+ " FROM tb_usuario as U, tb_tipousuario as T" 
				+ " WHERE U.nm_usuario LIKE ",
				usuario.getNome().trim() + "%",
				" AND U.cd_tipousuario = T.cd_tipousuario");

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			Usuario usuarioConsulta = new Usuario();
			usuarioConsulta.setId(rs.getInt("U.cd_usuario"));
			usuarioConsulta.setNome(rs.getString("U.nm_usuario"));
			
			TipoUsuario tipoUsuario = new TipoUsuario();
			tipoUsuario.setId(rs.getInt("T.cd_tipousuario"));
			tipoUsuario.setDescricao(rs.getString("T.cd_tipousuario"));
			
			usuarioConsulta.setTipoUsuario(tipoUsuario);
			
			usuarios.add(usuarioConsulta);
		}

		rs.close();
		stmt.close();

		return usuarios;
	}*/
}