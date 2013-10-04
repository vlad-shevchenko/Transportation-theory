package window.panels;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Component;

import javax.swing.Box;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;

import start.Const;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import javax.swing.SwingConstants;


public class SettingsPanel extends JPanel {
	public SettingsPanel() {
		
		JPanel mineInputPanel = new JPanel();
		JPanel factoryInputPanel = new JPanel();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(mineInputPanel);
		mineInputPanel.setLayout(new BorderLayout(0, 0));
		
		// "Количество производителей"
		mineLabel = new JLabel("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u0435\u0439:");
		mineInputPanel.add(mineLabel);
		
		mineTextField = new JTextField();
		mineTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mineTextField.setColumns(5);
		mineInputPanel.add(mineTextField, BorderLayout.EAST);
		
		add(factoryInputPanel);
		factoryInputPanel.setLayout(new BorderLayout(0, 0));

		// "Количество потребителей"
		factoryLabel = new JLabel("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u0435\u0439:");
		factoryInputPanel.add(factoryLabel, BorderLayout.WEST);
		
		factoryTextField = new JTextField();
		factoryTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		factoryInputPanel.add(factoryTextField, BorderLayout.EAST);
		factoryTextField.setColumns(5);
		
		JPanel okPanel = new JPanel();
		add(okPanel);
		
		okButton = new JButton("Ok");
		okPanel.add(okButton);
	}

	public void setOkAction(ActionListener listener) {
		okButton.addActionListener(listener);
	}
	
	public int getMineNumber() {
		try {
			int mines = Integer.parseInt(mineTextField.getText());
			return mines;
		} catch(Exception e) {
			return 0;
		}
	}
	
	public int getFactoriesNumber() {
		try {
			int factories = Integer.parseInt(factoryTextField.getText());
			return factories;
		} catch(Exception e) {
			return 0;
		}
	}
	
	/**
	 * Check data in tables and returns error code. 
	 * All codes - "public static int" fields of Const.java
	 * @return error code
	 */
	public int checkData() {
		if(mineTextField.getText().equals("") || factoryTextField.getText().equals(""))
			return Const.NOT_ENOGHT_DATA;
		int mines;
		int factories;
		try {
			mines = Integer.parseInt(mineTextField.getText());
			factories = Integer.parseInt(factoryTextField.getText());
		} catch (NumberFormatException e) {
			return Const.INCORRECT_DATA;
		}
		if(mines < 0 || factories < 0)
			return Const.INCORRECT_DATA;
		else if(mines == 0 || factories == 0)
			return Const.NOT_ENOGHT_DATA;
		else if(mines == 1 || factories == 1)
			return Const.INCORRECT_SETTINGS;
		else return Const.NO_ERRORS;
	}
	
	private JTextField factoryTextField;
	private JLabel mineLabel;
	private JLabel factoryLabel;
	private JButton okButton;
	private JTextField mineTextField;
}
