package window.panels.tables;


/**
 * List model for horizontal header for tables. Exactly repeats 
 * AbstractRowHeader, but set caption as 
 * <Производитель i>, where i is row serial number.
 */
public class CostRowHeader extends AbstractRowHeader {
	public CostRowHeader(int size) {
		super(size);
	}

	public Object getElementAt(int in) {
		return new String("Производитель " + (in + 1));
	}
}
