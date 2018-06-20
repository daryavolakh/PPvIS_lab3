package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	public Plot display;
	public Calculations calc;
	public JScrollPane scroll;

	public MainWindow(Controller controller) {
		
		this.controller = controller;
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new FlowLayout());

		display = new Plot(MainWindow.this, controller);
		mainTable = new Table(this, controller);

		scroll = new JScrollPane(display);
		scroll.setPreferredSize(new Dimension(605, 505));
		scroll.setAutoscrolls(true);
		scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

		frame.add(mainTable.getScrollPaneOfTable());
		frame.add(scroll);
		frame.add(panelButtons.panel);

		MoveMouse listener = new MoveMouse(display);
		scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		scroll.getViewport().addMouseListener(listener);
		scroll.getViewport().addMouseMotionListener(listener);
		Zoom zoomListener = new Zoom(MainWindow.this, display, panelButtons);
		scroll.addMouseWheelListener(zoomListener);

		panelButtons.getMainButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					if (panelButtons.getValueXBeg().equals("") || panelButtons.getValueXEnd().equals("")
							|| panelButtons.getValueA().equals("")) {
						JOptionPane.showMessageDialog(null, "Введены некорректные данные!");
					}
					else 
					{
						controller.clear();
						startCalculation();
						//mainTable.update();  когда словит сообщение от побочного потока
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
						display.setFontSize(display.getInitialFontSize() + 3 * number);
					}

					if (scale <= 25)
						display.setFontSize(display.getInitialFontSize());

					int zoomH = (int) (scale * display.getfirstSize().getHeight() / 100);
					int zoomW = (int) (scale * display.getfirstSize().getWidth() / 100);

					Dimension newSize = new Dimension((int) display.getfirstSize().getWidth() + zoomW,
							(int) display.getfirstSize().getHeight() + zoomH);
					panelButtons.changeLabelScale("Масштаб: " + scale + "%");
					display.setPreferredSize(newSize);
					display.setSize(newSize);
					display.revalidate();
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
			tempXBeg = panelButtons.getXBegin();
			tempXEnd = panelButtons.getXEnd();
		}

		calc = new Calculations(MainWindow.this, controller);
		Thread thread = new Thread(calc);
		thread.start();
		
	}

	public void update() {
		frame.repaint();
		mainTable.update();
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
