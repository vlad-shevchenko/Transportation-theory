package window.panels.tables;


public class CostRowHeader extends AbstractRowHeader {

	public CostRowHeader(int size) {
		super(size);
	}

	public Object getElementAt(int in) {
		return new String("Производитель " + (in + 1));
	}
}
