package solver;

/**
 *  ласс, инкапсулирующий данные дл€ Solver.
 * 
 * —одержит массив с количеством товара у производителей, потребност€ми
 * потребителей(...) и двумерный массив со стоимостью перевозки единицы товара
 * от i-ого производител€ j-ому потребителю.
 * 
 * “акже содержит список производителей/потребителей, который могут
 * поставл€ть/получать товар и предоставл€ет методы дл€ работы с этими данными.
 */

public class Data {

	private boolean[] correctMines;
	private boolean[] correctFactories;
	private int[] mines;
	private int[] factories;
	private int[][] cost;

	public Data(int[] mines, int[] factories, int[][] cost) {
		this.mines = new int[mines.length];
		this.factories = new int[factories.length];
		this.cost = new int[mines.length][factories.length];
		this.correctMines = new boolean[mines.length];
		this.correctFactories = new boolean[factories.length];

		for (int i = 0; i < correctMines.length; ++i) {
			correctMines[i] = true;
		}

		for (int i = 0; i < correctFactories.length; ++i) {
			correctFactories[i] = true;
		}

		for (int i = 0; i < mines.length; ++i) {
			this.mines[i] = mines[i];
		}

		for (int i = 0; i < factories.length; ++i) {
			this.factories[i] = factories[i];
		}

		for (int i = 0; i < cost.length; ++i) {
			for (int j = 0; j < cost[0].length; ++j) {
				this.cost[i][j] = cost[i][j];
			}
		}
	}

	/**
	 * ѕровер€ет корректность введЄнных данных
	 * 
	 * @return равна ли сумма товара у производителей сумме товара, необходимого
	 *         потребител€м
	 */
	public boolean isDataValid() {
		int minesSum = 0;
		int factoriesSum = 0;

		for (int i = 0; i < mines.length; ++i) {
			minesSum += mines[i];
		}
		for (int i = 0; i < factories.length; ++i) {
			factoriesSum += factories[i];
		}

		return (minesSum == factoriesSum);
	}

	public boolean isAllCellsForbidden() {
		boolean mineResult = true;
		boolean factoryResult = true;

		for (int i = 0; i < correctMines.length; ++i) {
			if (correctMines[i] == true)
				mineResult = false;
		}
		for (int i = 0; i < correctFactories.length; ++i) {
			if (correctFactories[i] == true)
				factoryResult = false;
		}

		return (mineResult || factoryResult);
	}

	public void decMineProposal(int mine, int number) {
		mines[mine] -= number;
	}

	public void decFactoryConsume(int factory, int number) {
		factories[factory] -= number;
	}

	public void removeMine(int num) {
		correctMines[num] = false;
	}

	public void removeFactory(int num) {
		correctFactories[num] = false;
	}

	public boolean isAlowedMine(int num) {
		return correctMines[num];
	}

	public boolean isAlowedFactory(int num) {
		return correctFactories[num];
	}

	public int[] getMines() {
		return this.mines;
	}

	public int[] getFactories() {
		return this.factories;
	}

	public int[][] getCost() {
		return this.cost;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
