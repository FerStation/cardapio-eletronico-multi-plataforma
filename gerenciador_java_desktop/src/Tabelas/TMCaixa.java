package Tabelas;

public class TMCaixa {

	private int idPedido;
	private int idMesa;
	private String pedidoPronto;
	private int qtd;
	private String produto;
	private double preco;
	
	public TMCaixa(int idPedido, int idMesa, int qtd, String produto, double preco){
		this.idPedido = idPedido;
		this.idMesa = idMesa;
		//this.pedidoPronto = pedidoPronto;
		this.qtd = qtd;
		this.produto = produto;
		this.preco = preco;
	}

	
	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}

	public String getPedidoPronto() {
		return pedidoPronto;
	}

	public void setPedidoPronto(String pedidoPronto) {
		this.pedidoPronto = pedidoPronto;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
	
	
	
}
