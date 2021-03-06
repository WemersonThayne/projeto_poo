package br.edu.ifpb.views.dialogs.pedido;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
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

import br.edu.ifpb.entidades.ItemEstoque;
import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.utils.ButtonColumn;

public class VisualizarListaProdutoPedidoDialog extends javax.swing.JDialog {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private List<Produto> produtos = new ArrayList<Produto>();
	private List<ItemEstoque> itens = new ArrayList<ItemEstoque>();

	/**
	 * Create the application.
	 */
	public VisualizarListaProdutoPedidoDialog(JFrame frame, List<Produto> produtos, List<ItemEstoque> itens) {
		super(frame, true);
		this.produtos = produtos;
		this.itens = itens;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("113px:grow"), ColumnSpec.decode("199px:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.LINE_GAP_ROWSPEC, RowSpec.decode("14px"), FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("14px"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(66dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(15dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(15dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(27dlu;default):grow"), }));

		JLabel lblCadastroProdutos = new JLabel("Lista de Produto no Pedido");
		lblCadastroProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroProdutos.setBackground(Color.BLACK);
		lblCadastroProdutos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblCadastroProdutos, "3, 2, 2, 1, fill, top");

		JPanel painelFundo = new JPanel();
		painelFundo.setSize(500, 500);
		painelFundo.setLayout(new GridLayout(1, 1));

		getContentPane().add(painelFundo, "3, 6, 5, 5, fill, fill");

		criaJTable();

		JScrollPane barraRolagem = new JScrollPane(table);
		barraRolagem.setSize(500, 500);
		painelFundo.add(barraRolagem);

		getClickColunaTabela();

	}

	private void criaJTable() {

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Nome ", "Valor Unit\u00E1rio", "Quantidade Atual", "" }));

		table.setEditingColumn(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);

		table.setRowHeight(30);

		new ButtonColumn(table, 4, new ImageIcon(
				VisualizarListaProdutoPedidoDialog.class.getClassLoader().getResource("imagens/delet.png")));

		montarListaProdutos();
	}

	private void getClickColunaTabela() {
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (table.getSelectedColumn() == 4 && table.getSelectedRow() != -1) {
					produtos.remove(table.getSelectedRow());
					System.out.println("produtos.isEmpty():" + produtos.isEmpty());
					if (!produtos.isEmpty()) {
						criaJTable();
					} else {
						dispose();
					}
				}
			}
		});
	}

	private void montarListaProdutos() {
		/* Captura o modelo da tabela */
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setNumRows(0);
		/* Copia os dados da consulta para a tabela */
		for (int i = 0; i < produtos.size(); i++) {
			modelo.addRow(new Object[] { produtos.get(i).getCodProduto(), produtos.get(i).getNome(),
					produtos.get(i).getValorUnitario(), itens.get(i).getQuantideProduto() });
		}
	}
}
