package br.edu.ifpb.views.dialogs.pedido;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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

import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.utils.ButtonColumn;
import br.edu.ifpb.views.dialogs.produto.ConsultaProdutoDialog;

public class DetalhePedidoDialog  extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private List<Produto> produtos = null;
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public DetalhePedidoDialog(JFrame frame) {
		super(frame, true);
		this.frame = frame;
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

		JLabel lblCadastroProdutos = new JLabel("Detalhe do Pedido");
		lblCadastroProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroProdutos.setBackground(Color.BLACK);
		lblCadastroProdutos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblCadastroProdutos, "4, 2, center, top");

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Data", "Valor Total", "Quantidade", "Lista de Produtos"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(65);

		table.setEditingColumn(0);

		table.setRowHeight(30);

		new ButtonColumn(table, 4,
				new ImageIcon(ConsultaProdutoDialog.class.getClassLoader().getResource("imagens/search.png")));
		
		getClickColunaTabela();
		JScrollPane barraRolagem = new JScrollPane(table);

		JPanel painelFundo = new JPanel();
		painelFundo.setLayout(new GridLayout(1, 1));
		painelFundo.add(barraRolagem);

		getContentPane().add(painelFundo, "3, 6, 5, 7, fill, fill");
		montarTable();

	}

	private void consultarProdutos() {
		/*produtos = new ArrayList<Produto>();
		try {
			produtos = new ProdutoController().readByName(textFieldNomeProduto.getText().toString().trim());
		} catch (ControleEstoqueSqlException e) {
			e.printStackTrace();
		}*/
		//chamar um tela com todos os produtod desse pedido
	}

	

	private void montarTable() {
		/* Captura o modelo da tabela 
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setNumRows(0);
		 Copia os dados da consulta para a tabela 
		for (Produto prod : produtos) {
			modelo.addRow(new Object[] { prod.getCodProduto(), prod.getNome(), prod.getValorUnitario(),
					prod.getQuantideAtual() });
		}*/
	}

	private int coluna = -1;
	private int linha = -1;

	private void getClickColunaTabela() {
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				coluna = table.getSelectedColumn();
				linha = table.getSelectedRow();
				//chamarDialogEditarDados();
				//chamar tela com lista de produtos
			}
		});
	}

	

}
