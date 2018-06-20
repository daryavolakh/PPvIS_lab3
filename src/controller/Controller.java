package controller;

import java.util.List;
import model.Coordinates;
import view.Plot;

public class Controller {
	public Coordinates coordinates;
	public Plot graphic;
	
	public Controller(Coordinates coordinates)
	{
		this.coordinates = coordinates;
	}

	public void addValues(double x, double fx) {
		//graphic.addValues(x,fx);
		coordinates.addValues(x, fx);
	}

	public List<List<Double>> getValues() {
		return coordinates.getValues();   //graphic.getValues
	}

	public void clear() {
		if (coordinates.getValues().isEmpty() == false)
			coordinates.clear();
	}
}
