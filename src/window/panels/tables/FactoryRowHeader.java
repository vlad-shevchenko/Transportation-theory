package window.panels.tables;

/**
 * List model for horizontal header for tables. Exactly repeats
 * AbstractRowHeader, but set caption as <Потребность в товаре>
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
