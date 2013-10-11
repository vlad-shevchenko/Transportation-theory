package solver;

/**
 *  ласс, инкапсулирующий два числа(координаты); нужен, чтобы иметь возможность
 * возвращать из методы пару чисел. ¬елосипед
 */

public class Pair {
	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public int a;
	public int b;
}