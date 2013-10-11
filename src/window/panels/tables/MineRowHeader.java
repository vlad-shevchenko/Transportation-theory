package window.panels.tables;

/**
 * ListModel для горизонтальных заголовков таблиц. В точности повторяет
 * AbstractRowHeader, кроме имён заголовка: <Количество товара>
 */

public class MineRowHeader extends AbstractRowHeader {
	private static final long serialVersionUID = 1L;

	public MineRowHeader(int size) {
		super(size);
	}

	public String getElementAt(int in) {
		return new String("Количество товара");
	}
}
