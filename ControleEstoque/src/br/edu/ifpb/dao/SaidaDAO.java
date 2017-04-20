package br.edu.ifpb.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.edu.ifpb.entidades.Funcionario;
import br.edu.ifpb.entidades.Saida;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.ConnectionFactory;
import br.edu.ifpb.utils.Mensagens;
import br.edu.ifpb.utils.Util;

public class SaidaDAO implements DAOInterface<Saida>{
	private final String INSERT = "INSERT INTO SAIDA (DATASAIDA, CODFUNCIONARIO) VALUES (?,?)";
	private final String LIST = "SELECT * FROM SAIDA ORDER BY CODSAIDA";
	
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

				pstm.setDate(1, (Date) saida.getDataSaida().getTime());
				pstm.setInt(2, saida.getFuncionario().getCodPessoa());

				pstm.execute();

				chave = Statement.RETURN_GENERATED_KEYS;
				pstm.close();
				connection.close();

				
				new Mensagens(Util.CADASTRO_PED_SUCESS);
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

				Funcionario f = new Funcionario();
				Saida saida = new Saida();

				saida.setCodSaida(rs.getInt("codSaida"));
				Calendar c = Calendar.getInstance();
				c.setTime(rs.getDate("dataSaida"));
				saida.setDataSaida(c);
				f.setCodPessoa(rs.getInt("codFuncionario"));
				saida.setFuncionario(f);

				saidas.add(saida);
			}

			stmt.close();
			rs.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return saidas;
	}

	@Override
	public int update(Saida pedido) throws ControleEstoqueSqlException {
		return 0;
	}

	@Override
	public int delete(Saida pedido) throws ControleEstoqueSqlException {
		return 0;
	}

}
