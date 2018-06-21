import controller.Controller;
import model.Calculations;
import view.*;

public class Main {

	public static void main(String args[]) {
		MainWindow window = new MainWindow();
		Controller controller = new Controller(window);
		Calculations calc = new Calculations(controller);		
		window.show();
	}
}
