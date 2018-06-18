package Interface;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.ComponentOrientation;
import javax.swing.border.LineBorder;
import javax.swing.plaf.DesktopPaneUI;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JMenuItem;
import javax.swing.JLabel;


public class Principal extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	
	Usuarios usuario = new Usuarios();
	Produtos produtos = new Produtos();
	Caixa caixa = new Caixa();
	Pedidos pedidos = new Pedidos();
	CategoriaSubCategoria categoriaSubCategoria = new CategoriaSubCategoria();

	public void efetuaLogoff(){
		
		Login login = new Login();
		login.show();
		
		Principal.this.hide();
	}
	
	/**
	 * Create the frame.
	 */
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Imagens/OK.png")));
		setTitle("Minister - Gerenciador de Card\u00E1pios Eletr\u00F4nicos");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmFazerLogoff = new JMenuItem("Fazer Logoff");
		mntmFazerLogoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				efetuaLogoff();
			}
		});
		mnArquivo.add(mntmFazerLogoff);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int res = JOptionPane.showConfirmDialog(null,"Deseja realmente sair? ","Aviso",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(res == JOptionPane.YES_OPTION){
					
					System.exit(0);
				}
			}
		});
		mnArquivo.add(mntmSair);
		
		JMenu mnFerramentas = new JMenu("Ferramentas");
		menuBar.add(mnFerramentas);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem mntmAjudaOnline = new JMenuItem("Suporte");
		mnAjuda.add(mntmAjudaOnline);
		
		JMenuItem mntmManual = new JMenuItem("Manual");
		mnAjuda.add(mntmManual);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mnAjuda.add(mntmSobre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnLogoff = new JButton("");
		btnLogoff.setToolTipText("Fazer Logoff");
		btnLogoff.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/logoff.png")));
		toolBar.add(btnLogoff);
		
		JButton btnalterarSenha = new JButton("");
		btnalterarSenha.setToolTipText("Alterar Senha");
		btnalterarSenha.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/alterarSenha.png")));
		toolBar.add(btnalterarSenha);
		
		JButton btnBtnusuarios = new JButton();
		btnBtnusuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				usuario.doDefaultCloseAction();
				usuario.setVisible(true);
				desktopPane.add(usuario);
				desktopPane.repaint(); 
				
				
			}
		});
		btnBtnusuarios.setToolTipText("Gerenciar Usuarios");
		btnBtnusuarios.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/usuarios.png")));
		toolBar.add(btnBtnusuarios);
		
		JButton btnBtncategoria = new JButton();
		btnBtncategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				categoriaSubCategoria.doDefaultCloseAction();
				categoriaSubCategoria.setVisible(true);
				desktopPane.add(categoriaSubCategoria);
				desktopPane.repaint(); 
				
				
			}
		});
		btnBtncategoria.setToolTipText("Gerenciar Categorias");
		btnBtncategoria.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/categoria.png")));
		toolBar.add(btnBtncategoria);
		
		JButton btnBtnsubcategoria = new JButton("");
		btnBtnsubcategoria.setToolTipText("Gerenciar Sub-Categorias");
		btnBtnsubcategoria.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/subCategoria.png")));
		toolBar.add(btnBtnsubcategoria);
		
		JButton btnBtnprodutos = new JButton("");
		btnBtnprodutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				produtos.doDefaultCloseAction();
				produtos.setVisible(true);
				desktopPane.add(produtos);
				desktopPane.repaint(); 
				
			}
		});
		btnBtnprodutos.setToolTipText("Gerenciar Produtos");
		btnBtnprodutos.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/produtos.png")));
		toolBar.add(btnBtnprodutos);
		
		JButton btnBtnpedidos = new JButton("");
		btnBtnpedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pedidos.setVisible(true);
				desktopPane.add(pedidos);
				desktopPane.repaint(); 
			}
		});
		btnBtnpedidos.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/pedidos.png")));
		btnBtnpedidos.setToolTipText("Gerenciar Pedidos");
		toolBar.add(btnBtnpedidos);
		
		JButton btnBtncaixa = new JButton("");
		btnBtncaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				desktopPane.remove(caixa);
				caixa.doDefaultCloseAction();
				caixa.setVisible(true);
				desktopPane.add(caixa);
				caixa.moveToFront();
			}
		});
		btnBtncaixa.setToolTipText("Caixa");
		btnBtncaixa.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/caixa.png")));
		toolBar.add(btnBtncaixa);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		contentPane.add(toolBar_1, BorderLayout.SOUTH);
	}
}
