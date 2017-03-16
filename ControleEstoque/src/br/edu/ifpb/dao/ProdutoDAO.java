package br.edu.ifpb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.edu.ifpb.entidades.CategoriaProduto;
import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.ConnectionFactory;
import br.edu.ifpb.utils.Mensagens;
import br.edu.ifpb.utils.Util;

public class ProdutoDAO {

	private final String INSERT = "INSERT INTO PRODUTO (NOME,VALORUNITARIO , CODCATEGORIA, QUANTIDADE) VALUES (?,?,?,?)";
	private final String LIST = "SELECT * FROM PRODUTO ORDER BY codProduto";
	private final String READBYNAME = "SELECT * FROM PRODUTO WHERE NOME LIKE ?";
	private final String UPDATE = "UPDATE PRODUTO SET NOME=?, VALORUNITARIO=?, CODCATEGORIA=?, QUANTIDADE=? WHERE CODPRODUTO=?";
	private final String DELETE = "DELETE FROM PRODUTO WHERE CODPRODUTO =?";

	private static ProdutoDAO instance;

	public static ProdutoDAO getInstance() {
		if (instance == null) {
			instance = new ProdutoDAO();
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;

	public ProdutoDAO() {
	}

	public int creat(Produto produto) throws ControleEstoqueSqlException {

		int chave = 0;

		if (produto != null) {

			connection = null;

			try {

				connection = (Connection) new ConnectionFactory().getConnection();

				// prepared statement para inserção
				PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(INSERT);

				pstm.setString(1, produto.getNome());
				pstm.setDouble(2, produto.getValorUnitario());
				pstm.setInt(3, produto.getCategoria().getCodCategoria());
				pstm.setInt(4, produto.getQuantideAtual());

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

	public ArrayList<Produto> listarTodos() throws ControleEstoqueSqlException {

		ArrayList<Produto> produtos = new ArrayList<Produto>();

		connection = null;

		try {
			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(LIST);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				CategoriaProduto c = new CategoriaProduto();
				Produto produto = new Produto();

				produto.setCodProduto(rs.getInt("codProduto"));
				produto.setNome(rs.getString("nome"));
				produto.setQuantideAtual(rs.getInt("quantidade"));
				produto.setValorUnitario(rs.getDouble("valorUnitario"));
				c.setCodCategoria(rs.getInt("codCategoria"));
				produto.setCategoria(c);

				produtos.add(produto);
			}

			stmt.close();
			rs.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return produtos;
	}

	public List<Produto> readByName(String nomeProduto) throws ControleEstoqueSqlException {

		ArrayList<Produto> produtos = new ArrayList<Produto>();

		connection = null;

		try {
			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(READBYNAME);
			pstm.setString(1, "%"+nomeProduto+"%");

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				CategoriaProduto c = new CategoriaProduto();
				Produto produto = new Produto();

				produto.setCodProduto(rs.getInt("codProduto"));
				produto.setNome(rs.getString("nome"));
				produto.setQuantideAtual(rs.getInt("quantidade"));
				produto.setValorUnitario(rs.getDouble("valorUnitario"));
				c.setCodCategoria(rs.getInt("codCategoria"));
				produto.setCategoria(c);

				produtos.add(produto);
			}

			pstm.close();
			rs.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return produtos;
	}

	public int update(Produto produto) throws ControleEstoqueSqlException {

		connection = null;
		int chave = 0;
		try {

			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(UPDATE);
			
			pstm.setString(1, produto.getNome());
			pstm.setDouble(2, produto.getValorUnitario());
			pstm.setInt(3, produto.getCategoria().getCodCategoria());
			pstm.setInt(4, produto.getQuantideAtual());
			pstm.setInt(5, produto.getCodProduto());
			pstm.execute();
			chave = Statement.RETURN_GENERATED_KEYS;
			pstm.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}
		
		return chave;
	}
	
	public int delete(Produto produto) throws ControleEstoqueSqlException {

		connection = null;
		int chave = 0;
		try {

			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(DELETE);
			
			pstm.setInt(1, produto.getCodProduto());
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
