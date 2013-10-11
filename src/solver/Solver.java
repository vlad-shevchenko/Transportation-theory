package solver;

/**
 * –ешает задачу.  онструктор принимает объект Data, метод solve() возвращает
 * матрицу перевозок
 */

public class Solver {
	public Solver(Data data) {
		this.data = data;
	}

	/**
	 * ћетод дл€ решени€ задачи. »спользуютс€ данные, ранее переданные в
	 * конструкторе.
	 * 
	 * @return матрица перевозок
	 */
	public int[][] solve() {
		int[][] solution = new int[data.getMines().length][data.getFactories().length];

		while (!data.isAllCellsForbidden()) {
			Pair coords = minCost(); // координаты €чейки с минимальным зданием
										// в матрице стоимости

			int dec = minOf(data.getMines()[coords.a],
					data.getFactories()[coords.b]);

			data.decMineProposal(coords.a, dec);
			data.decFactoryConsume(coords.b, dec);

			if (data.getMines()[coords.a] == 0)
				data.removeMine(coords.a);
			if (data.getFactories()[coords.b] == 0)
				data.removeFactory(coords.b);

			solution[coords.a][coords.b] = dec;
		}

		return solution;
	}

	/**
	 * “екуща€ минимальна€ стоимость(исключа€ производителей, у которых больше
	 * нет товара, и потребителей, которым он больше не нужен)
	 * 
	 * @return минимальна€ стоимость
	 */
	private Pair minCost() {
		Pair result = new Pair(0, 0);
		int[][] cost = data.getCost();

		int min = Integer.MAX_VALUE;

		for (int i = 0; i < cost.length; ++i) {
			for (int j = 0; j < cost[0].length; ++j) {
				if (data.isAlowedMine(i) && data.isAlowedFactory(j)) {
					if (cost[i][j] < min) {
						min = cost[i][j];
						result.a = i;
						result.b = j;
					}
				}
			}
		}

		return result;
	}

	private int minOf(int a, int b) {
		return (a > b) ? b : a;
	}

	private Data data;
}
