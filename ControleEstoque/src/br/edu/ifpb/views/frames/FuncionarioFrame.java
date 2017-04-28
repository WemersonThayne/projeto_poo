package br.edu.ifpb.views.frames;

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
import javax.swing.JSeparator;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.edu.ifpb.views.dialogs.pedido.ConsultaPedidoDialog;
import br.edu.ifpb.views.dialogs.pedido.FazerPedidoDialog;
import br.edu.ifpb.views.dialogs.produto.CadastroProdutoDialog;
import br.edu.ifpb.views.dialogs.produto.ConsultaProdutoDialog;

public class FuncionarioFrame{

	private JFrame frmFuncionario;
	private JPanel panelPrincipal; 
	private CadastroProdutoDialog cadastroProdutoDialog;
	private ConsultaProdutoDialog consultaProdutoDialog;
	private FazerPedidoDialog     cadastroPedidoDialog;
	private ConsultaPedidoDialog  consultaPedidoDialog;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FuncionarioFrame window = new FuncionarioFrame();
					window.frmFuncionario.setVisible(true);
					window.frmFuncionario.setLocationRelativeTo(null);
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
		panelPrincipal.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("136px"),
				ColumnSpec.decode("max(100dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("137px"),},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblCadastroProduto = new JLabel("Bem Vindo ao Sistema de Estoque");
		panelPrincipal.add(lblCadastroProduto, "2, 2, left, top");
		
		JSeparator separator = new JSeparator();
		panelPrincipal.add(separator, "1, 4, 4, 1, fill, default");
		
		
		JMenuBar menuBar= new JMenuBar();
		frmFuncionario.setJMenuBar(menuBar);

		//Menu de produto
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
        JMenu  saida = new JMenu("Sa�da");
        menuBar.add(saida);
        
        // Cria e adiciona um item simples para o menu
        JMenuItem cadastroSaida = new JMenuItem("Registrar Sa�da");
        JMenuItem consultaSaida = new JMenuItem("Consultar Sa�da");
		
        saida.add(cadastroSaida);
        saida.add(consultaSaida);
        
              
        //FIM DOS MENUS
        /*Chama as fun��es responsaveis por  gerenciar a apertura dos dialogs*/
        abrirCadatroProduto(cadastroProduto);
        abrirConsultaProduto(consultaProduto);
        abrirCadastroPedido(cadastroPedido);
        abrirConsultaPedido(consultaPedido);
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
	    		  consultaProdutoDialog.setBounds(100, 100, 600, 400);
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
	    		  cadastroPedidoDialog.setBounds(100, 100, 500, 500);
	    		  cadastroPedidoDialog.setTitle("Fazer Pedido de Produtos");
	    		  cadastroPedidoDialog.setLocationRelativeTo(null);
	    		  cadastroPedidoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    		  cadastroPedidoDialog.setVisible(true);
	}});
	}
	
	
	private void abrirConsultaPedido(JMenuItem consultarPedido){
		consultarPedido.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) { 
	    		  
	    		  consultaPedidoDialog = new ConsultaPedidoDialog(frmFuncionario);
	    		  consultaPedidoDialog.setBounds(100, 100, 600, 400);
	    		  consultaPedidoDialog.setTitle("Consultar Pedidos");
	    		  consultaPedidoDialog.setLocationRelativeTo(null);
	    		  consultaPedidoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    		  consultaPedidoDialog.setVisible(true);
	}});
	}
}
