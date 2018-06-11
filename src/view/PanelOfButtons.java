package view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelOfButtons {
	public JPanel panel = new JPanel();
	public JTextField a = new JTextField(3);
	public JTextField xBeg = new JTextField(3);
	public JTextField xEnd = new JTextField(3);
	public JTextField scale = new JTextField(3);
	public JLabel labelA = new JLabel("a : ");
	public JLabel labelXBeg = new JLabel("x : ");
	public JLabel labelXEnd = new JLabel("-");
	public JLabel labelScale = new JLabel("Масштаб: 0%");
	public JButton button = new JButton("Построить");
	public JButton buttonScale = new JButton("Изменить");	
	
	public PanelOfButtons()
	{		
		panel.setPreferredSize(new Dimension(350,400));
        panel.setOpaque(true);
		
		panel.add(labelA);
		panel.add(a);
		panel.add(labelXBeg);
		panel.add(xBeg);
		panel.add(labelXEnd);
		panel.add(xEnd);
		panel.add(button);
		panel.add(labelScale);
		panel.add(scale);
		panel.add(buttonScale);	

        panel.setVisible(true);
	}
	
	public JButton getMainButton()
	{
		return button;
	}
	
	public JButton getScaleButton()
	{
		return buttonScale;
	}
	
	public int getA()
	{
		int tempA = Integer.valueOf(a.getText());
		return tempA;
	}
	
	public double getXBegin()
	{
		double tempX = Integer.valueOf(xBeg.getText());
		return tempX;
	}
	
	public void changeLabelScale(String newScale)
	{
		labelScale.setText(newScale);
	}
	
	public JTextField getScale()
	{
		return scale;
	}
	
	public double getXEnd()
	{
		double tempX = Integer.valueOf(xEnd.getText());
		return tempX;
	}
	
	public String getValueA()
	{
		return a.getText();
	}
	
	public String getValueXBeg()
	{
		return xBeg.getText();
	}
	
	public String getValueXEnd()
	{
		return xEnd.getText();
	}
}
