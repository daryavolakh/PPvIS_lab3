package view;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Zoom implements MouseWheelListener {

	public Plot graphic;
	public PanelOfButtons buttons;
	public MainWindow mainWindow;

	public Zoom(MainWindow mainWindow, Plot graphic, PanelOfButtons buttons) {
		this.mainWindow = mainWindow;
		this.graphic = graphic;
		this.buttons = buttons;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		if (event.getPreciseWheelRotation() < 0 && KeyEvent.VK_CONTROL != 0) {
			Dimension newSize = new Dimension(graphic.getWidth() + 150, graphic.getHeight() + 100);
			graphic.setPreferredSize(newSize);
			graphic.setSize(newSize);
			graphic.setFontSize(graphic.getFontSize() + 3);
			int scale = (int) (Math.abs(graphic.getSize().getHeight() / 5 - 100));
			
			buttons.changeLabelScale("Масштаб: " + scale + "%");
			graphic.revalidate();
		}
		if (event.getPreciseWheelRotation() > 0 && KeyEvent.VK_CONTROL != 0) {
			if (graphic.getWidth() > 600) {
				Dimension newSize = new Dimension(graphic.getWidth() - 150, graphic.getHeight() - 100);
				graphic.setPreferredSize(newSize);
				graphic.setSize(newSize);
				
				if (graphic.getFontSize() > 15) {
					graphic.setFontSize(graphic.getFontSize() - 3);
				}
				
				int scale = (int) (Math.abs(graphic.getSize().getHeight() / 5 - 100));
				
				if (scale < 0) {
					scale = 0;
					buttons.changeLabelScale("Масштаб: " + scale + "%");
				}
				
				buttons.changeLabelScale("Масштаб: " + scale + "%");
				mainWindow.repaintGraph();
			}
		}

	}
}
