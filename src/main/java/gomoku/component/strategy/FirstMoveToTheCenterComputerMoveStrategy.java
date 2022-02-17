package gomoku.component.strategy;

import gomoku.component.ComputerMoveStrategy;
import gomoku.model.game.Cell;
import gomoku.model.game.GameTable;
import gomoku.model.game.Sign;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public class FirstMoveToTheCenterComputerMoveStrategy implements ComputerMoveStrategy {
    @Override
    public boolean tryToMakeMove(final GameTable gametable, final Sign sign) {
        final Cell center = new Cell(1, 1);
        if (gametable.isEmpty(center)) {
            gametable.setSign(center, sign);
            return true;
        } else {
            return false;
        }
    }

}
