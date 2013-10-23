package window.panels.tables;

import java.util.HashSet;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * TableModel для MineTable и FactoryTable. Содержит одномерный
 * массив(имитирующий двумерный - необходимо для корректной реализации
 * интерфейса) данных таблицы. Имена заголовков - их порядковые номера. Все
 * ячейки редактируемые.
 * 
 * Часть паттерна MVC - модель.
 */

public class OreTableModel implements TableModel {

	protected int rowCount;
	protected int columnCount;
	protected Set<TableModelListener> listeners = new HashSet<TableModelListener>();
	protected Object[][] data;

	public OreTableModel(int values) {
		this.data = new Object[1][values];
		columnCount = values;
	}

	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}

	public Class<?> getColumnClass(int i) {
		return Integer.class;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public String getColumnName(int index) {
		return new String("№ " + (index + 1));
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
		listeners.remove(l);
	}

	public void setValueAt(Object value, int row, int column) {
		data[row][column] = value;
	}
}
