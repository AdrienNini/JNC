/**
 * 
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.*;

import controller.ControllerNetwork;
import model.ModelNetwork;

/**
 * @author Adrien
 *
 */
public class GUI extends ViewNetwork implements ActionListener {
	
	private JFrame mainWindow;
	private JTextPane headerText;
	private JLabel ipAddrLabel;
	private JLabel netMaskLable;
	private JTextField ipAddrField;
	private JTextField netMaskField;
	private JButton requestIp;
	
	
	
	
	public GUI(ModelNetwork m, ControllerNetwork c) {
		super(m, c);
		this.createGUI();
		
		this.mainWindow = new JFrame();
		this.mainWindow.setTitle("Java Network Calculator");
		this.mainWindow.setVisible(true);
	}
	
	public void createGUI() {
		
		this.headerText = new JTextPane();
		
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
		// TODO Auto-generated method stub
		
	}
	
}
