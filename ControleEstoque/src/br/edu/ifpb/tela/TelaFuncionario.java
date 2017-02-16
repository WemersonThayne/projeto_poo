package br.edu.ifpb.tela;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TelaFuncionario {

	private JFrame frmFuncionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFuncionario window = new TelaFuncionario();
					window.frmFuncionario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaFuncionario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFuncionario = new JFrame();
		frmFuncionario.setTitle("Funcionario");
		frmFuncionario.setBounds(100, 100, 450, 300);
		frmFuncionario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFuncionario.getContentPane().setLayout(new BoxLayout(frmFuncionario.getContentPane(), BoxLayout.X_AXIS));
		
		//Menu de produto
		JMenuBar menuBar= new JMenuBar();
		frmFuncionario.setJMenuBar(menuBar);

		 // Define e adiciona dois menus drop down na barra de menus
        JMenu produto = new JMenu("Produto");
        menuBar.add(produto);
        
        // Cria e adiciona um item simples para o menu
        JMenuItem cadastroProduto = new JMenuItem("Cadastrar Novo Produto");
        JMenuItem consultaProduto = new JMenuItem("Consultar Produto");
		
        produto.add(cadastroProduto);
        produto.add(consultaProduto);
        
        //Menu de pedido
       
        // Define e adiciona dois menus drop down na barra de menus
        JMenu pedido = new JMenu("Pedido");
        menuBar.add(pedido);
        
        // Cria e adiciona um item simples para o menu
        JMenuItem cadastroPedido = new JMenuItem("Fazer Novo Pedido");
        JMenuItem consultaPedido = new JMenuItem("Consultar Pedido");
		
        pedido.add(cadastroPedido);
        pedido.add(consultaPedido);
        
        
        //Menu de Saida
        
        // Define e adiciona dois menus drop down na barra de menus
        JMenu  saida = new JMenu("Pedido");
        menuBar.add(saida);
        
        // Cria e adiciona um item simples para o menu
        JMenuItem cadastroSaida = new JMenuItem("Registrar Saída");
        JMenuItem consultaSaida = new JMenuItem("Consultar Saída");
		
        saida.add(cadastroSaida);
        saida.add(consultaSaida);
        
        JMenu  relatorio = new JMenu("Relatorios");
        menuBar.add(relatorio);

        // Cria e adiciona um item simples para o menu
        JMenuItem relatorioProdutos = new JMenuItem("Relatório de Produtos");
        JMenuItem relatorioPedidos = new JMenuItem("Relatório Pedidos");
        relatorio.add(relatorioProdutos);
        relatorio.add(relatorioPedidos);
       
        //FIM DOS MENUS 
	}

}
