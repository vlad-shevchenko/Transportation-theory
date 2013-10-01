package window.panels;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;

import java.awt.Insets;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.JLabel;

import window.panels.tables.InputTableModel;
import window.panels.tables.CostRowHeader;
import window.panels.tables.FactoryRowHeader;
import window.panels.tables.FactoryTableModel;
import window.panels.tables.MineRowHeader;
import window.panels.tables.MineTableModel;
import window.panels.tables.RowHeaderRenderer;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TablesPanel extends JPanel {
	
	public TablesPanel(int mines, int factories) {		
		// For debug
		mines = 4;
		factories = 8;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		
		// "Количество товара у производителей"
		label = new JLabel("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0442\u043E\u0432\u0430\u0440\u0430 \u0443 \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u0435\u0439");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		add(label, gbc_label);
		
		mineScroll = new JScrollPane();
		GridBagConstraints gbc_mineScroll = new GridBagConstraints();
		gbc_mineScroll.weighty = 0.1;
		gbc_mineScroll.weightx = 0.1;
		gbc_mineScroll.fill = GridBagConstraints.BOTH;
		gbc_mineScroll.insets = new Insets(0, 0, 5, 0);
		gbc_mineScroll.gridx = 0;
		gbc_mineScroll.gridy = 1;
		add(mineScroll, gbc_mineScroll);
		
		mineTable = new JTable();
		mineTable.setBackground(SystemColor.textHighlightText);
		mineScroll.setViewportView(mineTable);
		mineTable.setModel(new MineTableModel(mines));
		for(int i = 0; i < mineTable.getColumnCount(); ++i) {
			TableColumn column = mineTable.getColumnModel().getColumn(i);
			column.setMinWidth(30);
			column.setPreferredWidth(50);
			column.setMaxWidth(50);
		}
		mineTable.setCellSelectionEnabled(true);	
		mineTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ListModel mineListModel = new MineRowHeader(2);
	    JList mineHeader = new JList(mineListModel);
	    mineHeader.setBackground(SystemColor.control);
	    mineHeader.setFixedCellWidth(150);
	    mineHeader.setFixedCellHeight(mineTable.getRowHeight() + mineTable.getRowMargin() - 1);
	    mineHeader.setCellRenderer(new RowHeaderRenderer(mineTable));
	    mineScroll.setRowHeaderView(mineHeader);
		
	    // "Количество товара, необходимого потребителям"
		label_1 = new JLabel("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0442\u043E\u0432\u0430\u0440\u0430, \u043D\u0435\u043E\u0431\u0445\u043E\u0434\u0438\u043C\u043E\u0433\u043E \u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u044F\u043C");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 2;
		add(label_1, gbc_label_1);
		
		factoryScroll = new JScrollPane();
		GridBagConstraints gbc_factoryScroll = new GridBagConstraints();
		gbc_factoryScroll.weighty = 0.1;
		gbc_factoryScroll.weightx = 0.1;
		gbc_factoryScroll.fill = GridBagConstraints.BOTH;
		gbc_factoryScroll.insets = new Insets(0, 0, 5, 0);
		gbc_factoryScroll.gridx = 0;
		gbc_factoryScroll.gridy = 3;
		add(factoryScroll, gbc_factoryScroll);
		
		factoryTable = new JTable();
		factoryScroll.setViewportView(factoryTable);
		factoryTable.setModel(new FactoryTableModel(factories));
		factoryTable.setCellSelectionEnabled(true);	
		for(int i = 0; i < factoryTable.getColumnCount(); ++i) {
			TableColumn column = factoryTable.getColumnModel().getColumn(i);
			column.setMinWidth(30);
			column.setPreferredWidth(50);
			column.setMaxWidth(50);
		}
		factoryTable.getTableHeader().setVisible(false);		
		ListModel factoryListModel = new FactoryRowHeader(2);
	    JList factoryHeader = new JList(factoryListModel);
	    factoryHeader.setBackground(SystemColor.control);
	    factoryHeader.setFixedCellWidth(150);
	    factoryHeader.setFixedCellHeight(factoryTable.getRowHeight() + factoryTable.getRowMargin() - 1);
	    factoryHeader.setCellRenderer(new RowHeaderRenderer(factoryTable));
	    factoryScroll.setRowHeaderView(factoryHeader);	
	    factoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		label_2 = new JLabel("\u041C\u0430\u0442\u0440\u0438\u0446\u0430 \u0441\u0442\u043E\u0438\u043C\u043E\u0441\u0442\u0438");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 0);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 4;
		add(label_2, gbc_label_2);
		
		costScroll = new JScrollPane();
		costScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_costScroll = new GridBagConstraints();
		gbc_costScroll.insets = new Insets(0, 0, 5, 0);
		gbc_costScroll.weighty = 0.5;
		gbc_costScroll.weightx = 0.5;
		gbc_costScroll.fill = GridBagConstraints.BOTH;
		gbc_costScroll.gridx = 0;
		gbc_costScroll.gridy = 5;
		add(costScroll, gbc_costScroll);
		
		costTable = new JTable();
		costTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		costScroll.setViewportView(costTable);
		costTable.setModel(new InputTableModel(mines, factories));
		ListModel costListModel = new CostRowHeader(mines);
	    JList costHeader = new JList(costListModel);
	    costHeader.setBackground(SystemColor.control);
	    costHeader.setFixedCellWidth(150);
	    costHeader.setFixedCellHeight(costTable.getRowHeight() + costTable.getRowMargin() - 1);
	    costHeader.setCellRenderer(new RowHeaderRenderer(costTable));
	    costScroll.setRowHeaderView(costHeader);
		for(int i = 0; i < costTable.getColumnCount(); ++i) {
			TableColumn column = costTable.getColumnModel().getColumn(i);
			column.setPreferredWidth(110);
		}
	    
		gridBagLayout.columnWidths = new int[] {605};
		gridBagLayout.rowHeights = new int[] {20, 80, 20, 80, 20, 150, 30};
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		
		// "Посчитать"
		okButton = new JButton("\u041F\u043E\u0441\u0447\u0438\u0442\u0430\u0442\u044C");
		GridBagConstraints tablesOkButton = new GridBagConstraints();
		tablesOkButton.gridx = 0;
		tablesOkButton.gridy = 6;
		add(okButton, tablesOkButton);
	}
	
	public void resetWidth(int width) {
		((GridBagLayout) this.getLayout()).columnWidths = new int[] { width };
	}
	
	public int[] getMineArray() {
		int[] result = new int[mineTable.getColumnCount()];
		for(int i = 0; i < mineTable.getColumnCount(); ++i) {
			result[i] = Integer.parseInt((String) mineTable.getValueAt(1, i));
		}
		
		return result;
	}
	
	public int[] getFactoryArray() {
		int[] result = new int[factoryTable.getColumnCount()];
		for(int i = 0; i < factoryTable.getColumnCount(); ++i) {
			result[i] = Integer.parseInt((String) factoryTable.getValueAt(1, i));
		}
		
		return result;
	}
	
	public int[][] getCostArray() {
		int[][] result = new int[costTable.getRowCount()][costTable.getColumnCount()];
		
		for(int i = 0; i < costTable.getRowCount(); ++i) {
			for(int j = 0; j < costTable.getColumnCount(); ++j) {
				result[i][j] = Integer.parseInt((String) costTable.getValueAt(i, j));
			}
		}
		
		return result;
	}
	
	public void setOkAction(ActionListener listener) {
		okButton.addActionListener(listener);
	}
	
	private JTable factoryTable;
	private JTable mineTable;
	private JTable costTable;
	private JScrollPane mineScroll;
	private JScrollPane factoryScroll;
	private JScrollPane costScroll;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JButton okButton;
}
