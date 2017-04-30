package br.edu.ifpb.views.dialogs.saida;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.edu.ifpb.controllers.FuncionarioController;
import br.edu.ifpb.controllers.ItemEstoqueController;
import br.edu.ifpb.controllers.ProdutoController;
import br.edu.ifpb.controllers.SaidaController;
import br.edu.ifpb.controllers.SaidaProdutoController;
import br.edu.ifpb.entidades.Estoque;
import br.edu.ifpb.entidades.Funcionario;
import br.edu.ifpb.entidades.ItemEstoque;
import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.entidades.Saida;
import br.edu.ifpb.entidades.SaidaProduto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.Mensagens;
import br.edu.ifpb.utils.Util;

public class RegistraSaidaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JButton btnFinalizarPedido;
	private JButton btnAdicinarProduto;

	private JComboBox<Funcionario> comboBoxFuncionario;

	private JTextField textFieldNomeProduto;
	private JTextField textFieldNomeProdutoEscolhido;
	private JTextField textFieldValorUnitario;
	private JTextField textFieldQuantidadeProduto;

	private List<Produto> produtos = new ArrayList<Produto>();
	private List<Produto> produtosSaida = new ArrayList<Produto>();
	private Estoque estoque;
	private Set<ItemEstoque> chaves = new HashSet<ItemEstoque>();
	private List<ItemEstoque> itens = new ArrayList<ItemEstoque>();
	private List<ItemEstoque> itensSaida = new ArrayList<ItemEstoque>();

	private List<Funcionario> funcionarios = null;

	/**
	 * Create the application.
	 */
	public RegistraSaidaDialog(JFrame frame) {
		super(frame, true);
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
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(66dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(15dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(15dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(27dlu;default):grow"), }));

		JLabel lblCadastroProdutos = new JLabel("Registrar Sa\u00EDda de Produto");
		lblCadastroProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroProdutos.setBackground(Color.BLACK);
		lblCadastroProdutos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblCadastroProdutos, "4, 2, 1, 2, fill, top");

		JLabel lblNewLabel = new JLabel("Produto:");
		getContentPane().add(lblNewLabel, "3, 6, left, default");

		textFieldNomeProduto = new JTextField();
		getContentPane().add(textFieldNomeProduto, "4, 6, fill, default");
		textFieldNomeProduto.setColumns(10);

		JButton btnNewButtonPesquisar = new JButton("Pesquisar");
		getContentPane().add(btnNewButtonPesquisar, "6, 6, center, default");

		JPanel painelFundo = new JPanel();
		painelFundo.setSize(200, 800);
		painelFundo.setLayout(new GridLayout(1, 1));

		getContentPane().add(painelFundo, "3, 8, 5, 5, fill, fill");

		criaJTable();
		getClickLinhaTabela();

		JScrollPane barraRolagem = new JScrollPane(table);
		barraRolagem.setSize(200, 400);
		painelFundo.add(barraRolagem);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(2));
		getContentPane().add(separator, "3, 14, 4, 1, fill, default");

		JLabel lblNewLabel2 = new JLabel("Produto Escolhido:");
		getContentPane().add(lblNewLabel2, "3, 16, left, default");

		textFieldNomeProdutoEscolhido = new JTextField();
		textFieldNomeProdutoEscolhido.setEditable(false);
		textFieldNomeProdutoEscolhido.setColumns(10);
		getContentPane().add(textFieldNomeProdutoEscolhido, "4, 16, 3, 1, fill, default");

		JLabel lblNewLabel_1 = new JLabel("Valor Unitario:");
		getContentPane().add(lblNewLabel_1, "3, 18, left, default");

		textFieldValorUnitario = new JTextField();
		textFieldValorUnitario.setColumns(10);
		textFieldValorUnitario.setEditable(false);
		getContentPane().add(textFieldValorUnitario, "4, 18, left, default");

		JLabel lblQuantidade = new JLabel("Quantidade:");
		getContentPane().add(lblQuantidade, "3, 20, left, default");

		textFieldQuantidadeProduto = new JTextField();
		textFieldQuantidadeProduto.setColumns(10);
		getContentPane().add(textFieldQuantidadeProduto, "4, 20, left, default");

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 0, 2));
		getContentPane().add(separator_1, "3, 22, 4, 1");
								
								JLabel lblFuncionario = new JLabel("Funcionario:");
								getContentPane().add(lblFuncionario, "3, 24, fill, default");
						
								comboBoxFuncionario = new JComboBox<Funcionario>();
								getContentPane().add(comboBoxFuncionario, "4, 24, fill, default");
				
						btnAdicinarProduto = new JButton("Add Produto ");
						btnAdicinarProduto.setEnabled(false);
						getContentPane().add(btnAdicinarProduto, "3, 26, fill, default");
		
				btnFinalizarPedido = new JButton("Finalizar Sa\u00EDda de Produto");
				getContentPane().add(btnFinalizarPedido, "4, 26, center, default");
				btnFinalizarPedido.setEnabled(false);
				
						btnFinalizarPedido.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								finalizarSaida();
							}
						});

		butaoAdiconarProduto();
		montarFuncionario();

		btnNewButtonPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarProdutos();
				montarTable();
			}
		});
		
	}

	private int linha = -1;
	private void getClickLinhaTabela() {
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				linha = table.getSelectedRow();
				montarProdutoPedido();
			}
		});
	}

	private void criaJTable() {
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Nome ", "Valor Unit\u00E1rio", "Quantidade Atual" }));

		table.setEditingColumn(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.setRowHeight(30);
	}

	private void montarProdutoPedido() {
		if (linha != -1) {
			btnAdicinarProduto.setEnabled(true);
			btnFinalizarPedido.setEnabled(true);
			textFieldNomeProdutoEscolhido.setText(table.getModel().getValueAt(linha, 1).toString());
			textFieldValorUnitario.setText(table.getModel().getValueAt(linha, 2).toString());
			textFieldQuantidadeProduto.setText(table.getModel().getValueAt(linha, 3).toString());
		}
	}

	/*
	 * Monta a lista dos produtos e itens para salvar e mostar na tela de
	 * vizualizar lista
	 */
	private void montarListaPedido() {
		Produto produtoPedido = new Produto();
		ItemEstoque item = new ItemEstoque();

		for (int i = 0; i < produtos.size(); i++) {
			if (Integer.parseInt(table.getModel().getValueAt(linha, 0).toString()) == produtos.get(i).getCodProduto()) {
				produtoPedido = produtos.get(i);
				try {
					item =  (ItemEstoque) itens.get(i).clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				item.setQuantideProduto(Integer.parseInt(textFieldQuantidadeProduto.getText().toString()));
			}
		}

		if (produtosSaida.contains(produtoPedido)) {
			new Mensagens("Produto já adicionado na lista");
		} else {
			produtosSaida.add(produtoPedido);
			itensSaida.add(item);
		}

		textFieldNomeProdutoEscolhido.setText("");
		textFieldValorUnitario.setText("");
		textFieldQuantidadeProduto.setText("");
		produtoPedido = null;
		item = null;

	}

	private void butaoAdiconarProduto() {

		btnAdicinarProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int result = JOptionPane.showConfirmDialog(null,
						"Deseja Incluir esse produto?  " + table.getModel().getValueAt(linha, 1).toString(), "Incluir",
						JOptionPane.YES_NO_CANCEL_OPTION);

				if (result == JOptionPane.YES_OPTION)
					montarListaPedido();
			}
		});
	}

	private void consultarProdutos() {
		try {
			estoque = new ProdutoController().readByName(textFieldNomeProduto.getText().toString().trim());
			montarListaProdutos();
		} catch (ControleEstoqueSqlException e) {
			e.printStackTrace();
		}
	}

	private void montarTable() {
		itens = new ArrayList<ItemEstoque>();
		chaves = estoque.getItens().keySet();
		for (Iterator<ItemEstoque> iterator = chaves.iterator(); iterator.hasNext();) {
			ItemEstoque chave = iterator.next();
			if (chave != null)
				itens.add(chave);
		}

		/* Captura o modelo da tabela */
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setNumRows(0);
		/* Copia os dados da consulta para a tabela */
		for (int i = 0; i < produtos.size(); i++) {
			modelo.addRow(new Object[] { produtos.get(i).getCodProduto(), produtos.get(i).getNome(),
					produtos.get(i).getValorUnitario(), itens.get(i).getQuantideProduto() });
		}
	}


	private void finalizarSaida() {
		Saida saida = new Saida();
		saida.setDataSaida(Date.valueOf(LocalDate.now()));
		Funcionario f = (Funcionario) comboBoxFuncionario.getSelectedItem();
		saida.setFuncionario(f);
		
		try {
			saida.setHashSaida(Util.makeSHA1Hash());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		try {

			for (int i = 0; i < itensSaida.size(); i++) {
				for (int j = 0; j < itens.size(); j++) {
					if (itens.get(j).getIdEstoque() == itensSaida.get(i).getIdEstoque()) {
						itensSaida.get(i).setIdProduto(produtosSaida.get(i).getCodProduto());
						int quant = itensSaida.get(i).getQuantideProduto();
						itensSaida.get(i).setQuantideProduto(itens.get(j).getQuantideProduto() - quant);
						new ItemEstoqueController().update(itensSaida.get(i));
					}
				}
			}

			SaidaController saidaController = new SaidaController();
			saidaController.creat(saida);
			saida = saidaController.consultarByHash(saida.getHashSaida());

			if (saida != null) {

				SaidaProduto sp = new SaidaProduto();
				sp.setSaida(saida);
				sp.setProdutos(produtosSaida);
				sp.setQuantProdutos(produtosSaida.size());
				sp.setValorTotal(getValorTotal() * produtosSaida.size());

				new SaidaProdutoController().creat(sp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void montarListaProdutos() {
		produtos = new ArrayList<Produto>();
		chaves = estoque.getItens().keySet();
		for (Iterator<ItemEstoque> iterator = chaves.iterator(); iterator.hasNext();) {
			ItemEstoque chave = iterator.next();
			if (chave != null)
				produtos.add(estoque.getItens().get(chave));
		}
	}

	private Double getValorTotal() {
		Double valorTotal = 0D;
		for (Produto p : produtosSaida) {
			valorTotal += p.getValorUnitario();
		}
		return valorTotal;
	}

	
	
	/* Preenche o combo com os fornecedores cadastrados */
	private void montarFuncionario() {
		funcionarios = new ArrayList<Funcionario>();

		try {
			funcionarios = new FuncionarioController().listarTodos();
		} catch (ControleEstoqueSqlException e1) {
			e1.printStackTrace();
		}

		for (Funcionario funci : funcionarios) {
			comboBoxFuncionario.addItem(funci);
		}

	}
}
