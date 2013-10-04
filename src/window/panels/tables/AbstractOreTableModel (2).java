package window.panels.tables;


import java.util.HashSet;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public abstract class AbstractOreTableModel implements TableModel {
	
	public AbstractOreTableModel(int values) {
		this.data = new int[1][values];
		columnCount = values;
	}

	public void addTableModelListener(TableModelListener arg0) {
		this.listeners.add(arg0);
	}

	public Class<?> getColumnClass(int arg0) {
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

	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return true;
	}

	public void removeTableModelListener(TableModelListener arg0) {
		this.listeners.remove(arg0);
	}

	public void setValueAt(Object arg0, int arg1, int arg2) {
		data[arg1][arg2] = (int) arg0;
	}
	
	protected int rowCount;
	protected int columnCount;
	protected Set<TableModelListener> listeners = new HashSet<TableModelListener>();
	protected int[][] data;
}
