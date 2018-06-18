package Tabelas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PedidoTableModel extends AbstractTableModel{
	
	//constantes que vão representar as colunas
    //(só para facilitar o entendimento do código)
	private final int COL_MESA = 0;
    private final int COL_QTD = 1;
    private final int COL_NOME = 2;
 
    //lista dos produtos que serão exibidos
    private List<TMPedido> produtosCaixa;
 
	public PedidoTableModel() {
        produtosCaixa = new ArrayList();
    }
 
    public PedidoTableModel(List<TMPedido> lista) {
        this();
        produtosCaixa.addAll(lista);
    }
 
    public int getRowCount() {
        //cada produto na lista será uma linha
        return produtosCaixa.size();
    }
 
    public int getColumnCount() {
        //vamos exibir Categoria e Nome, então são 2 colunas
        return 3;
    }
 
    public String getColumnName(int column) {
        //qual o nome da coluna
        if (column == COL_MESA) {
            return "Mesa";
        } else if (column == COL_QTD) {
            return "Qtd";
        }else if (column == COL_NOME) {
            return "Produto";
        }
        return "";
    }
 
	public Class getColumnClass(int columnIndex) {
    	//retorna a classe que representa a coluna
        if (columnIndex == COL_QTD) {
            return int.class;
        }else if (columnIndex == COL_MESA) {
            return int.class;
        }
        else if (columnIndex == COL_NOME) {
            return String.class;
        }
        return String.class;
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        TMPedido c = produtosCaixa.get(rowIndex);
 
      //verifica qual valor deve ser retornado
        if (columnIndex == COL_QTD) {
            return c.getQuantidade();
        }else if (columnIndex == COL_MESA) {
            return c.getID_mesa();
        }
        else if (columnIndex == COL_NOME) {
            return c.getNomeProduto();
        }
        return "";
    }
 
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //pega o produto da linha
        TMPedido c = produtosCaixa.get(rowIndex);
 
        //verifica qual valor vai ser alterado
        if (columnIndex == COL_QTD) {
            c.setQuantidade(Integer.parseInt(aValue.toString()));
        }
        else if(columnIndex == COL_MESA){
        	c.setID_mesa(Integer.parseInt(aValue.toString()));
        	
        }
        else if(columnIndex == COL_NOME){
        	c.setNomeProduto(aValue.toString());
        	
        }
        //avisa que os dados mudaram
        fireTableDataChanged();
    }
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //no nosso caso todas não vão ser editáveis, entao retorna false pra todas
        return false;
    }
    
    public void removeRowAt(int row) {
        produtosCaixa.remove(row);
        //fireTableDataChanged();
        fireTableRowsDeleted(0, produtosCaixa.size() - 1);
        }
    
    public void addLinha(TMPedido c){  
        this.produtosCaixa.add(c);
        fireTableRowsInserted(produtosCaixa.size() + 1, produtosCaixa.size()+1);
        //fireTableDataChanged();  
    } 

}
