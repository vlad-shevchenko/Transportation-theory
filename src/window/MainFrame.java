package window;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import solver.Data;
import solver.Solver;
import start.Const;
import window.panels.SettingsPanel;
import window.panels.SolutionPanel;
import window.panels.TablesPanel;

import java.awt.Toolkit;

/**
 * Фрейм главного окна. Сначала отображает объект SettingsPanel. После
 * подтверждения ввода начальных параметров(при их корректности) заменяет его на
 * TablesPanel для ввода остальных данных. В конце выводит объект SolutionPanel
 * с матрицей перевозок.
 * 
 * Также обрабатывает нажатия на кнопки и перехватывает событие закрытия
 * окна(для вывода диалогового окна с подтверждением).
 * 
 * Используется паттерн Синглтон.
 */

public class MainFrame extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;

	private Box rootPanel;
	private SettingsPanel settingsPanel;
	private TablesPanel tablesPanel;
	private SolutionPanel solutionPanel;
	private Log log = new Log();
	private boolean saved;
	private static MainFrame instance;

	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	private MainFrame() {
		Image frameIcon = Toolkit.getDefaultToolkit().getImage("frameIcon.png");
		setIconImage(frameIcon);
		setTitle("Транспортная задача");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		saved = true;
		setLocation(150, 150);

		rootPanel = Box.createVerticalBox();
		createSettingsPanel();
		add(rootPanel);
		setVisible(true);

		log.addItem(GregorianCalendar.getInstance().getTimeInMillis(), "Старт");
	}

	/**
	 * Повторная отрисовка ранее созданного объекта SettingsPanel (по
	 * событию TablesPanel -> backButton -> click)
	 */
	public void rebuildSettingsPanel() {
		rootPanel.removeAll();
		rootPanel.add(settingsPanel);

		setMinimumSize(Const.START_FRAME_SIZE);
		setMaximumSize(Const.DEFAULT_FRAME_SIZE);
		setSize(Const.START_FRAME_SIZE);
	}

	/**
	 * Генерация объекта SettingsPanel
	 */
	public void createSettingsPanel() {
		rootPanel.removeAll();

		settingsPanel = new SettingsPanel();
		rootPanel.add(settingsPanel);

		setMinimumSize(Const.START_FRAME_SIZE);
		setMaximumSize(Const.DEFAULT_FRAME_SIZE);
		setSize(Const.START_FRAME_SIZE);
	}

	/**
	 * Повторная отрисовка ранее созданного объекта TablesPanel (по
	 * событию SolutionPanel -> backButton -> click)
	 */
	public void rebuildTablesPanel() {
		rootPanel.removeAll();
		rootPanel.add(tablesPanel);

		setMinimumSize(Const.MIN_FRAME_SIZE);
		setMaximumSize(Const.MAX_FRAME_SIZE);
		setSize(Const.DEFAULT_FRAME_SIZE);
	}

	/**
	 * Генерация объекта TablesPanel
	 */
	public void createTablesPanel() {
		int error = settingsPanel.checkData();
		if (error != Const.NO_ERRORS) {
			JOptionPane.showMessageDialog(this, errorMessages[error], "Ошибка",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		rootPanel.removeAll();

		tablesPanel = new TablesPanel(settingsPanel.getMineNumber(),
				settingsPanel.getFactoriesNumber());
		rootPanel.add(tablesPanel);

		setMinimumSize(Const.MIN_FRAME_SIZE);
		setMaximumSize(Const.MAX_FRAME_SIZE);
		setSize(Const.DEFAULT_FRAME_SIZE);

		log.addItem(
				GregorianCalendar.getInstance().getTimeInMillis(),
				"Заданы начальные параметры: " + settingsPanel.getMineNumber()
						+ " производителей и "
						+ settingsPanel.getFactoriesNumber() + " потребителей.");
	}

	/**
	 * Генерация объекта SolutionPanel
	 */
	public void createSolutionPanel() {
		int error = tablesPanel.checkData();
		if (error != Const.NO_ERRORS) {
			JOptionPane.showMessageDialog(this, errorMessages[error], "Ошибка",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		saved = false;

		rootPanel.removeAll();

		log.addItem(GregorianCalendar.getInstance().getTimeInMillis(),
				"Заданы основные параметры задачи: ");
		int[][] mineArray = { tablesPanel.getMineArray() };
		int[][] factoryArray = { tablesPanel.getFactoryArray() };
		log.addTable(mineArray, GregorianCalendar.getInstance()
				.getTimeInMillis(),
				"Количество товаров у каждого производителя:");
		log.addTable(factoryArray, GregorianCalendar.getInstance()
				.getTimeInMillis(),
				"Количество товара, необходимого каждому потребителю:");
		this.log.addTable(tablesPanel.getCostArray(), GregorianCalendar
				.getInstance().getTimeInMillis(), "Матрица стоимости");

		Data data = new Data(tablesPanel.getMineArray(),
				tablesPanel.getFactoryArray(), tablesPanel.getCostArray());
		Solver solver = new Solver(data);

		log.addItem(GregorianCalendar.getInstance().getTimeInMillis(),
				"Рассчёт оптимальной матрицы перевозок...");
		Integer[][] solution = solver.solve();
		log.addTable(solution, GregorianCalendar.getInstance()
				.getTimeInMillis(), "Матрица перевозок: ");

		solutionPanel = new SolutionPanel(settingsPanel.getMineNumber(),
				settingsPanel.getFactoriesNumber());
		solutionPanel.setTableData(solution);
		rootPanel.add(solutionPanel);

		setMinimumSize(new Dimension(Const.MIN_FRAME_SIZE.width,
				Const.MIN_FRAME_SIZE.height - 100));
		setSize(Const.DEFAULT_FRAME_SIZE.width - 5,
				Const.DEFAULT_FRAME_SIZE.height - 5);
		repaint();
	}

	/**
	 * Вывод диалога для выбора файла и сохранение лог работы программы в этот
	 * файл.
	 */
	public void saveLog() {
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
				FileWriter out = new FileWriter(file);
				String line;
				while ((line = this.log.nextLine()) != null) {
					out.write(line);
				}

				saved = true;
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

	/**
	 * Если лог не был сохранён, выводит подтверждающее диалоговое окно. Иначе -
	 * завершает программу.
	 */
	public void exit() {
		if (saved)
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

	/**
	 * Внутренний класс, отвечающий за ведение журнала работы программы.
	 */
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

		public void addTable(Integer[][] data, long time, String message) {
			int[][] newData = new int[data.length][data[0].length];

			for (int i = 0; i < data.length; ++i) {
				for (int j = 0; j < data[0].length; ++j) {
					if (data[i][j] == null)
						newData[i][j] = 0;
					else
						newData[i][j] = data[i][j];
				}
			}

			addTable(newData, time, message);
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

	private final String[] errorMessages = {
			"Ok",
			"Введены не все данные. Проверьте поля для ввода",
			"Неверные данные. Проверьте поля для ввода",
			"Сумма товаров у производителей и сумма товаров, необходимых потребителям должны быть равны",
			"Количество производителей и потребителей должно быть не меньше двух" 
			};

/*
 * Неиспользуемые методы. Необходимо для корректного субклассирования.
 */
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
}
