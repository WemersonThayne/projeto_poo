package br.edu.ifpb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.entidades.Saida;
import br.edu.ifpb.entidades.SaidaProduto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.ConnectionFactory;
import br.edu.ifpb.utils.Mensagens;
import br.edu.ifpb.utils.Util;

public class SaidaProdutoDAO implements DAOInterface<SaidaProduto> {

	private final String INSERT = "INSERT INTO SAIDA_PRODUTO (idSaida,idProdutoSaida) VALUES (?,?)";
	private final String LIST = "SELECT * FROM SAIDA_PRODUTO GROUP BY idSaida";
	private final String LISTIDSAIDA = "SELECT idProdutoSaida FROM SAIDA_PRODUTO WHERE idSaida=?";

	private static SaidaProdutoDAO instance;

	public static SaidaProdutoDAO getInstance() {
		if (instance == null) {
			instance = new SaidaProdutoDAO();
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;

	public SaidaProdutoDAO() {
	}

	private Double getValorTotal(ArrayList<Produto> produtos) {
		Double soma = 0D;
		for (Produto produto : produtos) {
			soma += produto.getValorUnitario();
		}
		return soma;
	}

	@Override
	public int creat(SaidaProduto saidaProduto) throws ControleEstoqueSqlException {
		int chave = 0;

		if (saidaProduto != null) {

			connection = null;

			try {

				connection = (Connection) new ConnectionFactory().getConnection();

				PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(INSERT);

				for (Produto p : saidaProduto.getProdutos()) {
					pstm.setInt(1, saidaProduto.getSaida().getCodSaida());
					pstm.setInt(2, p.getCodProduto());
					pstm.execute();
				}

				new Mensagens(Util.CADASTRO_SAIDA_SUCESS);
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
	public int update(SaidaProduto t) throws ControleEstoqueSqlException {

		return 0;
	}

	@Override
	public List<SaidaProduto> listarTodos() throws ControleEstoqueSqlException {
		ArrayList<SaidaProduto> saidaProdutos = new ArrayList<SaidaProduto>();

		connection = null;

		try {
			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(LIST);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				SaidaProduto saidaProduto = new SaidaProduto();
				SaidaDAO saidaDAO = new SaidaDAO();
				Saida saida = new Saida();
				ProdutoDAO produtoDAO = new ProdutoDAO();
				ArrayList<Produto> produtos = new ArrayList<Produto>();

				saida = saidaDAO.consultaByID(rs.getInt("idSaida"));
				saidaProduto.setSaida(saida);

				PreparedStatement stmtProdutos = (PreparedStatement) connection.prepareStatement(LISTIDSAIDA);
				stmtProdutos.setInt(1, saida.getCodSaida());

				ResultSet rsProdutos = stmtProdutos.executeQuery();

				while (rsProdutos.next()) {
					Produto p = new Produto();
					p = produtoDAO.consultaByID(rsProdutos.getInt("idProdutoSaida"));
					produtos.add(p);
				}

				saidaProduto.setProdutos(produtos);
				saidaProduto.setQuantProdutos(produtos.size());
				saidaProduto.setValorTotal(getValorTotal(produtos) * produtos.size());

				stmtProdutos.close();
				rsProdutos.close();
				saidaProdutos.add(saidaProduto);
			}

			stmt.close();
			rs.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return saidaProdutos;
	}

	@Override
	public int delete(SaidaProduto t) throws ControleEstoqueSqlException {
		return 0;
	}

}
