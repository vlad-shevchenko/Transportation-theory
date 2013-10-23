package window.panels.tables;

import javax.swing.AbstractListModel;

/**
 * Абстрактный класс для реализации горизонтальных заголовков таблиц.
 * 
 * Часть паттерна MVC - модель.
 */

public abstract class AbstractRowHeader extends AbstractListModel<String> {
	private static final long serialVersionUID = 1L;

	private int size;

	public AbstractRowHeader(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}
}
