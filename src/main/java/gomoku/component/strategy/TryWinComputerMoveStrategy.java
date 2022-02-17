package gomoku.component.strategy;

import gomoku.model.game.Sign;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public class TryWinComputerMoveStrategy extends AbstractComputerMoveStrategy {

    public TryWinComputerMoveStrategy(final int expectedCountEmptyCells) {
        super(expectedCountEmptyCells);
    }

    @Override
    protected Sign getFindSign(final Sign moveSign) {
        return moveSign;
    }
}