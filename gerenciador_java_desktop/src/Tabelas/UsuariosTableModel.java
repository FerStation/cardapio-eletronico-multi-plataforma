package Tabelas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class UsuariosTableModel extends AbstractTableModel {

	//constantes que vão representar as colunas
    //(só para facilitar o entendimento do código)
    private final int COL_NVACESSO = 0;
    private final int COL_LOGIN = 1;
    private final int COL_NOME = 2;
 
    //lista dos produtos que serão exibidos
    private List<TMUsuarios> usuarios;
 
    public UsuariosTableModel() {
        usuarios = new ArrayList();
    }
 
    public UsuariosTableModel(List<TMUsuarios> lista) {
        this();
        usuarios.addAll(lista);
    }
 
    public int getRowCount() {
        //cada produto na lista será uma linha
        return usuarios.size();
    }
 
    public int getColumnCount() {
        //vamos, então são 3 colunas
        return 3;
    }
 
    @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
        if (column == COL_NVACESSO) {
            return "Nivel de Acesso";
        } 
        else if (column == COL_LOGIN) {
            return "LOGIN";
        } 
        else if (column == COL_NOME) {
            return "Nome";
        }
        return "";
    }
 
    @Override
    public Class getColumnClass(int columnIndex) {
        //retorna a classe que representa a coluna
        if (columnIndex == COL_NVACESSO) {
            return String.class;
        }
        else if(columnIndex == COL_LOGIN){
        	return String.class;
        }
        else if(columnIndex == COL_NOME){
        	return String.class;
        }
        
        return String.class;
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        TMUsuarios u = usuarios.get(rowIndex);
 
        //verifica qual valor deve ser retornado
        if(columnIndex == COL_NVACESSO){
        	return u.getNivelAcesso();
        }
        else if (columnIndex == COL_LOGIN) {
            return u.getLogin();
        }
        else if (columnIndex == COL_NOME) {
            return u.getNome();
        }
        return "";
    }
 
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //pega o produto da linha
        TMUsuarios u = usuarios.get(rowIndex);
 
        //verifica qual valor vai ser alterado
        if(columnIndex == COL_NVACESSO){
        	u.setNivelAcesso(aValue.toString());
        }
        else if (columnIndex == COL_LOGIN) {
            u.setLogin(aValue.toString());
        }
        else if (columnIndex == COL_NOME) {
            u.setNome(aValue.toString());
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
    usuarios.remove(row);
    //fireTableDataChanged();
    fireTableRowsDeleted(row - 1, usuarios.size() - 1);
    }
}
