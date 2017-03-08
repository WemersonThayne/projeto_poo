package br.edu.ifpb.frames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.edu.ifpb.controllers.DepartamentoController;
import br.edu.ifpb.controllers.FornecedorController;
import br.edu.ifpb.controllers.FuncionarioController;
import br.edu.ifpb.entidades.Departamento;
import br.edu.ifpb.entidades.Fornecedor;
import br.edu.ifpb.entidades.Funcionario;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.exceptions.MensagenException;
import br.edu.ifpb.utils.Mensagens;
import br.edu.ifpb.utils.Util;

public class CadastroUsuarioFrame  {

	private JFrame frmCadastroDeUsurio;
	private JTextField textFieldNomeCompleto;
	private JLabel lblNewLabel;
	private JTextField textFieldEndereco;
	private JLabel lblNewLabel_1;
	private JFormattedTextField textFieldCPF;
	private JLabel lblNewLabel_2;
	private JFormattedTextField textFieldDtNascimento;
	private JLabel lblNewLabel_3;
	private JTextField textFieldEmail;
	private JLabel lblNewLabel_4;
	private JFormattedTextField textFieldTelefone;
	private JLabel lblTipoDeUsurio;
	private JComboBox<String> comboBoxTipoUsuario;
	private JSeparator separator;
	private JLabel lblDepartamento;
	private JComboBox<Departamento> comboBoxDepartamentos;
	private JSeparator separator_1;
	private JLabel lblNewLabel_6NomeLoja;
	private JTextField textFieldNomeLoja;
	private JSeparator separator_2;
	private JButton btnConfirmar;
	private JButton btnLimpar;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_5;
	private JProgressBar progressBar;
	private JLabel lblLogin;
	private JTextField textFieldLogin;
	
	List<Departamento> departamentos  = null;
	
	/*
	  Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuarioFrame window = new CadastroUsuarioFrame();
					window.frmCadastroDeUsurio.setVisible(true);
					window.frmCadastroDeUsurio.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Create the application.
	 */
	public CadastroUsuarioFrame() {
		initialize();
	}

	/*
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastroDeUsurio = new JFrame();
		frmCadastroDeUsurio.setTitle("Cadastro de Usu\u00E1rio");
		frmCadastroDeUsurio.setBounds(100, 100, 547, 501);
		frmCadastroDeUsurio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeUsurio.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(188dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNomeCompleto = new JLabel("Nome Completo:");
		frmCadastroDeUsurio.getContentPane().add(lblNomeCompleto, "4, 4, left, default");
		
		textFieldNomeCompleto = new JTextField();
		frmCadastroDeUsurio.getContentPane().add(textFieldNomeCompleto, "6, 4, default, center");
		textFieldNomeCompleto.setColumns(10);
		
		lblNewLabel = new JLabel("Endere\u00E7o:");
		frmCadastroDeUsurio.getContentPane().add(lblNewLabel, "4, 6, left, default");
		
		textFieldEndereco = new JTextField();
		frmCadastroDeUsurio.getContentPane().add(textFieldEndereco, "6, 6, fill, default");
		textFieldEndereco.setColumns(10);
		
		lblNewLabel_1 = new JLabel("CPF:");
		frmCadastroDeUsurio.getContentPane().add(lblNewLabel_1, "4, 8, left, default");
		
		textFieldCPF = new JFormattedTextField();
		frmCadastroDeUsurio.getContentPane().add(textFieldCPF, "6, 8, fill, default");
		textFieldCPF.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Data de Nascimento:");
		frmCadastroDeUsurio.getContentPane().add(lblNewLabel_2, "4, 10, left, default");

		textFieldDtNascimento = new JFormattedTextField();
		frmCadastroDeUsurio.getContentPane().add(textFieldDtNascimento, "6, 10, left, default");
		textFieldDtNascimento.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Email:");
		frmCadastroDeUsurio.getContentPane().add(lblNewLabel_3, "4, 12, left, default");
		
		textFieldEmail = new JTextField();
		frmCadastroDeUsurio.getContentPane().add(textFieldEmail, "6, 12, fill, default");
		textFieldEmail.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Telefone:");
		frmCadastroDeUsurio.getContentPane().add(lblNewLabel_4, "4, 14, left, default");
		
		textFieldTelefone = new JFormattedTextField();
		frmCadastroDeUsurio.getContentPane().add(textFieldTelefone, "6, 14, left, default");
		textFieldTelefone.setColumns(10);
		
		lblTipoDeUsurio = new JLabel("Tipo de Usu\u00E1rio:");
		frmCadastroDeUsurio.getContentPane().add(lblTipoDeUsurio, "4, 16, left, default");
		
		String[] tiposUsuarios = {"Selecione...","Funcionario", "Fornecedor"};
		
		comboBoxTipoUsuario = new JComboBox<String>();
		comboBoxTipoUsuario.addItem(tiposUsuarios[0]);
		comboBoxTipoUsuario.addItem(tiposUsuarios[1]);
		comboBoxTipoUsuario.addItem(tiposUsuarios[2]);
		mudaTipoUsuario();
		frmCadastroDeUsurio.getContentPane().add(comboBoxTipoUsuario, "6, 16, fill, default");
		
		separator = new JSeparator();
		frmCadastroDeUsurio.getContentPane().add(separator, "6, 18");
		
		lblDepartamento = new JLabel("Departamento:");
		frmCadastroDeUsurio.getContentPane().add(lblDepartamento, "4, 22, right, default");
		
		comboBoxDepartamentos = new JComboBox<Departamento>();
	
		
		frmCadastroDeUsurio.getContentPane().add(comboBoxDepartamentos, "6, 22, fill, default");
		
		separator_1 = new JSeparator();
		frmCadastroDeUsurio.getContentPane().add(separator_1, "6, 24");
		
		lblNewLabel_6NomeLoja = new JLabel("Nome da Loja:");
		frmCadastroDeUsurio.getContentPane().add(lblNewLabel_6NomeLoja, "4, 26, right, default");
		
		textFieldNomeLoja = new JTextField();
		frmCadastroDeUsurio.getContentPane().add(textFieldNomeLoja, "6, 26, fill, default");
		textFieldNomeLoja.setColumns(10);
		
		separator_2 = new JSeparator();
		frmCadastroDeUsurio.getContentPane().add(separator_2, "6, 28");
		
		lblLogin = new JLabel("Login:");
		frmCadastroDeUsurio.getContentPane().add(lblLogin, "4, 30, left, default");
		
		textFieldLogin = new JTextField();
		frmCadastroDeUsurio.getContentPane().add(textFieldLogin, "6, 30, fill, default");
		textFieldLogin.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Senha:");
		frmCadastroDeUsurio.getContentPane().add(lblNewLabel_5, "4, 32, left, default");
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		frmCadastroDeUsurio.getContentPane().add(passwordField, "6, 32, left, default");
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  if (roda == null) {
		    		  progressBar.setVisible(true);
                      roda = new roda();
                      roda.start(); 
                    }
		      }});
		frmCadastroDeUsurio.getContentPane().add(btnConfirmar, "6, 34");
		
		btnLimpar = new JButton("Limpar");
		//chamar e execulta o metodo limparCampos
		
		btnLimpar.addActionListener(new ActionListener() {
		    	  public void actionPerformed(ActionEvent e) { 
		    	  limparCampos();
                   
		}});
		
		frmCadastroDeUsurio.getContentPane().add(btnLimpar, "6, 36");
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setVisible(false);
		
		frmCadastroDeUsurio.getContentPane().add(progressBar, "6, 38");
		lblDepartamento.setVisible(false);
		comboBoxDepartamentos.setVisible(false);

		lblNewLabel_6NomeLoja.setVisible(false);
		textFieldNomeLoja.setVisible(false);
		lblDepartamento.setVisible(false);
		comboBoxDepartamentos.setVisible(false);
		
		try {
			Util.montarMascara(textFieldCPF,"###.###.###-##");
			Util.montarMascara(textFieldDtNascimento,"##/##/####");
			Util.montarMascara(textFieldTelefone,"(##)#####-####");
			
		} catch (MensagenException e1) {
			e1.printStackTrace();
		}
	}
	
	
	/*Metodo responsavel por escutar os click e a mudança de usuario*/
	private void mudaTipoUsuario(){
	
		comboBoxTipoUsuario.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  
					if(comboBoxTipoUsuario.getSelectedIndex() == 0){

						//FUNCIONARIO
						lblDepartamento.setVisible(false);
						comboBoxDepartamentos.setVisible(false);

						//FORNECEDOR
						lblNewLabel_6NomeLoja.setVisible(false);
						textFieldNomeLoja.setVisible(false);
						
						
					}else{
						if(comboBoxTipoUsuario.getSelectedIndex() == 1){
							//FUNCIONARIO
							
							lblDepartamento.setVisible(true);
							comboBoxDepartamentos.setVisible(true);
							monstarDepartamentos();
							
							//FORNECEDOR
							lblNewLabel_6NomeLoja.setVisible(false);
							textFieldNomeLoja.setVisible(false);
							
						}else{
							if(comboBoxTipoUsuario.getSelectedIndex() == 2){
								
								//FORNECEDOR
								lblNewLabel_6NomeLoja.setVisible(true);
								textFieldNomeLoja.setVisible(true);
							
								//FUNCIONARIO
								lblDepartamento.setVisible(false);
								comboBoxDepartamentos.setVisible(false);
							}
						}	
					}

		      }
		    });
	}
	
	/*Preenche o combo com os departamentos*/
	private void monstarDepartamentos(){
		departamentos = new ArrayList<Departamento>();
		
		try {
			departamentos = new DepartamentoController().listarTodos();
		} catch (ControleEstoqueSqlException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (Departamento departamento : departamentos) {
			comboBoxDepartamentos.addItem(departamento);	
		}

	}

	/*Metodo de limpar todos os campos*/
	private void limparCampos(){
		comboBoxTipoUsuario.setSelectedIndex(0);
		comboBoxDepartamentos.setSelectedIndex(0);
		textFieldCPF.setText("");
		textFieldDtNascimento.setText("");
		textFieldEmail.setText("");
		textFieldEndereco.setText("");
		textFieldNomeCompleto.setText("");
		textFieldNomeLoja.setText("");
		textFieldTelefone.setText("");
		passwordField.setText("");
	}
	
	/*Valida todos os campos*/
	private boolean validarAll(){
		if(Util.validarCampos(textFieldNomeCompleto) 
				&& Util.validarCampos(textFieldEndereco)
				   && Util.validarCampos(textFieldCPF)
					 && Util.validarCampos(textFieldDtNascimento)
					    && Util.validarCampos(textFieldEmail)
					       && Util.validarCampos(textFieldTelefone)
					          && Util.validarCampos(textFieldLogin)
					          	 && Util.validarCamposSenha(passwordField)
						          	    && Util.validarCamposComboTipoUsuario(comboBoxTipoUsuario)){
			return true;
				
			}else{
				new Mensagens(Util.VALOR_INSERIDOS_INVALIDOS);
				return false;
			}
	}
	
	/*Monta o objeto para salvar no banco*/
	private void montaObjeto(){
		
		int tipoPessoa = comboBoxTipoUsuario.getSelectedIndex();
			
			if(tipoPessoa == 1){
				try {
					new FuncionarioController().creat(montaFuncionario());
				} catch (ControleEstoqueSqlException e) {
					e.printStackTrace();
				}
			}else{
				try {
					new FornecedorController().creat(montaFornecedor());
				} catch (ControleEstoqueSqlException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	/*Criar um objeto do tipo Funcionario*/
	private Funcionario montaFuncionario(){
		
		Departamento departamento = (Departamento) comboBoxDepartamentos.getSelectedItem();
		Funcionario  funcionario = new Funcionario();
		funcionario.setNome(textFieldNomeCompleto.getText().toString());
		funcionario.setCpf(textFieldCPF.getText().toString());
		funcionario.setDataNascimento(textFieldDtNascimento.getText().toString());
		funcionario.setDepartamento(departamento);
		funcionario.setEmail(textFieldEmail.getText().toString());
		funcionario.setEndereco(textFieldEndereco.getText().toString());
		funcionario.setLogin(textFieldLogin.getText().toString());
		funcionario.setSenha(passwordField.getText().toString());		
		funcionario.setTelefone(textFieldTelefone.getText().toString());
		
		return funcionario;
	}
	
	/*Criar um objeto do tipo Fornecedor*/
	private Fornecedor montaFornecedor(){
		
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome(textFieldNomeCompleto.getText().toString());
		fornecedor.setCpf(textFieldCPF.getText().toString());
		fornecedor.setDataNascimento(textFieldDtNascimento.getText().toString());
		fornecedor.setEmail(textFieldEmail.getText().toString());
		fornecedor.setEndereco(textFieldEndereco.getText().toString());
		fornecedor.setLogin(textFieldLogin.getText().toString());
		fornecedor.setSenha(passwordField.getText().toString());		
		fornecedor.setTelefone(textFieldTelefone.getText().toString());
		fornecedor.setNomeLoja(textFieldNomeLoja.getText().toString());
		
		return fornecedor;
	}

	// Define uma Thread para simular rodando
	private Thread roda;
	
	
	/*Class interna*/
	  class roda extends Thread { 
		    public void run() {
		      // Cria um objeto para atualizar a Barra
		      Runnable runner = new Runnable() {
		        public void run() {
		          // Obtém o resultado atual da Barra
		          int valor = progressBar.getValue();
		          // Atualiza a Barra
		          progressBar.setValue(valor+1);
		        }
		      };
		      for (int i = 0; i <= 3000; i++) {
		    	  if(i == 3000){
		    		  if(validarAll()){
		    			  montaObjeto();
		    			  frmCadastroDeUsurio.dispose();
			              new Mensagens("Cadastro Efetuado com Sucesso...");
			              LoginFrame.main(null); 
		    		  }else{
		    			  progressBar.setVisible(false);
		    		  }
		    	  }
		    	  
		        // Atualiza a Barra de Progresso
		        try {
		          SwingUtilities.invokeAndWait(runner); 
		        } catch (java.lang.reflect.InvocationTargetException e) { 
		          break; 
		        } catch (InterruptedException e) {
		        }
		      }
		     roda = null; 
		    }     
	}
	 
}
