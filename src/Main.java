import controller.Controller;
import model.Coordinates;
import view.*;

public class Main {

	public static void main(String args[]) {
		// ������� ������
		// ������� ���������� � ������� � �������
		// ������� ���� � ������� � ������������
		
		Coordinates coordinates = new Coordinates();
		Controller controller = new Controller(coordinates);
		MainWindow window = new MainWindow(controller);
		window.show();
	}
}
