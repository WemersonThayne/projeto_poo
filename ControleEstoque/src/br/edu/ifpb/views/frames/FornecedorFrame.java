package br.edu.ifpb.views.frames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.edu.ifpb.views.dialogs.pedido.ConsultaPedidoDialog;

public class FornecedorFrame {

	private JFrame frame;
	private ConsultaPedidoDialog consultaPedidoDialog;

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

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu pedidosSolicitados = new JMenu("Pedidos Solicitados");
		menuBar.add(pedidosSolicitados);

		JMenuItem consultaPedido = new JMenuItem("Consultar Pedidos Solicitados");
		pedidosSolicitados.add(consultaPedido);

		abrirConsultaPedido(consultaPedido);

	}

	private void abrirConsultaPedido(JMenuItem consultarPedido) {
		consultarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				consultaPedidoDialog = new ConsultaPedidoDialog(frame);
				consultaPedidoDialog.setBounds(100, 100, 600, 400);
				consultaPedidoDialog.setTitle("Consultar Pedidos");
				consultaPedidoDialog.setLocationRelativeTo(null);
				consultaPedidoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				consultaPedidoDialog.setVisible(true);
			}
		});
	}

}
