package br.edu.ifpb.views.dialogs.pedido;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
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

import br.edu.ifpb.controllers.ItemEstoqueController;
import br.edu.ifpb.controllers.PedidoController;
import br.edu.ifpb.controllers.PedidoProdutoController;
import br.edu.ifpb.controllers.ProdutoController;
import br.edu.ifpb.entidades.Estoque;
import br.edu.ifpb.entidades.ItemEstoque;
import br.edu.ifpb.entidades.Pedido;
import br.edu.ifpb.entidades.PedidoProduto;
import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.Mensagens;

public class FazerPedidoDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JFrame frame;
	private JButton btnFinalizarPedido;
	private JButton btnAdicinarProduto;
	private JButton btnVisualiarLista;

	private JTextField textFieldNomeProduto;
	private JTextField textFieldNomeProdutoEscolhido;
	private JTextField textFieldValorUnitario;
	private JTextField textFieldQuantidadeProduto;

	private List<Produto> produtos = new ArrayList<Produto>();
	private List<Produto> produtosPedido = new ArrayList<Produto>();
	private Estoque estoque;
	private Set<ItemEstoque> chaves = new HashSet<ItemEstoque>();
	private List<ItemEstoque> itens = new ArrayList<ItemEstoque>();
	private List<ItemEstoque> itensPedido = new ArrayList<ItemEstoque>();

	/**
	 * Create the application.
	 */
	public FazerPedidoDialog(JFrame frame) {
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

		JLabel lblCadastroProdutos = new JLabel("Fazer Pedido");
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

		btnFinalizarPedido = new JButton("Finalizar Pedido");
		getContentPane().add(btnFinalizarPedido, "3, 24, center, default");
		btnFinalizarPedido.setEnabled(false);

		btnFinalizarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finalizarPedido();
			}
		});

		btnAdicinarProduto = new JButton("Adicinar Produto na Lista");
		btnAdicinarProduto.setEnabled(false);
		getContentPane().add(btnAdicinarProduto, "4, 24, center, default");

		btnVisualiarLista = new JButton("Visualiar Lista");
		getContentPane().add(btnVisualiarLista, "6, 24");

		btnVisualiarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarListaVisualizacao(produtosPedido, itensPedido);
			}
		});
		butaoAdiconarProduto();

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
				item = itens.get(i);
				item.setQuantideProduto(Integer.parseInt(textFieldQuantidadeProduto.getText().toString()));
			}
		}

		if (produtosPedido.contains(produtoPedido)) {
			new Mensagens("Produto já adicionado na lista");
		} else {
			produtosPedido.add(produtoPedido);
			itensPedido.add(item);
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

	private void mostrarListaVisualizacao(List<Produto> produtos, List<ItemEstoque> itens) {
		VisualizarListaProdutoPedidoDialog visualizarLista = new VisualizarListaProdutoPedidoDialog(frame, produtos,
				itens);
		visualizarLista.setBounds(100, 100, 400, 300);
		visualizarLista.setTitle("Resumo do Pedido");
		visualizarLista.setLocationRelativeTo(null);
		visualizarLista.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		visualizarLista.setVisible(true);
	}

	private void finalizarPedido() {
		Pedido pedido = new Pedido();
		pedido.setDataPedido(Date.valueOf(LocalDate.now()));
		try {
			pedido.setHashPedido(makeSHA1Hash());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			for (int i = 0; i < itensPedido.size(); i++) {
				itensPedido.get(i).setIdProduto(produtosPedido.get(i).getCodProduto());
				if (itens.get(i).getIdEstoque() == itensPedido.get(i).getIdEstoque()) {
					int quant = itensPedido.get(i).getQuantideProduto();
					itensPedido.get(i).setQuantideProduto(quant + itens.get(i).getQuantideProduto());
				}
				new ItemEstoqueController().update(itensPedido.get(i));
			}

			new PedidoController().creat(pedido);
			pedido = new PedidoController().consultarByHash(pedido.getHashPedido());

			if (pedido != null) {

				PedidoProduto pp = new PedidoProduto();
				pp.setPedido(pedido);
				pp.setProdutos(produtosPedido);
				pp.setQuantProdutos(produtosPedido.size());
				pp.setValorTotal(getValorTotal() * produtosPedido.size());

				new PedidoProdutoController().creat(pp);
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
		for (Produto p : produtosPedido) {
			valorTotal += p.getValorUnitario();
		}
		return valorTotal;
	}

	public String makeSHA1Hash() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA1");
		md.reset();
		byte[] buffer = String.valueOf(Calendar.getInstance().getTimeInMillis()).getBytes("UTF-8");
		md.update(buffer);
		byte[] digest = md.digest();

		String hexStr = "";
		for (int i = 0; i < digest.length; i++) {
			hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
		}
		return hexStr;
	}
}
