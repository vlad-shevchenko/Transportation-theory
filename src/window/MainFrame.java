package window;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.BoxLayout;

import start.Const;
import window.panels.SettingsPanel;
import window.panels.SolutionPanel;
import window.panels.TablesPanel;


public class MainFrame extends JFrame implements ActionListener {
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Транспортная задача");
		
		rootPanel = Box.createVerticalBox();
		
		settingsPanel = new SettingsPanel();
		settingsPanel.setOkAction(this);
		
		rootPanel.add(settingsPanel);
		
		add(rootPanel);
		this.setBounds(100, 100, 0, 0);
		this.setSize(Const.START_FRAME_SIZE);
		oldSize = Const.START_FRAME_SIZE;
		this.setPreferredSize(Const.DEFAULT_FRAME_SIZE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ev) {
		rootPanel.removeAll();

		tablesPanel = new TablesPanel(settingsPanel.getMineNumber(), settingsPanel.getFactoriNumber());
		rootPanel.add(tablesPanel);
		
		this.setSize(Const.DEFAULT_FRAME_SIZE);
		oldSize = Const.DEFAULT_FRAME_SIZE;
	}
	
	private Box rootPanel;
	private SettingsPanel settingsPanel;
	private TablesPanel tablesPanel;
	private SolutionPanel solutionPanel;
	
	private Dimension oldSize;
}
