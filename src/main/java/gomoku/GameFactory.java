package gomoku;

import gomoku.component.*;
import gomoku.component.config.CommandLineArgumentParser;
import gomoku.component.swing.GameWindow;
import gomoku.model.config.Level;
import gomoku.model.config.PlayerType;
import gomoku.model.game.Player;

import static gomoku.component.config.CommandLineArgumentParser.CommandLineArguments;
import static gomoku.model.config.PlayerType.USER;
import static gomoku.model.game.Sign.O;
import static gomoku.model.game.Sign.X;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public class GameFactory {

    private final PlayerType player1Type;
    private final PlayerType player2Type;
    private final Level level;

    public GameFactory(final String[] args) {
        final CommandLineArguments commandLineArguments = new CommandLineArgumentParser(args).parse();
        player1Type = commandLineArguments.getPlayer1Type();
        player2Type = commandLineArguments.getPlayer2Type();
        level = commandLineArguments.getGameLevel();
    }

    public Game create() {

        final GameWindow gameWindow = new GameWindow();

        Player player1;
        if (this.player1Type == USER) {
            player1 = new Player(X, new UserMove(gameWindow, gameWindow));
        } else {
            player1 = new Player(X, new ComputerMove(level.getStrategies()));
        }

        Player player2;
        if (this.player2Type == USER) {
            player2 = new Player(O, new UserMove(gameWindow, gameWindow));
        } else {
            player2 = new Player(O, new ComputerMove(level.getStrategies()));
        }

        final boolean canSecondPlayerMakeFirstMove = this.player1Type != this.player2Type;
        return new Game(
                gameWindow,
                player1,
                player2,
                new WinnerVerifier(),
                new CellVerifier(),
                canSecondPlayerMakeFirstMove
        );
    }
}
