/**
 * 
 */
package controller;

import model.ModelNetwork;
import view.ViewNetwork;

/**
 * @author Adrien
 *
 */
public class ControllerNetwork {

	private ModelNetwork model;
	private ViewNetwork view;
	
	public ControllerNetwork(ModelNetwork m) {
		this.model = m;
	}
	
	public void addView(ViewNetwork v) {
		this.view = v;
	}
	
}
