package gomoku.component.strategy;

import gomoku.component.ComputerMoveStrategy;
import gomoku.model.game.Cell;
import gomoku.model.game.GameTable;
import gomoku.model.game.Sign;

import java.util.Random;

import static gomoku.Constants.GAME_TABLE_SIZE;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public class RandomComputerMoveStrategy implements ComputerMoveStrategy {

    @Override
    public boolean tryToMakeMove(final GameTable gameTable, final Sign sign) {
        final Cell[] emptyCells = new Cell[GAME_TABLE_SIZE*GAME_TABLE_SIZE];
        int count = 0;
        for (int i = 0; i < GAME_TABLE_SIZE; i++) {
            for (int j = 0; j < GAME_TABLE_SIZE; j++) {
                final Cell cell = new Cell(i, j);
                if (gameTable.isEmpty(cell)) {
                    emptyCells[count++] = cell;
                }
            }
        }
        if (count > 0) {
            final Cell randomCell = emptyCells[new Random().nextInt(count)];
            gameTable.setSign(randomCell, sign);
            return true;
        } else {
            return false;
        }
    }
}
