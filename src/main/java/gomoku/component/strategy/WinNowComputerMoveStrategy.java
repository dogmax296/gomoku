package gomoku.component.strategy;

import gomoku.model.game.Sign;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public class WinNowComputerMoveStrategy extends AbstractComputerMoveStrategy {

    public WinNowComputerMoveStrategy() {
        super(1);
    }

    @Override
    protected Sign getFindSign(final Sign moveSign) {
        return moveSign;
    }
}