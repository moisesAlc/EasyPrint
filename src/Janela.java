import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.IntStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarculaLaf;

public class Janela extends JFrame {

	private JPanel contentPane;
	private JLabel lbl_inicial;
	private JLabel lbl_final;
	private JLabel lbl_impares;
	private JLabel lbl_pares;
	private JTextField pg_inicial;
	private JTextField pg_final;
	private JTextField pg_impares;
	private JButton btn_gerar;
	private JButton btn_copiar_impares;
	private JTextField pg_pares;
	private JButton btn_copiar_pares;
	private JMenuBar barraMenu;
	private JMenu menuSobre;
	private JMenuItem itemMenuSobre;
	private JButton btn_zerar;
	
	private ArrayList<Integer> impares;
	private ArrayList<Integer> pares;
	private StringBuilder strImpares;
	private StringBuilder strPares;
	
	private void popula(Integer pgInicial, Integer pgFinal){
		
		 IntStream.range(pgInicial, pgFinal+1).forEach(
				 n -> {
					 if (n % 2 == 0) {
						 pares.add(n);
					 }else {
						 impares.add(n);
					 }
				 }
				 );
		impares.forEach(
				n -> {
					 strImpares.append(n+",");
				 }
				);
		strImpares.deleteCharAt(strImpares.length()-1);
		pg_impares.setText(strImpares.toString());
		pares.forEach(
				n -> {
					 strPares.append(n+",");
				 }
				);
		strPares.deleteCharAt(strPares.length()-1);
		pg_pares.setText(strPares.toString());
	}
	
	private void limpa(){
		pares.clear();
		impares.clear();
		strImpares = new StringBuilder();
		strPares = new StringBuilder();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela frame = new Janela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Configura os componentes.
	 */
	public void configuraComponentes() {
		barraMenu = new JMenuBar();
		menuSobre = new JMenu("Sobre");
		itemMenuSobre = new JMenuItem("Autor");
		itemMenuSobre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, 
						"Moisés Madeira\nhttps://github.com/moisesAlc"
						, "Sobre o Autor", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		lbl_inicial = new JLabel("Página inicial");
		lbl_inicial.setAlignmentX(BoxLayout.X_AXIS);
		lbl_final = new JLabel("Página final");
		lbl_impares = new JLabel("Páginas ímpares");
		lbl_pares = new JLabel("Páginas pares");
		pg_inicial = new JTextField();
		pg_inicial.setColumns(3);
		pg_inicial.setText("1");
		pg_final = new JTextField();
		pg_final.setColumns(3);
		pg_final.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				popula(Integer.parseInt(pg_inicial.getText()), Integer.parseInt(pg_final.getText()));
				pack();
				
			}
		});
		pg_impares = new JTextField();
		
		pg_pares = new JTextField();
		
		btn_gerar = new JButton("Gerar");
		btn_gerar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!impares.isEmpty()) limpa();
				popula(Integer.parseInt(pg_inicial.getText()), Integer.parseInt(pg_final.getText()));
				pack();
			}
			
		});
		
		btn_copiar_impares = new JButton("Copiar ímpares");
		btn_copiar_impares.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Toolkit.getDefaultToolkit()
		        .getSystemClipboard()
		        .setContents(
		                new StringSelection(pg_impares.getText()),
		                null
		        );
				
			}
			
		});
		btn_copiar_pares = new JButton("Copiar pares");
		btn_copiar_pares.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit()
		        .getSystemClipboard()
		        .setContents(
		                new StringSelection(pg_pares.getText()),
		                null
		        );
			}
			
		});
		
		btn_zerar = new JButton("Zerar");
		btn_zerar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				limpa();
				pg_inicial.setText("1");
				pg_final.setText("");
				pg_impares.setText("");
				pg_pares.setText("");
				pack();
				pg_final.requestFocusInWindow();
			}
			
		});
		
		impares = new ArrayList<>();
		pares = new ArrayList<>();
		strImpares = new StringBuilder();
		strPares = new StringBuilder();
	}


	/**
	 * Create the frame.
	 */
	public Janela() {
		try {
		    UIManager.setLookAndFeel( new FlatDarculaLaf() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		configuraComponentes();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 350, 240);
		setTitle("Moisés - pegar páginas ímpares e pares");
		setIconImage(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		barraMenu.add(menuSobre);
		menuSobre.add(itemMenuSobre);
		setJMenuBar(barraMenu);
		contentPane.add(lbl_inicial);
		contentPane.add(pg_inicial);
		contentPane.add(lbl_final);
		contentPane.add(pg_final);
		contentPane.add(lbl_impares);
		contentPane.add(btn_gerar);
		contentPane.add(pg_impares);
		contentPane.add(btn_copiar_impares);
		contentPane.add(lbl_pares);
		contentPane.add(pg_pares);
		contentPane.add(btn_copiar_pares);
		contentPane.add(btn_zerar);
		setContentPane(contentPane);
		
		setPreferredSize(new Dimension(400,350));
		pack();
		setLocationRelativeTo(null);
		pg_final.requestFocusInWindow();
	}

}
