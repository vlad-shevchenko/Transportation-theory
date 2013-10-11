package window.panels.tables;

import javax.swing.AbstractListModel;

/**
 * Абстрактный класс для реализации горизонтальных заголовков таблиц.
 */

public abstract class AbstractRowHeader extends AbstractListModel<String> {
	private static final long serialVersionUID = 1L;

	public AbstractRowHeader(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	private int size;
}
