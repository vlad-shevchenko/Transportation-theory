package window.panels.tables;


public class FactoryRowHeader extends AbstractRowHeader {

	public FactoryRowHeader(int size) {
		super(size);
	}

	public Object getElementAt(int in) {
		return new String("Потребность в товаре");
	}
}
