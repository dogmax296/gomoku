package gomoku;

import gomoku.model.config.Level;
import gomoku.model.config.Size;

import static gomoku.model.config.Level.LEVEL2;
import static gomoku.model.config.Size.SIZE15;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public class Constants {
    public static final int WIN_COMBINATION_SIZE = 5;

    public static final String DELAY_PREFIX = "DELAY=";

    public static final Level DEFAULT_LEVEL = LEVEL2;

    public static final Size DEFAULT_SIZE = SIZE15;

    public static final long DEFAULT_DELAY_IN_MILLIS = 0;

    private Constants() {
    }
}
