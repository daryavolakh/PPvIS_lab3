package model;

import java.util.ArrayList;
import java.util.List;

public class Coordinates {
	List<List<Double>> values = new ArrayList<>();

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
}
