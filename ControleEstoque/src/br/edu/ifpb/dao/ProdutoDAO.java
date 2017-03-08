package br.edu.ifpb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	private final String LIST = "SELECT * FROM PRODUTO ORDER BY nome";
	/*private final String UPDATE = "UPDATE CONTATO SET NOME=?, TELEFONE=?, EMAIL=? WHERE ID=?";
	private final String DELETE = "DELETE FROM CONTATO WHERE ID =?";
	
	*/

	
	private static ProdutoDAO instance;

	public static ProdutoDAO getInstance() {
		if (instance == null) {
			instance = new ProdutoDAO();
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;
	
	public ProdutoDAO() {}

	public int creat(Produto produto) throws ControleEstoqueSqlException {

		
		
		int chave = 0;
		
		if (produto != null) {
			
			
			 
			connection = null;
		
			try {
				
				connection = (Connection) new  ConnectionFactory().getConnection();
				
				// prepared statement para inserção
				PreparedStatement pstm = (PreparedStatement) connection
						.prepareStatement(INSERT);
			
				pstm.setString(1, produto.getNome());
				pstm.setDouble(2, produto.getValorUnitario());
				pstm.setInt   (3, produto.getCategoria().getCodCategoria());
				pstm.setInt   (4, produto.getQuantideAtual());
				
				//envia para o Banco e fecha o objeto
				pstm.execute();

				chave = Statement.RETURN_GENERATED_KEYS;
				pstm.close();
				connection.close();
				
				System.out.println("Objeto inserido com sucesso:  "+chave);
				new Mensagens(Util.CADASTRO_PRD_SUCESS);
			} catch (SQLException sqle) {
				throw new ControleEstoqueSqlException(sqle.getErrorCode(),
						sqle.getLocalizedMessage());
			}

		}
		return chave;
	}
	
	public ArrayList<Produto> listarTodos() throws ControleEstoqueSqlException {

		ArrayList<Produto> produtos = new ArrayList<Produto>();

		connection = null;
		
		try {
			connection = (Connection) new  ConnectionFactory().getConnection();
			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(LIST);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				CategoriaProduto c = new CategoriaProduto();
				Produto produto = new Produto();
				
				produto.setCodProduto(rs.getInt("codProduto"));
				produto.setNome(rs.getString("nome"));
				produto.setQuantideAtual(rs.getInt("quantidade"));
				produto.setValorUnitario(rs.getDouble("valorUnitario"));
				c.setCodCategoria(rs.getInt("quantidade"));
				produto.setCategoria(c);
				
				produtos.add(produto);
			}

			stmt.close();
			rs.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return produtos;
	}
}
