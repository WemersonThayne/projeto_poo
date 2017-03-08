package br.edu.ifpb.frames.dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.edu.ifpb.controllers.ProdutoController;
import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class ConsultaProdutoDialog extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNomeProduto;
	private JButton btnNewButtonListarTodos;
	private JTable table;
	private List<Produto> produtos = null;

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
		getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("96px:grow"), ColumnSpec.decode("199px:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.LINE_GAP_ROWSPEC, RowSpec.decode("14px"), FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("14px"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(19dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

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
				consultarProdutos();
			}
		});
		getContentPane().add(btnNewButtonPesquisar, "6, 6, fill, default");

		btnNewButtonListarTodos = new JButton("Listar Todos");
		getContentPane().add(btnNewButtonListarTodos, "4, 8, fill, default");
		btnNewButtonListarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					consultarProdutos();
					montarTable();
					

			}
		});

		JButton btnNewButtonLimpar = new JButton("Limpar");
		btnNewButtonLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		getContentPane().add(btnNewButtonLimpar, "6, 8, fill, default");
		
		/*
		 * table = new JTable(dados,colunas); table.setBorder(null);
		 * table.setCellSelectionEnabled(true);
		 * table.setColumnSelectionAllowed(true);
		 * table.setFillsViewportHeight(true); table.setEnabled(false);
		 */
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome ", "Valor Unit\u00E1rio", "Quantidade Atual"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(108);
		

		JScrollPane barraRolagem = new JScrollPane(table);

		JPanel painelFundo = new JPanel();
		painelFundo.setLayout(new GridLayout(1, 1));
		painelFundo.add(barraRolagem);

		getContentPane().add(painelFundo, "3, 12, 5, 7, fill, fill");

	}

	private void consultarProdutos() {
		produtos = new ArrayList<Produto>();
		try {
			produtos = new ProdutoController().listarTodos();
		} catch (ControleEstoqueSqlException e) {
			e.printStackTrace();
		}
	}

	private void montarTable(){
		    /* Captura o modelo da tabela */
		    DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		    modelo.setNumRows(0);
			/* Copia os dados da consulta para a tabela */
			for (Produto prod : produtos) {
				modelo.addRow(new Object[]{
					prod.getCodProduto(),
					prod.getNome(),
					prod.getValorUnitario(),
					prod.getQuantideAtual()
				});
			}
	}
}
