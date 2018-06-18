package Interface;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;


abstract class JInternalFramePai extends JInternalFrame {
	
	abstract void habilitaDados();
	
	abstract void desabilitaDados();
	
	abstract void salvar();
	
	abstract void alterar();
	
	abstract void deletar();
	
	abstract void limpar();
	
	abstract boolean validaCampos();

}
