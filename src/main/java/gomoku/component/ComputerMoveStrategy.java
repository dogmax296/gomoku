package gomoku.component;

import gomoku.model.game.GameTable;
import gomoku.model.game.Sign;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public interface ComputerMoveStrategy {

    boolean tryToMakeMove(GameTable gameTable, Sign sign);
}
