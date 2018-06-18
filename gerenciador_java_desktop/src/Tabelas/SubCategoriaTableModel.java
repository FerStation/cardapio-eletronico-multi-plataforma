package Tabelas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class SubCategoriaTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//constantes que vão representar as colunas
    //(só para facilitar o entendimento do código)
    private final int COL_CATEGORIA = 0;
    private final int COL_NOME = 1;
 
    //lista dos produtos que serão exibidos
    private List<SubCategoria> subCategoria;
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public SubCategoriaTableModel() {
        subCategoria = new ArrayList();
    }
 
    public SubCategoriaTableModel(List<SubCategoria> lista) {
        this();
        subCategoria.addAll(lista);
    }
 
    public int getRowCount() {
        //cada produto na lista será uma linha
        return subCategoria.size();
    }
 
    public int getColumnCount() {
        //vamos exibir Categoria e Nome, então são 2 colunas
        return 2;
    }
 
    @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
        if (column == COL_CATEGORIA) {
            return "Nome Categoria";
        } else if (column == COL_NOME) {
            return "Nome Sub-Categoria";
        }
        return "";
    }
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Class getColumnClass(int columnIndex) {
    	//retorna a classe que representa a coluna
        if (columnIndex == COL_CATEGORIA) {
            return String.class;
        } else if (columnIndex == COL_NOME) {
            return String.class;
        }
        return String.class;
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        SubCategoria c = subCategoria.get(rowIndex);
 
      //verifica qual valor deve ser retornado
        if (columnIndex == COL_CATEGORIA) {
            return c.getNomeCategoria();
        } else if (columnIndex == COL_NOME) {
            return c.getNome();
        }
        return "";
    }
 
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //pega o produto da linha
        SubCategoria c = subCategoria.get(rowIndex);
 
        //verifica qual valor vai ser alterado
        if (columnIndex == COL_CATEGORIA) {
            c.setNomeCategoria(aValue.toString());
        }
        else if(columnIndex == COL_NOME){
        	c.setNome(aValue.toString());
        	
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
        subCategoria.remove(row);
        //fireTableDataChanged();
        fireTableRowsDeleted(0, subCategoria.size() - 1);
        }
    
    public void addLinha(SubCategoria c){  
        this.subCategoria.add(c);
        fireTableRowsInserted(subCategoria.size() + 1, subCategoria.size()+1);
        //fireTableDataChanged();  
    } 

}
