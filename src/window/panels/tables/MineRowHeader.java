package window.panels.tables;


public class MineRowHeader extends AbstractRowHeader {

	public MineRowHeader(int size) {
		super(size);
	}

	public Object getElementAt(int in) {
		return new String("Количество товара");
	}
}
