package br.edu.ifpb.frames.dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class ConsultaProdutoDialog extends javax.swing.JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNomeProduto;
	private JButton btnNewButtonListarTodos;
	private JTable table;

	/**
	 * Create the application.
	 */
	public ConsultaProdutoDialog(JFrame frame) {
		super(frame, true);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("96px:grow"),
				ColumnSpec.decode("199px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(19dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblCadastroProdutos = new JLabel("Consulta Produtos");
		lblCadastroProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroProdutos.setBackground(Color.BLACK);
		lblCadastroProdutos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblCadastroProdutos, "4, 2, center, top");
		
		JLabel lblNewLabel = new JLabel("Produto:");
		getContentPane().add(lblNewLabel, "3, 6, left, default");
		
		textFieldNomeProduto = new JTextField();
		getContentPane().add(textFieldNomeProduto, "4, 6, fill, default");
		textFieldNomeProduto.setColumns(10);
		
		JButton btnNewButtonPesquisar = new JButton("Pesquisar");
		btnNewButtonPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		getContentPane().add(btnNewButtonPesquisar, "6, 6, fill, default");
		
		btnNewButtonListarTodos = new JButton("Listar Todos");
		getContentPane().add(btnNewButtonListarTodos, "4, 8, fill, default");
		
		JButton btnNewButtonLimpar = new JButton("Limpar");
		btnNewButtonLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		getContentPane().add(btnNewButtonLimpar, "6, 8, fill, default");
		

		String [] colunas = {"Nome do Prodtuo", "Valor Unitario", "Quantidade"};
		
		Object [][] dados = {
				{"Computador", "2000,00 R$", "5"},
				{"Fonte", "100,00 R$", "2"},
				{"Moto x player", "2000,00 R$", "5"},
				{"Teste1", "10,00 R$", "5"},
				{"Teste1", "10,00 R$", "5"},
				{"Teste2", "10,00 R$", "5"},
				{"Teste3", "10,00 R$", "5"},
				{"Teste4", "10,00 R$", "5"},
				{"Teste5", "10,00 R$", "5"}
			};
		table = new JTable(dados,colunas);
		table.setBorder(null);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		JScrollPane barraRolagem = new JScrollPane(table);
		
		JPanel painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(1, 1));
        painelFundo.add(barraRolagem); 
 
       
		getContentPane().add(painelFundo, "3, 12, 5, 7, fill, fill");

	}

}
