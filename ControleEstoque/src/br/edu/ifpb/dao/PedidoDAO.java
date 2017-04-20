package br.edu.ifpb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.edu.ifpb.entidades.Pedido;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.ConnectionFactory;
import br.edu.ifpb.utils.Mensagens;
import br.edu.ifpb.utils.Util;

public class PedidoDAO implements DAOInterface<Pedido> {

	private final String INSERT    = "INSERT INTO PEDIDO (DATAPEDIDOR) VALUES (?)";
	private final String LIST      = "SELECT * FROM PEDIDO ORDER BY CODPEDIDO";
	private final String LISTBYID  = "SELECT * FROM PEDIDO WHERE codPedido=?";
	
	private static PedidoDAO instance;

	public static PedidoDAO getInstance() {
		if (instance == null) {
			instance = new PedidoDAO();
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;

	public PedidoDAO() {
	}

	@Override
	public int creat(Pedido pedido) throws ControleEstoqueSqlException {

		int chave = 0;

		if (pedido != null) {

			connection = null;

			try {

				connection = (Connection) new ConnectionFactory().getConnection();

				PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(INSERT);
			
				pstm.setDate(1, pedido.getDataPedido());

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
	public ArrayList<Pedido> listarTodos() throws ControleEstoqueSqlException {

		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

		connection = null;

		try {
			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(LIST);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Pedido pedido = new Pedido();

				pedido.setCodPedido(rs.getInt("codPedido"));
				pedido.setDataPedido(rs.getDate("dataPedido"));

				pedidos.add(pedido);
			}

			stmt.close();
			rs.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return pedidos;
	}
	
	public Pedido consultaByID(int id)  throws ControleEstoqueSqlException{
		Pedido pedido = new Pedido();
		try {
			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(LISTBYID);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				pedido.setCodPedido(rs.getInt("codPedido"));
				pedido.setDataPedido(rs.getDate("dataPedido"));
			}

			stmt.close();
			rs.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return pedido;
	}

	@Override
	public int update(Pedido pedido) throws ControleEstoqueSqlException {
		return 0;
	}

	@Override
	public int delete(Pedido pedido) throws ControleEstoqueSqlException {
		return 0;
	}

}
