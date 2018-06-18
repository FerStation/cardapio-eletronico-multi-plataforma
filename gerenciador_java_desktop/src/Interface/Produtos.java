package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.SliderUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.ConvolveOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import BancoDeDados.Dao;
import PacoteLogico.JNumberFormatField;
import PacoteLogico.JPanelImagem;
import PacoteLogico.JTextFieldPesquisar;
import Tabelas.ProdutosTableModel;
import Tabelas.TMProdutos;
import Trasferencia.DtoProdutos;

/**
 * Essa classe é a responsavel por listar produtos de uma pesquisa e tambêm
 * cadastrar novos produtos no banco de dados
 * 
 * @author Fernando Bartholomeu reis da Silva Cunha
 * @see WindowsBuilder
 * 
 */
public class Produtos extends JInternalFramePai {

	//Declaração das Variaveis
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanelImagem panelImagem;
	
	private ProdutosTableModel modelProdutos;
	
	private JTable table;
	
	private JTextField txtBuscar;
	private JTextField txtNome;
	private JTextField txtIngredientes;
	private JTextField txtPreco;
	
	private JComboBox cbxCategoria;
	private JComboBox cbxSubCategoria;
	
	private JSpinner spinner;
	
	private ButtonGroup bgExibir;
	private ButtonGroup bgDisponivel;
	
	private JRadioButton rdbtnSim;
	private JRadioButton rdbtnNo;
	private JRadioButton rdbtnDisponivel;
	private JRadioButton rdbtnIndisponivel;
	
	private JButton btnLimpar;
	private JButton btnSalvar;
	
	private boolean alterar;
	
	DtoProdutos produtos = new DtoProdutos();
	Dao dao = new Dao();
	
	/**
	 * Metodo que habilita JTexteField, JComboBox e JButton do cadastro
	 * 
	 * @return void
	 */
	public void habilitaDados(){
		
		txtNome.setEnabled(true);
		txtIngredientes.setEnabled(true);
		//txtTempo.setEnabled(true);
		txtPreco.setEnabled(true);
		btnLimpar.setEnabled(true);
		btnSalvar.setEnabled(true);
		cbxCategoria.setEnabled(true);
		cbxSubCategoria.setEnabled(true);
		rdbtnSim.setEnabled(true);
		rdbtnNo.setEnabled(true);
		rdbtnDisponivel.setEnabled(true);
		rdbtnIndisponivel.setEnabled(true);
		panelImagem.hablitaPanel();
	}
	
	/**
	 * Metodo que desabilita JTexteField, JComboBox e JButton do cadastro
	 * 
	 * @return void
	 */
	public void desabilitaDados(){
		
		txtNome.setEnabled(false);
		txtIngredientes.setEnabled(false);
		txtPreco.setEnabled(false);
		btnLimpar.setEnabled(false);
		btnSalvar.setEnabled(false);
		cbxCategoria.setEnabled(false);
		cbxSubCategoria.setEnabled(false);
		rdbtnSim.setEnabled(false);
		rdbtnNo.setEnabled(false);
		rdbtnDisponivel.setEnabled(false);
		rdbtnIndisponivel.setEnabled(false);
		panelImagem.desabilitaPanel();
		
	}
	
	/**
	 * Metodo que limpa os campos
	 * 
	 * @return void
	 */
	public void limpar(){
		
		txtNome.setText(null);
		txtIngredientes.setText(null);
		txtPreco.setText(null);
		cbxCategoria.setSelectedItem(null);
		cbxSubCategoria.setSelectedItem(null);
		bgExibir.clearSelection();
		bgDisponivel.clearSelection();
		panelImagem.limpaPanel();
		
	} 

	/**
	 * Construtor
	 */
	public Produtos() {
		setFrameIcon(new ImageIcon(Produtos.class.getResource("/Imagens/produtos.png")));
		setAutoscrolls(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Minister - Produtos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(5, 5, 842, 611);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panelTabela = new JPanel();
		panelTabela.setBorder(new TitledBorder(null, "Produtos Cadastrados", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.add(panelTabela);
		panelTabela.setLayout(new BorderLayout(0, 0));
		
		JPanel panelPesquisa = new JPanel();
		panelTabela.add(panelPesquisa, BorderLayout.NORTH);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		
		JComboBox cbxCategoriaPesquisa = new JComboBox();
		cbxCategoriaPesquisa.setToolTipText("Selecione a categoria do produto");
		
		JComboBox cbxSubCategoriaPesquisa = new JComboBox();
		cbxSubCategoriaPesquisa.setToolTipText("selecione a Sub-Categoria do produto");
		
		JLabel lblSubcategoria = new JLabel("Sub-Categoria:");
		
		txtBuscar = new JTextFieldPesquisar();
		txtBuscar.setToolTipText("Digite aqui para efetuar a pesquisa");
		txtBuscar.setColumns(10);
		GroupLayout gl_panelPesquisa = new GroupLayout(panelPesquisa);
		gl_panelPesquisa.setHorizontalGroup(
			gl_panelPesquisa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPesquisa.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCategoria)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxCategoriaPesquisa, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(54)
					.addComponent(lblSubcategoria)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxSubCategoriaPesquisa, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(txtBuscar, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelPesquisa.setVerticalGroup(
			gl_panelPesquisa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPesquisa.createSequentialGroup()
					.addGroup(gl_panelPesquisa.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxSubCategoriaPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSubcategoria)
						.addComponent(cbxCategoriaPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategoria)
						.addComponent(txtBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelPesquisa.setLayout(gl_panelPesquisa);
		
		JScrollPane scrollPaneTabela = new JScrollPane();
		panelTabela.add(scrollPaneTabela, BorderLayout.CENTER);
		
		 modelProdutos = new ProdutosTableModel(dao.listaProdutosTabela());
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(java.awt.event.MouseEvent e){
        		int row=table.rowAtPoint(e.getPoint());
        		
        		double preco = dao.listaProdutosTabela().get(row).getPreco();

        		desabilitaDados();
        		cbxCategoria.setSelectedItem(dao.listaProdutosTabela().get(row).getCategoria());
        		cbxSubCategoria.setSelectedItem(dao.listaProdutosTabela().get(row).getSubCategoria());
        		txtNome.setText(dao.listaSubCategoriaTabela().get(row).getNome());
        		txtIngredientes.setText(dao.listaProdutosTabela().get(row).getIngredientes());
        		txtPreco.setText(String.valueOf(preco));
        		spinner.setValue(dao.listaProdutosTabela().get(row).getTempoPreparo());
        		
        		if(dao.listaProdutosTabela().get(row).getDisponivel().equals("Y")){
        			rdbtnIndisponivel.setSelected(false);
        			rdbtnDisponivel.setSelected(true);
        		}
        		else{
        			rdbtnDisponivel.setSelected(false);
        			rdbtnIndisponivel.setSelected(true);
        		}
        		
        		if(dao.listaProdutosTabela().get(row).getExibir().equals("Y")){
        			rdbtnNo.setSelected(false);
        			rdbtnSim.setSelected(true);
        		}
        		else{
        			rdbtnSim.setSelected(false);
        			rdbtnNo.setSelected(true);
        		}
        		
        		panelImagem.exibeDadosPanel("C:\\wamp\\www\\Minister1.0\\Pagina\\imagens\\produtos\\"+dao.listaProdutosTabela().get(row).getImagem());
        		panelImagem.setNomeImagem(dao.listaProdutosTabela().get(row).getImagem());
        		
        		produtos.setId(dao.listaProdutosTabela().get(row).getId());
        		//categoriaSubCategoria.setImagem(dao.listaSubCategoriaTabela().get(row).getImagem());
        		//categoriaSubCategoria.setIdSubCategoria(dao.listaSubCategoriaTabela().get(row).getIdSubCategoria());
        	}
		});
		table.setModel(modelProdutos);
		scrollPaneTabela.setViewportView(table);
		
		JPanel panelDadosProduto = new JPanel();
		panelDadosProduto.setBorder(new TitledBorder(null, "Dados do Produto", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.add(panelDadosProduto);
		panelDadosProduto.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCadastro = new JPanel();
		panelDadosProduto.add(panelCadastro);
		
		JLabel lblImagem = new JLabel("Imagem:");
		lblImagem.setBounds(6, 15, 49, 16);
		
		panelImagem = new JPanelImagem();
		panelImagem.setBounds(6, 33, 196, 160);
		
		JLabel lblCategoria_1 = new JLabel("Categoria:");
		lblCategoria_1.setBounds(241, 38, 57, 16);
		
		JLabel lblSubcategoria_1 = new JLabel("Sub-Categoria:");
		lblSubcategoria_1.setBounds(241, 83, 83, 16);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(541, 38, 37, 16);
		
		JLabel lblIngredientes = new JLabel("Ingredientes");
		lblIngredientes.setBounds(541, 83, 69, 16);
		
		JLabel lblTempoDePreparo = new JLabel("Tempo de Preparo:");
		lblTempoDePreparo.setBounds(541, 164, 106, 16);
		
		cbxSubCategoria = new JComboBox();
		cbxSubCategoria.setBounds(330, 78, 155, 26);
		//cbxSubCategoria.
		cbxSubCategoria.setEditable(true);
		cbxSubCategoria.setEnabled(false);
		cbxSubCategoria.setToolTipText("Selecione a Sub-Categoria do Produto");
		
		cbxCategoria = new JComboBox();
		cbxCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					cbxSubCategoria.setModel(new DefaultComboBoxModel<String>(new Vector<String>(dao.listaSubCategoria((String)cbxCategoria.getSelectedItem()))));
					cbxSubCategoria.setSelectedItem(null);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cbxCategoria.setBounds(330, 33, 155, 26);
		cbxCategoria.setModel(new DefaultComboBoxModel<String>(new Vector<String>(dao.listaCategorias())));
		cbxCategoria.setSelectedItem(null);
		cbxCategoria.setEnabled(false);
		cbxCategoria.setEditable(true);
		cbxCategoria.setToolTipText("Selecione a Categoria do Produto");
		
		txtNome = new JTextField();
		txtNome.setBounds(622, 32, 170, 28);
		txtNome.setEnabled(false);
		txtNome.setToolTipText("Digite o nome do produto");
		txtNome.setColumns(10);
		
		txtIngredientes = new JTextField();
		txtIngredientes.setBounds(622, 77, 170, 28);
		txtIngredientes.setEnabled(false);
		txtIngredientes.setToolTipText("Digite os ingredientes do produto");
		txtIngredientes.setColumns(10);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setBounds(541, 123, 55, 16);
		
		JLabel lblExibir = new JLabel("Exibir:");
		lblExibir.setBounds(241, 123, 55, 16);
		
		bgExibir = new ButtonGroup();
		
		rdbtnSim = new JRadioButton("Sim");
		rdbtnSim.setEnabled(false);
		rdbtnSim.setBounds(330, 122, 57, 18);
		bgExibir.add(rdbtnSim);
		
		rdbtnNo = new JRadioButton("N\u00E3o");
		rdbtnNo.setEnabled(false);
		rdbtnNo.setBounds(405, 122, 49, 18);
		bgExibir.add(rdbtnNo);
		
		bgDisponivel = new ButtonGroup();
		
		JLabel lblExibirComo = new JLabel("Disponivel:");
		lblExibirComo.setBounds(241, 164, 69, 16);
		
		rdbtnDisponivel = new JRadioButton("Sim");
		rdbtnDisponivel.setEnabled(false);
		rdbtnDisponivel.setBounds(330, 163, 57, 18);
		bgDisponivel.add(rdbtnDisponivel);
		
		rdbtnIndisponivel = new JRadioButton("N\u00E3o");
		rdbtnIndisponivel.setEnabled(false);
		rdbtnIndisponivel.setBounds(404, 163, 115, 18);
		bgDisponivel.add(rdbtnIndisponivel);
		
		panelCadastro.setLayout(null);
		panelCadastro.add(lblImagem);
		panelCadastro.add(panelImagem);
		
		panelCadastro.add(lblCategoria_1);
		panelCadastro.add(lblSubcategoria_1);
		panelCadastro.add(lblExibir);
		panelCadastro.add(lblExibirComo);
		panelCadastro.add(cbxCategoria);
		panelCadastro.add(rdbtnSim);
		panelCadastro.add(rdbtnNo);
		panelCadastro.add(cbxSubCategoria);
		panelCadastro.add(lblIngredientes);
		panelCadastro.add(lblPreo);
		panelCadastro.add(lblNome);
		panelCadastro.add(txtNome);
		panelCadastro.add(txtIngredientes);
		panelCadastro.add(rdbtnDisponivel);
		panelCadastro.add(rdbtnIndisponivel);
		panelCadastro.add(lblTempoDePreparo);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
		spinner.setBounds(660, 157, 77, 28);
		panelCadastro.add(spinner);
		
		JLabel lblMinutos = new JLabel("minutos");
		lblMinutos.setBounds(746, 165, 46, 14);
		panelCadastro.add(lblMinutos);
		
		JLabel lblR = new JLabel("R$");
		lblR.setBounds(630, 124, 20, 14);
		panelCadastro.add(lblR);
		
		txtPreco = new JNumberFormatField();
		txtPreco.setBounds(622, 117, 170, 28);
		txtPreco.setEnabled(false);
		txtPreco.setToolTipText("Digite o pre\u00E7o do produto");
		txtPreco.setColumns(10);
		panelCadastro.add(txtPreco);
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelDadosProduto.add(panelBotoes, BorderLayout.SOUTH);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.setIcon(new ImageIcon(Produtos.class.getResource("/Imagens/salvar.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(validaCampos()){
					JOptionPane.showMessageDialog(null, "Não");
				}
				else{
					salvar();
					if(alterar){
						dao.alterarProduto(produtos);
						alterar = false;
						
					}
					else{
						dao.insereProduto(produtos);

					}
					
					limpar();
					
					
				}
			}
		});
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				habilitaDados();
			}
		});
		btnNovo.setIcon(new ImageIcon(Produtos.class.getResource("/Imagens/novo.png")));
		panelBotoes.add(btnNovo);
		panelBotoes.add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limpar();
			}
		});
		btnLimpar.setEnabled(false);
		btnLimpar.setIcon(new ImageIcon(Produtos.class.getResource("/Imagens/limpar.png")));
		panelBotoes.add(btnLimpar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				habilitaDados();
				alterar();
			}
		});
		btnAlterar.setIcon(new ImageIcon(Produtos.class.getResource("/Imagens/atualizar.png")));
		panelBotoes.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				deletar();
			}
		});
		btnExcluir.setIcon(new ImageIcon(Produtos.class.getResource("/Imagens/lixeira.png")));
		panelBotoes.add(btnExcluir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limpar();
				desabilitaDados();
			}
		});
		btnCancelar.setIcon(new ImageIcon(Produtos.class.getResource("/Imagens/cancelar.png")));
		panelBotoes.add(btnCancelar);
	}

	@Override
	void salvar() {
		
		String preco = txtPreco.getText().replace(".", "");
		preco = preco.replace(",", ".");
		
		//setando os dados
		produtos.setCategoria((String) cbxCategoria.getSelectedItem());
		produtos.setSubCategoria((String) cbxSubCategoria.getSelectedItem());
		produtos.setDisponivel(rdbtnDisponivel.isSelected() ? "Y" : "N");
		produtos.setExibir(rdbtnSim.isSelected() ? "Y" : "N");
		produtos.setNomeProduto(txtNome.getText());
		produtos.setIngredientes(txtIngredientes.getText());
		produtos.setTempoPreparo((int) spinner.getValue());
		produtos.setPreco(Double.parseDouble(preco));
		
		File destino = new File("C:\\wamp\\www\\Minister1.0\\Pagina\\imagens\\produtos\\"+panelImagem.getNomeImagem());
		
		produtos.setImagem(panelImagem.getNomeImagem());
		if(panelImagem.getCaminhoImagem() != null){
			try {
				JPanelImagem.copiarArquivo(panelImagem.getCaminhoImagem(), destino);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	void alterar() {
		alterar = true;
		
	}

	@Override
	void deletar() {
		
		dao.deletaProdutos(produtos);
		
	}

	@Override
	boolean validaCampos() {
		
		//Declaração das variaveis
				boolean campoVazio;
				
				String cbxCategoria;
				String cbxSubCategoria;
				String preco = txtPreco.getText().replace(".", "");
				preco = preco.replace(",", ".");
				
				cbxCategoria = (String) this.cbxCategoria.getSelectedItem();
				cbxSubCategoria = (String) this.cbxSubCategoria.getSelectedItem();
				
				//Se alguma campo estiver nulo, retorno sera true
				if(txtNome.getText().equals(null) | txtNome.getText().equals("") ||
				  txtIngredientes.getText().equals(null) | txtIngredientes.getText().equals("") ||
				  txtPreco.getText().equals(null) | txtPreco.getText().equals(null) ||
				  !rdbtnDisponivel.isSelected() & !rdbtnIndisponivel.isSelected() ||
				  !rdbtnNo.isSelected() & !rdbtnSim.isSelected() ||
				  cbxCategoria == null | cbxCategoria == "" ||
				  cbxSubCategoria.equals(null) | cbxSubCategoria.equals("")){
					
					campoVazio = true;
					
				}
				else{
					
					campoVazio = false;
				}
				
				return campoVazio;
			}
}
