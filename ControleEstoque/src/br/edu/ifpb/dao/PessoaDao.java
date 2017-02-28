package br.edu.ifpb.dao;

public class PessoaDao {

	/*
	 * 
	 * 
	private final String INSERT = "INSERT INTO CONTATO (NOME, TELEFONE, EMAIL) VALUES (?,?,?)";
	private final String UPDATE = "UPDATE CONTATO SET NOME=?, TELEFONE=?, EMAIL=? WHERE ID=?";
	private final String DELETE = "DELETE FROM CONTATO WHERE ID =?";
	private final String LIST = "SELECT * FROM CONTATO";
	private final String LISTBYID = "SELECT * FROM CONTATO WHERE ID=?";
	 * 
	 * */	
	/*static DBPool banco;
	private static CategoriaDAO instance;

	public static CategoriaDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new CategoriaDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public CategoriaDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	public CategoriaDAO() {
		this.connection = (Connection) banco.getConn();
	}
	
	/**
	 * Função: Seleção de Categoria no banco de dados pelo ID. 
	 * Retorno: Retorna somente uma Categoria.
	 * 
	public Categoria readById(Categoria categoria) {

		Categoria categoriaAux = null;

		try {

			String sql = "SELECT C.cd_categoria, C.nm_descricao"
					+ " FROM  tb_categoria  as C" 
					+ " WHERE  C.cd_categoria = ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, categoria.getId());
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				categoriaAux = new Categoria();
				categoriaAux.setId(rs.getInt("cd_categoria"));
				categoriaAux.setDescricao(rs
						.getString("nm_descricao"));
			}
			
			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return categoriaAux;
	}

	*//**
	 * Função: Atualizar o valor da descrição da categoria identificando pelo ID.
	 * Retorno: VOID.
	 * *//*
	public void update(Categoria categoria) {

		try {

			String sql = "UPDATE tb_categoria SET nm_descricao = ?"
					+ " WHERE cd_categoria = ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, categoria.getDescricao());
			stmt.setInt(2, categoria.getId());
			
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
	}*/
}
