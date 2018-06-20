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

	public void clear() {
		values.clear();
	}
}
