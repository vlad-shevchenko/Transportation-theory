package window;

import javax.swing.JFrame;

import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

/**
 * ‘рейм, содержащий справку по транспортной задаче
 * 
 */
public class HelpFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public HelpFrame() {
		getContentPane().setBackground(new Color(176, 224, 230));
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Yandex\\Programing\\Java\\Projects\\Transport\\help.png"));
		
		JLabel label = new JLabel(helpMessage);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		getContentPane().add(label, BorderLayout.CENTER);
		
		JButton btnOk = new JButton("\u042F\u0441\u043D\u043E");
		btnOk.setFocusable(false);
		btnOk.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnOk.setForeground(new Color(102, 205, 170));
		btnOk.setOpaque(false);
		getContentPane().add(btnOk, BorderLayout.SOUTH);

		this.setBounds(100, 100, label.getPreferredSize().width, (int) ((label.getPreferredSize().height + btnOk.getPreferredSize().height) * 1.2));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private String helpMessage = "<html><b>“ранспортна€ задача</b> это математическа€ проблема линейного программировани€. <br>¬ простейшей формулировке выгл€дит так: <br><br><i>≈сть <b>n</b> поставщиков, каждый из которых имеет некоторое количество <br>однородного товара и <b>m</b> потребителей, которым нужно доставить этот товар.<br>Ќеобходимо найти оптимальный способ удовлетворить спрос всех потребителей<br>с минимальными затратами на перевозку.<br></i><br>ќсновные термины:<ul><li><b>ћатрица стоимости</b> - двумерна€ матрица, котора€ определ€ет стоимость<br>перевозки единицы товара от некоторого производител€ к некоторому потребителю.<li><b>ћатрица перевозок</b> - матрица, в €чейках которой указано количество<br>товара, который необходимо перевезти от производител€ к потребителю.</ul>—уммарный объем предложени€ должен быть равен суммарному спросу, иначе задача<br>называетс€ несбалансированной и не решаетс€(без специального преобразовани€).<br></html>";
}
