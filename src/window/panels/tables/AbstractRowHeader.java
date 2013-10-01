package window.panels.tables;


import javax.swing.AbstractListModel;

public abstract class AbstractRowHeader extends AbstractListModel {

	public AbstractRowHeader(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;			
	}
	
	private int size;
}
