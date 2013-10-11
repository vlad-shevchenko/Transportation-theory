package window.panels.tables;

/**
 * ListModel для горизонтальных заголовков таблиц. В точности повторяет
 * AbstractRowHeader, кроме имён заголовков: <Производитель i>, где i -
 * порядковый номер заголовка.
 */

public class CostRowHeader extends AbstractRowHeader {
	private static final long serialVersionUID = 1L;

	public CostRowHeader(int size) {
		super(size);
	}

	public String getElementAt(int in) {
		return new String("Производитель " + (in + 1));
	}
}
