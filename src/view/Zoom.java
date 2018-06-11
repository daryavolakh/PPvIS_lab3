package view;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Zoom implements MouseWheelListener {
	
	public Display graphic;
	public PanelOfButtons buttons;
	public MainWindow mainWindow;
	public Zoom(MainWindow mainWindow, Display graphic, PanelOfButtons buttons)
	{
		this.mainWindow = mainWindow;
		this.graphic = graphic;
		this.buttons = buttons;
	}
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		if (e.getPreciseWheelRotation() < 0 && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            Dimension newSize = new Dimension(graphic.getWidth()+125, graphic.getHeight()+100);
            graphic.setPreferredSize(newSize);
            graphic.setSize(newSize);
            graphic.setFontSize(graphic.getFontSize()+3);
            int scale = (int) (graphic.getSize().getHeight()/graphic.getInitialSize().getHeight()*100 - 100);

            buttons.changeLabelScale("Масштаб: " + scale + "%");
            graphic.revalidate();
        }
        if (e.getPreciseWheelRotation() > 0 && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            if (graphic.getWidth() > graphic.getInitialSize().getWidth()) {
                Dimension newSize = new Dimension(graphic.getWidth() - 125, graphic.getHeight() - 100);
                graphic.setPreferredSize(newSize);
                graphic.setSize(newSize);
                if (graphic.getFontSize() > 15) {
                    graphic.setFontSize(graphic.getFontSize()-3);
                }
                int scale = (int) (graphic.getSize().getHeight()/graphic.getInitialSize().getHeight()*100 - 100);
                if (scale < 0) 
                {
                	scale = 0;
                	buttons.changeLabelScale("Масштаб: " + scale + "%");
                }
                buttons.changeLabelScale("Масштаб: " + scale + "%");
                graphic.revalidate();
				mainWindow.frame.repaint();
			}
		}
	}
}
