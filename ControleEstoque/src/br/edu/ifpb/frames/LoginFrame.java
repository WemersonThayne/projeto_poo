package br.edu.ifpb.frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.edu.ifpb.controllers.FornecedorController;
import br.edu.ifpb.controllers.FuncionarioController;
import br.edu.ifpb.entidades.Fornecedor;
import br.edu.ifpb.entidades.Funcionario;
import br.edu.ifpb.exceptions.ControleEstoqueException;
import br.edu.ifpb.exceptions.ControleEstoqueSqlException;
import br.edu.ifpb.exceptions.MensagenException;
import br.edu.ifpb.utils.Mensagens;
import br.edu.ifpb.utils.Util;

public class LoginFrame {

	private JFrame frmLogin;
	private JTextField txtLogin;
	private JPasswordField pwdSenha;
	private JButton btnCadastro;
	private JButton btnEntrar;
	private JLabel lblLogin;
	private JLabel lblSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					LoginFrame window = new LoginFrame();
					window.frmLogin.setVisible(true);
					window.frmLogin.setLocationRelativeTo(null);
				} catch (RuntimeException e) {
					e.printStackTrace();
					new MensagenException(e.getMessage()); 
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setBackground(SystemColor.activeCaption);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setTitle("Login");
		frmLogin.setForeground(Color.WHITE);
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("123px"),
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(32dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(88dlu;default)"), FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("166px"), FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("63px"), },
				new RowSpec[] { FormSpecs.LINE_GAP_ROWSPEC, RowSpec.decode("23px"), FormSpecs.LINE_GAP_ROWSPEC,
						RowSpec.decode("23px"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		lblLogin = new JLabel("Login:");
		frmLogin.getContentPane().add(lblLogin, "4, 6, left, default");

		txtLogin = new JTextField();
		txtLogin.setHorizontalAlignment(SwingConstants.LEFT);
		frmLogin.getContentPane().add(txtLogin, "6, 6, 3, 1, fill, center");
		txtLogin.setColumns(20);

		lblSenha = new JLabel("Senha:");
		frmLogin.getContentPane().add(lblSenha, "4, 8, right, default");

		pwdSenha = new JPasswordField();
		pwdSenha.setHorizontalAlignment(SwingConstants.LEFT);
		frmLogin.getContentPane().add(pwdSenha, "6, 8, 3, 1, fill, center");

		btnEntrar = new JButton("Entrar");
		frmLogin.getContentPane().add(btnEntrar, "4, 10, 5, 1, fill, center");

		btnCadastro = new JButton("Cadastro");
		frmLogin.getContentPane().add(btnCadastro, "4, 12, 5, 1, fill, center");

		// REGISTRA O EVENTO
		btnEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				btnEntrar.setText("Carregando...");
				try {
					chamarTelaPrincipal();
				} catch (ControleEstoqueException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnCadastro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				frmLogin.dispose(); //Destroy the JFrame object
				CadastroUsuarioFrame.main(null);
			}
		});
	}

	private void chamarTelaPrincipal() throws ControleEstoqueException {
		
		if(Util.validarCampos(txtLogin) && Util.validarCamposSenha(pwdSenha)){
			//TODO: chamar servico de login
			frmLogin.dispose(); //Destroy the JFrame object
			//TODO:escolher a tela ou de funcionario ou de fornecedor
			System.out.println(verficaLoginFuncionario());
			System.out.println(verficaLoginFornecedor());
			
		}else{
			new Mensagens(Util.VALOR_INSERIDOS_INVALIDOS);
			btnEntrar.setText("Entrar");
		}
	}
	
	private boolean verficaLoginFuncionario(){
		boolean retorno = false;
		Funcionario funci = new Funcionario();
		funci.setLogin(txtLogin.getText().toString().trim());
		funci.setSenha(pwdSenha.getText().toString().trim());
		
		try {
			Funcionario funcionarioRetorno = new FuncionarioController().verificaLogin(funci);
			if(funcionarioRetorno != null){
				if(funcionarioRetorno.getLogin().equalsIgnoreCase(funci.getLogin()) && funcionarioRetorno.getSenha().equalsIgnoreCase(funci.getSenha())){
					new Mensagens(Util.LOGIN_MENSAGEM + funcionarioRetorno.getNome());
					FuncionarioFrame.main(null);
					retorno = true;
				}else{
					new Mensagens(Util.LOGIN_MENSAGEM_INVALIDO + funcionarioRetorno.getNome());
					retorno = false;
				}	
			}else{
				retorno = false; 
			}
			
		} catch (ControleEstoqueSqlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}
	
	private boolean verficaLoginFornecedor(){
		boolean retorno = false;

		Fornecedor forne = new Fornecedor();
		forne.setLogin(txtLogin.getText().toString().trim());
		forne.setSenha(pwdSenha.getText().toString().trim());
		
		try {
			Fornecedor fornecedorRetorno = new FornecedorController().verificaLogin(forne);
			if(fornecedorRetorno != null){
				if(fornecedorRetorno.getLogin().equalsIgnoreCase(forne.getLogin()) && fornecedorRetorno.getSenha().equalsIgnoreCase(forne.getSenha()) ){
					new Mensagens(Util.LOGIN_MENSAGEM + fornecedorRetorno.getNome());
					FornecedorFrame.main(null);
					retorno = true;
				}else{
					new Mensagens(Util.LOGIN_MENSAGEM_INVALIDO + fornecedorRetorno.getNome());
					retorno = false;
				}	
			}else{
				retorno =  false;
			}
			
		} catch (ControleEstoqueSqlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno ;
	}
}
