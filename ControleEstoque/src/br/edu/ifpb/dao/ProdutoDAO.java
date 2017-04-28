package br.edu.ifpb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.edu.ifpb.entidades.CategoriaProduto;
import br.edu.ifpb.entidades.Estoque;
import br.edu.ifpb.entidades.Fornecedor;
import br.edu.ifpb.entidades.ItemEstoque;
import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.ConnectionFactory;

public class ProdutoDAO implements DAOInterface<Produto> {

	private final String INSERT = "INSERT INTO PRODUTO (NOME,VALORUNITARIO , CODCATEGORIA,CODFORNECEDOR) VALUES (?,?,?,?)";
	private final String LIST = "SELECT P.CODPRODUTO, P.NOME, P.VALORUNITARIO, P.CODCATEGORIA, P.CODFORNECEDOR,"
			+ "E.QUANTIDADEPRODUTO, E.IDESTOQUE  FROM PRODUTO P INNER JOIN ESTOQUE E ON P.CODPRODUTO = E.IDPRODUTOESTOQUE ORDER BY P.NOME ASC";
	private final String LISTBYID = "SELECT * FROM PRODUTO WHERE codProduto=?";
	private final String LISTBYNAME = "SELECT * FROM PRODUTO WHERE nome=?";
	private final String READBYNAME = "SELECT P.CODPRODUTO, P.NOME, P.VALORUNITARIO, P.CODCATEGORIA, P.CODFORNECEDOR,"
			+ "E.QUANTIDADEPRODUTO, E.IDESTOQUE  FROM PRODUTO P INNER JOIN ESTOQUE E ON P.CODPRODUTO = E.IDPRODUTOESTOQUE WHERE NOME LIKE ? ORDER BY P.NOME";
	private final String UPDATE = "UPDATE PRODUTO SET NOME=?, VALORUNITARIO=?, CODCATEGORIA=?, CODFORNECEDOR=? WHERE CODPRODUTO=?";
	private final String DELETE = "DELETE FROM PRODUTO WHERE CODPRODUTO =?";

	private FornecedorDAO dao = new FornecedorDAO();

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

	public int creat(Produto produto, int quantidade) throws ControleEstoqueSqlException {

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
				pstm.setInt(4, produto.getFornecedor().getCodPessoa());

				// envia para o Banco e fecha o objeto
				pstm.execute();

				chave = Statement.RETURN_GENERATED_KEYS;
				pstm.close();
				connection.close();
				
				cadatrarItemEstoque(produto.getNome(), quantidade);

				System.out.println("Objeto inserido com sucesso:  " + chave);
			} catch (SQLException sqle) {
				throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
			}

		}
		return chave;
	}

	public Estoque listarTodosEstoque() throws ControleEstoqueSqlException {

		Map<ItemEstoque,Produto> itens = new HashMap<ItemEstoque,Produto>();
		Estoque e = new Estoque();
		connection = null;

		try {
			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(LIST);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				CategoriaProduto c = new CategoriaProduto();
				Produto produto = new Produto();
				Fornecedor f = new Fornecedor();
				ItemEstoque i = new ItemEstoque();
				i.setQuantideProduto(rs.getInt("quantidadeproduto"));
				i.setIdEstoque(rs.getInt("idEstoque"));
				
				produto.setCodProduto(rs.getInt("codProduto"));
				produto.setNome(rs.getString("nome"));
				produto.setValorUnitario(rs.getDouble("valorUnitario"));
				c.setCodCategoria(rs.getInt("codCategoria"));
				f = dao.consultaByID(rs.getInt("codFornecedor"));
				produto.setCategoria(c);
				produto.setFornecedor(f);
				itens.put(i, produto);
				
				e.setItens(itens);
			}

			stmt.close();
			rs.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return e;
	}

	public Estoque readByName(String nomeProduto) throws ControleEstoqueSqlException {

		Map<ItemEstoque,Produto> itens = new HashMap<ItemEstoque,Produto>();
		Estoque e = new Estoque();
		connection = null;

		try {
			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(READBYNAME);
			stmt.setString(1, "%"+nomeProduto+"%");
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				CategoriaProduto c = new CategoriaProduto();
				Produto produto = new Produto();
				Fornecedor f = new Fornecedor();
				ItemEstoque i = new ItemEstoque();
				i.setQuantideProduto(rs.getInt("quantidadeproduto"));
				i.setIdEstoque(rs.getInt("idEstoque"));

				produto.setCodProduto(rs.getInt("codProduto"));
				produto.setNome(rs.getString("nome"));
				produto.setValorUnitario(rs.getDouble("valorUnitario"));
				c.setCodCategoria(rs.getInt("codCategoria"));
				f = dao.consultaByID(rs.getInt("codFornecedor"));
				produto.setCategoria(c);
				produto.setFornecedor(f);
				itens.put(i, produto);
				
				e.setItens(itens);
			}

			stmt.close();
			rs.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return e;
	}

	public Produto consultaByID(int id) throws ControleEstoqueSqlException {

		Produto produto = new Produto();

		connection = null;

		try {
			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(LISTBYID);
			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				CategoriaProduto c = new CategoriaProduto();
				Fornecedor f = new Fornecedor();

				produto.setCodProduto(rs.getInt("codProduto"));
				produto.setNome(rs.getString("nome"));
				produto.setValorUnitario(rs.getDouble("valorUnitario"));
				c.setCodCategoria(rs.getInt("codCategoria"));
				f = dao.consultaByID(rs.getInt("codFornecedor"));
				produto.setCategoria(c);
				produto.setFornecedor(f);
			}

			pstm.close();
			rs.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return produto;
	}

	public Produto consultaByName(String name) throws ControleEstoqueSqlException {

		Produto produto = new Produto();

		connection = null;

		try {
			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(LISTBYNAME);
			pstm.setString(1, name);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				CategoriaProduto c = new CategoriaProduto();
				Fornecedor f = new Fornecedor();

				produto.setCodProduto(rs.getInt("codProduto"));
				produto.setNome(rs.getString("nome"));
				produto.setValorUnitario(rs.getDouble("valorUnitario"));
				c.setCodCategoria(rs.getInt("codCategoria"));
				f = dao.consultaByID(rs.getInt("codFornecedor"));
				produto.setCategoria(c);
				produto.setFornecedor(f);
			}

			pstm.close();
			rs.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return produto;
	}

	public int update(Produto produto, int quantidade) throws ControleEstoqueSqlException {

		connection = null;
		int chave = 0;
		try {

			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(UPDATE);

			pstm.setString(1, produto.getNome());
			pstm.setDouble(2, produto.getValorUnitario());
			pstm.setInt(3, produto.getCategoria().getCodCategoria());
			pstm.setInt(4, produto.getFornecedor().getCodPessoa());
			pstm.setInt(5, produto.getCodProduto());

			pstm.execute();
			chave = Statement.RETURN_GENERATED_KEYS;
			pstm.close();
			connection.close();

			updateItemEstoque(produto.getCodProduto(), quantidade);

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return chave;
	}

	public int delete(Produto produto) throws ControleEstoqueSqlException {

		connection = null;
		int chave = 0;
		try {

			deleteItemEstoque(produto.getCodProduto());
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

	private void cadatrarItemEstoque(String nome, int quantidade) {

		ItemEstoqueDAO itemEstoqueDAO = new ItemEstoqueDAO();
		ItemEstoque item = new ItemEstoque();

		try {
			Produto p = consultaByName(nome);
			item.setIdProduto(p.getCodProduto());
			item.setQuantideProduto(quantidade);
			itemEstoqueDAO.creat(item);
		} catch (ControleEstoqueSqlException e) {
			e.printStackTrace();
		}
	}

	private void updateItemEstoque(int idProduto, int quantidade) {

		ItemEstoqueDAO itemEstoqueDAO = new ItemEstoqueDAO();
		ItemEstoque item = new ItemEstoque();

		try {
			item.setIdProduto(idProduto);
			item.setQuantideProduto(quantidade);
			itemEstoqueDAO.update(item);
		} catch (ControleEstoqueSqlException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteItemEstoque(int idProduto) {

		ItemEstoqueDAO itemEstoqueDAO = new ItemEstoqueDAO();
		ItemEstoque item = new ItemEstoque();

		try {
			item.setIdProduto(idProduto);
			itemEstoqueDAO.delete(item);
		} catch (ControleEstoqueSqlException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int creat(Produto t) throws ControleEstoqueSqlException {
		return 0;
	}

	@Override
	public int update(Produto t) throws ControleEstoqueSqlException {
		return 0;
	}

	@Override
	public List<Produto> listarTodos() throws ControleEstoqueSqlException {
		// TODO Auto-generated method stub
		return null;
	}

}
