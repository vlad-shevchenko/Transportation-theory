package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import solver.Data;
import solver.Solver;
import start.Const;
import window.panels.SettingsPanel;
import window.panels.SolutionPanel;
import window.panels.TablesPanel;

/**
 * Main window frame. At first displays SettingsPanel object. After submitting
 * base settings Displays TablesPanel object with tables to input problem data.
 * And finally displays SolutionPanel object to show shipping matrix and save
 * program log.
 * 
 * Also frame handles the click events of a buttons and hooks frame close
 * event(to show confirm dialog window).
 */

public class MainFrame extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		setTitle("Транспортная задача");
		this.saved = true;

		rootPanel = Box.createVerticalBox();

		settingsPanel = new SettingsPanel();
		settingsPanel.getOkButton().addActionListener(this);
		rootPanel.add(settingsPanel);

		add(rootPanel);
		this.setBounds(100, 100, 0, 0);
		this.setSize(Const.START_FRAME_SIZE);
		this.setPreferredSize(Const.DEFAULT_FRAME_SIZE);
		this.setVisible(true);

		this.log.addItem(GregorianCalendar.getInstance().getTimeInMillis(),
				"Start");
	}

	public void actionPerformed(ActionEvent ev) {
		if (((JButton) ev.getSource()).getText().equals("Ok")) {
			// SettingsPanel -> okButton -> Click
			// If there are no errors in data, replace settingsPanel with
			// tablesPanel.
			int error = settingsPanel.checkData();
			if (error != Const.NO_ERRORS) {
				JOptionPane.showMessageDialog(this, errorMessages[error],
						"Ошибка", JOptionPane.WARNING_MESSAGE);
				return;
			}
			rootPanel.removeAll();

			tablesPanel = new TablesPanel(settingsPanel.getMineNumber(),
					settingsPanel.getFactoriesNumber());
			tablesPanel.getOkButton().addActionListener(this);
			rootPanel.add(tablesPanel);

			this.setMinimumSize(Const.MIN_FRAME_SIZE);
			this.setSize(Const.DEFAULT_FRAME_SIZE);

			this.log.addItem(
					GregorianCalendar.getInstance().getTimeInMillis(),
					"Set the initial parameters: "
							+ settingsPanel.getMineNumber() + " mines and "
							+ settingsPanel.getFactoriesNumber()
							+ " factories.");
		} else if (((JButton) ev.getSource()).getText().equals("Посчитать")) {
			// TablesPanel -> okButton -> Click
			//
			// If there are no errors, replace tablesPanel with
			// solutionPanel
			int error = tablesPanel.checkData();
			if (error != Const.NO_ERRORS) {
				JOptionPane.showMessageDialog(this, errorMessages[error],
						"Ошибка", JOptionPane.WARNING_MESSAGE);
				return;
			}
			this.saved = false;
			rootPanel.removeAll();

			this.log.addItem(GregorianCalendar.getInstance().getTimeInMillis(),
					"Specifying the parameters of the problem: ");
			int[][] mineArray = { tablesPanel.getMineArray() };
			int[][] factoryArray = { tablesPanel.getFactoryArray() };
			this.log.addTable(mineArray, GregorianCalendar.getInstance()
					.getTimeInMillis(), "The amount of goods from producers");
			this.log.addTable(factoryArray, GregorianCalendar.getInstance()
					.getTimeInMillis(), "Number of product required consumers");
			this.log.addTable(tablesPanel.getCostArray(), GregorianCalendar
					.getInstance().getTimeInMillis(), "Cost matrix");

			Data data = new Data(tablesPanel.getMineArray(),
					tablesPanel.getFactoryArray(), tablesPanel.getCostArray());
			Solver solver = new Solver(data);

			this.log.addItem(GregorianCalendar.getInstance().getTimeInMillis(),
					"Start the calculation of the optimal transport matrix");
			int[][] solution = solver.solve();
			this.log.addTable(solution, GregorianCalendar.getInstance()
					.getTimeInMillis(),
					"The end the calculation of the optimal transport matrix");

			solutionPanel = new SolutionPanel(settingsPanel.getMineNumber(),
					settingsPanel.getFactoriesNumber());
			solutionPanel.setTableData(solution);
			solutionPanel.setSaveAction(this);
			solutionPanel.setExitAction(this);
			this.add(solutionPanel);

			this.setSize(Const.DEFAULT_FRAME_SIZE.width - 5,
					Const.DEFAULT_FRAME_SIZE.height - 5);
			this.repaint();
		} else if (((JButton) ev.getSource()).getText().equals("Сохранить")) {
			// SolutionPanel -> saveButton -> Click
			saveLog();
		} else if (((JButton) ev.getSource()).getText().equals("Выход")) {
			// SolutionPanel -> exitButton -> Click
			exit();
		}
	}

	private void saveLog() {
		ExtensionFileFilter filter = new ExtensionFileFilter();
		filter.addExtension(".txt");
		filter.setDescription("Text files");

		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File("C:/"));

		switch (chooser.showSaveDialog(this)) {
		case JFileChooser.APPROVE_OPTION: {
			File file = chooser.getSelectedFile();
			try {
				FileOutputStream out = new FileOutputStream(file);
				String line;
				while ((line = this.log.nextLine()) != null) {
					for (int i = 0; i < line.length(); ++i) {
						out.write((byte) line.charAt(i));
					}
				}

				this.saved = true;
				out.close();
			} catch (IOException e) {
				JOptionPane
						.showMessageDialog(
								this,
								"Ошибка при записи файла. Попробуйте выбрать другой файл",
								"Ошибка", JOptionPane.WARNING_MESSAGE);
				return;
			}

			break;
		}
		case JFileChooser.CANCEL_OPTION: {
			return;
		}
		case JFileChooser.ERROR_OPTION: {
			JOptionPane.showMessageDialog(this,
					"Ошибка при выборе файла. Результаты не были сохранены",
					"Ошибка", JOptionPane.WARNING_MESSAGE);
			return;
		}
		}
	}

	private void exit() {
		if (this.saved)
			System.exit(0);
		else {
			int answer = JOptionPane.showConfirmDialog(this,
					"Результат работы программы не сохранен. Сохранить?",
					"Сохранить?", JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION)
				saveLog();
			else if (answer == JOptionPane.NO_OPTION)
				System.exit(0);
		}
	}

	public void windowClosing(WindowEvent arg0) {
		exit();
	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
	}

	private class Log {
		public Log() {
			this.log = new ArrayList<String>();
		}

		public void addItem(long time, String event) {
			this.log.add("[" + (TimeUnit.MILLISECONDS.toHours(time) % 24 + 3)
					+ ":" + TimeUnit.MILLISECONDS.toMinutes(time) % 60 + ":"
					+ TimeUnit.MILLISECONDS.toSeconds(time) % 60 + "] - "
					+ event + "\n");
		}

		public void addTable(int[][] data, long time, String message) {
			this.log.add(message + "\n");

			String curLine = new String();

			for (int i = 0; i < data.length; ++i) {
				curLine = new String("|");
				for (int j = 0; j < data[i].length; ++j) {
					curLine += fillString(" ", 10 - String.valueOf(data[i][j])
							.length())
							+ data[i][j] + "|";
				}
				this.log.add(fillString("-", curLine.length()) + "\n");
				this.log.add(curLine + "\n");
			}

			this.log.add(fillString("-", curLine.length()) + "\n");
		}

		public String nextLine() {
			if (returnedLine == log.size()) {
				returnedLine = 0;
				return null;
			} else
				return log.get(returnedLine++);
		}

		private ArrayList<String> log;
		private int returnedLine;
	}

	private String fillString(String subStr, int times) {
		String result = new String();
		for (int i = 0; i < times; ++i)
			result += subStr;

		return result;
	}

	private Box rootPanel;
	private SettingsPanel settingsPanel;
	private TablesPanel tablesPanel;
	private SolutionPanel solutionPanel;
	private Log log = new Log();
	private boolean saved;
	private String[] errorMessages = {
			"Ok",
			"Введены не все данные. Проверьте поля для ввода",
			"Неверные данные. Проверьте поля для ввода",
			"Сумма товаров у производителей и сумма товаров, необходимых потребителям должны быть равны",
			"Количество производителей и потребителей должно быть не меньше двух" };
}
