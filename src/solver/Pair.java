package solver;

/**
 * Класс, инкапсулирующий два числа(координаты). Велосипед. Нужно, чтобы
 * избежать проблем с совместимостью Java1.5 / 1.7
 */

public class Pair {

	public int a;
	public int b;

	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}
}