package view;

import java.awt.Dimension;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class Table {
	public Vector<String> columns = new Vector<String>();
	public DefaultTableModel model = new DefaultTableModel(columns, 0);
	public JTable table;
	public JScrollPane scrollPane;
	public MainWindow mainWindow;

	public Table(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		columns.add("x");
		columns.add("F(x)");
		table = new JTable();
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

		List<List<Double>> newValues = mainWindow.getValues();

		model = new DefaultTableModel(columns,0);
		table.setModel(model);
		
		for (int index = 0; index < mainWindow.getValues().size(); index++) {
			Vector<Double> vector = new Vector<Double>();

			vector.add(newValues.get(index).get(0));
			vector.add(newValues.get(index).get(1));
			model.addRow(vector);
		}
	}
}
