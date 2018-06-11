package controller;
import java.util.ArrayList;

import model.Coordinates;


public class Controller {
	public Coordinates coordinates = new Coordinates();
	public ArrayList values = new ArrayList();
	
	public void addValues(double x, double fx)
	{
		coordinates.addValues(x,fx);
	}
	
	public ArrayList getValues()
	{
		return coordinates.getValues();
	}
	
	public ArrayList getArgs()
	{
		return coordinates.getArgs();
	}
	
	public void clear()
	{
		if (coordinates.getValues().isEmpty() == false)
			coordinates.clear();
	}
}
