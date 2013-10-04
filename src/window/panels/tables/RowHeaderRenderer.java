package window.panels.tables;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
 
/**
 * RowHeaderRenderer renders row headers. K.O.
 */
public class RowHeaderRenderer extends JLabel implements ListCellRenderer {
  
  public RowHeaderRenderer(JTable table) {
    JTableHeader header = table.getTableHeader();
    setOpaque(true);
    setBorder(UIManager.getBorder("TableHeader.cellBorder"));
    setHorizontalAlignment(CENTER);
    setForeground(header.getForeground());
    setBackground(header.getBackground());
    setFont(header.getFont());
  }
  
  public Component getListCellRendererComponent(JList list, 
         Object value, int index, boolean isSelected, boolean cellHasFocus) {
    setText((value == null) ? "" : value.toString());
    return this;
  }
}

