package Interface;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.text.AttributeSet;  
import javax.swing.text.BadLocationException;  
import javax.swing.text.PlainDocument;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.Random;
import java.util.regex.PatternSyntaxException;

import javax.swing.DefaultComboBoxModel;  

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

import BancoDeDados.Dao;
import PacoteLogico.JTextFieldPesquisar;
import Tabelas.CategoriaTableModel;
import Tabelas.TMUsuarios;
import Tabelas.UsuariosTableModel;
import Trasferencia.DtoUsuario;


public class Usuarios extends JInternalFramePai {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UsuariosTableModel modelUsuarios;
	
	private JPanel contentPane;
	
	private JTextField txtPesquisar;
	private JTable table;
	
	private JTextField txtNome;
	private JTextField txtLogin;
	private JTextField txtEmail;
	
	private JComboBox cbxNvAcesso;
	
	private ButtonGroup btnGroupAtivo;
	
	private JRadioButton rdbtnAtivo;
	private JRadioButton rdbtnInativo;
	
	private JFormattedTextField formattedTxtCpf;
	private JFormattedTextField formattedTxtRg;
	private JFormattedTextField formattedTxtTel;
	private JFormattedTextField formattedTxtCel;
	
	private JButton btnSalvar;
	private JButton btnLimpar;
	
	private boolean alterar;
	
	DtoUsuario usuario = new DtoUsuario();
	Dao dao = new Dao();
	CPFValidator validador = new CPFValidator();
	

	public void habilitaDados() {
		
		rdbtnAtivo.setEnabled(true);
		rdbtnInativo.setEnabled(true);
		
		cbxNvAcesso.setEnabled(true);
		
		txtLogin.setEnabled(true);
		txtNome.setEnabled(true);
		txtEmail.setEnabled(true);
		
		formattedTxtRg.setEnabled(true);				
		formattedTxtCpf.setEnabled(true);
		formattedTxtTel.setEnabled(true);
		formattedTxtCel.setEnabled(true);
		
		btnSalvar.setEnabled(true);
		btnLimpar.setEnabled(true);
	}
	
	public void desabilitaDados(){
		
		rdbtnAtivo.setEnabled(false);
		rdbtnInativo.setEnabled(false);
		
		cbxNvAcesso.setEnabled(false);
		
		txtLogin.setEnabled(false);
		txtNome.setEnabled(false);
		txtEmail.setEnabled(false);
		
		formattedTxtRg.setEnabled(false);				
		formattedTxtCpf.setEnabled(false);
		formattedTxtTel.setEnabled(false);
		formattedTxtCel.setEnabled(false);
		
		btnSalvar.setEnabled(false);
		btnLimpar.setEnabled(false);
	}
	
	public void limpar(){
		
		btnGroupAtivo.clearSelection();
		
		cbxNvAcesso.setSelectedItem(null);
		
		txtLogin.setText(null);
		txtNome.setText(null);
		txtEmail.setText(null);
		
		formattedTxtRg.setText(null);				
		formattedTxtCpf.setText(null);
		formattedTxtTel.setText(null);
		formattedTxtCel.setText(null);
	}
	
	public boolean validaCampos(){
		
		boolean camposValidos = false;
		
		if((!rdbtnAtivo.isSelected() & !rdbtnInativo.isSelected()) || 
			cbxNvAcesso.getSelectedItem() == null ||
			txtEmail.getText().equals("") | txtEmail.getText().equals(null) ||
			txtLogin.getText().equals("") | txtLogin.getText().equals(null) ||
			txtNome.getText().equals("")  | txtNome.getText().equals(null)  ||
			formattedTxtRg.equals("")     | formattedTxtRg.equals(null)  ||				
			formattedTxtCpf.equals("")    | formattedTxtCpf.equals(null) ||
			formattedTxtTel.equals("")    | formattedTxtTel.equals(null) ||
			formattedTxtCel.equals("")    | formattedTxtCel.equals(null)){
			
			camposValidos = false;
		}
		else{
			 camposValidos = true;
		}
		
		return camposValidos;
	}
	
	public String gerarSenha(){
		
		String senha = null;
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		Random random = new Random();
		
		int index = -1;
		for(int i = 0; i < 5; i++){
			
			index = random.nextInt(letras.length());
			senha += letras.substring(index, index +1);
		}
		
		return senha;
	}
	
	public void salvar(){
				
		usuario.setUsuarioAtivo(rdbtnAtivo.isSelected() ? "Y" : "N");
		usuario.setNvAcesso((String)cbxNvAcesso.getSelectedItem());
		usuario.setLogin(txtLogin.getText());
		usuario.setNome(txtNome.getText());
		usuario.setSenha(gerarSenha());
		usuario.setRg(formattedTxtRg.getText());
		usuario.setCpf(formattedTxtCpf.getText());
		usuario.setEmail(txtEmail.getText());
		usuario.setTelefone(formattedTxtTel.getText());
		usuario.setCelular(formattedTxtCel.getText());
	}
	
	public boolean validaCpf(String cpf){
		
		boolean cpfValido = false;
		
		try {
			
			validador.assertValid(cpf);
			cpfValido = true;
			
		} catch (InvalidStateException e) {
			
			cpfValido = false;
			
		}
		
		return cpfValido;
	}

	/**
	 * Create the frame.
	 */
	public Usuarios() {
		setClosable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Minister - Usuarios");
		setBounds(5, 5 ,842, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panelUsuariosCadastrados = new JPanel();
		panelUsuariosCadastrados.setBorder(new TitledBorder(null, "Usuarios Cadastrados", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelUsuariosCadastrados.setBounds(0, 0, 826, 276);
		panel.add(panelUsuariosCadastrados);
		panelUsuariosCadastrados.setLayout(null);
		
		modelUsuarios = new UsuariosTableModel(dao.listaUsuariosTabela());
		
		//RowSorter
        final TableRowSorter<UsuariosTableModel> sorterUsuarios = new TableRowSorter<UsuariosTableModel>(modelUsuarios);
		
		txtPesquisar = new JTextFieldPesquisar();
		txtPesquisar.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent arg0) {
        		String text = txtPesquisar.getText();
                if (text.length() == 0) {
                  sorterUsuarios.setRowFilter(null);
                } else {
                  try {
                    sorterUsuarios.setRowFilter(
                        RowFilter.regexFilter("(?i)"+text));
                  } catch (PatternSyntaxException pse) {
                    System.err.println("Bad regex pattern");
                  }
                }
        	}
        });
		txtPesquisar.setToolTipText("Digite aqui para efetuar a pesquisa");
		txtPesquisar.setBounds(17, 21, 201, 28);
		panelUsuariosCadastrados.add(txtPesquisar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 55, 814, 215);
		panelUsuariosCadastrados.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(java.awt.event.MouseEvent e){
        		int row=table.rowAtPoint(e.getPoint());

        		desabilitaDados();
        		usuario.setLogin(dao.listaUsuariosTabela().get(row).getLogin());
        		cbxNvAcesso.setSelectedItem(dao.listaUsuariosTabela().get(row).getNivelAcesso());
        		txtLogin.setText(dao.listaUsuariosTabela().get(row).getLogin());
        		txtNome.setText(dao.listaUsuariosTabela().get(row).getNome());
        		txtEmail.setText(dao.listaUsuariosTabela().get(row).getEmail());
        		formattedTxtRg.setText(dao.listaUsuariosTabela().get(row).getRg());
        		formattedTxtCpf.setText(dao.listaUsuariosTabela().get(row).getCpf());
        		formattedTxtTel.setText(dao.listaUsuariosTabela().get(row).getTel());
        		formattedTxtCel.setText(dao.listaUsuariosTabela().get(row).getCel());
        	}
		});
		table.setModel(modelUsuarios);
		table.setRowSorter(sorterUsuarios);
		scrollPane.setViewportView(table);
		
		JPanel panelCadastro = new JPanel();
		panelCadastro.setBorder(new TitledBorder(null, "Dados do Cadastro", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelCadastro.setBounds(0, 279, 826, 284);
		panel.add(panelCadastro);
		panelCadastro.setLayout(null);
		
		JPanel panelDados = new JPanel();
		panelDados.setLayout(null);
		panelDados.setBounds(16, 16, 798, 202);
		panelCadastro.add(panelDados);
		
		JLabel lblNivelDeAcesso = new JLabel("Nivel de Acesso:");
		lblNivelDeAcesso.setBounds(6, 90, 91, 16);
		panelDados.add(lblNivelDeAcesso);
		
		cbxNvAcesso = new JComboBox();
		cbxNvAcesso.setEnabled(false);
		cbxNvAcesso.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Usuario"}));
		cbxNvAcesso.setToolTipText("Selecione o nivel de acesso do usuario");
		cbxNvAcesso.setBounds(119, 85, 153, 26);
		panelDados.add(cbxNvAcesso);
		
		JLabel lblRg = new JLabel("RG:");
		lblRg.setBounds(319, 90, 48, 16);
		panelDados.add(lblRg);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(319, 141, 37, 16);
		panelDados.add(lblCpf);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(319, 39, 37, 16);
		panelDados.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setToolTipText("Digite o nome do usuario");
		txtNome.setColumns(10);
		txtNome.setBounds(368, 33, 140, 28);
		panelDados.add(txtNome);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(541, 141, 48, 16);
		panelDados.add(lblCelular);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(6, 147, 37, 16);
		panelDados.add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setEnabled(false);
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
		txtLogin.setToolTipText("Digite o login do usuario");
		txtLogin.setColumns(10);
		txtLogin.setBounds(119, 135, 153, 28);
		panelDados.add(txtLogin);
		
		JLabel lblUsuarioAtivo = new JLabel("Usuario Ativo:");
		lblUsuarioAtivo.setToolTipText("Selecione o staus do usuario");
		lblUsuarioAtivo.setBounds(6, 38, 91, 16);
		panelDados.add(lblUsuarioAtivo);
		
		btnGroupAtivo = new ButtonGroup();
		
		rdbtnAtivo = new JRadioButton("Ativo");
		rdbtnAtivo.setEnabled(false);
		rdbtnAtivo.setToolTipText("Usuario Ativo");
		rdbtnAtivo.setBounds(119, 37, 69, 18);
		panelDados.add(rdbtnAtivo);
		btnGroupAtivo.add(rdbtnAtivo);
		
		rdbtnInativo = new JRadioButton("Inativo");
		rdbtnInativo.setEnabled(false);
		rdbtnInativo.setToolTipText("Usuario Inativo");
		rdbtnInativo.setBounds(200, 37, 62, 18);
		panelDados.add(rdbtnInativo);
		btnGroupAtivo.add(rdbtnInativo);
		
		JLabel lblEmail = new JLabel("email:");
		lblEmail.setBounds(541, 38, 48, 16);
		panelDados.add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(541, 90, 56, 16);
		panelDados.add(lblTelefone);
		
		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setToolTipText("Digite o email do usuario");
		txtEmail.setColumns(10);
		txtEmail.setBounds(609, 32, 153, 28);
		panelDados.add(txtEmail);
		
		
		try{
			javax.swing.text.MaskFormatter cpf= new javax.swing.text.MaskFormatter("###.###.###-##");
			formattedTxtCpf = new JFormattedTextField(cpf);
			formattedTxtCpf.setEnabled(false);
			formattedTxtCpf.setToolTipText("Digite o CPF do Usuario");
			formattedTxtCpf.setBounds(368, 135, 140, 28);
			panelDados.add(formattedTxtCpf);
			
			javax.swing.text.MaskFormatter rg= new javax.swing.text.MaskFormatter("##.###.###-#");
			formattedTxtRg = new JFormattedTextField(rg);
			formattedTxtRg.setEnabled(false);
			formattedTxtRg.setToolTipText("Digite o RG do usuario");
			formattedTxtRg.setBounds(368, 84, 140, 28);
			panelDados.add(formattedTxtRg);
			
			javax.swing.text.MaskFormatter tel= new javax.swing.text.MaskFormatter("(##)####-##-##");
			formattedTxtTel = new JFormattedTextField(tel);
			formattedTxtTel.setEnabled(false);
			formattedTxtTel.setToolTipText("Digite o Telefone do usuario");
			formattedTxtTel.setBounds(609, 79, 153, 28);
			panelDados.add(formattedTxtTel);
			
			formattedTxtCel = new JFormattedTextField(tel);
			formattedTxtCel.setEnabled(false);
			formattedTxtCel.setToolTipText("Digite o telefone celular do usuario");
			formattedTxtCel.setBounds(609, 135, 153, 28);
			panelDados.add(formattedTxtCel);
			
			}
			catch (Exception e){
			}
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelBotoes.setBounds(16, 224, 798, 50);
		panelCadastro.add(panelBotoes);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent arg0) {
				
				habilitaDados();
			}
		});
		btnNovo.setIcon(new ImageIcon(Usuarios.class.getResource("/Imagens/novo.png")));
		panelBotoes.add(btnNovo);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(validaCampos()){
					
					if(validaCpf(formattedTxtCpf.getText())){
						
						salvar();
						
						if(alterar){
							
							dao.alterarUsuario(usuario);
							alterar = false;
						}
						else{
							
							dao.insereUsuario(usuario);
						}
						
						limpar();
					}
					else{
						
						JOptionPane.showMessageDialog(null, "CPF Inválido", "ERRO", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				else{
					
					JOptionPane.showMessageDialog(null, "É necessario preencher todos os campos", "Alerta", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSalvar.setIcon(new ImageIcon(Usuarios.class.getResource("/Imagens/salvar.png")));
		panelBotoes.add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limpar();
			}
		});
		btnLimpar.setIcon(new ImageIcon(Usuarios.class.getResource("/Imagens/limpar.png")));
		panelBotoes.add(btnLimpar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				alterar();
			}
		});
		btnAlterar.setIcon(new ImageIcon(Usuarios.class.getResource("/Imagens/atualizar.png")));
		panelBotoes.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				deletar();
			}
		});
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/Imagens/lixeira.png")));
		panelBotoes.add(btnExcluir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limpar();
				desabilitaDados();
			}
		});
		btnCancelar.setIcon(new ImageIcon(Usuarios.class.getResource("/Imagens/cancelar.png")));
		panelBotoes.add(btnCancelar);
	}

	@Override
	void alterar() {
		
		alterar = true;
		habilitaDados();
		
	}

	@Override
	void deletar() {
		
		int res = JOptionPane.showConfirmDialog(null,"Deseja realmente deletar? ","Aviso",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(res == JOptionPane.YES_OPTION){
			
			dao.deletaUsuario(usuario);
			
			modelUsuarios.removeRowAt(table.getSelectedRow());
			
		}
		
	}

}
