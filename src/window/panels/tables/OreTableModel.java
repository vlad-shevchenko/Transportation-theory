package window.panels.tables;

import java.util.HashSet;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * Table model for MineTable and FactoryTable. Contains one-dimensional Object
 * array(rather, Object[1][] - its required by interface) of table data. Column
 * header is its serial number. Any cell is editable.
 */

public class OreTableModel implements TableModel {

	public OreTableModel(int values) {
		this.data = new Object[1][values];
		columnCount = values;
	}

	public void addTableModelListener(TableModelListener l) {
		this.listeners.add(l);
	}

	public Class<?> getColumnClass(int i) {
		return Integer.class;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public String getColumnName(int index) {
		return new String("¹ " + (index + 1));
	}

	public int getRowCount() {
		return data.length;
	}

	public Object getValueAt(int row, int column) {
		return data[row][column];
	}

	public boolean isCellEditable(int row, int column) {
		return true;
	}

	public void removeTableModelListener(TableModelListener l) {
		this.listeners.remove(l);
	}

	public void setValueAt(Object value, int row, int column) {
		data[row][column] = value;
	}

	protected int rowCount;
	protected int columnCount;
	protected Set<TableModelListener> listeners = new HashSet<TableModelListener>();
	protected Object[][] data;
}
