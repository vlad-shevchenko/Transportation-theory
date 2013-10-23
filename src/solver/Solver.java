package solver;
/**
 * Решает задачу. Конструктор принимает объект Data, метод solve() возвращает
 * матрицу перевозок
 */

public class Solver {

	private Data data;
	
	public Solver(Data data) {
		this.data = data;
	}

	/**
	 * Метод для решения задачи. Используются данные, ранее переданные в
	 * конструкторе.
	 * 
	 * @return матрица перевозок
	 */
	public Integer[][] solve() {
		Integer[][] solution = new Integer[data.getMines().length][data
				.getFactories().length];

		while (!data.isAllCellsForbidden()) {
			Pair coords = minCost();

			int dec = minOf(data.getMines()[coords.a],
					data.getFactories()[coords.b]);

			data.decMineProposal(coords.a, dec);
			data.decFactoryConsume(coords.b, dec);

			if (data.getMines()[coords.a] == 0)
				data.removeMine(coords.a);
			else if (data.getFactories()[coords.b] == 0)
				data.removeFactory(coords.b);

			solution[coords.a][coords.b] = dec;
		}
		
		return solution;
	}

	/**
	 * Текущая минимальная стоимость(исключая производителей, у которых больше
	 * нет товара, и потребителей, которым он больше не нужен)
	 * 
	 * @return координаты ячейки с минимальной стоимостью
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
}
