package Interface;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JInternalFrame;
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


public class Caixa2 extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the frame.
	 */
	public Caixa2() {
		setTitle("Minister - Caixa");
		setFrameIcon(new ImageIcon(Caixa2.class.getResource("/Imagens/caixa_registradora.png")));
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
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnMesa = new JButton("Mesa1");
		panel_6.add(btnMesa);
		
		JButton btnMesa_1 = new JButton("Mesa2");
		panel_6.add(btnMesa_1);
		
		JButton btnMesa_2 = new JButton("Mesa3");
		panel_6.add(btnMesa_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Caixa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(16, 22, 246, 339);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_2.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(SystemColor.info);
		textArea.setLineWrap(true);
		scrollPane_1.setViewportView(textArea);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(266, 108, 123, 34);
		lblTotal.setFont(new Font("SansSerif", Font.PLAIN, 26));
		panel_2.add(lblTotal);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(266, 144, 122, 43);
		textField.setFont(new Font("SansSerif", Font.PLAIN, 25));
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblPago = new JLabel("Pago");
		lblPago.setFont(new Font("SansSerif", Font.PLAIN, 26));
		lblPago.setBounds(266, 199, 123, 34);
		panel_2.add(lblPago);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("SansSerif", Font.PLAIN, 25));
		textField_1.setColumns(10);
		textField_1.setBounds(266, 233, 122, 43);
		panel_2.add(textField_1);
		
		JLabel lblTroco = new JLabel("Troco");
		lblTroco.setFont(new Font("SansSerif", Font.PLAIN, 26));
		lblTroco.setBounds(266, 283, 123, 34);
		panel_2.add(lblTroco);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setFont(new Font("SansSerif", Font.PLAIN, 25));
		textField_2.setColumns(10);
		textField_2.setBounds(266, 318, 122, 43);
		panel_2.add(textField_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBounds(16, 401, 373, 93);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JButton btnFinalizarPedido = new JButton("Finalizar Pedido");
		btnFinalizarPedido.setBounds(39, 7, 144, 36);
		btnFinalizarPedido.setIcon(new ImageIcon(Caixa2.class.getResource("/Imagens/caixa_registradora2.png")));
		panel_4.add(btnFinalizarPedido);
		
		JButton btnAdicionarProduto = new JButton("Adicionar produto");
		btnAdicionarProduto.setBounds(188, 7, 152, 36);
		btnAdicionarProduto.setIcon(new ImageIcon(Caixa2.class.getResource("/Imagens/novo.png")));
		panel_4.add(btnAdicionarProduto);
		
		JButton btnRetirarProduto = new JButton("Retirar produto");
		btnRetirarProduto.setBounds(39, 48, 144, 36);
		btnRetirarProduto.setIcon(new ImageIcon(Caixa2.class.getResource("/Imagens/atualizar.png")));
		panel_4.add(btnRetirarProduto);
		
		JButton btnCancelar = new JButton("Cancelar Pedido");
		btnCancelar.setBounds(188, 48, 152, 36);
		btnCancelar.setIcon(new ImageIcon(Caixa2.class.getResource("/Imagens/cancelar.png")));
		panel_4.add(btnCancelar);
		
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
