package br.edu.ifpb.views.dialogs.produto;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.edu.ifpb.controllers.ProdutoController;
import br.edu.ifpb.entidades.Estoque;
import br.edu.ifpb.entidades.ItemEstoque;
import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.ButtonColumn;
import br.edu.ifpb.utils.Mensagens;
import br.edu.ifpb.utils.Util;

public class ConsultaProdutoDialog extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNomeProduto;
	private JButton btnNewButtonListarTodos;
	private JTable table;
	private List<Produto> produtos = new ArrayList<Produto>();
	private Estoque estoque;
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public ConsultaProdutoDialog(JFrame frame) {
		super(frame, true);
		this.frame = frame;
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
				montarTable();
			}
		});
		getContentPane().add(btnNewButtonPesquisar, "6, 6, fill, default");

		btnNewButtonListarTodos = new JButton("Listar Todos");
		getContentPane().add(btnNewButtonListarTodos, "4, 8, fill, default");
		btnNewButtonListarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarTodosProdutos();
				montarTable();
			}
		});

		JButton btnNewButtonLimpar = new JButton("Limpar");
		btnNewButtonLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNomeProduto.setText("");
				DefaultTableModel modelo = (DefaultTableModel) table.getModel();
				modelo.setNumRows(0);
				produtos = null;
			}
		});
		getContentPane().add(btnNewButtonLimpar, "6, 8, fill, default");

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Nome ", "Valor Unit\u00E1rio", "Quantidade Atual", "", "" }));

		table.setEditingColumn(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.getColumnModel().getColumn(5).setPreferredWidth(20);

		table.setRowHeight(30);

		new ButtonColumn(table, 4,
				new ImageIcon(ConsultaProdutoDialog.class.getClassLoader().getResource("imagens/edit.png")));
		new ButtonColumn(table, 5,
				new ImageIcon(ConsultaProdutoDialog.class.getClassLoader().getResource("imagens/delet.png")));
		getClickColunaTabela();
		JScrollPane barraRolagem = new JScrollPane(table);

		JPanel painelFundo = new JPanel();
		painelFundo.setLayout(new GridLayout(1, 1));
		painelFundo.add(barraRolagem);

		getContentPane().add(painelFundo, "3, 12, 5, 7, fill, fill");

	}

	private void consultarProdutos() {
		try {
			estoque = new ProdutoController().readByName(textFieldNomeProduto.getText().toString().trim());
			montarListaProdutos();
		} catch (ControleEstoqueSqlException e) {
			e.printStackTrace();
		}
	}

	private void listarTodosProdutos() {
		try {

			estoque = new ProdutoController().listarTodos();
			montarListaProdutos();
		} catch (ControleEstoqueSqlException e) {
			e.printStackTrace();
		}
	}

	private void montarTable() {
		List<ItemEstoque> itens = new ArrayList<ItemEstoque>();
		Set<ItemEstoque> chaves = estoque.getItens().keySet();
		for (Iterator<ItemEstoque> iterator = chaves.iterator(); iterator.hasNext();) {
			ItemEstoque chave = iterator.next();
			if (chave != null)
				itens.add(chave);
		}
		
		/* Captura o modelo da tabela */
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setNumRows(0);
		/* Copia os dados da consulta para a tabela */
		for (int i = 0 ; i < produtos.size();  i++ ) {
			modelo.addRow(new Object[] { produtos.get(i).getCodProduto(), produtos.get(i).getNome(), produtos.get(i).getValorUnitario(),  itens.get(i).getQuantideProduto()});
		}
	}

	private int coluna = -1;
	private int linha = -1;
	private int quantidadeAtual = 0;
	
	private void getClickColunaTabela() {
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				coluna = table.getSelectedColumn();
				linha = table.getSelectedRow();
				chamarDialogEditarDados();
			}
		});
	}

	private void chamarDialogEditarDados() {
		if (coluna == 4 && linha != -1) {

			Produto produtoEditar = null;
			for (Produto produto : produtos) {
				if (Integer.parseInt(table.getModel().getValueAt(linha, 0).toString()) == produto.getCodProduto()) {
					produtoEditar = produto;
					quantidadeAtual = Integer.parseInt(table.getModel().getValueAt(linha, 3).toString());
				}
			}
			if (produtoEditar != null) {
				getEditarCadastroProduto(produtoEditar);
			} else {
				new Mensagens("Não foi possivel carregar as informações do produto.");
			}

		} else {
			if (coluna == 5 && linha != -1) {
				Produto produtoExcluir = null;
				for (Produto produto : produtos) {
					if (Integer.parseInt(table.getModel().getValueAt(linha, 0).toString()) == produto.getCodProduto()) {
						produtoExcluir = produto;
					}
				}
				if (produtoExcluir != null) {
					try {
						getDeletarProduto(produtoExcluir);
					} catch (ControleEstoqueSqlException e) {
						e.printStackTrace();
					}
				} else {
					new Mensagens("Não foi possivel carregar as informações do produto.");
				}
			}
		}
	}

	
	private void getEditarCadastroProduto(Produto produto) {

		EditarCadastroProdutoDialog editarCadastro = new EditarCadastroProdutoDialog(frame, produto,quantidadeAtual);
		editarCadastro.setBounds(100, 100, 400, 300);
		editarCadastro.setTitle("Editar dados do cadastro");
		editarCadastro.setLocationRelativeTo(null);
		editarCadastro.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		editarCadastro.setVisible(true);
	}

	private void getDeletarProduto(Produto produtoDeletar) throws ControleEstoqueSqlException {
		int result = JOptionPane.showConfirmDialog(null, "Deseja Deletar esse produto?  " + produtoDeletar.getNome(),
				"Excluir", JOptionPane.YES_NO_CANCEL_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			System.out.println("Deletou");
			if (new ProdutoController().delete(produtoDeletar) == 1) {
				new Mensagens(Util.DELETE_PRD_SUCESS);
				listarTodosProdutos();
			}
		}
	}

	private void montarListaProdutos() {
		produtos = new ArrayList<Produto>();
		Set<ItemEstoque> chaves = estoque.getItens().keySet();
		for (Iterator<ItemEstoque> iterator = chaves.iterator(); iterator.hasNext();) {
			ItemEstoque chave = iterator.next();
			if (chave != null)
				produtos.add(estoque.getItens().get(chave));
		}
	}
}
