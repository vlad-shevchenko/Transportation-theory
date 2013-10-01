package window;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import solver.Data;
import solver.Solver;
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
		this.setPreferredSize(Const.DEFAULT_FRAME_SIZE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ev) {
		this.setMinimumSize(Const.MIN_FRAME_SIZE);
		if( ((JButton) ev.getSource()).getText().equals("Ok") ) {
			rootPanel.removeAll();
	
			tablesPanel = new TablesPanel(settingsPanel.getMineNumber(), settingsPanel.getFactoriesNumber());
			tablesPanel.setOkAction(this);
			rootPanel.add(tablesPanel);
			
			this.setSize(Const.DEFAULT_FRAME_SIZE);
		} else if ( ((JButton) ev.getSource()).getText().equals("Посчитать") ) {
			rootPanel.removeAll();
			Data data = new Data(
					tablesPanel.getMineArray(), 
					tablesPanel.getFactoryArray(), 
					tablesPanel.getCostArray());
			Solver solver = new Solver(data);
			
			solutionPanel = new SolutionPanel(settingsPanel.getMineNumber(), settingsPanel.getFactoriesNumber());
			solutionPanel.setTableData(solver.solve());
			solutionPanel.setSaveAction(this);
			solutionPanel.setExitAction(this);
			this.add(solutionPanel);
					
			this.setSize(Const.DEFAULT_FRAME_SIZE);
			this.revalidate();
			this.repaint();
		} else if ( ((JButton) ev.getSource()).getText().equals("Сохранить") ) {
			
		} else if ( ((JButton) ev.getSource()).getText().equals("Выход") ) {
			System.exit(0);
		}
	}
	
	private Box rootPanel;
	private SettingsPanel settingsPanel;
	private TablesPanel tablesPanel;
	private SolutionPanel solutionPanel;
}
