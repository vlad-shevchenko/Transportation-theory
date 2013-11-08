package window.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HelpPanel extends JPanel  {
	private static final long serialVersionUID = 1L;

	private ButtonSubpanel buttons;
	private JButton okButton;
	private JButton rabbitButton;
	private JLabel helpLabel;
	private JLabel rabbitLabel;
	private boolean rabbitStatus;

	public HelpPanel() {
		setLayout(new BorderLayout(0, 0));
		helpLabel = new JLabel(helpMessage);
		helpLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		add(helpLabel, BorderLayout.CENTER);
		
		rabbitLabel = new JLabel();
		rabbitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rabbitLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("rabbit.png")));
		
		buttons = new ButtonSubpanel();
		add(buttons, BorderLayout.SOUTH);
		rabbitButton.setToolTipText("<html><font size=\"4\">\u041A\u0430\u0440\u0442\u0438\u043D\u043A\u0430 \u043A\u0440\u043E\u043B\u0438\u043A\u0430 \u0434\u043B\u044F \u043F\u043E\u0434\u043D\u044F\u0442\u0438\u044F \u043D\u0430\u0441\u0442\u0440\u043E\u0435\u043D\u0438\u044F :-)</font></html>");
		okButton.setToolTipText("<html><font size=\"4\">\u0412\u0435\u0440\u043D\u0443\u0442\u044C\u0441\u044F \u043A \u043D\u0430\u0441\u0442\u0440\u043E\u0439\u043A\u0430\u043C</font></html>");

		resizeAll(helpLabel.getPreferredSize().width,
				(int) ((helpLabel.getPreferredSize().height + buttons.getPreferredSize().height) * 1.05));
		setOpaque(false);
	}

	public JButton getOkButton() {
		return this.okButton;
	}

	public JButton getRabbitButton() {
		return this.rabbitButton;
	}

	public JLabel getHelpLabel() {
		return this.helpLabel;
	}

	public JLabel getRabbitLabel() {
		return this.rabbitLabel;
	}

	public boolean getRabbitStatus() {
		return this.rabbitStatus;
	}
	
	public void setRabbitStatus(boolean status) {
		rabbitStatus = status;
	}
	
	private void resizeAll(int width, int height) {
		Dimension size = new Dimension(width, height);
		this.setMinimumSize(size);
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.setSize(size);
	}

	private class ButtonSubpanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public ButtonSubpanel() {
			okButton = new JButton("\u042F\u0441\u043D\u043E");
			okButton.setFocusable(false);
			okButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			okButton.setForeground(new Color(102, 205, 170));
			okButton.setOpaque(false);

			rabbitButton = new JButton("\u041A\u0440\u043E\u043B\u0438\u043A :-)");
			rabbitButton.setFocusable(false);
			rabbitButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			rabbitButton.setForeground(new Color(102, 205, 170));
			rabbitButton.setOpaque(false);

			add(okButton);
//			add(rabbitButton);

			this.setOpaque(false);
		}
	}

	private final String helpMessage = "<html><b>“ранспортна€ задача</b> это математическа€ проблема линейного программировани€. <br>¬ простейшей формулировке выгл€дит так: <br><br><i>≈сть <b>n</b> поставщиков, каждый из которых имеет некоторое количество <br>однородного товара и <b>m</b> потребителей, которым нужно доставить этот товар.<br>Ќеобходимо найти оптимальный способ удовлетворить спрос всех потребителей<br>с минимальными затратами на перевозку.<br></i><br>ќсновные термины:<ul><li><b>ћатрица стоимости</b> - двумерна€ матрица, котора€ определ€ет стоимость<br>перевозки единицы товара от некоторого производител€ к некоторому потребителю.<li><b>ћатрица перевозок</b> - матрица, в €чейках которой указано количество<br>товара, который необходимо перевезти от производител€ к потребителю.</ul>—уммарный объем предложени€ должен быть равен суммарному спросу, иначе задача<br>называетс€ несбалансированной и не решаетс€(без специального преобразовани€).<br></html>";
}
