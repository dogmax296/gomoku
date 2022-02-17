package gomoku.component.strategy;

import gomoku.component.ComputerMoveStrategy;
import gomoku.model.game.Cell;
import gomoku.model.game.GameTable;
import gomoku.model.game.Sign;

import java.util.ArrayList;
import java.util.List;
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
        final List<Cell> bestCells = new ArrayList<>();

        findBestCellForMoveByRows(gameTable, findSign, bestCells);
        findBestCellForMoveByCols(gameTable, findSign, bestCells);
        findBestCellForMoveByMainDiagonal(gameTable, findSign, bestCells);
        findBestCellForMoveBySecondaryDiagonal(gameTable, findSign, bestCells);

        if (bestCells.size() > 0) {
            final Cell randomCell = bestCells.get(new Random().nextInt(bestCells.size()));
            gameTable.setSign(randomCell, moveSign);
            return true;
        } else {
            return false;
        }
    }

    private void findBestCellForMoveByRows(final GameTable gameTable, final Sign findSign, final List<Cell> bestCells) {
        findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, (i, j, k) -> new Cell(i, j + k));


    }

    private void findBestCellForMoveByCols(final GameTable gameTable, final Sign findSign, final List<Cell> bestCells) {
        findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, (i, j, k) -> new Cell(i + k, j));

    }

    private void findBestCellForMoveByMainDiagonal(final GameTable gameTable, final Sign findSign, final List<Cell> bestCells) {
        findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, (i, j, k) -> new Cell(i+k, j+k));
    }

    private void findBestCellForMoveBySecondaryDiagonal(final GameTable gameTable, final Sign findSign, final List<Cell> bestCells) {
        findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, (i, j, k) -> new Cell(i+k, j-k));
    }


    protected abstract Sign getFindSign(Sign moveSign);

    private void findBestCellForMoveUsingLambdaConversion(final GameTable gameTable,
                                                          final Sign findSign,
                                                          final List<Cell> bestCells,
                                                          final Lambda lambda) {


        for (int i = 0; i < gameTable.getSize(); i++) {
            for (int j = 0; j < gameTable.getSize(); j++) {
                final List<Cell> localEmptyCells = new ArrayList<>(WIN_COMBINATION_SIZE);
                int countEmptyCells = 0;
                int countSignCells = 0;
                for (int k = 0; k < WIN_COMBINATION_SIZE; k++) {
                    final Cell cell = lambda.convert(i, j, k);

                    if (gameTable.isValid(cell)) {
                        if (gameTable.isEmpty(cell)) {
                            localEmptyCells.add(cell);
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
                    bestCells.addAll(localEmptyCells);
                }

            }

        }


    }

    @FunctionalInterface
    private interface Lambda {
        Cell convert(int i, int j, int k);
    }

}