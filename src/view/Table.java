package view;

import java.awt.Dimension;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import controller.Controller;

public class Table {
	public Vector<String> columns = new Vector<String>();
	public DefaultTableModel model = new DefaultTableModel(columns, 0);
	public JTable table;
	public JScrollPane scrollPane;
	public MainWindow mainWindow;
	public Controller controller;

	public Table(MainWindow mainWindow, Controller controller) {
		this.mainWindow = mainWindow;
		this.controller = controller;
		columns.add("x");
		columns.add("F(x)");
		table = new JTable(model);
		table.setModel(model);
		scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setPreferredScrollableViewportSize(new Dimension(150, 350));
	}

	public JScrollPane getScrollPaneOfTable() {
		return scrollPane;
	}

	public void update() {

		List<List<Double>> newValues = controller.getValues();
		
		for (int index = model.getRowCount() - 1; index >= 0; index--) {
			model.removeRow(index);
		}

		for (int index = 0; index < controller.getValues().size(); index++) {
			Vector<Double> vector = new Vector<Double>();

			vector.addAll(newValues.get(index));

			model.addRow(vector);
		}
	}
}
