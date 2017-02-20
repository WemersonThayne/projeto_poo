package br.edu.ifpb.frames.dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import com.sun.glass.events.MouseEvent;

import br.edu.ifpb.utils.Mensagens;

public class FazerPedidoDialog  extends JDialog{

	/**
	 * cadastro
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNomeProduto;
	private JTable table;

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
				RowSpec.decode("default:grow"),}));
		
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


		String [] colunas = {"Nome do Prodtuo", "Valor Unitario", "Quantidade Atual"," Novo Pedido"};
		
		Object [][] dados = {
				{"Computador", "2000,00 R$", "5","+"},
				{"Fonte", "100,00 R$", "2","+","-"},
				{"Moto x player", "2000,00 R$", "5","+"},
				{"Teste1", "10,00 R$", "5","+"},
				{"Teste1", "10,00 R$", "5","+"},
				{"Teste2", "10,00 R$", "5","+"},
				{"Teste3", "10,00 R$", "5","+"},
				{"Teste4", "10,00 R$", "5","+"},
				{"Teste5", "10,00 R$", "5","+"}
			};
		
		JPanel painelFundo = new JPanel();
		painelFundo.setSize(200, 800);
        painelFundo.setLayout(new GridLayout(1, 1));
 
       
		getContentPane().add(painelFundo, "3, 8, 5, 5, fill, fill");
		table = new JTable(dados,colunas);
		table.setCellSelectionEnabled(true);
		table.setEnabled(false);
		table.setBorder(null);
		JScrollPane barraRolagem = new JScrollPane(table);
		barraRolagem.setSize(200, 400);
		painelFundo.add(barraRolagem);
		getClickLinhaTabela();
	}
	
	private void getClickLinhaTabela(){
		table.addMouseListener(new MouseAdapter(){
            private int linha;
            public void mouseClicked(MouseEvent e) {  
               
                    linha = table.getSelectedRow();  
                    
                new Mensagens("Linha: "+ linha);  
            }  
        });
		
	}
}
