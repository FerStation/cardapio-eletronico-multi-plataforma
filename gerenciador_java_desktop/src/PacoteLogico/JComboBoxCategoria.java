package PacoteLogico;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import BancoDeDados.Dao;

public class JComboBoxCategoria extends JComboBox<String>{
	
	Dao dao = new Dao();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JComboBoxCategoria(){
		
		setToolTipText("Selecione a categoria do produto");
		setModel(new DefaultComboBoxModel<String>(new Vector<String>(dao.listaCategorias())));
		setSelectedItem(null);
		
	}

}
