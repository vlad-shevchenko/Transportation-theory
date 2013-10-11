package window.panels;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.JButton;

import start.Const;

import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Dimension;

/**
 * Панель с двумя JTextField'ами для ввода количества производителей и
 * потребителей. Панель вызывается при запуске программы. Метод checkData()
 * проверяет корректность введённых данных.
 */

public class SettingsPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public SettingsPanel() {

		JPanel mineInputPanel = new JPanel();
		JPanel factoryInputPanel = new JPanel();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(mineInputPanel);
		mineInputPanel.setLayout(new BorderLayout(0, 0));

		// "Количество производителей"
		mineLabel = new JLabel(
				" \u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u0435\u0439:");
		mineLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		mineInputPanel.add(mineLabel);

		mineTextField = new JTextField();
		mineTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		mineTextField
				.setToolTipText("<html><font size=\"4\">\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u0435\u0439</font></html");
		mineTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		mineTextField.setColumns(5);
		mineInputPanel.add(mineTextField, BorderLayout.EAST);

		add(factoryInputPanel);
		factoryInputPanel.setLayout(new BorderLayout(0, 0));

		// "Количество потребителей"
		factoryLabel = new JLabel(
				" \u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u0435\u0439:");
		factoryLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		factoryInputPanel.add(factoryLabel, BorderLayout.WEST);

		factoryTextField = new JTextField();
		factoryTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		factoryTextField
				.setToolTipText("<html><font size=\"4\">\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u0435\u0439</font></html>");
		factoryTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		factoryInputPanel.add(factoryTextField, BorderLayout.EAST);
		factoryTextField.setColumns(5);

		Component verticalGlue_1 = Box.createVerticalGlue();
		verticalGlue_1.setSize(new Dimension(0, 50));
		verticalGlue_1.setPreferredSize(new Dimension(0, 150));
		verticalGlue_1.setMinimumSize(new Dimension(0, 50));
		add(verticalGlue_1);

		JPanel okPanel = new JPanel();
		add(okPanel);
		okPanel.setLayout(new BoxLayout(okPanel, BoxLayout.X_AXIS));

		ImageIcon buttonIcon = new ImageIcon("help.png");

		Component horizontalStrut = Box.createHorizontalStrut(50);
		okPanel.add(horizontalStrut);

		Component horizontalGlue = Box.createHorizontalGlue();
		okPanel.add(horizontalGlue);

		okButton = new JButton("Ok");
		okButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		okButton.setToolTipText("<html><font size=\"4\">\u041F\u043E\u0434\u0442\u0432\u0435\u0440\u0434\u0438\u0442\u044C \u0432\u0432\u043E\u0434</font></html>");
		okPanel.add(okButton);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		okPanel.add(horizontalGlue_1);
		helpButton = new JButton("");
		helpButton
				.setToolTipText("<html><font size=\"4\">\u041E\u0442\u043A\u0440\u044B\u0442\u044C \u0441\u043F\u0440\u0430\u0432\u043A\u0443 \u043F\u043E \u0442\u0440\u0430\u043D\u0441\u043F\u043E\u0440\u0442\u043D\u043E\u0439 \u0437\u0430\u0434\u0430\u0447\u0435</font></html>");
		helpButton.setIcon(buttonIcon);
		helpButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		okPanel.add(helpButton);
	}

	/**
	 * Проверяет данные в полях ввода и возвращает код ошибки. 0 - данные
	 * валидны, в остальных случаях - код соответствующей ошибки из Const.
	 * 
	 * @return код ошибки
	 */
	public int checkData() {
		if (mineTextField.getText().equals("")
				|| factoryTextField.getText().equals(""))
			return Const.NOT_ENOGHT_DATA;
		int mines;
		int factories;
		try {
			mines = Integer.parseInt(mineTextField.getText());
			factories = Integer.parseInt(factoryTextField.getText());
		} catch (NumberFormatException e) {
			return Const.INCORRECT_DATA;
		}
		if (mines < 0 || factories < 0)
			return Const.INCORRECT_DATA;
		else if (mines == 0 || factories == 0)
			return Const.NOT_ENOGHT_DATA;
		else if (mines == 1 || factories == 1)
			return Const.INCORRECT_SETTINGS;
		else
			return Const.NO_ERRORS;
	}

	public int getMineNumber() {
		try {
			int mines = Integer.parseInt(mineTextField.getText());
			return mines;
		} catch (Exception e) {
			return 0;
		}
	}

	public int getFactoriesNumber() {
		try {
			int factories = Integer.parseInt(factoryTextField.getText());
			return factories;
		} catch (Exception e) {
			return 0;
		}
	}

	public JButton getOkButton() {
		return this.okButton;
	}

	public JButton getHelpButton() {
		return helpButton;
	}

	private JTextField factoryTextField;
	private JLabel mineLabel;
	private JLabel factoryLabel;
	private JButton okButton;
	private JTextField mineTextField;
	private JButton helpButton;
}
