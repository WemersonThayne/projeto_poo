package br.edu.ifpb.frames.dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.edu.ifpb.entidades.CategoriaProduto;
import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.frames.CadastroUsuarioFrame;
import br.edu.ifpb.utils.Mensagens;

public class FazerPedidoDialog  extends JDialog{

	/**
	 * cadastro
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNomeProdutoPesquisa;
	private JTable table;

	private JButton btnFinalizarPedido;
	private JButton btnAdicinarProduto;
	private JButton btnVisualiarLista;
	
	private JTextField textFieldNomeProduto;
	private JTextField textFieldNomeProdutoEscolhido;
	private JTextField textFieldValorUnitario;
	private JTextField textFieldQuantidadeProduto;
	
	
	private List<Produto> produtos = new ArrayList<Produto>();
	/**
	 * Create the application.
	 */
	public FazerPedidoDialog(JFrame frame) {
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

		JScrollPane barraRolagem = new JScrollPane(table);
		barraRolagem.setSize(200, 400);
		painelFundo.add(barraRolagem);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(2));
		getContentPane().add(separator, "3, 14, 4, 1, fill, default");
		
		getClickLinhaTabela();
		
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

		btnAdicinarProduto = new JButton("Adicinar Produto na Lista");
		btnAdicinarProduto.setEnabled(false);
		getContentPane().add(btnAdicinarProduto, "4, 24, center, default");
	
		btnVisualiarLista = new JButton("Visualiar Lista");
		getContentPane().add(btnVisualiarLista, "6, 24");
		
		butaoAdiconarProduto();
		
	}
	
	private int linha = -1;
	private void getClickLinhaTabela(){
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	linha = table.getSelectedRow();
		    	montarProdutoPedido();
		    }
		});
	}
	
    private void criaJTable() {

    	 Object [] colunas = {"Nome do Prodtuo", "Valor Unitario", "Quantidade Atual"};

    	 Object [][] dados = {
    			{"Computador", "2000.00", "5"},
    			{"Fonte", "100.00", "2",},
    			{"Moto x player", "2000.00", "5"},
    			{"Teste1", "10.00", "5"},
    			{"Teste1", "10.00", "5"},
    			{"Teste2", "10.00", "5"},
    			{"Teste3", "10.00", "5"},
    			{"Teste4", "10.00", "5"},
    			{"Teste5", "10.00", "5"}
    		};
    	
    	table = new JTable(dados,colunas);
    	
    	table.setEditingColumn(0);
    	table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
       
    }
    
    private void montarProdutoPedido(){
    	if(linha != -1){
    		btnAdicinarProduto.setEnabled(true);
    		textFieldNomeProdutoEscolhido.setText(table.getModel().getValueAt(linha, 0).toString());
    		textFieldValorUnitario.setText(table.getModel().getValueAt(linha, 1).toString());
    		textFieldQuantidadeProduto.setText(table.getModel().getValueAt(linha, 2).toString());
    	}
    }
    
    private void montarListaPedido(){
    	Produto produto = new Produto();
    	produto.setNome(textFieldNomeProdutoEscolhido.getText().toString());
    	produto.setQuantideAtual(Integer.parseInt(textFieldQuantidadeProduto.getText().toString()));
    	produto.setValorUnitario(Double.parseDouble(textFieldValorUnitario.getText().toString()));
    	produto.setCategoria(new CategoriaProduto());

		System.out.println(produtos.contains(produto));
		
		if(produtos.contains(produto)){
	    	new Mensagens("Produto já adicionado na lista");
	    }else{
	        produtos.add(produto);
	    }
			
    	textFieldNomeProdutoEscolhido.setText("");
		textFieldValorUnitario.setText("");
		textFieldQuantidadeProduto.setText("");
    }
    
    private void butaoAdiconarProduto(){

		btnAdicinarProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
		    	int result = JOptionPane.showConfirmDialog(null,"Deseja Incluir esse produto?  "+table.getModel().getValueAt(linha, 0).toString(),"Incluir",JOptionPane.YES_NO_CANCEL_OPTION);   
				  
				if(result ==JOptionPane.YES_OPTION)  
					montarListaPedido();
				
			}
		});
    }
}
