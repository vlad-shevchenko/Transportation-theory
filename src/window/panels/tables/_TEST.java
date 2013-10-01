package window.panels.tables;

import java.awt.SystemColor;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class _TEST extends JPanel {
	private JTable table;
	public _TEST() {
		setSize(new Dimension(700, 550));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{619, 0};
		gridBagLayout.rowHeights = new int[]{444, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		ListModel costListModel = new CostRowHeader(3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMaximumSize(new Dimension(400, 150));
		scrollPane.setPreferredSize(new Dimension(400, 150));
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 3));
		scrollPane.setSize(new Dimension(550, 250));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		JList rowHeader = new JList(costListModel);
		rowHeader.setSize(new Dimension(160, 250));
		rowHeader.setBackground(SystemColor.control);
		rowHeader.setFixedCellWidth(150);
		rowHeader.setFixedCellHeight(table.getRowHeight() + table.getRowMargin() - 1);
		rowHeader.setCellRenderer(new RowHeaderRenderer(table));
		scrollPane.setRowHeaderView(rowHeader);
	}

}
