package window.panels.tables;

/**
 * List model for horizontal header for tables. Exactly repeats 
 * AbstractRowHeader, but set caption as <Количество товара>
 */
public class MineRowHeader extends AbstractRowHeader {

	public MineRowHeader(int size) {
		super(size);
	}

	public Object getElementAt(int in) {
		return new String("Количество товара");
	}
}
