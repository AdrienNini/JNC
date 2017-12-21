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
import javax.swing.JTable;
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
import model.Subnet;
import net.miginfocom.swing.MigLayout;

/**
 * @author Adrien
 *
 */
public class GUI extends ViewNetwork implements ActionListener {
	
	private int width;
	private int height;
	
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
	
	// Third page GUI variables
	private JScrollPane tableMain;
	private JTable subnetTable;
	
	// Subnets
	ArrayList<Integer> sizes = new ArrayList<Integer>();
	
	
	
	public GUI(ModelNetwork m, ControllerNetwork c, int w, int h) {
		super(m, c);
		
		this.width = w;
		this.height = h;
		
		this.createGUI(this.width, this.height, 3);
		
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
			
		case 3:
			this.showThirdPage(width, height);
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
				controller.createNetwork(ipField.getText(), (int) maskField.getValue());
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
		instructions.setText(this.readFile("/txt/instructions.txt"));
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
		
		
		JLabel lblHeader = new JLabel("Configuration des sous-réseaux (" + this.controller.getNetwork() + ")");
		lblHeader.setHorizontalAlignment(SwingConstants.LEFT);
		header.add(lblHeader);
		
		
		// Footer
		JPanel footer = new JPanel();
		this.contentPane.add(footer, BorderLayout.SOUTH);
		
		geneSubnets = new JButton("Generate Subnets");
		geneSubnets.setEnabled(false);
		footer.add(geneSubnets);
		geneSubnets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (JSpinner spinSub : spinSubnets) {
					sizes.add((int) spinSub.getValue());
				}
				
				if (!controller.requestIp(sizes)) {
					JOptionPane.showMessageDialog(mainWindow, "Adressage Impossible ! Veuillez recommencer !");
					sizes.clear();
				} else {
					JOptionPane.showMessageDialog(mainWindow, "Adressage Réussi");
				}
				
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
		main.setLayout(new MigLayout("","[][grow]", ""));
		
		// Subnets
		
		addButton();
		
		
	}
	
	private void showThirdPage(int width, int height) {
		
		// Header
		JPanel header = new JPanel();
		this.contentPane.add(header, BorderLayout.NORTH);
		
		
		JLabel lblHeader = new JLabel("Tableau d'adressage");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(lblHeader);
		
		// Main
		tableMain = new JScrollPane();
		this.contentPane.add(tableMain, BorderLayout.CENTER);
		
		// Footer
		JPanel footer = new JPanel();
		this.contentPane.add(footer, BorderLayout.SOUTH);
		
		JButton exportCSV = new JButton("Exporter vers CSV");
		footer.add(exportCSV);
		
		JButton close = new JButton("Quitter");
		footer.add(close);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		
	}
	
	// Methods for Second page
	
	private void addSubEntry() {
		this.main.removeAll();
		System.gc();
		
		this.lblSubnets.add(new JLabel("Sous-réseau n° " + (this.lblSubnets.size()+1) + " :"));
		this.spinSubnets.add(new JSpinner(new SpinnerNumberModel(1, 1, null, 1)));
		
		this.subEntry();
	}
	
	
	private void removeSubEntry() {
		this.main.removeAll();
		System.gc();
		
		this.lblSubnets.remove(this.lblSubnets.size() - 1);
		this.spinSubnets.remove(this.spinSubnets.size()-1);
		
		this.subEntry();
		
		
	}
	
	
	private void subEntry() {
		for (int i = 0; i < this.lblSubnets.size(); i++) {
			this.main.add(lblSubnets.get(i), "cell 1 " + i);
			this.main.add(spinSubnets.get(i), "cell 2 " + i + ", growx");
			if (i == this.lblSubnets.size()-1) {
				this.main.add(removeButton(), "cell 3 " + i);
			}
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
	
	// Multi pages methods
	
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
	
	// Table function
	
	private void createJTable(ArrayList<Subnet> subnets) {
		String columns[] = {
				"Adresse Réseau", 
				"Première Adresse", 
				"Dernière Adress", 
				"Adresse de Broadcast", 
				"Masque de sous-réseau"
			};
		
		String data[][] = new String[subnets.size()][5];
		
		for (Subnet sub: subnets) {
			int i = subnets.indexOf(sub);
			data[i][0] = sub.getAddr();
			data[i][1] = sub.getFirstIpHost();
			data[i][2] = sub.getLastIpHost();
			data[i][3] = sub.getBroadcast();
			data[i][4] = sub.getMask();
		}
		
		subnetTable = new JTable(data, columns);
		
		// JTable configuration
		subnetTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		subnetTable.getTableHeader().setReorderingAllowed(false);
		subnetTable.getTableHeader().setFont(new Font("Lucia Grande", Font.BOLD, 13));
		subnetTable.setRowHeight(30);
		subnetTable.setCellSelectionEnabled(false);
		subnetTable.setRowSelectionAllowed(false);
		subnetTable.setColumnSelectionAllowed(false);
		subnetTable.setFocusable(false);
		
		
		this.tableMain.setViewportView(subnetTable);
		
		this.mainWindow.validate();
		this.mainWindow.repaint();
		
		
	}
	
	
	// General Methods

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) {
			this.clearWindow();
			this.createGUI(800, 600, 3);
			this.createJTable(this.model.getSubnets());
		}
	}

	/* (non-Javadoc)
	 * @see view.ViewNetwork#show(java.lang.String)
	 */
	@Override
	public void show(String string) {
		JOptionPane.showMessageDialog(this.mainWindow, string);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
