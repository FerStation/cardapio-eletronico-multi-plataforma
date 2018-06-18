package Tabelas;

public class TMPedido {

	private int ID;
	private int ID_mesa;
	private int quantidade;
	private String nomeProduto;
	
	public TMPedido(int iD, int iD_mesa, int quantidade, String nomeProduto) {
		super();
		ID = iD;
		ID_mesa = iD_mesa;
		this.quantidade = quantidade;
		this.nomeProduto = nomeProduto;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getID_mesa() {
		return ID_mesa;
	}

	public void setID_mesa(int iD_mesa) {
		ID_mesa = iD_mesa;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	
	
	
}
