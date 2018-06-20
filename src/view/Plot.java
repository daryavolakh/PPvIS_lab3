package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import controller.Controller;

public class Plot extends JPanel {

	private int width = 600;
	private int height = 500;
	public List<List<Double>> values = new ArrayList<>();
	public Controller controller;
	public MainWindow mainWindow;
	public int penSize, fontSize;
	public Dimension firstSize;
	public Dimension newSize;
	public Dimension size;
	public Dimension center;
	public int initialFontSize = 15;
	public int initialPenSize = 1;
	double maxFx;
	double maxX;

	public Plot(MainWindow mainWindow, Controller controller) {  
		this.mainWindow = mainWindow;
		this.controller = controller;  
		size = new Dimension(width, height);
		center = new Dimension(width/2, height/2);
		setPreferredSize(size);
		setSize(size);
		
		penSize = 1;
		fontSize = 15;
		firstSize = size;
		
		maxFx = size.height / 2;
		maxX = size.width;
	}

	@Override
	public void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);

		setBackground(Color.WHITE);
		graphic.setColor(Color.DARK_GRAY);

		size = new Dimension(this.getWidth(), this.getHeight());
		center = new Dimension(size.width/2, size.height/2);
		Graphics2D graph = (Graphics2D) graphic;

		graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graph.setStroke(new BasicStroke(penSize));
		graph.setFont(new Font("Calibri", Font.PLAIN, fontSize));

		graph.drawLine(10, size.height / 2, size.width - 10, size.height / 2);
		graph.drawLine(size.width / 2, 10, size.width / 2, size.height - 10);

		graph.drawLine(size.width - 30, size.height / 2 - 10, size.width - 10, size.height / 2);
		graph.drawLine(size.width - 30, size.height / 2 + 10, size.width - 10, size.height / 2);

		graph.drawLine(size.width / 2, 10, size.width / 2 - 10, 30);
		graph.drawLine(size.width / 2, 10, size.width / 2 + 10, 30);

		graph.drawString("X", size.width - 20, size.height / 2 + 20);
		graph.drawString("Y", size.width / 2 - 20, 20);
		graph.drawString("0", size.width / 2 - 20, size.height / 2 + 20);

		for (int index = 1; index < size.width / 20; index++) {
			graph.drawLine((int) (20 * 1.005 * index), size.height / 2 - 3 , (int) (20 * 1.005 * index), size.height / 2 + 3);
			
		}
		
		for (int index = 1; index < size.height / 20; index++) {
			graph.drawLine(size.width / 2 - 3, 20 * index, size.width / 2 + 3, 20 * index);
		}

		values = controller.getValues();

		for (int index = 1; index < values.size(); index++) {
			double tempFx = (values.get(index)).get(1);
			double tempX = (values.get(index)).get(0);
			double prevFx = (values.get(index - 1)).get(1);
			double prevX = (values.get(index - 1)).get(0);

			int newFx = (int) (10 * tempFx);
			int newX = (int) (10 * tempX);
			int newprevFx = (int) (10 * prevFx);
			int newPrevX = (int) (10 * prevX);
			maxFx = newFx;
			
			graph.setColor(Color.RED);
			int drawPrevX = center.width + 2 * newPrevX * 2;   //size.width / 2 + 2 * newPrevX * size.width / 300;
			int drawPrevY = center.height - (int) (0.02 * newprevFx * 2);   //size.height / 2 - (int) (0.02 * newprevFx * size.height / 250);
			int drawX = center.width + 2 * newX * 2;   //size.width / 2 + 2 * newX * size.width / 300;
			int drawY = center.height - (int) (0.02 * newFx * 2);   //size.height / 2 - (int) (0.02 * newFx * size.height / 250);
			
			if (Math.abs(drawY) > maxFx)
			{
				maxFx = Math.abs(drawY);

				System.out.println("DRAW" + drawY);
				System.out.println(maxFx);
			}
			
			
			if (Math.abs(drawX) > maxX / 2 || drawX < 0)
			{
				maxX = Math.abs(drawX);
			}
			
			if (index == values.size() - 1)
			{				
				newSize =  new Dimension ((int) (maxX), (int) (1.1 * maxFx));
				
				setPreferredSize(newSize);
				setSize(newSize);
			}			
			graph.drawLine(drawPrevX, drawPrevY, drawX, drawY);
		}
	}

	public List<List<Double>> getValues() {
		return values;
	}

	public void addValues(double x, double fx)
	{
		List<Double> newValues = new ArrayList<>();
		newValues.add(0,x);
		newValues.add(1,fx);
		values.add(newValues);
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
		values.clear();
	}

}
