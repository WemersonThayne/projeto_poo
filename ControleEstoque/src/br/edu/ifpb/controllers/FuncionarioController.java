package br.edu.ifpb.controllers;

import br.edu.ifpb.dao.FuncionarioDAO;
import br.edu.ifpb.entidades.Funcionario;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class FuncionarioController {

	/*	public ArrayList<Usuario> listarTodos() throws SQLException {
	return UsuarioDAO.getInstance().listarTodos();
}

public ArrayList<Usuario> readById(Usuario user) {
	return UsuarioDAO.getInstance().readById(user);
}
*/
	public int creat(Funcionario funcionario) throws ControleEstoqueSqlException {		
		return FuncionarioDAO.getInstance().creat( funcionario);
	}
/*
public String delete(Usuario user) {
	UsuarioDAO.getInstance().delete(user);
	return "Deletado com sucesso";
}*/

/*	public String update(Usuario user) {
	UsuarioDAO.getInstance().update(user);
	return "Atualizado com sucesso";
}

public Usuario verificaLogin(Usuario usuario) {
	return UsuarioDAO.getInstance().verificarLogin(usuario);
}

public List<Usuario> consultarUsuariosByNome(Usuario usuario) throws SQLException {
	return UsuarioDAO.getInstance().consultarUsuariosByNome(usuario);
}*/

}
