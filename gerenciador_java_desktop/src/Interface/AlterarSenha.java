package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlterarSenha extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordFieldAntiga;
	private JPasswordField passwordFieldNovo;
	private JPasswordField passwordFieldNovo2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarSenha frame = new AlterarSenha();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AlterarSenha() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AlterarSenha.class.getResource("/Imagens/alterarSenha.png")));
		setTitle("Alterar Senha");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 292, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDigiteSuaSenha = new JLabel("Digite Sua Senha:");
		lblDigiteSuaSenha.setBounds(28, 24, 125, 16);
		contentPane.add(lblDigiteSuaSenha);
		
		passwordFieldAntiga = new JPasswordField();
		passwordFieldAntiga.setBounds(28, 43, 217, 28);
		contentPane.add(passwordFieldAntiga);
		
		JLabel lblNovaSenha = new JLabel("Nova Senha:");
		lblNovaSenha.setBounds(28, 100, 102, 16);
		contentPane.add(lblNovaSenha);
		
		passwordFieldNovo = new JPasswordField();
		passwordFieldNovo.setBounds(28, 116, 217, 28);
		contentPane.add(passwordFieldNovo);
		
		JLabel lblRepitaSuaNova = new JLabel("Repita sua nova Senha");
		lblRepitaSuaNova.setBounds(28, 166, 170, 16);
		contentPane.add(lblRepitaSuaNova);
		
		passwordFieldNovo2 = new JPasswordField();
		passwordFieldNovo2.setBounds(28, 183, 217, 28);
		contentPane.add(passwordFieldNovo2);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(passwordFieldNovo.getText() != passwordFieldNovo2.getText()){
					
				}
				else{
					JOptionPane.showMessageDialog(null, "As novas senhas são diferentes");
				}
			}
		});
		btnSalvar.setIcon(new ImageIcon(AlterarSenha.class.getResource("/Imagens/salvar.png")));
		btnSalvar.setBounds(73, 223, 125, 36);
		contentPane.add(btnSalvar);
	}
}
