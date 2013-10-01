package window.panels;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.SystemColor;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableColumn;

import window.panels.tables.CostInputTableModel;
import window.panels.tables.CostRowHeader;
import window.panels.tables.RowHeaderRenderer;

import java.awt.GridBagLayout;
import java.awt.FlowLayout;


public class SolutionPanel extends JPanel {
	public SolutionPanel(int mines, int factories) {
		// For debug
//		mines = 3;
//		factories = 4;
		
		this.setBounds(0, 0, 600, 300);
		ListModel costListModel = new CostRowHeader(mines);
	    
	    JScrollPane solutionScroll = new JScrollPane();
	    solutionScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	    GridBagConstraints gbc_costScroll = new GridBagConstraints();
	    gbc_costScroll.anchor = GridBagConstraints.NORTHWEST;
	    gbc_costScroll.fill = GridBagConstraints.BOTH;
	    gbc_costScroll.gridx = 0;
	    gbc_costScroll.gridy = 0;
	    setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
	    add(solutionScroll);
	    
	    solutionTable = new JTable();
	    solutionTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    solutionScroll.setViewportView(solutionTable);
	    solutionTable.setModel(new CostInputTableModel(mines, factories));
	    JList costHeader = new JList(costListModel);
	    costHeader.setBackground(SystemColor.control);
	    costHeader.setFixedCellWidth(150);
	    costHeader.setFixedCellHeight(solutionTable.getRowHeight() + solutionTable.getRowMargin() - 1);
	    costHeader.setCellRenderer(new RowHeaderRenderer(solutionTable));
	    solutionScroll.setRowHeaderView(costHeader);
		for(int i = 0; i < solutionTable.getColumnCount(); ++i) {
			TableColumn column = solutionTable.getColumnModel().getColumn(i);
			column.setMinWidth(80);
			column.setPreferredWidth(100);
			column.setMaxWidth(120);
		}
	}
	private JTable solutionTable;
}
