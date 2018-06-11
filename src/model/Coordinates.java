package model;

import java.util.ArrayList;

public class Coordinates {
	ArrayList args = new ArrayList();
	ArrayList values = new ArrayList();
	
	public ArrayList getValues()
	{
		return values;
	}
	
	public ArrayList getArgs()
	{
		return args;
	}
	
	public void addValues(double x, double fx)
	{
		args.add(x);
		values.add(fx);
	}
	
	public void clear()
	{
		args.clear();
		values.clear();
	}	
}
