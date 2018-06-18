package PacoteLogico;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public class JTextFieldPesquisar extends JTextField{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTextFieldPesquisar(){
		
		setText("pesquisar");
		setForeground(Color.GRAY);
		setFont(new Font("SansSerif", Font.ITALIC, 12));
		setColumns(10);
		
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				
				setText(null);
				setForeground(Color.BLACK);
				setFont(new Font("SansSerif", Font.PLAIN, 12));
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				setForeground(Color.GRAY);
				setFont(new Font("SansSerif", Font.ITALIC, 12));
				setText("pesquisar");
			}
		});
		
	}

}
