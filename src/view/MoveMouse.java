package view;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

public class MoveMouse extends MouseAdapter{
	public Point point;
	public Display graphic;
	public MoveMouse(Display graphic)
	{
		this.graphic = graphic;
	}
	public void mousePressed(MouseEvent event)
	{
		point = event.getPoint();
	}
	
	public void mouseDragged(MouseEvent event)
	{
		if (point != null)
		{
			JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, graphic);
			if (viewPort != null)
			{
				int diffX = point.x - event.getX();
				int diffY = point.y - event.getY();
				
				Rectangle view = viewPort.getViewRect();
				
				view.x += 0.5*diffX; 
				view.y += 0.5*diffY;
				
				graphic.scrollRectToVisible(view);
			}
		}
	}
}
