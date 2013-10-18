package window.panels;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;

import java.awt.Insets;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.JLabel;

import start.Const;
import window.panels.tables.*;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;

import javax.swing.DebugGraphics;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;

/**
 * Панель с таблицами для ввода основных данных: количества товара у
 * производителей, количества товара, необходимого потребителям и матрицы
 * стоимости. Метод checkData() проверяет корректность введённых данных.
 */

public class TablesPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public TablesPanel(int mines, int factories) {
		setBackground(new Color(176, 224, 230));
		// Debug
		// mines = 3;
		// factories = 4;
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);

		// "Количество товара у производителей"
		label = new JLabel(
				"\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0442\u043E\u0432\u0430\u0440\u0430 \u0443 \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u0435\u0439");
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label.setFocusable(false);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		add(label, gbc_label);

		mineScroll = new JScrollPane();
		mineScroll.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		mineScroll.setForeground(new Color(176, 224, 230));
		mineScroll.setBackground(new Color(176, 224, 230));
		mineScroll.setToolTipText("");
		mineScroll.setFocusable(false);
		mineScroll.getViewport().setOpaque(false);
		GridBagConstraints gbc_mineScroll = new GridBagConstraints();
		gbc_mineScroll.weighty = 0.1;
		gbc_mineScroll.weightx = 0.1;
		gbc_mineScroll.fill = GridBagConstraints.BOTH;
		gbc_mineScroll.insets = new Insets(0, 0, 5, 0);
		gbc_mineScroll.gridx = 0;
		gbc_mineScroll.gridy = 1;
		add(mineScroll, gbc_mineScroll);

		mineTable = new JTable();
		mineTable.setOpaque(false);
		mineTable.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		mineTable
				.setToolTipText("<html><font size=\"4\">\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0432 \u044F\u0447\u0435\u0439\u043A\u0438 \u043A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0442\u043E\u0432\u0430\u0440\u0430 \u0443 \u043A\u0430\u0436\u0434\u043E\u0433\u043E \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u044F</font></html>");
		mineTable.setBackground(new Color(176, 224, 230));
		mineTable.setRowHeight(mineTable.getRowHeight() + 5);
		mineScroll.setViewportView(mineTable);
		mineTable.setModel(new OreTableModel(mines));
		mineTable.setCellSelectionEnabled(true);
		mineTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		mineTable.getTableHeader().setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		mineTable.getTableHeader().setBackground(new Color(176, 224, 230));
		ListModel<String> mineListModel = new MineRowHeader(1);
		JList<String> mineHeader = new JList<String>(mineListModel);
		mineHeader
				.setToolTipText("<html><font size=\"4\">\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0432 \u044F\u0447\u0435\u0439\u043A\u0438 \u043A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0442\u043E\u0432\u0430\u0440\u0430 \u0443 \u043A\u0430\u0436\u0434\u043E\u0433\u043E \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u044F</font></html>");
		mineHeader.setFocusable(false);
		mineHeader.setFixedCellWidth(150);
		mineHeader.setFixedCellHeight(mineTable.getRowHeight()
				+ mineTable.getRowMargin() - 1);
		mineHeader.setCellRenderer(new RowHeaderRenderer(mineTable));
		mineScroll.setRowHeaderView(mineHeader);
		mineScroll.getRowHeader().setOpaque(true);
		mineScroll.getRowHeader().getView().setBackground(new Color(176, 224, 230));

		// "Количество товара, необходимого потребителям"
		label_1 = new JLabel(
				"\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0442\u043E\u0432\u0430\u0440\u0430, \u043D\u0435\u043E\u0431\u0445\u043E\u0434\u0438\u043C\u043E\u0433\u043E \u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u044F\u043C");
		label_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label_1.setFocusable(false);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 2;
		add(label_1, gbc_label_1);

		factoryScroll = new JScrollPane();
		factoryScroll.setOpaque(false);
		factoryScroll.setForeground(new Color(176, 224, 230));
		factoryScroll.setBackground(new Color(176, 224, 230));
		factoryScroll.setToolTipText("");
		factoryScroll.setFocusable(false);
		factoryScroll.getViewport().setOpaque(false);
		GridBagConstraints gbc_factoryScroll = new GridBagConstraints();
		gbc_factoryScroll.weighty = 0.1;
		gbc_factoryScroll.weightx = 0.1;
		gbc_factoryScroll.fill = GridBagConstraints.BOTH;
		gbc_factoryScroll.insets = new Insets(0, 0, 5, 0);
		gbc_factoryScroll.gridx = 0;
		gbc_factoryScroll.gridy = 3;
		add(factoryScroll, gbc_factoryScroll);

		factoryTable = new JTable();
		factoryTable.setOpaque(false);
		factoryTable.setBackground(new Color(176, 224, 230));
		factoryTable.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		factoryTable.getTableHeader().setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		factoryTable
				.setToolTipText("<html><font size=\"4\">\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0432 \u044F\u0447\u0435\u0439\u043A\u0438 \u043A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0442\u043E\u0432\u0430\u0440\u0430, \u043D\u0435\u043E\u0431\u0445\u043E\u0434\u0438\u043C\u043E\u0433\u043E \u043A\u0430\u0436\u0434\u043E\u043C\u0443 \u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u044E</font></html>");
		factoryScroll.setViewportView(factoryTable);
		factoryTable.setModel(new OreTableModel(factories));
		factoryTable.setCellSelectionEnabled(true);
		factoryTable.getTableHeader().setBackground(new Color(176, 224, 230));
		factoryTable.setRowHeight(factoryTable.getRowHeight() + 5);
		ListModel<String> factoryListModel = new FactoryRowHeader(1);
		JList<String> factoryHeader = new JList<String>(factoryListModel);
		factoryHeader.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		factoryHeader.setOpaque(true);
		factoryHeader.setBackground(new Color(176, 224, 230));
		factoryHeader
				.setToolTipText("<html><font size=\"4\">\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0432 \u044F\u0447\u0435\u0439\u043A\u0438 \u043A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0442\u043E\u0432\u0430\u0440\u0430, \u043D\u0435\u043E\u0431\u0445\u043E\u0434\u0438\u043C\u043E\u0433\u043E \u043A\u0430\u0436\u0434\u043E\u043C\u0443 \u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u044E</font></html>");
		factoryHeader.setFocusable(false);
		factoryHeader.setFixedCellWidth(150);
		factoryHeader.setFixedCellHeight(factoryTable.getRowHeight()
				+ factoryTable.getRowMargin() - 1);
		factoryHeader.setCellRenderer(new RowHeaderRenderer(factoryTable));
		factoryScroll.setRowHeaderView(factoryHeader);
		factoryScroll.getRowHeader().getView().setBackground(new Color(176, 224, 230));
		factoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		label_2 = new JLabel(
				"\u041C\u0430\u0442\u0440\u0438\u0446\u0430 \u0441\u0442\u043E\u0438\u043C\u043E\u0441\u0442\u0438");
		label_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label_2.setFocusable(false);
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 0);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 4;
		add(label_2, gbc_label_2);

		costScroll = new JScrollPane();
		costScroll.setForeground(new Color(176, 224, 230));
		costScroll.setOpaque(false);
		costScroll.setBackground(new Color(176, 224, 230));
		costScroll.setToolTipText("");
		costScroll.setFocusable(false);
		costScroll.getViewport().setOpaque(false);
		GridBagConstraints gbc_costScroll = new GridBagConstraints();
		gbc_costScroll.insets = new Insets(0, 0, 5, 0);
		gbc_costScroll.weighty = 0.5;
		gbc_costScroll.weightx = 0.5;
		gbc_costScroll.fill = GridBagConstraints.BOTH;
		gbc_costScroll.gridx = 0;
		gbc_costScroll.gridy = 5;
		add(costScroll, gbc_costScroll);

		costTable = new JTable();
		costTable.setOpaque(false);
		costTable.setBackground(new Color(176, 224, 230));
		costTable.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		costTable.getTableHeader().setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		costTable.getTableHeader().setBackground(new Color(176, 224, 230));
		costTable
				.setToolTipText("<html><font size=\"4\">\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0432 \u043C\u0430\u0442\u0440\u0438\u0446\u0443 \u0441\u0442\u043E\u0438\u043C\u043E\u0441\u0442\u044C \u0434\u043E\u0441\u0442\u0430\u0432\u043A\u0438 \u0435\u0434\u0438\u043D\u0438\u0446\u044B \u0442\u043E\u0432\u0430\u0440\u0430 <br> \r\n\u043E\u0442 \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u044F(\u0443\u043A\u0430\u0437\u0430\u043D \u0432 \u0433\u043E\u0440\u0438\u0437\u043E\u043D\u0442\u0430\u043B\u044C\u043D\u043E\u043C \u0437\u0430\u0433\u043E\u043B\u043E\u0432\u043A\u0435) <br>\r\n\u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u044E(\u0443\u043A\u0430\u0437\u0430\u043D \u0432 \u0432\u0435\u0440\u0442\u0438\u043A\u0430\u043B\u044C\u043D\u043E\u043C \u0437\u0430\u0433\u043E\u043B\u043E\u0432\u043A\u0435)</font></html>");
		costTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		costTable.setRowHeight(costTable.getRowHeight() + 5);
		costScroll.setViewportView(costTable);
		MatrixTableModel costModel = new MatrixTableModel(mines, factories);
		costModel.setEditable(true);
		costTable.setModel(costModel);
		ListModel<String> costListModel = new CostRowHeader(mines);
		JList<String> costHeader = new JList<String>(costListModel);
		costHeader.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		costHeader.setOpaque(true);
		costHeader.setBackground(new Color(176, 224, 230));
		costHeader
				.setToolTipText("<html><font size=\"4\">\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0432 \u043C\u0430\u0442\u0440\u0438\u0446\u0443 \u0441\u0442\u043E\u0438\u043C\u043E\u0441\u0442\u044C \u0434\u043E\u0441\u0442\u0430\u0432\u043A\u0438 \u0435\u0434\u0438\u043D\u0438\u0446\u044B \u0442\u043E\u0432\u0430\u0440\u0430 <br> \r\n\u043E\u0442 \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u044F(\u0443\u043A\u0430\u0437\u0430\u043D \u0432 \u0433\u043E\u0440\u0438\u0437\u043E\u043D\u0442\u0430\u043B\u044C\u043D\u043E\u043C \u0437\u0430\u0433\u043E\u043B\u043E\u0432\u043A\u0435) <br>\r\n\u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u044E(\u0443\u043A\u0430\u0437\u0430\u043D \u0432 \u0432\u0435\u0440\u0442\u0438\u043A\u0430\u043B\u044C\u043D\u043E\u043C \u0437\u0430\u0433\u043E\u043B\u043E\u0432\u043A\u0435)</font></html>");
		costHeader.setFocusable(false);
		costHeader.setFixedCellWidth(150);
		costHeader.setFixedCellHeight(costTable.getRowHeight()
				+ costTable.getRowMargin() - 1);
		costHeader.setCellRenderer(new RowHeaderRenderer(costTable));
		costScroll.setRowHeaderView(costHeader);
		costScroll.getRowHeader().getView().setBackground(new Color(176, 224, 230));

		gridBagLayout.columnWidths = new int[] { 605 };
		gridBagLayout.rowHeights = new int[] { 30, 65, 30, 65, 20, 150, 40 };
		gridBagLayout.columnWeights = new double[] { 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0 };
		
		panel = new JPanel();
		panel.setOpaque(false);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 6;
		add(panel, gbc_panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		
		backButton = new JButton("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C \u043D\u0430\u0441\u0442\u0440\u043E\u0439\u043A\u0438");
		backButton.setToolTipText("<html><font size=\"4\">\u041F\u043E\u0434\u0442\u0432\u0435\u0440\u0434\u0438\u0442\u044C \u0432\u0432\u043E\u0434 \u0434\u0430\u043D\u043D\u044B\u0445 \u0438 \u0440\u0435\u0448\u0438\u0442\u044C \u0437\u0430\u0434\u0430\u0447\u0443</font></html>");
		backButton.setForeground(new Color(250, 250, 210));
		backButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		backButton.setBackground(new Color(102, 205, 170));
		panel.add(backButton);

		// "Посчитать"
		okButton = new JButton(
				"\u041F\u043E\u0441\u0447\u0438\u0442\u0430\u0442\u044C");
		panel.add(okButton);
		okButton.setForeground(new Color(250, 250, 210));
		okButton.setBackground(new Color(102, 205, 170));
		okButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		okButton.setToolTipText("<html><font size=\"4\">\u041F\u043E\u0434\u0442\u0432\u0435\u0440\u0434\u0438\u0442\u044C \u0432\u0432\u043E\u0434 \u0434\u0430\u043D\u043D\u044B\u0445 \u0438 \u0440\u0435\u0448\u0438\u0442\u044C \u0437\u0430\u0434\u0430\u0447\u0443</font></html>");
		
		horizontalGlue_1 = Box.createHorizontalGlue();
		panel.add(horizontalGlue_1);

		for (int i = 0; i < mineTable.getColumnCount(); ++i) {
			TableColumn column = mineTable.getColumnModel().getColumn(i);
			column.setMinWidth(30);
			column.setPreferredWidth(50);
			column.setMaxWidth(50);
		}

		for (int i = 0; i < factoryTable.getColumnCount(); ++i) {
			TableColumn column = factoryTable.getColumnModel().getColumn(i);
			column.setMinWidth(30);
			column.setPreferredWidth(50);
			column.setMaxWidth(50);
		}

		for (int i = 0; i < costTable.getColumnCount(); ++i) {
			TableColumn column = costTable.getColumnModel().getColumn(i);
			column.setMinWidth(100);
			column.setPreferredWidth(110);
			column.setMaxWidth(120);
		}
	}

	public int[] getMineArray() {
		int[] result = new int[mineTable.getColumnCount()];
		for (int i = 0; i < mineTable.getColumnCount(); ++i) {
			try {
				result[i] = (Integer) mineTable.getValueAt(0, i);
			} catch (Exception e) {
				result[i] = 0;
			}
		}

		return result;
	}

	public int[] getFactoryArray() {
		int[] result = new int[factoryTable.getColumnCount()];
		for (int i = 0; i < factoryTable.getColumnCount(); ++i) {
			try {
				result[i] = (Integer) factoryTable.getValueAt(0, i);
			} catch (Exception e) {
				result[i] = 0;
			}
		}

		return result;
	}

	public int[][] getCostArray() {
		int[][] result = new int[costTable.getRowCount()][costTable
				.getColumnCount()];

		for (int i = 0; i < costTable.getRowCount(); ++i) {
			for (int j = 0; j < costTable.getColumnCount(); ++j) {
				Object value = costTable.getValueAt(i, j);
				if(value == null)
					result[i][j] = 0;
				else
					result[i][j] = (Integer) costTable.getValueAt(i, j);
			}
		}

		return result;
	}

	/**
	 * Проверяет данные в полях ввода и возвращает код ошибки. 0 - данные
	 * валидны, в остальных случаях - код соответствующей ошибки из Const.
	 * 
	 * @return код ошибки
	 */
	public int checkData() {
		int mineSum = 0;
		int factorySum = 0;

		int[] mineArray = getMineArray();
		int[] factoryArray = getFactoryArray();
		int[][] costArray = getCostArray();

		for (int i = 0; i < mineArray.length; ++i) {
			if (mineArray[i] < 0)
				return Const.INCORRECT_DATA;
			else if (mineArray[i] == 0)
				return Const.NOT_ENOGHT_DATA;
			mineSum += mineArray[i];
		}

		for (int i = 0; i < factoryArray.length; ++i) {
			if (factoryArray[i] < 0)
				return Const.INCORRECT_DATA;
			else if (factoryArray[i] == 0)
				return Const.NOT_ENOGHT_DATA;
			factorySum += factoryArray[i];
		}

		if (mineSum != factorySum)
			return Const.UNEQUAL_ORE_AMOUNT;

		for (int i = 0; i < costArray.length; ++i) {
			for (int j = 0; j < costArray[0].length; ++j) {
				if (costArray[i][j] == 0)
					return Const.NOT_ENOGHT_DATA;
				else if (costArray[i][j] < 0)
					return Const.INCORRECT_DATA;
			}
		}

		return Const.NO_ERRORS;
	}

	public JButton getOkButton() {
		return this.okButton;
	}
	
	public JButton getBackButton() {
		return this.backButton;
	}

	public JTable getFactoryTable() {
		return this.factoryTable;
	}

	public JTable getMineTable() {
		return this.mineTable;
	}

	public JTable getCostTable() {
		return this.costTable;
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
	private JButton backButton;
	private JPanel panel;
	private Component horizontalGlue;
	private Component horizontalGlue_1;
}
