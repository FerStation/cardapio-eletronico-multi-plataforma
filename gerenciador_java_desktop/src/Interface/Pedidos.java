package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import BancoDeDados.Dao;
import Tabelas.PedidoTableModel;


public class Pedidos extends JInternalFrame {

	private JPanel contentPane;
	private JTable table;
	
	private PedidoTableModel modelPedido;
	private int idPedido;
	
	Dao dao = new Dao();

	/**
	 * Create the frame.
	 */
	public Pedidos() {
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Minister - Pedidos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 842, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel label = new JLabel("");
		panel.add(label);
		
		JButton btnFinalizarPedido = new JButton("Finalizar Pedido");
		btnFinalizarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dao.finalizaPedido(idPedido);
				modelPedido.removeRowAt(table.getSelectedRow());
			}
		});
		btnFinalizarPedido.setIcon(new ImageIcon(Pedidos.class.getResource("/Imagens/OK.png")));
		panel.add(btnFinalizarPedido);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel label_1 = new JLabel("-");
		panel_1.add(label_1);
		
		final JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				if(slider.getValue() > 0){
					table.setRowHeight(slider.getValue());
					table.setFont(new Font("SansSerif", Font.PLAIN, slider.getValue()));
				}
				
			}
		});
		slider.setMaximum(100);
		slider.setMinimum(0);
		panel_1.add(slider);
		
		
		JLabel label_2 = new JLabel("+");
		panel_1.add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		modelPedido = new PedidoTableModel(dao.selectPedido());
		
		table = new JTable();
		table.setModel(modelPedido);
		table.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(java.awt.event.MouseEvent e){
        		int row=table.rowAtPoint(e.getPoint());
        		idPedido = dao.selectPedido().get(row).getID();
        	}
		});
		table.setRowHeight(25);
		table.setShowVerticalLines(true);
		table.setFont(new Font("SansSerif", Font.PLAIN, 20));
		scrollPane.setViewportView(table);
	}

}
