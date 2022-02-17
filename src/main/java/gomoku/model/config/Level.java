package gomoku.model.config;

import gomoku.component.ComputerMoveStrategy;
import gomoku.component.strategy.*;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public enum Level {
    LEVEL1(new ComputerMoveStrategy[]{
            new FirstMoveToTheCenterComputerMoveStrategy(),
            new RandomComputerMoveStrategy()
    }),
    LEVEL2(new ComputerMoveStrategy[]{
            new TryWinComputerMoveStrategy(1),
            new TryPreventUserWinComputerMoveStrategy(1),
            new TryWinComputerMoveStrategy(2),
            new TryPreventUserWinComputerMoveStrategy(2),
            new TryWinComputerMoveStrategy(3),
            new TryPreventUserWinComputerMoveStrategy(3),
            new TryWinComputerMoveStrategy(4),
            new TryPreventUserWinComputerMoveStrategy(4),
            new FirstMoveToTheCenterComputerMoveStrategy(),
            new RandomComputerMoveStrategy()
    });

    private final ComputerMoveStrategy[] strategies;

    Level(final ComputerMoveStrategy[] strategies) {
        this.strategies = strategies;
    }

    public ComputerMoveStrategy[] getStrategies() {
        return strategies.clone();
    }
}
    
