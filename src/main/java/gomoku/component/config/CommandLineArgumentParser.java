package gomoku.component.config;

import gomoku.model.config.Level;
import gomoku.model.config.PlayerType;
import gomoku.model.config.Size;

import static gomoku.model.config.Level.LEVEL1;
import static gomoku.model.config.Level.LEVEL2;
import static gomoku.model.config.PlayerType.COMPUTER;
import static gomoku.model.config.PlayerType.USER;
import static gomoku.model.config.Size.SIZE15;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public class CommandLineArgumentParser {

    final String[] args;

    public CommandLineArgumentParser(final String[] args) {
        this.args = args;
    }

    public CommandLineArguments parse() {

        PlayerType player1Type = null;
        PlayerType player2Type = null;
        Level level = null;
        Size size = null;

        for (final String arg : args) {
            if (USER.name().equalsIgnoreCase(arg) || COMPUTER.name().equalsIgnoreCase(arg)) {
                if (player1Type == null) {
                    player1Type = PlayerType.valueOf(arg.toUpperCase());

                } else if (player2Type == null) {
                    player2Type = PlayerType.valueOf(arg.toUpperCase());
                } else {
                    System.err.printf(
                            "Invalid command line arguments: '%s', because player types already set: player1Type='%s', player2Type='%s'!%n",
                            arg, player1Type, player2Type
                    );
                }
            } else if (LEVEL1.name().equalsIgnoreCase(arg) ||
                    LEVEL2.name().equalsIgnoreCase(arg)
            ) {
                if (level == null) {
                    level = Level.valueOf(arg.toUpperCase());
                } else {
                    System.err.printf(
                            "Invalid command line arguments: '%s', because the game level is already set: '%s'!%n",
                            arg, level
                    );
                }

            } else if (isSizeArg(arg)) {
                if (size == null) {
                    size = Size.valueOf(arg.toUpperCase());
                } else {
                    System.err.printf(
                            "Invalid command line arguments: '%s', because the game level is already set: '%s'!%n",
                            arg, level
                    );
                }

            } else {
                System.err.printf(
                        "Unsupported command line argument: '%s'%n",
                        arg);
            }
        }


        if (level == null) {
            level = LEVEL2;
        }
        if(size == null){
            size = SIZE15;
        }
        if (player1Type == null) {
            return new CommandLineArguments(USER, COMPUTER, level, size);
        } else if (player2Type == null) {
            return new CommandLineArguments(USER, player1Type, level, size);
        } else {
            return new CommandLineArguments(player1Type, player2Type, level, size);
        }

    }

    private boolean isSizeArg(final String arg) {
        for (final Size value : Size.values()) {
            if (value.name().equalsIgnoreCase(arg)) {
                return true;
            }
        }
        return false;
    }

    public static class CommandLineArguments {

        private final PlayerType player1Type;
        private final PlayerType player2Type;
        private final Level level;
        private final Size size;

        private CommandLineArguments(final PlayerType player1Type,
                                     final PlayerType player2Type,
                                     final Level level,
                                     final Size size
        ) {
            this.size = size;
            this.player1Type = player1Type;
            this.player2Type = player2Type;
            this.level = level;
        }

        public PlayerType getPlayer1Type() {
            return player1Type;
        }

        public PlayerType getPlayer2Type() {
            return player2Type;
        }

        public Level getLevel() {
            return level;
        }

        public Size getSize() {
            return size;
        }
    }
}
