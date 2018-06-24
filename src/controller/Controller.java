package controller;

import java.util.List;

import view.MainWindow;

public class Controller {
	public MainWindow window;
	public int a;
	public double xBeg;
	public double xEnd;
	
	public Controller (MainWindow window)
	{
		this.window = window;
	}

	public synchronized void addValues(double x, double fx) {
		window.addValues(x, fx);
		window.update();
	}
	
	public synchronized void addValueOnPlace(int place, double x, double fx) {
		window.addValueOnPlace(place, x, fx);
		window.update();
	}

	public List<List<Double>> getValues() {
		return window.getValues();
	}

	public synchronized void clear() {
		if (window.getValues().isEmpty() == false)
			window.clear();
		window.update();
	}
	
	public void setA(int a)
	{
		this.a = a;
	}
	
	public void setXBeg(double xBeg)
	{
		this.xBeg = xBeg;
	}
	
	public void setXEnd(double xEnd)
	{
		this.xEnd = xEnd;
	}
	
	public int getA()
	{
		return a;
	}
	
	public double getXBeg()
	{
		return xBeg;
	}
	
	public double getXEnd()
	{
		return xEnd;
	}
}
