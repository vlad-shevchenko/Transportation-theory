package start;

import java.awt.Dimension;

/**
 * Класс, инкапсулирующий разные константы.
 */

public class Const {
	// Размеры окна
	public static Dimension START_FRAME_SIZE = new Dimension(350, 200);
	public static Dimension DEFAULT_FRAME_SIZE = new Dimension(600, 450);
	public static Dimension MIN_FRAME_SIZE = new Dimension(400, 450);
	public static Dimension MAX_FRAME_SIZE = new Dimension(800, 600);

	// Коды ошибок, возвращаемые методом checkData() классов SettingsPanel и
	// TablesPanel
	public static int NO_ERRORS = 0;
	public static int NOT_ENOGHT_DATA = 1;
	public static int INCORRECT_DATA = 2;
	public static int UNEQUAL_ORE_AMOUNT = 3;
	public static int INCORRECT_SETTINGS = 4;
}
