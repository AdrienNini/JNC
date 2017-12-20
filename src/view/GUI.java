/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.ControllerNetwork;
import model.ModelNetwork;
import net.miginfocom.swing.MigLayout;

/**
 * @author Adrien
 *
 */
public class GUI extends ViewNetwork implements ActionListener {
	
	// General GUI variables
	private JFrame mainWindow;
	private JPanel contentPane;
	
	// First page GUI variables
	private JTextField ipField;
	private JSpinner maskField;
	private JButton requestIP;
	
	// Second page GUI variables
	private JButton geneSubnets;
	private JPanel main;
	private JButton addSub;
	private ArrayList<JLabel> lblSubnets = new ArrayList<JLabel>();
	private ArrayList<JSpinner> spinSubnets = new ArrayList<JSpinner>();
	
	
	
	
	public GUI(ModelNetwork m, ControllerNetwork c, int width, int height) {
		super(m, c);
		
		this.createGUI(width, height, 2);
		
	}
	
	private void createGUI(int width, int height, int page) {
		// Initialize the JFrame
		this.mainWindow = new JFrame();
		this.mainWindow.setSize(new Dimension(width, height));
		this.mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialize Layout
		this.contentPane = new JPanel();
		this.mainWindow.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		switch(page) {
		case 1:
			this.showFirstPage(width, height);
			break;
			
		case 2:
			this.showSecondPage(width, height);
			break;
		}
		
		this.mainWindow.setTitle("Java Network Calculator");
		this.mainWindow.setVisible(true);
		this.mainWindow.setResizable(false);
	}
	
	private void showFirstPage(int width, int height) {
		
		// HEADER
		JPanel header = new JPanel();
		this.contentPane.add(header, BorderLayout.NORTH);
		
		JLabel lblHeader = new JLabel("Welcome to JNC !");
		lblHeader.setFont(new Font("Lucia Grande", Font.PLAIN, 20));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(lblHeader);
		
		// Submit Button
		JPanel footer = new JPanel();
		this.contentPane.add(footer, BorderLayout.SOUTH);
		
		requestIP = new JButton("Request IP");
		footer.add(requestIP);		
		requestIP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainWindow, "Ip requested !");
				clearWindow();
				createGUI(width, height, 2);
			}
		});
		requestIP.setEnabled(false);
		
		// Instructions (SIDE)
		JScrollPane instructionsPane = new JScrollPane();
		instructionsPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		instructionsPane.setViewportBorder(new EmptyBorder(0, 5, 0, 5));
		instructionsPane.setPreferredSize(new Dimension(width/3, height));
		this.contentPane.add(instructionsPane, BorderLayout.WEST);
		
		JTextPane instructions = new JTextPane();
		instructions.setEditable(false);
		instructions.setText(this.readFile("/instructions.txt"));
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
		ipField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				this.check(ipField.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				this.check(ipField.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {}
			
			public void check(String ip) {
				if (!ip.matches("\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b")) {
					ipField.setBackground(new Color(255, 0, 0, 50));
				} else {
					ipField.setBackground(new Color(0, 255, 0, 50));
					requestIP.setEnabled(true);
				}
			}
			
		});
		
		JLabel lblMask = new JLabel("Masque de sous-réseau");
		mainForm.add(lblMask, "cell 1 3");
		
		maskField = new JSpinner(new SpinnerNumberModel(1, 1, 32, 1));
		mainForm.add(maskField, "cell 3 3");
		
	}
	
	private void showSecondPage(int width, int height) {
		
		
		// Header
		JPanel header = new JPanel();
		this.contentPane.add(header, BorderLayout.NORTH);
		
		JLabel lblHeader = new JLabel("Configuration des sous-réseaux");
		//lblHeader.setHorizontalAlignment(SwingConstants.LEFT);
		header.add(lblHeader);
		
		// Footer
		JPanel footer = new JPanel();
		this.contentPane.add(footer, BorderLayout.SOUTH);
		
		geneSubnets = new JButton("Generate Subnets");
		geneSubnets.setEnabled(false);
		footer.add(geneSubnets);
		geneSubnets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainWindow, "Requesting subnets generation...");
			}
		});
		
		// Main
		JScrollPane mainPane = new JScrollPane();
		mainPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		mainPane.setPreferredSize(new Dimension(width, height));
		mainPane.setViewportBorder(new EmptyBorder(0, 5, 0, 5));
		this.contentPane.add(mainPane, BorderLayout.CENTER);
		
		main = new JPanel();
		mainPane.setViewportView(main);
		main.setLayout(new MigLayout("","", ""));
		
		// Subnets
		
		addButton();
		
		
	}
	
	private void addSubEntry() {
		this.main.removeAll();
		System.gc();
		
		this.lblSubnets.add(new JLabel("Sous-réseau n° " + (this.lblSubnets.size()+1) + " :"));
		this.spinSubnets.add(new JSpinner(new SpinnerNumberModel(1, 1, null, 1)));
		
		for (int i = 0; i < this.lblSubnets.size(); i++) {
			this.main.add(lblSubnets.get(i), "cell 1 " + i);
			this.main.add(spinSubnets.get(i), "cell 2 " + i);
			if (i == this.lblSubnets.size()-1) {
				this.main.add(removeButton(), "cell 3 " + i);
			}
		}
		
		addButton();
		
		this.mainWindow.validate();
		this.mainWindow.repaint();
		this.enableButton();
	}
	
	private void removeSubEntry() {
		this.main.removeAll();
		System.gc();
		
		this.lblSubnets.remove(this.lblSubnets.size() - 1);
		this.spinSubnets.remove(this.spinSubnets.size()-1);
		
		for (int i = 0; i < this.lblSubnets.size(); i++) {
			this.main.add(lblSubnets.get(i), "cell 1 " + i);
			this.main.add(spinSubnets.get(i), "cell 2 " + i);
			if (i == this.lblSubnets.size()-1) this.main.add(removeButton(), "cell 3 " + i);
		}
		
		addButton();
		
		this.mainWindow.validate();
		this.mainWindow.repaint();
		this.enableButton();
		
		
	}
	
	private void addButton() {
		addSub = new JButton("+");
		main.add(addSub, "cell 1 " + (this.lblSubnets.size()+1));
		addSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSubEntry();
			}
				
		});
	}
	
	private JButton removeButton() {
		JButton remove = new JButton("Supprimer");
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSubEntry();
			}
		});
		
		return remove;
		
	}
	
	
	
	private void enableButton() {
		if (this.lblSubnets.size() > 0) {
			this.geneSubnets.setEnabled(true);
		} else {
			this.geneSubnets.setEnabled(false);
		}
	}
	
	private void clearWindow() {
		this.mainWindow.setVisible(false);
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
		
	}
	
}
