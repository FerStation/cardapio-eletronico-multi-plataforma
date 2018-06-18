package Tabelas;

public class SubCategoria {
	
	private int idSubCategoria;
	private String nomeCategoria;
	private String imagem;
	private String descricao;
	private String nome;
	
	public SubCategoria(int idSubCategoria, String imagem, String descricao, String nomeCategoria, String nome){
		
		this.idSubCategoria = idSubCategoria;
		this.imagem = imagem;
		this.descricao = descricao;
		this.nomeCategoria = nomeCategoria;
		this.nome = nome;
		
	}


	public int getIdSubCategoria() {
		return idSubCategoria;
	}



	public void setIdSubCategoria(int idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}



	public String getNomeCategoria() {
		return nomeCategoria;
	}



	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}



	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
