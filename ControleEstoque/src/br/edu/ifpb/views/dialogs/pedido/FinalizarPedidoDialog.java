package br.edu.ifpb.views.dialogs.pedido;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import br.edu.ifpb.controllers.FornecedorController;
import br.edu.ifpb.controllers.PedidoController;
import br.edu.ifpb.entidades.Fornecedor;
import br.edu.ifpb.entidades.Pedido;
import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;

public class FinalizarPedidoDialog  extends javax.swing.JDialog{
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private List<Produto> produtos  = new ArrayList<Produto>();
	private List<Fornecedor> fornecedores  = new ArrayList<Fornecedor>();
	private JComboBox<Fornecedor> comboBoxForncedor;
	/**
	 * Create the application.
	 */
	public FinalizarPedidoDialog(JFrame frame, List<Produto> produtos) {
		super(frame, true);
		this.produtos = produtos;
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
		
		JLabel lblCadastroProdutos = new JLabel("Lista de Produto no Pedido");
		lblCadastroProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroProdutos.setBackground(Color.BLACK);
		lblCadastroProdutos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblCadastroProdutos, "3, 2, 2, 1, fill, top");

		JPanel painelFundo = new JPanel();
		painelFundo.setSize(500, 500);
        painelFundo.setLayout(new GridLayout(1, 1));
        
        JLabel lblFornecedor = new JLabel("Fornecedor:");
        getContentPane().add(lblFornecedor, "3, 12, fill, default");
 
        comboBoxForncedor = new JComboBox<Fornecedor>();
		getContentPane().add(comboBoxForncedor, "4, 12, fill, default");
       
		getContentPane().add(painelFundo, "3, 6, 5, 5, fill, fill");
		
		criaJTable();

		JScrollPane barraRolagem = new JScrollPane(table);
		barraRolagem.setSize(500, 500);
		painelFundo.add(barraRolagem);
		
		JButton btnSalvarPedido = new JButton("Salvar Pedido");
		getContentPane().add(btnSalvarPedido, "4, 16");
		
		btnSalvarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				montaObjeto();
				dispose();
			}
		});
		
		
		montarFornecedores();
		
	}
	
    private void criaJTable() {

    	table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Nome ", "Valor Unit\u00E1rio", "Quantidade Atual"}));

		table.setEditingColumn(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);


		table.setRowHeight(30);

		/* Captura o modelo da tabela */
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setNumRows(0);
		/* Copia os dados da consulta para a tabela */
		for (Produto prod : produtos) {
			modelo.addRow(new Object[] { prod.getCodProduto(), prod.getNome(), prod.getValorUnitario(),
					prod.getQuantideAtual() });
		}
    }

	
	/* Preenche o combo com os fornecedores cadastrados */
	private void montarFornecedores() {
		fornecedores = new ArrayList<Fornecedor>();

		try {
			fornecedores = new FornecedorController().listarTodos();
		} catch (ControleEstoqueSqlException e1) {
			e1.printStackTrace();
		}

		for (Fornecedor fornecedor : fornecedores) {
			comboBoxForncedor.addItem(fornecedor);
		}

	}
	
	private void montaObjeto(){
		Pedido pedido  = new Pedido();
		pedido.setDataPedido(Date.valueOf(LocalDate.now()));
		Fornecedor fornecedor = (Fornecedor) comboBoxForncedor.getSelectedItem();
		//pedido.setFornecedor(fornecedor);
		List<Pedido> pedidos = new ArrayList<Pedido>();
		
		try {
			new PedidoController().creat(pedido);
			pedidos = new PedidoController().listarTodos();
			
			if(pedidos != null){
				
				for (Produto pro : produtos) {
					
//					PedidosFeitos pf = new PedidosFeitos();
//					pf.setCodPedido(pedidos.get(pedidos.size()-1).getCodPedido());
//					pf.setCodProduto(pro.getCodProduto());
//					new PedidosFeitosController().creat(pf);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
