package view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import controller.Controller;

public class Display extends JPanel {

	private int width = 600;
	private int height = 500;
	public ArrayList values = new ArrayList();
	public ArrayList args = new ArrayList();
	public Controller controller;
	public MainWindow mainWindow;
	public int penSize, fontSize;
	public Dimension firstSize;
	public Dimension size;
	public int initialFontSize = 15;
	public int initialPenSize = 1;

	public Display(MainWindow mainWindow, Controller controller) {
		this.mainWindow = mainWindow;
		this.controller = controller;
		size = new Dimension(width, height);
		setPreferredSize(size);
		setSize(size);

		penSize = 1;
		fontSize = 15;
		firstSize = size;

	}

	public void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);

		setBackground(Color.WHITE);
		graphic.setColor(Color.DARK_GRAY);

		size = new Dimension(this.getWidth(), this.getHeight());
		Graphics2D graph = (Graphics2D) graphic;

		graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graph.setStroke(new BasicStroke(penSize));
		graph.setFont(new Font("Calibri", Font.PLAIN, fontSize));

		graph.drawLine(10,  size.height / 2, size.width - 10,  size.height / 2); // x
		graph.drawLine(size.width / 2, 10, size.width / 2, size.height - 10); // y

		graph.drawLine(size.width - 30,  size.height / 2 - 10, size.width - 10,  size.height / 2);
		graph.drawLine(size.width - 30,  size.height / 2 + 10, size.width - 10,  size.height / 2);

		graph.drawLine(size.width / 2, 10, size.width / 2 - 10, 30);
		graph.drawLine(size.width / 2, 10, size.width / 2 + 10, 30);

		graph.drawString("X", size.width - 20 * size.width / 600,  size.height / 2 + 20 * size.height / 500);
		graph.drawString("Y", size.width / 2 - 20 * size.width / 600, 20 * size.height / 500);
		graph.drawString("0", size.width / 2 - 20 * size.width / 600,  size.height / 2 + 20 * size.height / 500);

		for (int index = 1; index < size.width / 20; index++) 
		{ 
			graph.drawLine(20 * index * size.width / 600,  size.height / 2 - 3 * size.height / 500,
					20 * index * size.width / 600,  size.height / 2 + 3 * size.height / 500);// x
			graph.drawLine(size.width / 2 - 3 * size.width / 600, 20 * index * size.height / 500,
					size.width / 2 + 3 * size.width / 600, 20 * index * size.height / 500);// y
		}

		args = controller.getArgs();
		values = controller.getValues();

		for (int index = 1; index < args.size(); index++) {
			double tempFx = (double) values.get(index);
			double tempX = (double) args.get(index);
			double prevFx = (double) values.get(index - 1);
			double prevX = (double) args.get(index - 1);

			int newFx = (int) (10 * tempFx);
			int newX = (int) (10 * tempX);
			int newprevFx = (int) (10 * prevFx);
			int newPrevX = (int) (10 * prevX);

			graph.setColor(Color.RED);
			int drawPrevX = (int) size.width / 2 + 2 * newPrevX * size.width / 300;
			int drawPrevY = (int)  size.height / 2 - (int) (0.02 * newprevFx * size.height / 250);
			int drawX = (int) size.width / 2 + 2 * newX * size.width / 300;
            int drawY = (int)  size.height / 2 - (int) (0.02 * newFx * size.height / 250);
            
			graph.drawLine(drawPrevX,drawPrevY,drawX,drawY);
			
		}

	}

	public ArrayList getValues() {
		return values;
	}

	public ArrayList getArgs() {
		return args;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setPenSize(int penSize) {
		this.penSize = penSize;
	}

	public int getPenSize() {
		return penSize;
	}

	public Dimension getfirstSize() {
		return firstSize;
	}

	public int getInitialFontSize() {
		return initialFontSize;
	}

	public int getInitialPenSize() {
		return initialPenSize;
	}

	public void clean() {
		args = new ArrayList();
		values = new ArrayList();
	}

}
