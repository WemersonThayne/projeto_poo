package br.edu.ifpb.views.frames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.edu.ifpb.views.dialogs.ConsultaPedidoDialog;

public class FornecedorFrame {

	private JFrame frame;
	private ConsultaPedidoDialog  consultaPedidoDialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FornecedorFrame window = new FornecedorFrame();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FornecedorFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Menu de produto
				JMenuBar menuBar= new JMenuBar();
				frame.setJMenuBar(menuBar);

				 // Define e adiciona dois menus drop down na barra de menus
		        JMenu entegra = new JMenu("Entegras");
		        menuBar.add(entegra);
		        
		        // Cria e adiciona um item simples para o menu
		        JMenuItem cadastro = new JMenuItem("Cadastrar Nova Entegra");
		        JMenuItem consulta= new JMenuItem("Consultar Entegra");
				
		        entegra.add(cadastro);
		        entegra.add(consulta);
		        
		        JMenu pedidosSolicitados = new JMenu("Pedidos Solicitados");
		        menuBar.add(pedidosSolicitados);
		        
		        JMenuItem consultaPedido = new JMenuItem("Consultar Pedidos Solicitados");
		        pedidosSolicitados.add(consultaPedido);
		        
		        abrirConsultaPedido(consultaPedido);

	}      
	
	private void abrirConsultaPedido(JMenuItem consultarPedido){
		consultarPedido.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) { 
	    		  
	    		  consultaPedidoDialog = new ConsultaPedidoDialog(frame);
	    		  consultaPedidoDialog.setBounds(100, 100, 600, 400);
	    		  consultaPedidoDialog.setTitle("Consultar Pedidos");
	    		  consultaPedidoDialog.setLocationRelativeTo(null);
	    		  consultaPedidoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    		  consultaPedidoDialog.setVisible(true);
	}});
	}

	
}
