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

public class Plot extends JPanel {

	private int width = 600;
	private int height = 500;
	public List<List<Double>> values = new ArrayList<>();
	public int fontSize;
	public Dimension firstSize;
	public Dimension newSize;
	public Dimension size;
	public Dimension center;
	public int initialFontSize = 15;
	public int initialPenSize = 1;

	public Plot() {
		size = new Dimension(width, height);
		center = new Dimension(width / 2, height / 2);
		setPreferredSize(size);
		setSize(size);

		fontSize = 15;
		firstSize = new Dimension(600, 500);
	}

	@Override
	public void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);

		setBackground(Color.WHITE);
		graphic.setColor(Color.DARK_GRAY);

		size = new Dimension(this.getWidth(), this.getHeight());
		center = new Dimension(size.width / 2, size.height / 2);
		Graphics2D graph = (Graphics2D) graphic;

		graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

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
			graph.drawLine((int) (20 * 1.005 * index), size.height / 2 - 3, (int) (20 * 1.005 * index),
					size.height / 2 + 3);
		}

		for (int index = 1; index < size.height / 20; index++) {
			graph.drawLine(size.width / 2 - 3, 20 * index, size.width / 2 + 3, 20 * index);
		}

		for (int index = 1; index < values.size(); index++) {

			double tempFx = (values.get(index)).get(1);
			double tempX = (values.get(index)).get(0);
			double prevFx = (values.get(index - 1)).get(1);
			double prevX = (values.get(index - 1)).get(0);

			int newFx = (int) (10 * tempFx);
			int newX = (int) (10 * tempX);
			int newprevFx = (int) (10 * prevFx);
			int newPrevX = (int) (10 * prevX);

			graph.setColor(Color.RED);
			int drawPrevX = center.width + 4 * newPrevX;
			int drawPrevY = center.height - (int) (0.02 * newprevFx * 2);

			int drawX = center.width + 4 * newX;
			int drawY = center.height - (int) (0.02 * newFx * 2);

			graph.drawLine(drawPrevX, drawPrevY, drawX, drawY);
			graph.setColor(Color.DARK_GRAY);
			if (index == 1) {
				String stringX = (values.get(0)).get(0).toString();
				String stringY = (values.get(0)).get(1).toString();

				graph.drawString(stringX, drawPrevX, size.height / 2 + 30);
				graph.drawString(stringY, size.width / 2 - 50, drawPrevY);
			}

			if (values.get(index).get(0) % 1 == 0) {
				String stringX = (values.get(index)).get(0).toString();
				String stringY = (values.get(index)).get(1).toString();

				graph.drawString(stringX, drawX, size.height / 2 + 30);
				graph.drawString(stringY, size.width / 2 - 50, drawY);
			}
		}
	}
	
	public List<List<Double>> getValues() {
		return values;
	}

	public void addValues(double x, double fx) {
		List<Double> newValues = new ArrayList<>();
		newValues.add(0,x);
		newValues.add(1,fx);
		values.add(newValues);
	}
	
	public void addValueOnPlace(int place, double x, double fx)
	{
		List<Double> newPoints = new ArrayList<>();
		newPoints.add(0,x);
		newPoints.add(1,fx);
		values.set(place, newPoints);
	}

	public void clear() {
		values.clear();
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public int getFontSize() {
		return fontSize;
	}

	public int getInitialFontSize() {
		return initialFontSize;
	}

	public int getInitialPenSize() {
		return initialPenSize;
	}
	
	public Dimension getFirstSize()
	{
		return firstSize;
	}
	
	public void changeFirstSize(Dimension tempSize)
	{
		firstSize = tempSize;
	}
}
