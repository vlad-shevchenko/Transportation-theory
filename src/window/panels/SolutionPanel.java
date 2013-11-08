package window.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;

import start.Const;
import window.MainFrame;
import window.panels.tables.*;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.Box;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

/**
 * Панель содержит таблицу с матрицей перевозок, которая является решением
 * задачи, а также кнопки для сохранения лога работы программы и выхода.
 */

public class SolutionPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTable solutionTable;

	public SolutionPanel(int mines, int factories) {
		setBackground(new Color(176, 224, 230));
		setBounds(0, 0, Const.DEFAULT_FRAME_SIZE.width, 300);
		CostRowHeader listModel = new CostRowHeader(mines);
		GridBagConstraints gbc_costScroll = new GridBagConstraints();
		gbc_costScroll.anchor = GridBagConstraints.NORTHWEST;
		gbc_costScroll.fill = GridBagConstraints.BOTH;
		gbc_costScroll.gridx = 0;
		gbc_costScroll.gridy = 0;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 602, 0 };
		gridBagLayout.rowHeights = new int[] {20, 330, 50, 0};
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		setLayout(gridBagLayout);
		MatrixTableModel model = new MatrixTableModel(mines, factories);
		model.setEditable(false);

		JLabel label = new JLabel(
				"\u041C\u0430\u0442\u0440\u0438\u0446\u0430 \u043F\u0435\u0440\u0435\u0432\u043E\u0437\u043E\u043A");
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		label.setMinimumSize(new Dimension(150, 20));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		add(label, gbc_label);

		JScrollPane solutionScroll = new JScrollPane();
		solutionScroll.setOpaque(false);
		solutionScroll.setToolTipText("");
		GridBagConstraints gbc_solutionScroll = new GridBagConstraints();
		gbc_solutionScroll.insets = new Insets(0, 0, 5, 0);
		gbc_solutionScroll.anchor = GridBagConstraints.NORTH;
		gbc_solutionScroll.gridx = 0;
		gbc_solutionScroll.gridy = 1;
		add(solutionScroll, gbc_solutionScroll);

		solutionTable = new JTable();
		solutionTable.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		solutionTable.getTableHeader().setBackground(new Color(176, 224, 230));
		solutionTable.getTableHeader().setFont(
				new Font("Comic Sans MS", Font.PLAIN, 12));
		solutionTable.setBackground(new Color(176, 224, 230));
		solutionTable
				.setToolTipText("<html><font size=\"4\">\u0420\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442 \u0440\u0430\u0431\u043E\u0442\u044B \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u044B - \u043C\u0430\u0442\u0440\u0438\u0446\u0430 \u043F\u0435\u0440\u0435\u0432\u043E\u0437\u043E\u043A. <br>\r\n\u0412 \u044F\u0447\u0435\u0439\u043A\u0430\u0445 \u0442\u0430\u0431\u043B\u0438\u0446\u044B \u0443\u043A\u0430\u0437\u0430\u043D\u043E \u043A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0442\u043E\u0432\u0430\u0440\u0430, <br>\r\n\u043A\u043E\u0442\u043E\u0440\u044B\u0439 \u043D\u0435\u043E\u0431\u0445\u043E\u0434\u0438\u043C\u043E \u043F\u0435\u0440\u0435\u0432\u0435\u0437\u0442\u0438 \u043E\u0442 \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u044F(\u0441\u0442\u0440\u043E\u043A\u0430) \u043A <br>\r\n\u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u044E(\u0441\u0442\u043E\u043B\u0431\u0435\u0446)</font></html>");
		solutionTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		solutionScroll.setViewportView(solutionTable);
		solutionScroll.getViewport().setOpaque(false);
		solutionTable.setModel(model);

		for (int i = 0; i < solutionTable.getColumnCount(); ++i) {
			TableColumn column = solutionTable.getColumnModel().getColumn(i);
			column.setMinWidth(80);
			column.setPreferredWidth(100);
			column.setMaxWidth(120);
		}
		
		JList<String> rowHeader = new JList<String>(listModel);
		rowHeader
				.setToolTipText("<html><font size=\"4\">\u0420\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442 \u0440\u0430\u0431\u043E\u0442\u044B \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u044B - \u043C\u0430\u0442\u0440\u0438\u0446\u0430 \u043F\u0435\u0440\u0435\u0432\u043E\u0437\u043E\u043A. <br>\r\n\u0412 \u044F\u0447\u0435\u0439\u043A\u0430\u0445 \u0442\u0430\u0431\u043B\u0438\u0446\u044B \u0443\u043A\u0430\u0437\u0430\u043D\u043E \u043A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0442\u043E\u0432\u0430\u0440\u0430, <br>\r\n\u043A\u043E\u0442\u043E\u0440\u044B\u0439 \u043D\u0435\u043E\u0431\u0445\u043E\u0434\u0438\u043C\u043E \u043F\u0435\u0440\u0435\u0432\u0435\u0437\u0442\u0438 \u043E\u0442 \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u044F(\u0441\u0442\u0440\u043E\u043A\u0430) \u043A <br>\r\n\u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u044E(\u0441\u0442\u043E\u043B\u0431\u0435\u0446)</font></html>");
		rowHeader.setBackground(new Color(176, 224, 230));
		rowHeader.setFixedCellWidth(150);
		rowHeader.setFixedCellHeight(solutionTable.getRowHeight()
				+ solutionTable.getRowMargin() - 1);
		rowHeader.setCellRenderer(new RowHeaderRenderer(solutionTable));
		solutionScroll.setRowHeaderView(rowHeader);
		solutionScroll.getRowHeader().getView()
				.setBackground(new Color(176, 224, 230));

		solutionScroll.setPreferredSize(new Dimension(Integer.MAX_VALUE, 230));
		solutionScroll.setMaximumSize(solutionScroll.getPreferredSize());
		solutionScroll.setMinimumSize(solutionScroll.getPreferredSize());
		solutionScroll.setSize(solutionScroll.getPreferredSize());
		
		solutionTable.getTableHeader().setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		solutionScroll.getViewport().setFont(new Font("Comic Sans MS", Font.PLAIN, 14));

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);

		JButton saveButton = new JButton(
				"\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		panel.add(saveButton);
		saveButton
				.setToolTipText("<html><font size=\"4\">\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C \u043B\u043E\u0433 \u0440\u0430\u0431\u043E\u0442\u044B \u0432 \u0442\u0435\u043A\u0441\u0442\u043E\u0432\u044B\u0439 \u0444\u0430\u0439\u043B</font></html>");
		saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
		saveButton.setForeground(new Color(250, 250, 210));
		saveButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		saveButton.setBackground(new Color(102, 205, 170));
		saveButton.setAlignmentX(0.5f);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_2);

		JButton backButton = new JButton("\u041D\u0430\u0437\u0430\u0434");
		panel.add(backButton);
		backButton
				.setToolTipText("<html><font size=\"4\">\u0412\u0435\u0440\u043D\u0443\u0442\u044C\u0441\u044F \u043A \u0432\u0432\u043E\u0434\u0443 \u0438\u0441\u0445\u043E\u0434\u043D\u044B\u0445 \u0434\u0430\u043D\u043D\u044B\u0445 \u0437\u0430\u0434\u0430\u0447\u0438</font></html>");
		backButton.setForeground(new Color(250, 250, 210));
		backButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		backButton.setBackground(new Color(102, 205, 170));
		backButton.setAlignmentX(0.5f);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_3);

		JButton exitButton = new JButton("\u0412\u044B\u0445\u043E\u0434");
		panel.add(exitButton);
		exitButton
				.setToolTipText("<html><font size=\"4\">\u0412\u044B\u0439\u0442\u0438 \u0438\u0437 \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u044B</font></html>");
		exitButton.setForeground(new Color(250, 250, 210));
		exitButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		exitButton.setBackground(new Color(102, 205, 170));
		exitButton.setAlignmentX(0.5f);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel.add(horizontalGlue_1);
		
		saveButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().saveLog();
			}
		});
		backButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().rebuildTablesPanel();
			}
		});
		exitButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().exit();
			}
		});
	}

	public void setTableData(Integer[][] data) {
		for (int i = 0; i < data.length; ++i) {
			for (int j = 0; j < data[0].length; ++j) {
				solutionTable.setValueAt(data[i][j], i, j);
			}
		}
	}
}
