package window.panels.tables;
import java.util.HashSet;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class SolutionTableModel implements TableModel {
	
	public SolutionTableModel(int mines, int factories) {
		this.rowCount = mines;
		this.columnCount = factories;
		
		this.data = new Object[mines][factories];
	}

	public void addTableModelListener(TableModelListener arg0) {
		this.listeners.add(arg0);
	}

	public Class<?> getColumnClass(int arg0) {
		return String.class;			
	}

	public int getColumnCount() {
		return columnCount;
	}

	public String getColumnName(int arg0) {
		return new String("Потребитель №" + (arg0 + 1));			
	}

	public int getRowCount() {
		return rowCount;
	}

	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	public void removeTableModelListener(TableModelListener arg0) {
		this.listeners.remove(arg0);
	}

	public void setValueAt(Object arg0, int arg1, int arg2) {
		data[arg1][arg2] = arg0;
	}
	
	private int rowCount;
	private int columnCount;
	private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
	private Object[][] data;

}
