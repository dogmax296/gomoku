package gomoku.component.strategy;

import gomoku.model.game.Sign;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public class WinOnTheNextStepComputerMoveStrategy extends AbstractComputerMoveStrategy {

    public WinOnTheNextStepComputerMoveStrategy() {
        super(2);
    }

    @Override
    protected Sign getFindSign(final Sign moveSign) {
        return moveSign;
    }
}
