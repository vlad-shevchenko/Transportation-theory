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

import start.Const;
import window.panels.tables.*;

import java.awt.GridBagLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.Box;
import java.awt.ComponentOrientation;

public class SolutionPanel extends JPanel {
	public SolutionPanel(int mines, int factories) {		
		this.setBounds(0, 0, Const.DEFAULT_FRAME_SIZE.width, 300);
		ListModel listModel = new CostRowHeader(mines);
	    GridBagConstraints gbc_costScroll = new GridBagConstraints();
	    gbc_costScroll.anchor = GridBagConstraints.NORTHWEST;
	    gbc_costScroll.fill = GridBagConstraints.BOTH;
	    gbc_costScroll.gridx = 0;
	    gbc_costScroll.gridy = 0;
	    GridBagLayout gridBagLayout = new GridBagLayout();
	    gridBagLayout.columnWidths = new int[]{602, 0};
	    gridBagLayout.rowHeights = new int[] {20, 230, 50};
	    gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	    gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
	    setLayout(gridBagLayout);
	    MatrixTableModel model = new MatrixTableModel(mines, factories);
	    model.setEditable(false);
	    
	    JLabel label = new JLabel("\u041C\u0430\u0442\u0440\u0438\u0446\u0430 \u043F\u0435\u0440\u0435\u0432\u043E\u0437\u043E\u043A");
	    label.setMinimumSize(new Dimension(150, 20));
	    GridBagConstraints gbc_label = new GridBagConstraints();
	    gbc_label.anchor = GridBagConstraints.SOUTH;
	    gbc_label.insets = new Insets(0, 0, 5, 0);
	    gbc_label.gridx = 0;
	    gbc_label.gridy = 0;
	    add(label, gbc_label);
	    
	    JScrollPane solutionScroll = new JScrollPane();
	    label.setLabelFor(solutionScroll);
	    solutionScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	    GridBagConstraints gbc_solutionScroll = new GridBagConstraints();
	    gbc_solutionScroll.anchor = GridBagConstraints.NORTH;
	    gbc_solutionScroll.gridx = 0;
	    gbc_solutionScroll.gridy = 1;
	    add(solutionScroll, gbc_solutionScroll);
	    
	    solutionTable = new JTable();
	    solutionTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    solutionScroll.setViewportView(solutionTable);
	    solutionTable.setModel(model);
	    JList rowHeader = new JList(listModel);
	    rowHeader.setBackground(SystemColor.control);
	    rowHeader.setFixedCellWidth(150);
	    rowHeader.setFixedCellHeight(solutionTable.getRowHeight() + solutionTable.getRowMargin() - 1);
	    rowHeader.setCellRenderer(new RowHeaderRenderer(solutionTable));
	    solutionScroll.setRowHeaderView(rowHeader);
	    
	    JPanel panel = new JPanel();
	    panel.setPreferredSize(new Dimension(0, 0));
	    panel.setMinimumSize(new Dimension(0, 0));
	    GridBagConstraints gbc_panel = new GridBagConstraints();
	    gbc_panel.weighty = 100.0;
	    gbc_panel.weightx = 100.0;
	    gbc_panel.gridx = 0;
	    gbc_panel.gridy = 2;
	    add(panel, gbc_panel);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    
	    saveButton = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
	    saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panel.add(saveButton);
	    Component horizontalStrut = Box.createHorizontalStrut(50);
	    panel.add(horizontalStrut);
	    exitButton = new JButton("\u0412\u044B\u0445\u043E\u0434");
	    exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panel.add(exitButton);
	    
		for(int i = 0; i < solutionTable.getColumnCount(); ++i) {
			TableColumn column = solutionTable.getColumnModel().getColumn(i);
			column.setMinWidth(80);
			column.setPreferredWidth(100);
			column.setMaxWidth(120);
		}
		
	    solutionScroll.setPreferredSize(new Dimension(Integer.MAX_VALUE, 230));
	    solutionScroll.setMaximumSize(solutionScroll.getPreferredSize());
	    solutionScroll.setMinimumSize(solutionScroll.getPreferredSize());
	    solutionScroll.setSize(solutionScroll.getPreferredSize());
	    
	    panel.setPreferredSize(new Dimension(
	    		saveButton.getPreferredSize().width + horizontalStrut.getPreferredSize().width + exitButton.getPreferredSize().width, 
	    		50));
	    panel.setMinimumSize(panel.getPreferredSize());
	    panel.setSize(panel.getPreferredSize());
	    panel.setMaximumSize(panel.getPreferredSize());
	}
	
	public void setTableData(int[][] data) {
		for(int i = 0; i < data.length; ++i) {
			for(int j = 0; j < data[0].length; ++j) {
				this.solutionTable.setValueAt(data[i][j], i, j);
			}
		}
	}
	
	public void setSaveAction(ActionListener l) {
		this.saveButton.addActionListener(l);
	}
	
	public void setExitAction(ActionListener l) {
		this.exitButton.addActionListener(l);
	}
	
	private JTable solutionTable;
	private JButton saveButton;
	private JButton exitButton;
}
