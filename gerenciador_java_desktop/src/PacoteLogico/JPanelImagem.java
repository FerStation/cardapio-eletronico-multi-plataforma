package PacoteLogico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Interface.CategoriaSubCategoria;

public class JPanelImagem extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblImagem;
	private JTextField txtBuscarImagem;
	private JButton btnBuscar;
	
	private File caminhoImagem;
	private String nomeImagem;
	
	public File getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(File caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public void hablitaPanel(){
		
		txtBuscarImagem.setEnabled(true);
		btnBuscar.setEnabled(true);
	}
	
	public void desabilitaPanel(){
		
		txtBuscarImagem.setEnabled(false);
		btnBuscar.setEnabled(false);
	}
	
	public void limpaPanel(){
		
		txtBuscarImagem.setText(null);
		lblImagem.setIcon(new ImageIcon(CategoriaSubCategoria.class.getResource("/Imagens/logo.png")));
	}
	
	public boolean validaDadospanel(){
		boolean dadosValidos = false;
		
		if(txtBuscarImagem.getText().equals(null) | txtBuscarImagem.getText().equals("")){
			
			dadosValidos = false;
		}
		else {
			dadosValidos = true;
		}
		
		return dadosValidos;
	}
	
	public void exibeDadosPanel(String caminho){
		
		txtBuscarImagem.setText(caminho);
		
		ImageIcon img = new ImageIcon(caminho.replace("\\", "\\\\")); //define o caminho da imagem
		img.setImage(img.getImage().getScaledInstance(192, 126, 100)); //define o tamanho da imagem
		lblImagem.setIcon(img); //insere a imagem na JLabel
	}
	
	/**
	 * Metodo que abre um JFileChoose para carregar arquivos
	 * e coloca a imagem dentro de uma JLabel
	 * 
	 */
	public void abrirArquivo() throws IOException{
		

/*
 * try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 */
		
		
		JFileChooser chooser = new JFileChooser(); //cria um JFileChooser
		chooser.setMultiSelectionEnabled(false); //desabilita a seleção de mais de um arquivo
		chooser.setFileFilter(new FileNameExtensionFilter("Arquivos de imagens", "jpg", "png", "gif"));
		chooser.showOpenDialog(null); //abre o dialog para busca de arquivos
		
		txtBuscarImagem.setText(chooser.getSelectedFile().getParent()+"\\"+chooser.getSelectedFile().getName()); //seta JTextField busar, com o caminho da imagem
		
		ImageIcon img = new ImageIcon(txtBuscarImagem.getText().replace("\\", "\\\\")); //define o caminho da imagem
		img.setImage(img.getImage().getScaledInstance(192, 126, 100)); //define o tamanho da imagem
		lblImagem.setIcon(img); //insere a imagem na JLabel
		
		File origem = new File(txtBuscarImagem.getText().replace("\\", "\\\\"));
		setNomeImagem(chooser.getSelectedFile().getName());
		setCaminhoImagem(origem);
		
		//File destino = new File("C:\\Documents and Settings\\fernandocunha\\Desktop\\testeminister");
		
		//copiarArquivo(origem, destino);
	}
	
	
	/**
	 * Esse Metodo copia a imagem para o diretorio especificado
	 * 
	 * @see http://www.guj.com.br/java/128689-copia-de-arquivo-via-java
	 */
	public static void copiarArquivo(File source, File destination) throws IOException {  
	     if (destination.exists())  
	         destination.delete();  
	  
	     FileChannel sourceChannel = null;  
	     FileChannel destinationChannel = null;  
	  
	     try {  
	         sourceChannel = new FileInputStream(source).getChannel();  
	         destinationChannel = new FileOutputStream(destination).getChannel();  
	         sourceChannel.transferTo(0, sourceChannel.size(),  
	                 destinationChannel);  
	     } finally {  
	         if (sourceChannel != null && sourceChannel.isOpen())  
	             sourceChannel.close();  
	         if (destinationChannel != null && destinationChannel.isOpen())  
	             destinationChannel.close();  
	    }  
	}  


	/**
	 * Create the panel.
	 */
	public JPanelImagem() {
		setLayout(null);
		setBounds(0, 0, 196, 160);
		
		lblImagem = new JLabel("");
		lblImagem.setBounds(0, 0, 196, 130);
		lblImagem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblImagem.setIcon(new ImageIcon(CategoriaSubCategoria.class.getResource("/Imagens/logo.png")));
		lblImagem.setAlignmentX(0.5f);
		add(lblImagem);
		
		txtBuscarImagem = new JTextField();
		txtBuscarImagem.setBounds(0, 133, 99, 26);
		txtBuscarImagem.setToolTipText("Digite a origem da imagem\r\n ou clique no bot\u00E3o Buscar para procurar");
		txtBuscarImagem.setEnabled(false);
		txtBuscarImagem.setColumns(10);
		add(txtBuscarImagem);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					abrirArquivo();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(109, 132, 87, 28);
		btnBuscar.setIcon(new ImageIcon(CategoriaSubCategoria.class.getResource("/Imagens/buscarpasta.png")));
		btnBuscar.setEnabled(false);
		add(btnBuscar);

	}

}
