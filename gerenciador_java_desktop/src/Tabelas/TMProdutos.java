package Tabelas;

public class TMProdutos {
	
	private int id;
	private String disponivel;
	private String exibir;
	private String nomeProduto;
	private String ingredientes;
	private int tempoPreparo;
	private double preco;
	private String imagem;
	private String categoria;
	private String subCategoria;
	
	
	public TMProdutos(int id, String disponivel, String exibir,
			String nomeProduto, String ingredientes, int tempoPreparo,
			double preco, String imagem, String categoria, String subCategoria) {
		super();
		this.id = id;
		this.disponivel = disponivel;
		this.exibir = exibir;
		this.nomeProduto = nomeProduto;
		this.ingredientes = ingredientes;
		this.tempoPreparo = tempoPreparo;
		this.preco = preco;
		this.imagem = imagem;
		this.categoria = categoria;
		this.subCategoria = subCategoria;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}
	public String getExibir() {
		return exibir;
	}
	public void setExibir(String exibir) {
		this.exibir = exibir;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}
	public int getTempoPreparo() {
		return tempoPreparo;
	}
	public void setTempoPreparo(int tempoPreparo) {
		this.tempoPreparo = tempoPreparo;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}
	
	
	
	

}
