package br.edu.ifpb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.edu.ifpb.entidades.Funcionario;
import br.edu.ifpb.entidades.Saida;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.ConnectionFactory;

public class SaidaDAO implements DAOInterface<Saida> {

	private final String INSERT = "INSERT INTO SAIDA (DATASAIDA,CODFUNCIONARIO,HASHSAIDA) VALUES (?,?,?)";
	private final String LIST = "SELECT * FROM SAIDA ORDER BY CODSAIDA";
	private final String LISTBYID = "SELECT * FROM SAIDA WHERE CODSAIDA=?";
	private final String LISTBYHASHSAIDA = "SELECT * FROM SAIDA WHERE HASHSAIDA=?";

	private static SaidaDAO instance;

	public static SaidaDAO getInstance() {
		if (instance == null) {
			instance = new SaidaDAO();
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;

	public SaidaDAO() {
	}

	@Override
	public int creat(Saida saida) throws ControleEstoqueSqlException {

		int chave = 0;

		if (saida != null) {

			connection = null;

			try {

				connection = (Connection) new ConnectionFactory().getConnection();

				PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(INSERT);

				pstm.setDate(1, saida.getDataSaida());
				pstm.setInt(2, saida.getFuncionario().getCodPessoa());
				pstm.setString(3, saida.getHashSaida());

				pstm.execute();

				chave = Statement.RETURN_GENERATED_KEYS;
				pstm.close();
				connection.close();

			} catch (SQLException sqle) {
				throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
			}

		}
		return chave;
	}

	@Override
	public ArrayList<Saida> listarTodos() throws ControleEstoqueSqlException {

		ArrayList<Saida> saidas = new ArrayList<Saida>();

		connection = null;

		try {
			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(LIST);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Saida s = new Saida();
				Funcionario f = new Funcionario();
				FuncionarioDAO fd = new FuncionarioDAO();

				s.setCodSaida(rs.getInt("codSaida"));
				s.setDataSaida(rs.getDate("dataSaida"));
				f = fd.consultaByID(rs.getInt("codFuncionario"));
				s.setFuncionario(f);
				s.setHashSaida(rs.getString("hashSaida"));

				saidas.add(s);
			}

			stmt.close();
			rs.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return saidas;
	}

	public Saida consultaByID(int id) throws ControleEstoqueSqlException {
		Saida s = new Saida();
		try {
			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(LISTBYID);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				Funcionario f = new Funcionario();
				FuncionarioDAO fd = new FuncionarioDAO();

				s.setCodSaida(rs.getInt("codSaida"));
				s.setDataSaida(rs.getDate("dataSaida"));
				f = fd.consultaByID(rs.getInt("codFuncionario"));
				s.setFuncionario(f);
				s.setHashSaida(rs.getString("hashSaida"));
			}

			stmt.close();
			rs.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return s;
	}

	public Saida consultaByHash(String hash) throws ControleEstoqueSqlException {
		Saida s = new Saida();
		try {
			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(LISTBYHASHSAIDA);
			stmt.setString(1, hash);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Funcionario f = new Funcionario();
				FuncionarioDAO fd = new FuncionarioDAO();

				s.setCodSaida(rs.getInt("codSaida"));
				s.setDataSaida(rs.getDate("dataSaida"));
				f = fd.consultaByID(rs.getInt("codFuncionario"));
				s.setFuncionario(f);
				s.setHashSaida(rs.getString("hashSaida"));
			}

			stmt.close();
			rs.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return s;
	}

	@Override
	public int update(Saida t) throws ControleEstoqueSqlException {
		return 0;
	}

	@Override
	public int delete(Saida t) throws ControleEstoqueSqlException {
		return 0;
	}

}
