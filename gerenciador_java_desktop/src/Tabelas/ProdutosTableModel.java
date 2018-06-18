package Tabelas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ProdutosTableModel extends AbstractTableModel {

	//constantes que vão representar as colunas
    //(só para facilitar o entendimento do código)
    private final int COL_CATEGORIA = 0;
    private final int COL_SUBCATEGORIA = 1;
    private final int COL_NOME = 2;
 
    //lista dos produtos que serão exibidos
    private List<TMProdutos> produtos;
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ProdutosTableModel() {
        produtos = new ArrayList();
    }
 
    public ProdutosTableModel(List<TMProdutos> lista) {
        this();
        produtos.addAll(lista);
    }
 
    public int getRowCount() {
        //cada produto na lista será uma linha
        return produtos.size();
    }
 
    public int getColumnCount() {
        //vamos exibir Categoria e Nome, então são 2 colunas
        return 3;
    }
 
    public String getColumnName(int column) {
        //qual o nome da coluna
        if (column == COL_CATEGORIA) {
            return "Nome Categoria";
        } else if (column == COL_SUBCATEGORIA) {
            return "Nome Sub-Categoria";
        }else if (column == COL_NOME) {
            return "Nome Produto";
        }
        return "";
    }
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int columnIndex) {
    	//retorna a classe que representa a coluna
        if (columnIndex == COL_CATEGORIA) {
            return String.class;
        }else if (columnIndex == COL_SUBCATEGORIA) {
            return String.class;
        }
        else if (columnIndex == COL_NOME) {
            return String.class;
        }
        return String.class;
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        TMProdutos c = produtos.get(rowIndex);
 
      //verifica qual valor deve ser retornado
        if (columnIndex == COL_CATEGORIA) {
            return c.getCategoria();
        }else if (columnIndex == COL_SUBCATEGORIA) {
            return c.getSubCategoria();
        }
        else if (columnIndex == COL_NOME) {
            return c.getNomeProduto();
        }
        return "";
    }
 
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //pega o produto da linha
        TMProdutos c = produtos.get(rowIndex);
 
        //verifica qual valor vai ser alterado
        if (columnIndex == COL_CATEGORIA) {
            c.setCategoria(aValue.toString());
        }
        else if(columnIndex == COL_SUBCATEGORIA){
        	c.setSubCategoria(aValue.toString());
        	
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
        produtos.remove(row);
        //fireTableDataChanged();
        fireTableRowsDeleted(0, produtos.size() - 1);
        }
    
    public void addLinha(TMProdutos c){  
        this.produtos.add(c);
        fireTableRowsInserted(produtos.size() + 1, produtos.size()+1);
        //fireTableDataChanged();  
    } 
}
