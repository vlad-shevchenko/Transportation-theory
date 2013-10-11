package window.panels.tables;

/**
 * ListModel для горизонтальных заголовков таблиц. В точности повторяет
 * AbstractRowHeader, кроме имён заголовка: <Потребность в товаре>
 */

public class FactoryRowHeader extends AbstractRowHeader {
	private static final long serialVersionUID = 1L;

	public FactoryRowHeader(int size) {
		super(size);
	}

	public String getElementAt(int in) {
		return new String("Потребность в товаре");
	}
}
