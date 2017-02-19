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
	private JButton btnNewButtonNovo;

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
		setLayout(new FormLayout(new ColumnSpec[] {
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
		add(lblCadastroProdutos, "4, 2, center, top");
		
		JLabel lblNewLabel = new JLabel("Produto:");
		add(lblNewLabel, "3, 6, left, default");
		
		textFieldNomeProduto = new JTextField();
		add(textFieldNomeProduto, "4, 6, fill, default");
		textFieldNomeProduto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Valor Unitario:");
		add(lblNewLabel_1, "3, 8, left, default");
		
		textFieldValorUnitario = new JTextField();
		add(textFieldValorUnitario, "4, 8, left, default");
		textFieldValorUnitario.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		add(lblQuantidade, "3, 10, left, default");
		
		textFieldQuantidadeProduto = new JTextField();
		add(textFieldQuantidadeProduto, "4, 10, left, default");
		textFieldQuantidadeProduto.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		add(lblCategoria, "3, 12, left, default");
		
		JComboBox<String> comboBoxCategoria = new JComboBox<String>();
		add(comboBoxCategoria, "4, 12, fill, default");
		
		btnNewButtonNovo = new JButton("Novo Produto");
		add(btnNewButtonNovo, "3, 14, center, default");
		
		JButton btnNewButtonSalvar = new JButton("Salvar");
		add(btnNewButtonSalvar, "4, 14, center, default");
		
		JButton btnNewButtonLimpar = new JButton("Limpar");
		btnNewButtonLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnNewButtonLimpar, "6, 14, center, default");
		//TODO: chamar o serviço de categorias
		String[] categoria = {"Selecione...","Categoria1", "Categoria2"};
		comboBoxCategoria.addItem(categoria[0]);
		comboBoxCategoria.addItem(categoria[1]);
		comboBoxCategoria.addItem(categoria[2]);
		
	}
	
}
