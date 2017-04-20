package br.edu.ifpb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.edu.ifpb.entidades.Pedido;
import br.edu.ifpb.entidades.PedidoProduto;
import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.ConnectionFactory;
import br.edu.ifpb.utils.Mensagens;
import br.edu.ifpb.utils.Util;

public class PedidoProdutoDAO implements DAOInterface<PedidoProduto> {

	private final String INSERT = "INSERT INTO PEDIDO_PRODUTO (idPedido,idProduto) VALUES (?,?)";
	private final String LIST   = "SELECT * FROM PEDIDO_PRODUTO ORDER BY idPedido";
	private final String LISTIDPEDIDOS   = "SELECT idProduto FROM PEDIDO_PRODUTO WHERE idPedido=?";
	
	private static PedidoProdutoDAO instance;

	public static PedidoProdutoDAO getInstance() {
		if (instance == null) {
			instance = new PedidoProdutoDAO();
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;

	public PedidoProdutoDAO() {
	}

	@Override
	public int creat(PedidoProduto pedido) throws ControleEstoqueSqlException {

		int chave = 0;

		if (pedido != null) {

			connection = null;

			try {

				connection = (Connection) new ConnectionFactory().getConnection();

				PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(INSERT);
			
				for (Produto p : pedido.getProdutos()) {
					pstm.setInt(1, pedido.getPedido().getCodPedido());
					pstm.setInt(2, p.getCodProduto() );
					pstm.execute();	
				}

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
	public ArrayList<PedidoProduto> listarTodos() throws ControleEstoqueSqlException {

		ArrayList<PedidoProduto> pedidosProdutos = new ArrayList<PedidoProduto>();

		connection = null;

		try {
			connection = (Connection) new ConnectionFactory().getConnection();

			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(LIST);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				PedidoProduto pedidoProduto = new PedidoProduto();
				PedidoDAO pedidoDAO = new PedidoDAO();
				Pedido pedido = new Pedido();
				ProdutoDAO produtoDAO = new ProdutoDAO();
				ArrayList<Produto> produtos = new ArrayList<Produto>();
				
				pedido = pedidoDAO.consultaByID(rs.getInt("idPedido"));
				pedidoProduto.setPedido(pedido);
				
				PreparedStatement stmtProdutos = (PreparedStatement) connection.prepareStatement(LISTIDPEDIDOS);
				stmtProdutos.setInt(1, pedido.getCodPedido());

				ResultSet rsProdutos = stmt.executeQuery();
				
				while (rsProdutos.next()){
					Produto p = new Produto();
					p = produtoDAO.consultaByID(rsProdutos.getInt("idProduto"));
					produtos.add(p);
				}
				pedidoProduto.setProdutos(produtos);
				pedidoProduto.setQuantProdutos(produtos.size());
				pedidoProduto.setValorTotal(getValorTotal(produtos));
				
//				pedidos.add(pedido);
			}

			stmt.close();
			rs.close();
			connection.close();

		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return pedidosProdutos;
	}

	@Override
	public int update(PedidoProduto t) throws ControleEstoqueSqlException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(PedidoProduto t) throws ControleEstoqueSqlException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private Double getValorTotal(ArrayList<Produto> produtos){
		Double soma = 0D;
		for (Produto produto : produtos) {
			soma += produto.getValorUnitario();
		}
		return soma;
	}
	

}
