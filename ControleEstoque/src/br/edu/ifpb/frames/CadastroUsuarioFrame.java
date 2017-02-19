package br.edu.ifpb.frames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class CadastroUsuarioFrame  {

	private JFrame frmCadastroDeUsurio;
	private JTextField textFieldNomeCompleto;
	private JLabel lblNewLabel;
	private JTextField textFieldEndereco;
	private JLabel lblNewLabel_1;
	private JTextField textFieldCPF;
	private JLabel lblNewLabel_2;
	private JTextField textFieldDtNascimento;
	private JLabel lblNewLabel_3;
	private JTextField textFieldEmail;
	private JLabel lblNewLabel_4;
	private JTextField textFieldTelefone;
	private JLabel lblTipoDeUsurio;
	private JComboBox<String> comboBoxTipoUsuario;
	private JSeparator separator;
	private JLabel lblNewLabel_5Matricula;
	private JTextField textFieldMatriculaFuncinario;
	private JLabel lblDepartamento;
	private JComboBox<String> comboBoxDepartamentos;
	private JSeparator separator_1;
	private JLabel lblNewLabel_6NomeLoja;
	private JTextField textFieldNomeLoja;
	private JSeparator separator_2;
	private JButton btnConfirmar;
	private JButton btnLimpar;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_5;
	private JProgressBar progressBar;

	/*
	  Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuarioFrame window = new CadastroUsuarioFrame();
					window.frmCadastroDeUsurio.setVisible(true);
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
		frmCadastroDeUsurio.setBounds(100, 100, 532, 465);
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
		
		textFieldCPF = new JTextField();
		frmCadastroDeUsurio.getContentPane().add(textFieldCPF, "6, 8, fill, default");
		textFieldCPF.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Data de Nascimento:");
		frmCadastroDeUsurio.getContentPane().add(lblNewLabel_2, "4, 10, left, default");
		
		textFieldDtNascimento = new JTextField();
		frmCadastroDeUsurio.getContentPane().add(textFieldDtNascimento, "6, 10, left, default");
		textFieldDtNascimento.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Email:");
		frmCadastroDeUsurio.getContentPane().add(lblNewLabel_3, "4, 12, left, default");
		
		textFieldEmail = new JTextField();
		frmCadastroDeUsurio.getContentPane().add(textFieldEmail, "6, 12, fill, default");
		textFieldEmail.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Telefone:");
		frmCadastroDeUsurio.getContentPane().add(lblNewLabel_4, "4, 14, left, default");
		
		textFieldTelefone = new JTextField();
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
		
		lblNewLabel_5Matricula = new JLabel("Matricula:");
		frmCadastroDeUsurio.getContentPane().add(lblNewLabel_5Matricula, "4, 20, right, default");
		
		textFieldMatriculaFuncinario = new JTextField();
		frmCadastroDeUsurio.getContentPane().add(textFieldMatriculaFuncinario, "6, 20, left, default");
		textFieldMatriculaFuncinario.setColumns(10);
		
		lblDepartamento = new JLabel("Departamento:");
		frmCadastroDeUsurio.getContentPane().add(lblDepartamento, "4, 22, right, default");
		
		//TODO: chamar serviço de departamentos
		String[] departamentos = {"Selecione...","Departamento1", "Departamento2"};
		comboBoxDepartamentos = new JComboBox<String>();
		comboBoxDepartamentos.addItem(departamentos[0]);
		comboBoxDepartamentos.addItem(departamentos[1]);
		comboBoxDepartamentos.addItem(departamentos[2]);
		
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
		
		lblNewLabel_5 = new JLabel("Senha:");
		frmCadastroDeUsurio.getContentPane().add(lblNewLabel_5, "4, 30, left, default");
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		frmCadastroDeUsurio.getContentPane().add(passwordField, "6, 30, left, default");
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  if (roda == null) {
		    		  progressBar.setVisible(true);
                      roda = new roda();
                      roda.start(); 
                    }
		      }});
		frmCadastroDeUsurio.getContentPane().add(btnConfirmar, "6, 32");
		
		btnLimpar = new JButton("Limpar");
		//chamar e execulta o metodo limparCampos
		
		btnLimpar.addActionListener(new ActionListener() {
		    	  public void actionPerformed(ActionEvent e) { 
		    	  
		    	  limparCampos();
                   
		}});
		
		frmCadastroDeUsurio.getContentPane().add(btnLimpar, "6, 34");
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setVisible(false);
		
		frmCadastroDeUsurio.getContentPane().add(progressBar, "6, 36");
		
		//TODOS OS ITENS RELACIONADOS A TIPO DA PESSOA INVISIVEIS
		lblNewLabel_5Matricula.setVisible(false);
		textFieldMatriculaFuncinario.setVisible(false);
		lblDepartamento.setVisible(false);
		comboBoxDepartamentos.setVisible(false);

		lblNewLabel_6NomeLoja.setVisible(false);
		textFieldNomeLoja.setVisible(false);
		lblDepartamento.setVisible(false);
		comboBoxDepartamentos.setVisible(false);
	}
	
	// Define uma Thread para simular rodando
	private Thread roda;
	
	/*Metodo responsavel por escutar os click e a mudança de usuario*/
	private void mudaTipoUsuario(){
	
		comboBoxTipoUsuario.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  
					if(comboBoxTipoUsuario.getSelectedIndex() == 0){

						//FUNCIONARIO
						lblNewLabel_5Matricula.setVisible(false);
						textFieldMatriculaFuncinario.setVisible(false);
						lblDepartamento.setVisible(false);
						comboBoxDepartamentos.setVisible(false);

						//FORNECEDOR
						lblNewLabel_6NomeLoja.setVisible(false);
						textFieldNomeLoja.setVisible(false);
						
						
					}else{
						if(comboBoxTipoUsuario.getSelectedIndex() == 1){
							//FUNCIONARIO
							lblNewLabel_5Matricula.setVisible(true);
							textFieldMatriculaFuncinario.setVisible(true);
							lblDepartamento.setVisible(true);
							comboBoxDepartamentos.setVisible(true);
							
							//FORNECEDOR
							lblNewLabel_6NomeLoja.setVisible(false);
							textFieldNomeLoja.setVisible(false);
							
						}else{
							if(comboBoxTipoUsuario.getSelectedIndex() == 2){
								
								//FORNECEDOR
								lblNewLabel_6NomeLoja.setVisible(true);
								textFieldNomeLoja.setVisible(true);
								
								//FUNCIONARIO
								lblNewLabel_5Matricula.setVisible(false);
								textFieldMatriculaFuncinario.setVisible(false);
								lblDepartamento.setVisible(false);
								comboBoxDepartamentos.setVisible(false);
							}
						}	
					}

		      }
		    });
	}
	
	/*Metodo de limpar todos os campos*/
	private void limparCampos(){
		comboBoxTipoUsuario.setSelectedIndex(0);
		comboBoxDepartamentos.setSelectedIndex(0);
		textFieldCPF.setText("");
		textFieldDtNascimento.setText("");
		textFieldEmail.setText("");
		textFieldEndereco.setText("");
		textFieldMatriculaFuncinario.setText("");
		textFieldNomeCompleto.setText("");
		textFieldNomeLoja.setText("");
		textFieldTelefone.setText("");
		passwordField.setText("");
	}
	
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
		      for (int i = 0; i <= 2000; i++) {
		        // ---------------------------------
		        // Faça aqui o processo a realizar
		        // ---------------------------------
		    	  if(i == 2000){
		    		  frmCadastroDeUsurio.dispose();
		              JOptionPane.showMessageDialog(null, "Cadastro Efetuado com Sucesso...");
		              LoginFrame.main(null); 
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
