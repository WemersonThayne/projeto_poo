package br.edu.ifpb.entidades;

public  abstract class Pessoa {

	private int codPessoa;
	private String nome;
	private String cpf;
	private String endereco;
	private String dataNascimento;
	private String telefone;
	private String email;
	private int tipoPessoa;
	private String login;
	private String senha;
	
	public Pessoa(){
	}
	
	public Pessoa(String nome , String cpf, String dataNascimento,
			String telefone, String email, int tipoPessoa,String login, String senha,String endereco){
		
		//setCodPessoa(codPessoa);
		setCpf(cpf);
		setDataNascimento(dataNascimento);
		setEmail(email);
		setEndereco(endereco);
		setNome(nome);
		setTelefone(telefone);
		setTipoPessoa(tipoPessoa);		
		setLogin(login);
		setSenha(senha);
	
	}

	public int getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(int codPessoa) {
		this.codPessoa = codPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(int tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Pessoa [codPessoa=" + codPessoa + ", nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco
				+ ", dataNascimento=" + dataNascimento + ", telefone=" + telefone + ", email=" + email + ", tipoPessoa="
				+ tipoPessoa + ", login=" + login + ", senha=" + senha + "]";
	}

}
