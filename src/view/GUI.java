/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.ControllerNetwork;
import model.ModelNetwork;
import net.miginfocom.swing.MigLayout;

/**
 * @author Adrien
 *
 */
public class GUI extends ViewNetwork implements ActionListener {
	
	private JFrame mainWindow;
	private JPanel contentPane;
	private JTextField ipField;
	private JSpinner maskField;
	private JButton requestIP;
	
	
	public GUI(ModelNetwork m, ControllerNetwork c, int width, int height) {
		super(m, c);
		
		// Initialize the JFrame
		this.mainWindow = new JFrame();
		this.mainWindow.setSize(new Dimension(width, height));
		this.mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialize Layout
		this.contentPane = new JPanel();
		this.mainWindow.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		this.showFirstPage(width, height);
		
		this.mainWindow.setTitle("Java Network Calculator");
		this.mainWindow.setVisible(true);
		this.mainWindow.setResizable(false);
		
	}
	
	public void showFirstPage(int width, int height) {
		
		// HEADER
		JPanel header = new JPanel();
		this.contentPane.add(header, BorderLayout.NORTH);
		
		JLabel lblHeader = new JLabel("Welcome to JNC !");
		lblHeader.setFont(new Font("Lucia Grande", Font.PLAIN, 20));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(lblHeader);
		
		// Instructions (SIDE)
		JScrollPane instructionsPane = new JScrollPane();
		instructionsPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		instructionsPane.setViewportBorder(new EmptyBorder(0, 5, 0, 5));
		instructionsPane.setPreferredSize(new Dimension(width/3, height));
		this.contentPane.add(instructionsPane, BorderLayout.WEST);
		
		JTextPane instructions = new JTextPane();
		instructions.setEditable(false);
		// TODO : Replace by real instructions
		instructions.setText(this.readFile(this.getPath() + "/txt/instructions.txt"));
		instructions.setCaretPosition(0);
		instructionsPane.setViewportView(instructions);
		
		
		// Main Form
		JPanel mainForm = new JPanel();
		mainForm.setPreferredSize(new Dimension(width*2/3, height));
		this.contentPane.add(mainForm, BorderLayout.CENTER);
		mainForm.setLayout(new MigLayout("", "[][][][grow]", "[][][][][]"));
		
		JLabel lblIp = new JLabel("Adresse IP :");
		mainForm.add(lblIp, "cell 1 1");
		
		ipField = new JTextField();
		mainForm.add(ipField, "cell 3 1, growx");
		
		JLabel lblMask = new JLabel("Masque de sous-réseau");
		mainForm.add(lblMask, "cell 1 3");
		
		maskField = new JSpinner(new SpinnerNumberModel(1, 1, 32, 1));
		mainForm.add(maskField, "cell 3 3");
		
		
		// Submit Button
		JPanel footer = new JPanel();
		this.contentPane.add(footer, BorderLayout.SOUTH);
		
		requestIP = new JButton("Request IP");
		footer.add(requestIP);		
		requestIP.addActionListener(this);
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see view.ViewNetwork#show(java.lang.String)
	 */
	@Override
	public void show(String string) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.requestIP) {
			
		}
	}
	
}
