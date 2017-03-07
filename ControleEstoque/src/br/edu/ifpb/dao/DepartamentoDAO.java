package br.edu.ifpb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.edu.ifpb.entidades.Departamento;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.ConnectionFactory;

public class DepartamentoDAO {

	private final String SELECTALL = "SELECT * FROM DEPARTAMENTO";

	private static DepartamentoDAO instance;

	public static DepartamentoDAO getInstance() {
		if (instance == null) {
			instance = new DepartamentoDAO();
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;

	public DepartamentoDAO() {
	}

	public List<Departamento> selectAll() throws ControleEstoqueSqlException {

		List<Departamento> departamentos = null;
		connection = null;

		try {

			connection = (Connection) new ConnectionFactory().getConnection();

			// prepared statement para inserção
			PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(SELECTALL);

			// envia para o Banco e fecha o objeto
			pstm.execute();

			ResultSet rs = pstm.executeQuery();
			departamentos = new ArrayList<Departamento>();
			while (rs.next()) {

				Departamento departamento = new Departamento();
				departamento.setCodDepartamento(rs.getInt("codDepartamento"));
				departamento.setDescricao(rs.getString("descricao"));
				departamentos.add(departamento);
			}

			pstm.close();
			connection.close();
		} catch (SQLException sqle) {
			throw new ControleEstoqueSqlException(sqle.getErrorCode(), sqle.getLocalizedMessage());
		}

		return departamentos;
	}

}
