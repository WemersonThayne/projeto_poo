package br.edu.ifpb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.edu.ifpb.entidades.CategoriaProduto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.ConnectionFactory;

public class CategoriaDAO {
	private final String SELECTALL = "SELECT * FROM CATEGORIA";

	private static CategoriaDAO instance;

	public static CategoriaDAO getInstance() {
		if (instance == null) {
			instance = new CategoriaDAO();
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;

	public CategoriaDAO() {
	}

	public List<CategoriaProduto> selectAll() throws ControleEstoqueSqlException {

		List<CategoriaProduto> categorias = null;
		connection = null;

		try {

			connection = (Connection) new ConnectionFactory().getConnection();

			// prepared statement para inserção
			PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(SELECTALL);

			// envia para o Banco e fecha o objeto
			pstm.execute();

			ResultSet rs = pstm.executeQuery();
			categorias = new ArrayList<CategoriaProduto>();
			while (rs.next()) {

				CategoriaProduto categoria = new CategoriaProduto();
				categoria.setCodCategoria(rs.getInt("codCategoria"));
				categoria.setDescricao(rs.getString("descricao"));
				categorias.add(categoria);
			}

			pstm.close();
			connection.close();
		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return categorias;
	}
}
