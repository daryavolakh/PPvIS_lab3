package model;

import controller.Controller;

public class Calculations implements Runnable {

	public double x;
	public double beginI;
	public double endI;
	public int a;
	public double h = 0.1;
	public Controller controller;

	public Calculations(Controller controller) {	
	
		this.controller = controller;
		a = controller.getA();
		beginI = controller.getXBeg();
		endI = controller.getXEnd();
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
		synchronized (this) {
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
