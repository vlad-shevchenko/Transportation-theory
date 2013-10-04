package window.panels.tables;

import javax.swing.AbstractListModel;

/**
 * Abstract class for horizontal headers for all tables.
 *
 */
public abstract class AbstractRowHeader extends AbstractListModel {

	public AbstractRowHeader(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;			
	}
	
	private int size;
}
