package window.panels.tables;


import java.util.HashSet;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class CostInputTableModel implements TableModel {

	public CostInputTableModel(int mines, int factories) {
		rowCount = mines;
		columnCount = factories;
		
		data = new Object[mines][factories];
	}
	
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;			
	}

	public int getColumnCount() {
		return columnCount;
	}

	public String getColumnName(int columnIndex) {
//		return new Integer(columnIndex + 1).toString();
		return new String("Потребитель " + (columnIndex + 1));
	}

	public int getRowCount() {
		return rowCount;
	}

	public Object getValueAt(int row, int column) {
		return data[row][column];
	}

	public boolean isCellEditable(int row, int column) {
		return true;
	}

	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}

	public void setValueAt(Object aValue, int row, int column) {
		data[row][column] = aValue;
	}

	private int rowCount;
	private int columnCount;
	private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
	private Object[][] data;
}
