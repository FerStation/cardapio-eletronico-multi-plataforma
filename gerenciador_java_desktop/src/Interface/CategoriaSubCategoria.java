package Interface;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.Rectangle;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableRowSorter;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JTextArea;

import BancoDeDados.Dao;
import PacoteLogico.JComboBoxCategoria;
import PacoteLogico.JPanelImagem;
import PacoteLogico.JTextFieldPesquisar;
import Tabelas.Categoria;
import Tabelas.CategoriaTableModel;
import Tabelas.SubCategoria;
import Tabelas.SubCategoriaTableModel;
import Trasferencia.DtoCategoriaSubCategoria;


public class CategoriaSubCategoria extends JInternalFramePai {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanelImagem panelImagem;
	
	private JTextField txtPesquisarSubCategorias;
	private JTextField txtPesquisarCategorias;
	private JTextField txtNome;
	
	private JTextArea textAreaDescricao;
	
	private JComboBoxCategoria cbxCategoria;
	private JComboBoxCategoria cbxCategoriaPesquisa;
	
	private ButtonGroup btnGroup;
	
	private JRadioButton rdbtnCategoria;
	private JRadioButton rdbtnSubcategoria;
	
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	
	private JTable tableCategoria;
	private JTable tableSubCategoria;
	
	private JScrollPane scrollPaneCategoria;
	
	private CategoriaTableModel modelCategoria;
	private SubCategoriaTableModel modelSubCategoria;
	
	private boolean alterar;
	
	Dao dao = new Dao();
	DtoCategoriaSubCategoria categoriaSubCategoria = new DtoCategoriaSubCategoria();
	

	
	
	public void habilitaDados(){
		
		txtNome.setEnabled(true);
		textAreaDescricao.setEnabled(true);
		rdbtnCategoria.setEnabled(true);
		rdbtnSubcategoria.setEnabled(true);
		btnSalvar.setEnabled(true);
		btnLimpar.setEnabled(true);
		panelImagem.hablitaPanel();
		
	}
	
	public void desabilitaDados(){
		
		txtNome.setEnabled(false);
		textAreaDescricao.setEnabled(false);
		cbxCategoria.setEnabled(false);
		rdbtnCategoria.setEnabled(false);
		rdbtnSubcategoria.setEnabled(false);
		btnSalvar.setEnabled(false);
		btnLimpar.setEnabled(false);
		panelImagem.desabilitaPanel();
		
	}
	
	public boolean validaCampos(){
		
		boolean camposVazio = true;
		
		String cbxCategoria = (String) this.cbxCategoria.getSelectedItem();
		
		if(txtNome.getText().equals(null) | txtNome.getText().equals("") ||
			panelImagem.validaDadospanel() == false ||
		   !rdbtnCategoria.isSelected() & !rdbtnSubcategoria.isSelected()){
			
			camposVazio = true;
		}
		else{
			if(rdbtnSubcategoria.isSelected() & (cbxCategoria == null | cbxCategoria == "")){
				camposVazio = true;
			}
			else{
				camposVazio = false;
			}
			
		}
		
		return camposVazio;
		
	}
	
	public void limpar(){
		
		txtNome.setText(null);
		textAreaDescricao.setText(null);
		cbxCategoria.setSelectedItem(null);
		btnGroup.clearSelection();
		panelImagem.limpaPanel();
		
	}
	
	public void salvar(){
		
		File destino = null;
		
		if(rdbtnSubcategoria.isSelected()){
			categoriaSubCategoria.setCategoria((String)cbxCategoria.getSelectedItem());
			destino = new File("C:\\wamp\\www\\Minister1.0\\Pagina\\imagens\\subcategoria\\"+panelImagem.getNomeImagem());
		}
		
		if(rdbtnCategoria.isSelected()){
			destino = new File("C:\\wamp\\www\\Minister1.0\\Pagina\\imagens\\categoria\\"+panelImagem.getNomeImagem());
		}
		
		categoriaSubCategoria.setNome(txtNome.getText());
		categoriaSubCategoria.setImagem(panelImagem.getNomeImagem());
		categoriaSubCategoria.setDescricao(textAreaDescricao.getText());
		if(panelImagem.getCaminhoImagem() != null){
			try {
				JPanelImagem.copiarArquivo(panelImagem.getCaminhoImagem(), destino);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void alterar(){
		
		if(rdbtnCategoria.isSelected()){
			dao.alterarCategoria(categoriaSubCategoria);
		}
		else if(rdbtnSubcategoria.isSelected()){
			dao.alterarSubCategoria(categoriaSubCategoria);
			
		}
		
	}
	
	public void deletar(){
		
		int res = JOptionPane.showConfirmDialog(null,"Deseja realmente deletar? ","Aviso",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(res == JOptionPane.YES_OPTION){
			
			if(rdbtnCategoria.isSelected()){
				
				dao.deletaCategoria(categoriaSubCategoria);
				
				modelCategoria.removeRowAt(tableCategoria.getSelectedRow());
				
			}
			else if(rdbtnSubcategoria.isSelected()){
				
				dao.deletaSubCategoria(categoriaSubCategoria);
				
				modelSubCategoria.removeRowAt(tableSubCategoria.getSelectedRow());
				
			}
			
		}
	}

	/**
	 * Create the frame.
	 */
	public CategoriaSubCategoria() {
		setFrameIcon(new ImageIcon(CategoriaSubCategoria.class.getResource("/Imagens/produtos.png")));
		setAutoscrolls(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Minister - Produtos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setBounds(5, 5, 842, 611);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent arg0) {
				
				limpar();
				habilitaDados();
				
			}
		});
		btnNovo.setIcon(new ImageIcon(CategoriaSubCategoria.class.getResource("/Imagens/novo.png")));
		panel_2.add(btnNovo);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent arg0) {
				
				if(validaCampos()){
					JOptionPane.showMessageDialog(null, "NÂO OK");
				}
				else{
					salvar();
					if(alterar){
						if(rdbtnCategoria.isSelected()){
							dao.alterarCategoria(categoriaSubCategoria);
						}
						else if(rdbtnSubcategoria.isSelected()){
							dao.alterarSubCategoria(categoriaSubCategoria);
						}
						alterar = false;
					}
					else{
						if(rdbtnSubcategoria.isSelected()){
							dao.insereSubCategoria(categoriaSubCategoria);
							//modelSubCategoria.addLinha(new SubCategoria(categoriaSubCategoria.getIdSubCategoria(), categoriaSubCategoria.getImagem(), categoriaSubCategoria.getDescricao(), categoriaSubCategoria.getCategoria(), categoriaSubCategoria.getNome()));
							
						}
						
						if(rdbtnCategoria.isSelected()){
							dao.insereCategoria(categoriaSubCategoria);
							
						}
					}
					
					limpar();
					
				}
			}
		});
		btnSalvar.setIcon(new ImageIcon(CategoriaSubCategoria.class.getResource("/Imagens/salvar.png")));
		btnSalvar.setEnabled(false);
		panel_2.add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				limpar();
			}
		});
		btnLimpar.setIcon(new ImageIcon(CategoriaSubCategoria.class.getResource("/Imagens/limpar.png")));
		btnLimpar.setEnabled(false);
		panel_2.add(btnLimpar);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				habilitaDados();
				alterar = true;
				
			}
		});
		btnAlterar.setIcon(new ImageIcon(CategoriaSubCategoria.class.getResource("/Imagens/atualizar.png")));
		panel_2.add(btnAlterar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				deletar();
			}
		});
		btnExcluir.setIcon(new ImageIcon(CategoriaSubCategoria.class.getResource("/Imagens/lixeira.png")));
		panel_2.add(btnExcluir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				limpar();
				desabilitaDados();
			}
		});
		btnCancelar.setIcon(new ImageIcon(CategoriaSubCategoria.class.getResource("/Imagens/cancelar.png")));
		panel_2.add(btnCancelar);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Sub-Categorias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(421, 6, 415, 292);
		panel_1.add(panel_3);
		
		modelSubCategoria = new SubCategoriaTableModel(dao.listaSubCategoriaTabela());
		
		//RowSorter
        final TableRowSorter<SubCategoriaTableModel> sorterSubCategoria = new TableRowSorter<SubCategoriaTableModel>(modelSubCategoria);
		
		txtPesquisarSubCategorias = new JTextFieldPesquisar();
		txtPesquisarSubCategorias.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent arg0) {
        		String text = txtPesquisarSubCategorias.getText();
                if (text.length() == 0) {
                  sorterSubCategoria.setRowFilter(null);
                } else {
                  try {
                    sorterSubCategoria.setRowFilter(
                        RowFilter.regexFilter("(?i)"+text));
                  } catch (PatternSyntaxException pse) {
                    System.err.println("Bad regex pattern");
                  }
                }
        	}
        });
		txtPesquisarSubCategorias.setBounds(237, 17, 159, 28);
		panel_3.add(txtPesquisarSubCategorias);
		
		tableSubCategoria = new JTable();
		tableSubCategoria.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(java.awt.event.MouseEvent e){
        		int row=tableSubCategoria.rowAtPoint(e.getPoint());

        		tableCategoria.clearSelection();
        		desabilitaDados();
        		rdbtnSubcategoria.setSelected(true);
        		cbxCategoria.setSelectedItem(dao.listaSubCategoriaTabela().get(row).getNomeCategoria());
        		txtNome.setText(dao.listaSubCategoriaTabela().get(row).getNome());
        		textAreaDescricao.setText(dao.listaSubCategoriaTabela().get(row).getDescricao());
        		panelImagem.exibeDadosPanel("C:\\wamp\\www\\Minister1.0\\Pagina\\imagens\\subcategoria\\"+dao.listaSubCategoriaTabela().get(row).getImagem());
        		panelImagem.setNomeImagem(dao.listaSubCategoriaTabela().get(row).getImagem());
        		categoriaSubCategoria.setImagem(dao.listaSubCategoriaTabela().get(row).getImagem());
        		categoriaSubCategoria.setIdSubCategoria(dao.listaSubCategoriaTabela().get(row).getIdSubCategoria());
        	}
		});
		tableSubCategoria.setModel(modelSubCategoria);
		tableSubCategoria.setRowSorter(sorterSubCategoria);
		
		JScrollPane scrollPaneSubCategoria = new JScrollPane();
		scrollPaneSubCategoria.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneSubCategoria.setBounds(16, 57, 380, 220);
		scrollPaneSubCategoria.setViewportView(tableSubCategoria);
		panel_3.add(scrollPaneSubCategoria);
		
		cbxCategoriaPesquisa = new JComboBoxCategoria();
		cbxCategoriaPesquisa.setBounds(77, 18, 148, 26);
		cbxCategoriaPesquisa.addItem("Todos");
		cbxCategoriaPesquisa.setSelectedItem("Todos");
		panel_3.add(cbxCategoriaPesquisa);
		
		JLabel label = new JLabel("Categoria:");
		label.setBounds(16, 23, 67, 16);
		panel_3.add(label);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(null, "Cetegorias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(6, 6, 415, 292);
		panel_1.add(panel_4);
		
		//cria o modelo de Produto
		modelCategoria = new CategoriaTableModel(dao.listaCategoriaTabela());
		
		//RowSorter
        final TableRowSorter<CategoriaTableModel> sorterCategoria = new TableRowSorter<CategoriaTableModel>(modelCategoria);
		
		txtPesquisarCategorias = new JTextFieldPesquisar();
		txtPesquisarCategorias.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent arg0) {
        		String text = txtPesquisarCategorias.getText();
                if (text.length() == 0) {
                  sorterCategoria.setRowFilter(null);
                } else {
                  try {
                    sorterCategoria.setRowFilter(
                        RowFilter.regexFilter("(?i)"+text));
                  } catch (PatternSyntaxException pse) {
                    System.err.println("Bad regex pattern");
                  }
                }
        	}
        });
		txtPesquisarCategorias.setBounds(17, 17, 237, 28);
		panel_4.add(txtPesquisarCategorias);
				        
		tableCategoria = new JTable();
		tableCategoria.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(java.awt.event.MouseEvent e){
        		int row=tableCategoria.rowAtPoint(e.getPoint());

        		tableSubCategoria.clearSelection();
        		desabilitaDados();
        		rdbtnCategoria.setSelected(true);
        		txtNome.setText(dao.listaCategoriaTabela().get(row).getNome());
        		textAreaDescricao.setText(dao.listaCategoriaTabela().get(row).getDescricao());
        		panelImagem.exibeDadosPanel("C:\\wamp\\www\\Minister1.0\\Pagina\\imagens\\categoria\\"+dao.listaCategoriaTabela().get(row).getImagem());
        		panelImagem.setNomeImagem(dao.listaCategoriaTabela().get(row).getImagem());
        		categoriaSubCategoria.setIdcategoria(dao.listaCategoriaTabela().get(row).getIdcategoria());
        		categoriaSubCategoria.setImagem(dao.listaCategoriaTabela().get(row).getImagem());
        	}
		});
		tableCategoria.setModel(modelCategoria);
		tableCategoria.setRowSorter(sorterCategoria);
		
		scrollPaneCategoria = new JScrollPane();
		scrollPaneCategoria.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneCategoria.setBounds(16, 57, 380, 220);
		scrollPaneCategoria.setViewportView(tableCategoria);
		panel_4.add(scrollPaneCategoria);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Dados do Cadastro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(6, 298, 818, 216);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		panelImagem = new JPanelImagem();
		panelImagem.setBounds(17, 38, 196,160);
		panel_5.add(panelImagem);

		
		JLabel label_2 = new JLabel("Imagem:");
		label_2.setBounds(16, 19, 49, 16);
		panel_5.add(label_2);
		
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(243, 20, 64, 14);
		panel_5.add(lblDescrio);
		
		JLabel lblCadastrar = new JLabel("Cadastrar:");
		lblCadastrar.setBounds(480, 41, 64, 14);
		panel_5.add(lblCadastrar);
		
		rdbtnCategoria = new JRadioButton("Categoria");
		rdbtnCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cbxCategoria.setSelectedItem(null);
				cbxCategoria.setEnabled(false);
			}
		});
		rdbtnCategoria.setEnabled(false);
		rdbtnCategoria.setBounds(543, 38, 77, 18);
		panel_5.add(rdbtnCategoria);
		
		rdbtnSubcategoria = new JRadioButton("Sub-Categoria");
		rdbtnSubcategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cbxCategoria.setEnabled(true);
			}
		});
		rdbtnSubcategoria.setEnabled(false);
		rdbtnSubcategoria.setBounds(632, 38, 115, 18);
		panel_5.add(rdbtnSubcategoria);
		
		btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnCategoria);
		btnGroup.add(rdbtnSubcategoria);
		
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(480, 92, 62, 14);
		panel_5.add(lblCategoria);
		
		cbxCategoria = new JComboBoxCategoria();
		cbxCategoria.setEnabled(false);
		cbxCategoria.setBounds(541, 85, 148, 26);
		panel_5.add(cbxCategoria);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(479, 144, 46, 14);
		panel_5.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(540, 138, 148, 26);
		txtNome.setEnabled(false);
		panel_5.add(txtNome);
		txtNome.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(243, 38, 205, 126);
		panel_5.add(scrollPane_2);
		
		textAreaDescricao = new JTextArea();
		textAreaDescricao.setEnabled(false);
		textAreaDescricao.setRows(10);
		textAreaDescricao.setLineWrap(true);
		scrollPane_2.setViewportView(textAreaDescricao);

	}

}
