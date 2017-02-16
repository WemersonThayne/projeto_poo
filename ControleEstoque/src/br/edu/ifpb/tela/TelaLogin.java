package br.edu.ifpb.tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.edu.ifpb.exceptions.ControleEstoqueException;
import br.edu.ifpb.utils.Mensagens;
import br.edu.ifpb.utils.Util;

public class TelaLogin {

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
					TelaLogin window = new TelaLogin();
					window.frmLogin.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaLogin() {
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
				TelaCadastroUsuario.main(null);
			}
		});
	}

	private void chamarTelaPrincipal() throws ControleEstoqueException {
		
		if(Util.validarCampos(txtLogin) && Util.validarCamposSenha(pwdSenha)){
			//TODO: chamar servico de login
			frmLogin.dispose(); //Destroy the JFrame object
			//TODO:escolher a tela ou de funcionario ou de fornecedor
			TelaFuncionario.main(null);
		}else{
			new Mensagens(Util.VALOR_INSERIDOS_INVALIDOS);
			btnEntrar.setText("Entrar");
		}
	}
}
