package Interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import BancoDeDados.Dao;
import PacoteLogico.CriptografarMD5;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField passwordField;
	
	Dao dao = new Dao();
	Principal principal = new Principal();
	CriptografarMD5 md5 = new CriptografarMD5();

	/**
	 * Create the frame.
	 */
	public Login() {
		setAutoRequestFocus(false);
		setTitle("Minister - Login");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(317, 509);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/Imagens/cadeado.png")));
		lblNewLabel.setBounds(19, 18, 260, 148);
		contentPane.add(lblNewLabel);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblLogin.setBounds(19, 208, 55, 19);
		contentPane.add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setDocument(new PlainDocument()  
		{  
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override  
		    public void insertString( int offs, String str, AttributeSet a )  
		                    throws BadLocationException  
		    {  
		        super.insertString( offs, str.toUpperCase(), a );  
		    }  
		} ); 
		txtLogin.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtLogin.setBounds(19, 231, 260, 32);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblSenha.setBounds(19, 288, 55, 19);
		contentPane.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
		passwordField.setBounds(19, 314, 260, 32);
		contentPane.add(passwordField);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		btnSair.setBounds(145, 366, 90, 47);
		contentPane.add(btnSair);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!txtLogin.getText().equals("") && !passwordField.getText().equals("")){
					
					if(dao.validaLogin(txtLogin.getText(), md5.criptografarMD5(passwordField.getText()))){
						
						principal.show();
						Login.this.hide();
					}
					else{
						
						JOptionPane.showMessageDialog(null, "Login ou Senha Incorreto(a)", "ERRO", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				else{
					
					JOptionPane.showMessageDialog(null, "Preencha os Campos Login e Senha", "AVISO", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnEntrar.setBounds(43, 366, 90, 47);
		contentPane.add(btnEntrar);
	}
}
