
package br.edu.ifpb.views.dialogs.produto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

import br.edu.ifpb.controllers.CategoriaController;
import br.edu.ifpb.controllers.FornecedorController;
import br.edu.ifpb.controllers.ProdutoController;
import br.edu.ifpb.entidades.CategoriaProduto;
import br.edu.ifpb.entidades.Fornecedor;
import br.edu.ifpb.entidades.Produto;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.utils.Mensagens;
import br.edu.ifpb.utils.Util;

public class CadastroProdutoDialog extends javax.swing.JDialog {

	/**
	 * cadastro de produto
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNomeProduto;
	private JTextField textFieldValorUnitario;
	private JTextField textFieldQuantidadeProduto;

	private JComboBox<CategoriaProduto> comboBoxCategoria;
	private JComboBox<Fornecedor> comboBoxForncedor;


	private List<CategoriaProduto> categorias = null;
	private List<Fornecedor> fornecedores = null;

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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(66dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
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
		
				comboBoxCategoria = new JComboBox<CategoriaProduto>();
				getContentPane().add(comboBoxCategoria, "4, 12, fill, default");
		
		JLabel lblFornecedor = new JLabel("Fornecedor:");
		getContentPane().add(lblFornecedor, "3, 14, left, default");

		comboBoxForncedor = new JComboBox<Fornecedor>();
		getContentPane().add(comboBoxForncedor, "4, 14, fill, default");
			
		JButton btnNewButtonSalvar = new JButton("Salvar");
		getContentPane().add(btnNewButtonSalvar, "4, 16, center, center");

		btnNewButtonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				montaObjeto();
			}
		});

		JButton btnNewButtonLimpar = new JButton("Limpar");
		btnNewButtonLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		getContentPane().add(btnNewButtonLimpar, "6, 16, center, default");

		montarCategoria();
		montarFornecedores();
	}

	/* Preenche o combo com os departamentos */
	private void montarCategoria() {
		categorias = new ArrayList<CategoriaProduto>();

		try {
			categorias = new CategoriaController().listarTodos();
		} catch (ControleEstoqueSqlException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (CategoriaProduto categoriaProduto : categorias) {
			comboBoxCategoria.addItem(categoriaProduto);
		}

	}

	/* Metodo de limpar todos os campos */
	private void limparCampos() {
		comboBoxCategoria.setSelectedIndex(0);
		textFieldNomeProduto.setText("");
		textFieldQuantidadeProduto.setText("");
		textFieldValorUnitario.setText("");
	}

	/* Valida todos os campos */
	private boolean validarAll() {
		if (Util.validarCampos(textFieldNomeProduto) && Util.validarCampos(textFieldQuantidadeProduto)
				&& Util.validarCampos(textFieldValorUnitario)) {
			return true;
		} else {
			new Mensagens(Util.VALOR_INSERIDOS_INVALIDOS);
			return false;
		}
	}

	/* Monta o objeto para salvar no banco */
	private void montaObjeto() {
		if(validarAll()){
			CategoriaProduto cat = (CategoriaProduto) comboBoxCategoria.getSelectedItem();
			Fornecedor f = (Fornecedor) comboBoxForncedor.getSelectedItem();
			
			Produto produto = new Produto();
			produto.setCategoria(cat);
			produto.setFornecedor(f);
			produto.setNome(textFieldNomeProduto.getText().toString());
			produto.setValorUnitario(Double.parseDouble(textFieldValorUnitario.getText().toString().replaceAll(",", ".")));
			try {
				new ProdutoController().creat(produto,Integer.parseInt(textFieldQuantidadeProduto.getText().toString()));
			} catch (ControleEstoqueSqlException e) {
				e.printStackTrace();
			}
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
	
}
