package Tabelas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Trasferencia.DtoCategoriaSubCategoria;

public class CategoriaTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	//constantes que v�o representar as colunas
    //(s� para facilitar o entendimento do c�digo)
    private final int COL_NOME = 0;
    private final int COL_QUANT = 1;
 
    //lista dos produtos que ser�o exibidos
    private List<Categoria> categoria;
 
    public CategoriaTableModel() {
        categoria = new ArrayList();
    }
 
    public CategoriaTableModel(List<Categoria> lista) {
        this();
        categoria.addAll(lista);
    }
 
    public int getRowCount() {
        //cada produto na lista ser� uma linha
        return categoria.size();
    }
 
    public int getColumnCount() {
        //vamos exibir s� Nome, ent�o s�o 1 colunas
        return 1;
    }
 
    @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
        if (column == COL_NOME) {
            return "Nome Categoria";
        } else if (column == COL_QUANT) {
            return "Quant. Disp";
        }
        return "";
    }
 
    @Override
    public Class getColumnClass(int columnIndex) {
        //retorna a classe que representa a coluna
        if (columnIndex == COL_NOME) {
            return String.class;
        }
        return String.class;
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        Categoria c = categoria.get(rowIndex);
 
        //verifica qual valor deve ser retornado
        if (columnIndex == COL_NOME) {
            return c.getNome();
        }
        return "";
    }
 
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //pega o produto da linha
        Categoria p = categoria.get(rowIndex);
 
        //verifica qual valor vai ser alterado
        if (columnIndex == COL_NOME) {
            p.setNome(aValue.toString());
        }
        //avisa que os dados mudaram
        fireTableDataChanged();
    }
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //no nosso caso todas n�o v�o ser edit�veis, entao retorna false pra todas
        return false;
    }
    
    public void removeRowAt(int row) {
    categoria.remove(row);
    //fireTableDataChanged();
    fireTableRowsDeleted(row - 1, categoria.size() - 1);
    }
    
	
}
