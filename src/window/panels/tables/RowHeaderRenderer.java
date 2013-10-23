package window.panels.tables;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * RowHeaderRenderer renders row headers. K.O. 
 * Отрисовывает горизонтальные заголовки.
 * 
 * Часть паттерна MVC - представление.
 */

@SuppressWarnings("rawtypes")
public class RowHeaderRenderer extends JLabel implements ListCellRenderer {
	private static final long serialVersionUID = 1L;

	public RowHeaderRenderer(JTable table) {
		JTableHeader header = table.getTableHeader();
		setOpaque(true);
		setFocusable(false);
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		setHorizontalAlignment(CENTER);
		setForeground(header.getForeground());
		setBackground(header.getBackground());
		setFont(header.getFont());
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		setText((value == null) ? "" : value.toString());
		return this;
	}
}
