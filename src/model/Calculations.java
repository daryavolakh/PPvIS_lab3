package model;

import view.*;

import controller.Controller;

public class Calculations implements Runnable {

	public double x;
	public double beginI;
	public double endI;
	public int a;
	public double h = 0.1;
	public MainWindow mainWindow;
	public Controller controller;

	public Calculations(MainWindow mainWindow, Controller controller) {
		this.mainWindow = mainWindow;
		this.controller = controller;
		a = mainWindow.tempA;
		beginI = mainWindow.tempXBeg;
		endI = mainWindow.tempXEnd;
	}

	public double function(double x) {
		double fx = 0;
		for (double i = beginI; i <= endI; i++) {
			fx += Math.pow((-1), (i + 1)) * Math.pow(2, (2 * i + 1)) * Math.pow((a * x), 2 * i) / getFactorial(2 * i);//Math.pow((-1), (i + 1)) * Math.pow(2, (2 * i + 1)) * Math.pow((a * x), 2 * i) / getFactorial(2 * i);
		}
		return fx;
	}

	@Override
	public void run() {
		synchronized (mainWindow) {
			double beginX = beginI;
			double endX = endI;
			double tempFx = 0;
			double h = 0.1;

			for (double x = beginX; x <= endX; x += h) {
				tempFx = function(x);
				tempFx = Math.round(tempFx * 10d) / 10d;
				x = Math.round(x * 10d) / 10d;
				controller.addValues(x, tempFx);

				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mainWindow.update(); 
			}
		}
	}

	public int getFactorial(double fact) {
		int result = 1;
		for (int index = 1; index <= fact; index++)
			result *= index;
		return result;
	}
}
