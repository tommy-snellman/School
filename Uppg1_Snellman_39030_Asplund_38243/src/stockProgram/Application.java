package stockProgram;

import javax.swing.SwingUtilities;

import controller.Controller;
import model.Model;
import view.View;

public class Application {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				runApp();
				
			}
		});
	}
	
	public static void runApp() {
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(view, model);
		
		view.setSearchListener(controller);
		view.setCurrencyListener(controller);
	}
}
