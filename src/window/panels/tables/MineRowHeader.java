package window.panels.tables;

/**
 * List model for horizontal header for tables. Exactly repeats
 * AbstractRowHeader, but set caption as <Количество товара>
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
