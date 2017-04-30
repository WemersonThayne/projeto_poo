package br.edu.ifpb.views.dialogs.saida;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.edu.ifpb.controllers.SaidaProdutoController;
import br.edu.ifpb.entidades.SaidaProduto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class ConsultaSaidaDialog extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private List<SaidaProduto> saidas = new ArrayList<SaidaProduto>();

	/**
	 * Create the application.
	 */
	public ConsultaSaidaDialog(JFrame frame) {
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
				ColumnSpec.decode("113px:grow"),
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
				RowSpec.decode("max(66dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(15dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(15dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(27dlu;default):grow"),}));

		JLabel lblCadastroProdutos = new JLabel("Consultar Pedidos");
		lblCadastroProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroProdutos.setBackground(Color.BLACK);
		lblCadastroProdutos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblCadastroProdutos, "4, 2, center, top");

		JPanel painelFundo = new JPanel();
		painelFundo.setSize(500, 500);
		painelFundo.setLayout(new GridLayout(1, 1));

		getContentPane().add(painelFundo, "3, 6, 6, 5, fill, fill");

		criaJTable();

		JScrollPane barraRolagem = new JScrollPane(table);
		barraRolagem.setSize(500, 500);
		painelFundo.add(barraRolagem);

		montarListaPedido();
	}

	private void criaJTable() {

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Data da Saída ", "Valor Total", "Funcionario" }));

		table.setEditingColumn(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.setRowHeight(25);
	}

	private void montarListaPedido() {
		/* Captura o modelo da tabela */
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setNumRows(0);
		try {
			saidas = new SaidaProdutoController().listarTodos();
		} catch (ControleEstoqueSqlException e) {
			e.printStackTrace();
		}

		/* Copia os dados da consulta para a tabela */
		for (int i = 0; i < saidas.size(); i++) {
			modelo.addRow(new Object[] { saidas.get(i).getSaida().getCodSaida(),
					saidas.get(i).getSaida().getDataSaida(), saidas.get(i).getValorTotal(),
					saidas.get(i).getSaida().getFuncionario().getNome().toUpperCase() });
		}
	}

}