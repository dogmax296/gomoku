package gomoku.component.strategy;

import gomoku.component.ComputerMoveStrategy;
import gomoku.model.game.Cell;
import gomoku.model.game.GameTable;
import gomoku.model.game.Sign;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public class RandomComputerMoveStrategy implements ComputerMoveStrategy {

    @Override
    public boolean tryToMakeMove(final GameTable gameTable, final Sign sign) {
        final List<Cell> emptyCells = new ArrayList<>();
        for (int i = 0; i < gameTable.getSize(); i++) {
            for (int j = 0; j < gameTable.getSize(); j++) {
                final Cell cell = new Cell(i, j);
                if (gameTable.isEmpty(cell)) {
                    emptyCells.add(cell);
                }
            }
        }
        if (emptyCells.size() > 0) {
            final Cell randomCell = emptyCells.get(new Random().nextInt(emptyCells.size()));
            gameTable.setSign(randomCell, sign);
            return true;
        } else {
            return false;
        }
    }
}
