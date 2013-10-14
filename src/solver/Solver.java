package solver;

import java.util.ArrayList;

/**
 * Решает задачу. Конструктор принимает объект Data, метод solve() возвращает
 * матрицу перевозок
 */

public class Solver {
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

		// Построение опорного плана
		while (!data.isAllCellsForbidden()) {
			Pair coords = minCost(); // координаты ячейки с минимальным
										// значением
										// в матрице стоимости

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

		// Нахождение оптимального плана методом потенциалов
		Integer[] minePotential = new Integer[data.getMines().length];
		Integer[] factoryPotential = new Integer[data.getFactories().length];
		ArrayList<Pair> basisList = new ArrayList<Pair>(minePotential.length
				+ factoryPotential.length - 1);
		ArrayList<Pair> nobasisList = new ArrayList<Pair>(minePotential.length
				* factoryPotential.length - basisList.size());
		for(int i = 0; i < solution.length; ++i) {
			for(int j = 0; j < solution[0].length; ++j) {
				if(solution[i][j] != null)
					basisList.add(new Pair(i, j));
				else
					nobasisList.add(new Pair(i, j));
			}
		}

		Pair maxCost = maxCost();
		minePotential[maxCost.a] = 0;
		for(int i = 0; i < factoryPotential.length; ++i) {
			if(basisList.contains(new Pair(maxCost.a, i)))
				factoryPotential[i] = data.getCost()[maxCost.a][i];
		}
		
		boolean repeat = false;
		while(repeat) {
			repeat = false;
			for(Pair p : basisList) {
				if(minePotential[p.a] != null) {
					if(factoryPotential[p.b] != null) {
						// Nothing
					} else {
						factoryPotential[p.b] = data.getCost()[p.a][p.b] - minePotential[p.a];
					}
				} else if (factoryPotential[p.b] != null) {
					if(minePotential[p.a] != null) {
						minePotential[p.a] = data.getCost()[p.a][p.b] - factoryPotential[p.b];
					} else {
						repeat = true;
					}
				}
			}
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
		Integer[][] cost = data.getCost();

		int min = Integer.MAX_VALUE;

		for (int i = 0; i < cost.length; ++i) {
			for (int j = 0; j < cost[0].length; ++j) {
				if (data.isAlowedMine(i) && data.isAlowedFactory(j)) {
					if (cost[i][j] != null) {
						if (cost[i][j] < min) {
							min = cost[i][j];
							result.a = i;
							result.b = j;
						}
					}
				}
			}
		}

		return result;
	}
	
	/**
	 * Максимальная стоимость (среди всех ячеек)
	 * 
	 * @return координаты ячейки с максимальной стоимостью
	 */
	private Pair maxCost() {
		Pair result = new Pair(0, 0);
		Integer[][] cost = data.getCost();

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < cost.length; ++i) {
			for (int j = 0; j < cost[0].length; ++j) {
				if (cost[i][j] != null) {
					if (cost[i][j] > max) {
						max = cost[i][j];
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
