package window;

import javax.swing.JFrame;
import java.awt.Toolkit;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import window.panels.HelpPanel;

/**
 * Фрейм, содержащий справку по транспортной задаче
 * 
 */
public class HelpFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	public HelpFrame() {
		getContentPane().setBackground(new Color(176, 224, 230));
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Yandex\\Programing\\Java\\Projects\\Transport\\help.png"));
		setTitle("Помощь");
		
		helpPanel = new HelpPanel();
		getContentPane().add(helpPanel, BorderLayout.CENTER);

		helpPanel.getOkButton().addActionListener(this);
		helpPanel.getRabbitButton().addActionListener(this);
		
		this.setLocation(100, 100);
		this.setSize((int) (helpPanel.getSize().width * 1.05), (int) (helpPanel.getSize().height * 1.05));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ev) {
		if(((JButton) ev.getSource()).getText().equals("Ясно")) {
			this.dispose();
		} else { 
			if(helpPanel.getRabbitStatus()) {
				helpPanel.remove(helpPanel.getRabbitLabel());
				helpPanel.add(helpPanel.getHelpLabel(), BorderLayout.CENTER);
				helpPanel.setRabbitStatus(false);
				
				// Замена revalidate() для Java 1.5
				this.setSize(getSize().width + 1, getSize().height);
			} else {
				helpPanel.remove(helpPanel.getHelpLabel());
				helpPanel.add(helpPanel.getRabbitLabel(), BorderLayout.CENTER);
				helpPanel.setRabbitStatus(true);
				
				// Замена revalidate() для Java 1.5
				this.setSize(getSize().width - 1, getSize().height);
			}
			this.repaint();
		}
	}
	
	private HelpPanel helpPanel;
}
