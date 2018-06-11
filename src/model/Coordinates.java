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
	
	public void changeValues()
	{
		/*ArrayList tempV = values;
		values.clear();
		for (int index = 0; index < tempV.size(); index++)
		{	
			double temp = (double) tempV.get(index) / 5	;
			values.add(temp);
		}
		System.out.println(values);*/
	}
	
}
