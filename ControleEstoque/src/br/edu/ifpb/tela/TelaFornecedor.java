package br.edu.ifpb.tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TelaFornecedor {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFornecedor window = new TelaFornecedor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaFornecedor() {
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
		        
	}

}
