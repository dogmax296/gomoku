package gomoku.component.strategy;

import gomoku.component.ComputerMoveStrategy;
import gomoku.model.game.Cell;
import gomoku.model.game.GameTable;
import gomoku.model.game.Sign;

import java.util.Random;

import static gomoku.Constants.WIN_COMBINATION_SIZE;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public abstract class AbstractComputerMoveStrategy implements ComputerMoveStrategy {

    private final int expectedCountEmptyCells;

    protected AbstractComputerMoveStrategy(final int expectedCountEmptyCells) {
        this.expectedCountEmptyCells = expectedCountEmptyCells;
    }

    @Override
    public final boolean tryToMakeMove(final GameTable gameTable, final Sign moveSign) {
        final Sign findSign = getFindSign(moveSign);
        final BestCells bestCells = new BestCells(gameTable.getSize());

        findBestCellForMoveByRows(gameTable, findSign, bestCells);
        findBestCellForMoveByCols(gameTable, findSign, bestCells);
        findBestCellForMoveByMainDiagonal(gameTable, findSign, bestCells);
        findBestCellForMoveBySecondaryDiagonal(gameTable, findSign, bestCells);

        if (bestCells.count > 0) {
            final Cell randomCell = bestCells.emptyCells[new Random().nextInt(bestCells.count)];
            gameTable.setSign(randomCell, moveSign);
            return true;
        } else {
            return false;
        }
    }

    private void findBestCellForMoveByRows(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, (i, j, k) -> new Cell(i, j + k));


    }

    private void findBestCellForMoveByCols(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, (i, j, k) -> new Cell(i + k, j));

    }

    private void findBestCellForMoveByMainDiagonal(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, (i, j, k) -> new Cell(i+k, j+k));
    }

    private void findBestCellForMoveBySecondaryDiagonal(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, (i, j, k) -> new Cell(i+k, j-k));
    }


    protected abstract Sign getFindSign(Sign moveSign);

    private void findBestCellForMoveUsingLambdaConversion(final GameTable gameTable,
                                                          final Sign findSign,
                                                          final BestCells bestCells,
                                                          final Lambda lambda) {


        for (int i = 0; i < gameTable.getSize(); i++) {
            for (int j = 0; j < gameTable.getSize(); j++) {
                final Cell[] localEmptyCells = new Cell[WIN_COMBINATION_SIZE];
                int countEmptyCells = 0;
                int countSignCells = 0;
                int count = 0;
                for (int k = 0; k < WIN_COMBINATION_SIZE; k++) {
                    final Cell cell = lambda.convert(i, j, k);

                    if (gameTable.isValid(cell)) {
                        if (gameTable.isEmpty(cell)) {
                            localEmptyCells[count++] = cell;
                            countEmptyCells++;
                        } else if (gameTable.getSign(cell) == findSign) {
                            countSignCells++;
                        } else {
                            break;
                        }
                    }
                }

                if (
                        countEmptyCells == expectedCountEmptyCells &&
                                countSignCells == WIN_COMBINATION_SIZE - expectedCountEmptyCells
                ) {
                    for (int l = 0; l < count; l++) {
                        bestCells.add(localEmptyCells[l]);
                    }
                }

            }

        }


    }

    @FunctionalInterface
    private interface Lambda {
        Cell convert(int i, int j, int k);
    }


    private static class BestCells {
        private final Cell[] emptyCells;
        private int count;

        private BestCells(final int size) {
            emptyCells = new Cell[size * size];
        }

        private void add(final Cell cell) {
            boolean isUnique = true;
            for (int i = 0; i < count; i++) {
                if (cell.getRow() == emptyCells[i].getRow() && cell.getColl() == emptyCells[i].getColl()) {
                    return;
                }
            }

            emptyCells[count++] = cell;

        }

    }
}