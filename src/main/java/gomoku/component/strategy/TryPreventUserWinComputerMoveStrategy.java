package gomoku.component.strategy;

import gomoku.model.game.Sign;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public class TryPreventUserWinComputerMoveStrategy extends AbstractComputerMoveStrategy {

    public TryPreventUserWinComputerMoveStrategy(final int expectedCountEmptyCells) {
        super(expectedCountEmptyCells);
    }

    @Override
    protected Sign getFindSign(final Sign moveSign) {
        return moveSign.oppositeSign();
    }

}
