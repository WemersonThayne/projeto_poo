package br.edu.ifpb.main;

import br.edu.ifpb.utils.ConnectionFactory;

public class Principal {

	public static void main(String[] args) {
		ConnectionFactory cf = new ConnectionFactory();
		cf.iniciarConexao();
	}
}
