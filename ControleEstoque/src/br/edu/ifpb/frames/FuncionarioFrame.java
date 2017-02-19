package br.edu.ifpb.frames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import br.edu.ifpb.frames.dialogs.CadastroProdutoDialog;
import br.edu.ifpb.frames.dialogs.ConsultaProdutoDialog;
import br.edu.ifpb.frames.dialogs.FazerPedidoDialog;

public class FuncionarioFrame{

	private JFrame frmFuncionario;
	private JPanel panelPrincipal; 
	private CadastroProdutoDialog cadastroProdutoDialog;
	private ConsultaProdutoDialog consultaProdutoDialog;
	private FazerPedidoDialog     cadastroPedidoDialog;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FuncionarioFrame window = new FuncionarioFrame();
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
	public FuncionarioFrame() {
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
		
		panelPrincipal = new JPanel();
		frmFuncionario.getContentPane().add(panelPrincipal);
		
		JLabel lblCadastroProduto = new JLabel("Bem Vindo ao Sistema de Estoque");
		panelPrincipal.add(lblCadastroProduto);
		
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
        abrirCadatroProduto(cadastroProduto);
        abrirConsultaProduto(consultaProduto);
        abrirCadastroPedido(cadastroPedido);
	}

	private void abrirCadatroProduto(JMenuItem cadastroProduto){
		cadastroProduto.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) { 
	    		  
	    		  cadastroProdutoDialog = new CadastroProdutoDialog(frmFuncionario);
	    		  cadastroProdutoDialog.setBounds(100, 100, 450, 300);
	    		  cadastroProdutoDialog.setTitle("Cadastro de Produtos");
	    		  cadastroProdutoDialog.setLocationRelativeTo(null);
	    		  cadastroProdutoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    		  cadastroProdutoDialog.setVisible(true);
	}});
	}
	
	private void abrirConsultaProduto(JMenuItem consultarProduto){
		consultarProduto.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) { 
	    		  
	    		  consultaProdutoDialog = new ConsultaProdutoDialog(frmFuncionario);
	    		  consultaProdutoDialog.setBounds(100, 100, 450, 300);
	    		  consultaProdutoDialog.setTitle("Consultas de Produtos");
	    		  consultaProdutoDialog.setLocationRelativeTo(null);
	    		  consultaProdutoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    		  consultaProdutoDialog.setVisible(true);
	}});
	}
	
	private void abrirCadastroPedido(JMenuItem cadastroPedido){
		cadastroPedido.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) { 
	    		  
	    		  cadastroPedidoDialog = new FazerPedidoDialog(frmFuncionario);
	    		  cadastroPedidoDialog.setBounds(100, 100, 450, 300);
	    		  cadastroPedidoDialog.setTitle("Fazer Pedido de Produtos");
	    		  cadastroPedidoDialog.setLocationRelativeTo(null);
	    		  cadastroPedidoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    		  cadastroPedidoDialog.setVisible(true);
	}});
	}
}
