package br.edu.ifpb.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.edu.ifpb.entidades.ItemEstoque;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.ConnectionFactory;
import br.edu.ifpb.utils.Mensagens;
import br.edu.ifpb.utils.Util;

public class ItemEstoqueDAO implements DAOInterface<ItemEstoque> {

	private final String INSERT = "INSERT INTO ESTOQUE (QUANTIDADEPRODUTO,IDPRODUTOESTOQUE) VALUES (?,?)";
	private final String UPDATE = "UPDATE ESTOQUE SET QUANTIDADEPRODUTO=? WHERE IDPRODUTOESTOQUE=?";
	private final String DELETE = "DELETE FROM ESTOQUE WHERE IDPRODUTOESTOQUE =?";

	private static ItemEstoqueDAO instance;

	public static ItemEstoqueDAO getInstance() {
		if (instance == null) {
			instance = new ItemEstoqueDAO();
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;

	public ItemEstoqueDAO() {
	}

	@Override
	public int creat(ItemEstoque i) throws ControleEstoqueSqlException {
		int chave = 0;

		if (i != null) {

			connection = null;

			try {

				connection = (Connection) new ConnectionFactory().getConnection();

				// prepared statement para inserção
				PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(INSERT);

				pstm.setInt(1, i.getQuantideProduto());
				pstm.setInt(2, i.getIdProduto());

				// envia para o Banco e fecha o objeto
				pstm.execute();

				chave = Statement.RETURN_GENERATED_KEYS;
				pstm.close();
				connection.close();

				System.out.println("Objeto inserido com sucesso:  " + chave);
				new Mensagens(Util.CADASTRO_PRD_SUCESS);
			} catch (SQLException sqle) {
				throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
			}

		}
		return chave;
	}

	@Override
	public int update(ItemEstoque t) throws ControleEstoqueSqlException {
		connection = null;
		int chave = 0;
		try {

			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(UPDATE);

			pstm.setInt(1, t.getQuantideProduto());
			pstm.setInt(2, t.getIdProduto());

			pstm.execute();
			chave = Statement.RETURN_GENERATED_KEYS;
			pstm.close();
			connection.close();
		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return chave;
	}

	@Override
	public List<ItemEstoque> listarTodos() throws ControleEstoqueSqlException {
		return null;
	}

	@Override
	public int delete(ItemEstoque t) throws ControleEstoqueSqlException {
		connection = null;
		int chave = 0;
		try {

			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(DELETE);

			pstm.setInt(1, t.getIdProduto());
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
