package Interface;


import java.awt.BorderLayout;
import java.awt.RenderingHints.Key;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BancoDeDados.Dao;
import PacoteLogico.JNumberFormatField;
import Tabelas.CaixaTableModel;
import Tabelas.ProdutosTableModel;
import Tabelas.SubCategoriaTableModel;
import Tabelas.TMCaixa;
import Tabelas.TMProdutos;
import Trasferencia.DtoCaixa;

import java.awt.Color;


public class Caixa extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JNumberFormatField txtTotal;
	private JNumberFormatField txtPago;
	private JNumberFormatField txtTroco;
	private JTextField textField_3;
	private JTable table;
	
	private CaixaTableModel modelProdutos;
	private ProdutosTableModel produtosTableModel;
	
	Dao dao = new Dao();
	DtoCaixa caixa = new DtoCaixa();
	
	public void abreMesa(int numMesa){
		
		modelProdutos = new CaixaTableModel(dao.selectListaProdutos(numMesa));
		table.setModel(modelProdutos);
		txtTotal.setValue(BigDecimal.valueOf(caixa.getTotal()));
		textField_3.setText(""+numMesa);
		
	}

	/**
	 * Create the frame.
	 */
	public Caixa() {
		setTitle("Minister - Caixa");
		setFrameIcon(new ImageIcon(Caixa.class.getResource("/Imagens/caixa_registradora.png")));
		setIconifiable(true);
		setClosable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(5, 5, 840, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Mesas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		scrollPane.setViewportView(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.SOUTH);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new GridLayout(0, 3, 2, 2));
		
		JButton btnNewButton = new JButton("Mesa 01");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numMesa = 1;
				abreMesa(numMesa);
				
			}
		});
		panel_6.add(btnNewButton);
		
		JButton btnMesa = new JButton("Mesa 02");
		btnMesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numMesa = 2;
				abreMesa(numMesa);
				
			}
		});
		panel_6.add(btnMesa);
		
		JButton btnNewButton_1 = new JButton("Mesa 03");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numMesa = 3;
				abreMesa(numMesa);
				
			}
		});
		panel_6.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Mesa 04");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int numMesa = 4;
				abreMesa(numMesa);
			}
		});
		panel_6.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Mesa 05");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numMesa = 5;
				abreMesa(numMesa);
				
			}
		});
		panel_6.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Mesa06");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numMesa = 6;
				abreMesa(numMesa);
				
			}
		});
		panel_6.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Mesa 07");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numMesa = 7;
				abreMesa(numMesa);
				
			}
		});
		panel_6.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Mesa 08");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numMesa = 8;
				abreMesa(numMesa);
				
			}
		});
		panel_6.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Mesa 09");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numMesa = 9;
				abreMesa(numMesa);
				
			}
		});
		panel_6.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("mesa 10");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numMesa = 10;
				abreMesa(numMesa);
				
			}
		});
		panel_6.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("mesa 11");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numMesa = 11;
				abreMesa(numMesa);
				
			}
		});
		panel_6.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("mesa 12");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numMesa = 12;
				abreMesa(numMesa);
				
			}
		});
		panel_6.add(btnNewButton_10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Caixa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.BLACK);
		scrollPane_1.getViewport().setBackground(new Color(255, 255, 204));
		scrollPane_1.setBounds(16, 22, 246, 339);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_2.add(scrollPane_1);
		
		table = new JTable();
		table.setBackground(new Color(255, 255, 204));
		scrollPane_1.setViewportView(table);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(266, 108, 123, 34);
		lblTotal.setFont(new Font("SansSerif", Font.PLAIN, 26));
		panel_2.add(lblTotal);
		
		txtTotal = new JNumberFormatField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(266, 144, 122, 43);
		txtTotal.setFont(new Font("SansSerif", Font.PLAIN, 25));
		panel_2.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblPago = new JLabel("Pago");
		lblPago.setFont(new Font("SansSerif", Font.PLAIN, 26));
		lblPago.setBounds(266, 199, 123, 34);
		panel_2.add(lblPago);
		
		txtPago = new JNumberFormatField();
		txtPago.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
				BigDecimal total = txtTotal.getValue();
				BigDecimal pago = txtPago.getValue();
				if(pago.compareTo(total)>=0){
					BigDecimal troco = pago.subtract(total);
					txtTroco.setValue(troco);
				}
				else {
					txtTroco.setValue(new BigDecimal(0));
				}
			}
		});
		txtPago.setFont(new Font("SansSerif", Font.PLAIN, 25));
		txtPago.setColumns(10);
		txtPago.setBounds(266, 233, 122, 43);
		panel_2.add(txtPago);
		
		JLabel lblTroco = new JLabel("Troco");
		lblTroco.setFont(new Font("SansSerif", Font.PLAIN, 26));
		lblTroco.setBounds(266, 283, 123, 34);
		panel_2.add(lblTroco);
		
		txtTroco = new JNumberFormatField();
		txtTroco.setEditable(false);
		txtTroco.setFont(new Font("SansSerif", Font.PLAIN, 25));
		txtTroco.setColumns(10);
		txtTroco.setBounds(266, 318, 122, 43);
		panel_2.add(txtTroco);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBounds(16, 401, 373, 93);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JButton btnFinalizarPedidoDinheiro = new JButton("Dinheiro");
		btnFinalizarPedidoDinheiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtPago.getValue().compareTo(txtTotal.getValue())>=0){
					
					dao.finalizaConta(caixa.getIdPedido());
				}
				else{
					JOptionPane.showMessageDialog(null, "Por Favor insira o valor pago");
				}
			}
		});
		btnFinalizarPedidoDinheiro.setBounds(30, 18, 152, 60);
		btnFinalizarPedidoDinheiro.setIcon(new ImageIcon(Caixa.class.getResource("/Imagens/moedas.png")));
		panel_4.add(btnFinalizarPedidoDinheiro);
		
		JButton btnFinalizarCartao = new JButton("F4 - Cart\u00E3o");
		btnFinalizarCartao.setEnabled(false);
		btnFinalizarCartao.setBounds(194, 18, 152, 60);
		panel_4.add(btnFinalizarCartao);
		btnFinalizarCartao.setIcon(new ImageIcon(Caixa.class.getResource("/Imagens/cartaoCredito.png")));
		
		JLabel lblMesa = new JLabel("Mesa");
		lblMesa.setFont(new Font("SansSerif", Font.PLAIN, 26));
		lblMesa.setBounds(266, 22, 123, 34);
		panel_2.add(lblMesa);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setFont(new Font("SansSerif", Font.PLAIN, 25));
		textField_3.setColumns(10);
		textField_3.setBounds(266, 57, 122, 43);
		panel_2.add(textField_3);
	}
}
