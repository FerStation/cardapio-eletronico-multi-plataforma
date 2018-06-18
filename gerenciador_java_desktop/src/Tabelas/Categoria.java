package Tabelas;

public class Categoria {
	
	private int idcategoria;
	private String nome;
	private String imagem;
	private String descricao;
	
	public Categoria(int idCategoria, String nome, String imagem, String descricao){
		
		this.idcategoria = idCategoria;
		this.nome = nome;
		this.imagem = imagem;
		this.descricao = descricao;
		
	}

	public int getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	
	

}
