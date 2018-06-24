package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import model.Calculations;
import controller.*;

public class MainWindow {
	public JFrame frame = new JFrame();
	public Controller controller;
	private int width = 900;
	private int height = 700;
	public int tempA;
	public double tempXBeg, tempXEnd;
	private PanelOfButtons panelButtons = new PanelOfButtons();
	public Table mainTable;
	public Plot graphic;
	public Calculations calc;
	public JScrollPane scroll;

	public MainWindow() {
		
		controller = new Controller(MainWindow.this);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new FlowLayout());

		graphic = new Plot();
		
		mainTable = new Table(this);

		scroll = new JScrollPane(graphic);
		scroll.setPreferredSize(new Dimension(605, 505));
		scroll.setAutoscrolls(true);
		scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

		frame.add(mainTable.getScrollPaneOfTable());
		frame.add(scroll);
		frame.add(panelButtons.panel);

		MoveMouse listener = new MoveMouse(graphic);
		scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		scroll.getViewport().addMouseListener(listener);
		scroll.getViewport().addMouseMotionListener(listener);
		Zoom zoomListener = new Zoom(MainWindow.this, graphic, panelButtons);
		scroll.addMouseWheelListener(zoomListener);

		panelButtons.getMainButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					if (panelButtons.getValueXBeg().equals("") || panelButtons.getValueXEnd().equals("")
							|| panelButtons.getValueA().equals("")
							|| Integer.valueOf(panelButtons.getValueXBeg()) >= Integer
									.valueOf(panelButtons.getValueXEnd())) {
						JOptionPane.showMessageDialog(null, "Введены некорректные данные!");
					} else {
						graphic.clear();
						startCalculation();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		panelButtons.getScaleButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int scale = Integer.valueOf(panelButtons.getScale().getText());

				if (scale >= 0) {
					if (scale / 25 >= 1) {
						int number = (int) Math.floor(scale / 25);
						graphic.setFontSize(graphic.getInitialFontSize() + 3 * number);
					}

					if (scale <= 25)
						graphic.setFontSize(graphic.getInitialFontSize());

					int zoomW = (int) (scale * 6);
					int zoomH = (int) (scale * 5);

					Dimension newSize = new Dimension((int) graphic.getFirstSize().getWidth() + zoomW,
							(int) graphic.getFirstSize().getHeight() + zoomH);
					panelButtons.changeLabelScale("Масштаб: " + scale + "%");
					graphic.setPreferredSize(newSize);
					graphic.setSize(newSize);
				}
			}
		});
	}

	public void startCalculation() throws InterruptedException {
		if (isInt(panelButtons.getValueA()) == false || isInt(panelButtons.getValueXBeg()) == false
				|| isInt(panelButtons.getValueXEnd()) == false) {
			JOptionPane.showMessageDialog(null, "Введите целые числа!");
			return;
		} else {
			tempA = panelButtons.getA();
			controller.setA(tempA);
			tempXBeg = panelButtons.getXBegin();
			controller.setXBeg(tempXBeg);
			tempXEnd = panelButtons.getXEnd();
			controller.setXEnd(tempXEnd);
		}

		calc = new Calculations(controller);
		Thread thread = new Thread(calc);
		thread.start();
	}

	public void update() {
		List<List<Double>> values = controller.getValues();
		
		if (values.size() == 1 + 10 * (Integer.valueOf(panelButtons.getValueXEnd()) - Integer.valueOf(panelButtons.getValueXBeg()))) {
			
			double maxY = Math.abs((values.get(0)).get(1));
			double maxX = Math.abs((values.get(0)).get(0));
			
			for (int index = 0; index < values.size(); index++) {
				if (Math.abs((values.get(index)).get(1)) > maxY)
					maxY = Math.abs((values.get(index)).get(1));
				
				if (Math.abs((values.get(index)).get(0)) > maxX)
					maxX = Math.abs((values.get(index)).get(0));
			}

			int newFx = (int) (10 * maxY);
			int newX = (int) (10 * maxX);
			
			Dimension firstSize = new Dimension(602,502);
			
			int drawY = (int) (firstSize.getHeight() / 2 - 0.02 * newFx * 2);			
			int drawX = (int) (firstSize.getWidth() / 2 + 4 * newX);

			
			if (Math.abs(drawY) > firstSize.getHeight() && Math.abs(drawX) > firstSize.getWidth()) {
				Dimension newSize = new Dimension((int) (Math.abs(drawX) * 2), (int) (Math.abs(drawY) * 2.5));
				graphic.changeFirstSize(newSize);
				graphic.setPreferredSize(newSize);
				graphic.setSize(newSize);
			}

			else if (Math.abs(drawY) > firstSize.getHeight()) {
				Dimension newSize = new Dimension(graphic.getWidth(), (int) (Math.abs(drawY) * 3));
				graphic.changeFirstSize(newSize);
				graphic.setPreferredSize(newSize);
				graphic.setSize(newSize);
			}
			
			else if (drawY < 0)
			{
				Dimension newSize = new Dimension(graphic.getWidth(), (int) (502 + Math.abs(drawY) * 2.5));
				graphic.changeFirstSize(newSize);
				graphic.setPreferredSize(newSize);
				graphic.setSize(newSize);
			}

			else if (Math.abs(drawX) > firstSize.getWidth()) {
				Dimension newSize = new Dimension((int) (Math.abs(drawX) * 2), graphic.getHeight());
				graphic.changeFirstSize(newSize);
				graphic.setPreferredSize(newSize);
				graphic.setSize(newSize);
			}

			else if (Math.abs(drawY) <= 502 && Math.abs(drawX) <= 602) {
				Dimension newSize = new Dimension(602, 502);
				graphic.changeFirstSize(newSize);
				graphic.setPreferredSize(newSize);
				graphic.setSize(newSize);
			}
		}

		frame.repaint();
		mainTable.update();
	}
	
	public void repaintGraph()
	{
		frame.repaint();
	}
	
	public List<List<Double>> getValues() {
		return graphic.getValues();
	}

	public void addValues(double x, double fx) {
		graphic.addValues(x, fx);
	}
	
	public void addValueOnPlace(int place, double x, double fx)
	{
		graphic.addValueOnPlace(place,x,fx);
	}

	public void clear() {
		graphic.clear();
	}

	public void show() {
		frame.setVisible(true);
	}

	public boolean isInt(String value) throws NumberFormatException {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
