package solver;

/**
 * Solving of math model. Takes object of Data; method solve() returns int[][]
 * with necessary shipping
 */

public class Solver {
	public Solver(Data data) {
		this.data = data;
	}

	public int[][] solve() {
		int[][] solution = new int[data.getMines().length][data.getFactories().length];

		while (!data.isAllCellsForbidden()) {
			Pair coords = minCost(); // coords of minimal cost in table

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
	 * @return minimal cost, in table except for the forbidden mines and
	 *         factories
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
