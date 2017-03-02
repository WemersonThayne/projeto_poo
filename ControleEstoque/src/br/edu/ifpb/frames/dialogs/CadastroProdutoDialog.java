package br.edu.ifpb.frames.dialogs;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class CadastroProdutoDialog  extends javax.swing.JDialog{

	/**
	 * cadastro de produto 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNomeProduto;
	private JTextField textFieldValorUnitario;
	private JTextField textFieldQuantidadeProduto;
	private JButton btnNewButtonNovoProduto;

	/**
	 * Create the application.
	 */
	public CadastroProdutoDialog(JFrame frame) {
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
				ColumnSpec.decode("113px"),
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(66dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblCadastroProdutos = new JLabel("Cadastro Produtos");
		lblCadastroProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroProdutos.setBackground(Color.BLACK);
		lblCadastroProdutos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblCadastroProdutos, "4, 2, center, top");
		
		JLabel lblNewLabel = new JLabel("Produto:");
		getContentPane().add(lblNewLabel, "3, 6, left, default");
		
		textFieldNomeProduto = new JTextField();
		getContentPane().add(textFieldNomeProduto, "4, 6, fill, default");
		textFieldNomeProduto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Valor Unitario:");
		getContentPane().add(lblNewLabel_1, "3, 8, left, default");
		
		textFieldValorUnitario = new JTextField();
		getContentPane().add(textFieldValorUnitario, "4, 8, left, default");
		textFieldValorUnitario.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		getContentPane().add(lblQuantidade, "3, 10, left, default");
		
		textFieldQuantidadeProduto = new JTextField();
		getContentPane().add(textFieldQuantidadeProduto, "4, 10, left, default");
		textFieldQuantidadeProduto.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		getContentPane().add(lblCategoria, "3, 12, left, default");
		
		JComboBox<String> comboBoxCategoria = new JComboBox<String>();
		getContentPane().add(comboBoxCategoria, "4, 12, fill, default");
		
		btnNewButtonNovoProduto = new JButton("Novo Produto");
		getContentPane().add(btnNewButtonNovoProduto, "3, 14, center, default");
		
		JButton btnNewButtonSalvar = new JButton("Salvar");
		getContentPane().add(btnNewButtonSalvar, "4, 14, center, default");
		
		JButton btnNewButtonLimpar = new JButton("Limpar");
		btnNewButtonLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		getContentPane().add(btnNewButtonLimpar, "6, 14, center, default");
		//TODO: chamar o serviço de categorias
		String[] categoria = {"Selecione...","Categoria1", "Categoria2"};
		comboBoxCategoria.addItem(categoria[0]);
		comboBoxCategoria.addItem(categoria[1]);
		comboBoxCategoria.addItem(categoria[2]);
		
	}
	
}
