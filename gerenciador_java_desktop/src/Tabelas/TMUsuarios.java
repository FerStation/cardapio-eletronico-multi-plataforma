package Tabelas;

public class TMUsuarios {
	
	private String login;
	private String nivelAcesso;
	private String nome;
	private String email;
	private String rg;
	private String cpf;
	private String tel;
	private String cel;
	
	public TMUsuarios(String login, String nvAcesso, String nome, String email, String rg, String cpf, String tel, String cel){
		
		this.login = login;
		this.nivelAcesso = nvAcesso;
		this.nome = nome;
		this.email = email;
		this.rg = rg;
		this.cpf = cpf;
		this.tel = tel;
		this.cel = cel;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(String nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCel() {
		return cel;
	}

	public void setCel(String cel) {
		this.cel = cel;
	}
	
	
	

}
