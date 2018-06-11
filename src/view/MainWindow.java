package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import java.util.*;

import javax.swing.table.DefaultTableModel;
import model.Calculations;
import controller.*;

public class MainWindow{	
	public JFrame frame = new JFrame();
	public Controller controller;
	private int width = 900;
	private int height = 700;
	public int tempA;
	public double tempXBeg, tempXEnd;
	private PanelOfButtons panelButtons = new PanelOfButtons();
	public Table mainTable;
	public Display display;
	public Calculations calc;//static
	public JScrollPane scroll;
	
	public MainWindow()
	{
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);		
		frame.getContentPane().setLayout(new FlowLayout());
		
		controller = new Controller();
		display = new Display(MainWindow.this,controller); 
		mainTable = new Table(this, controller);

		scroll = new JScrollPane(display);
		scroll.setPreferredSize(new Dimension(605,505));
		scroll.setAutoscrolls(true);
		scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		frame.add(mainTable.getScrollPaneOfTable());
		frame.add(scroll);
		frame.add(panelButtons.panel);
		
		MoveMouse listener = new MoveMouse(display);
		scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		scroll.getViewport().addMouseListener(listener);
		scroll.getViewport().addMouseMotionListener(listener);
		Zoom zoomListener = new Zoom(MainWindow.this,display,panelButtons);
		scroll.addMouseWheelListener(zoomListener);
		int res = (int) (display.getSize().getHeight()/display.getInitialSize().getHeight()*100 - 100);

		
		panelButtons.getMainButton().addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						try {
							if(panelButtons.getValueXBeg().equals("") || panelButtons.getValueXEnd().equals("") || panelButtons.getValueA().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Введены некорректные данные!");
							}
							 
							else {
								controller.clear();
								startCalculation();
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});
		
		panelButtons.getScaleButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				int scale = Integer.valueOf(panelButtons.getScale().getText());
				
				if (scale >=0)
				{
					if (scale / 25 >= 1)
					{
						int number = (int) Math.floor(scale / 25);
						display.setFontSize(display.getInitialFontSize()+ 3 * number); // initial		
					}
					
					if (scale / 50 >= 1)
					{
						int number = (int) Math.floor(scale / 50);
						display.setPenSize(display.getInitialPenSize() + number);
					}
					
					if (scale <= 25)
					{
						display.setFontSize(display.getInitialFontSize());
						display.setPenSize(display.getInitialPenSize());
					}
					
					int zoomH = (int) (scale * display.getInitialSize().getHeight() / 100);
					int zoomW = (int) (scale * display.getInitialSize().getWidth() / 100);
					
					Dimension newSize = new Dimension((int)display.getInitialSize().getWidth() + zoomW, (int)display.getInitialSize().getHeight() + zoomH);
					panelButtons.changeLabelScale("Масштаб: " + scale + "%");
					display.setPreferredSize(newSize);
					display.setSize(newSize);
					display.revalidate();
				}
			}
		});
	}
	
	public void startCalculation() throws InterruptedException
	{
		if (isInt(panelButtons.getValueA()) == false || isInt(panelButtons.getValueXBeg()) == false || isInt(panelButtons.getValueXEnd()) == false)
		{
			JOptionPane.showMessageDialog(null, "Введите целые числа!");
			return;
		}
		else 
		{
			tempA = panelButtons.getA();
			tempXBeg = panelButtons.getXBegin();
			tempXEnd = panelButtons.getXEnd();
		}
		
		calc = new Calculations(MainWindow.this,controller);
		Thread thread = new Thread(calc);		
		thread.start();		
	}
	
	public void update()
	{
		mainTable.update();			
	}
	  
	public void show() {
		frame.setVisible(true);
	}
	
	public boolean isInt(String value) throws NumberFormatException
	{
	    try {
	        Integer.parseInt(value);
	        return true;
	    } catch(Exception e) {
	        return false;
	    }
	}
}
