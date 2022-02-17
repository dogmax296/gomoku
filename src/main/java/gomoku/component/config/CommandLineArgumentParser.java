package gomoku.component.config;

import gomoku.Constants;
import gomoku.model.config.Level;
import gomoku.model.config.PlayerType;
import gomoku.model.config.Size;

import static gomoku.Constants.*;
import static gomoku.model.config.Level.LEVEL1;
import static gomoku.model.config.Level.LEVEL2;
import static gomoku.model.config.PlayerType.COMPUTER;
import static gomoku.model.config.PlayerType.USER;

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
        long delayInMillis = -1;

        for (final String arg : args) {
            final String normalizedArg = arg.toUpperCase();
            if (USER.name().equals(normalizedArg) || COMPUTER.name().equals(normalizedArg)) {
                if (player1Type == null) {
                    player1Type = PlayerType.valueOf(normalizedArg);

                } else if (player2Type == null) {
                    player2Type = PlayerType.valueOf(normalizedArg);
                } else {
                    System.err.printf(
                            "Invalid command line arguments: '%s', because player types already set: player1Type='%s', player2Type='%s'!%n",
                            arg, player1Type, player2Type
                    );
                }
            } else if (LEVEL1.name().equals(normalizedArg) ||
                    LEVEL2.name().equals(normalizedArg)
            ) {
                if (level == null) {
                    level = Level.valueOf(normalizedArg);
                } else {
                    System.err.printf(
                            "Invalid command line arguments: '%s', because the game level is already set: '%s'!%n",
                            arg, level
                    );
                }

            } else if (isSizeArg(normalizedArg)) {
                if (size == null) {
                    size = Size.valueOf(normalizedArg);
                } else {
                    System.err.printf(
                            "Invalid command line arguments: '%s', because the game level is already set: '%s'!%n",
                            arg, level
                    );
                }

            } else if (normalizedArg.startsWith(DELAY_PREFIX)) {
                if (delayInMillis == -1) {
                    delayInMillis = getDelayInMillis(arg);
                } else {
                    System.err.printf(
                            "Invalid command line argument: '%s', because delay in millis already set: '%s'!%n",
                            arg, delayInMillis
                    );
                }
            } else {
                System.err.printf(
                        "Unsupported command line argument: '%s'%n",
                        arg);
            }
        }


        if(delayInMillis == -1){
            delayInMillis = DEFAULT_DELAY_IN_MILLIS;
        }
        if (level == null) {
            level = DEFAULT_LEVEL;
        }
        if(size == null){
            size = DEFAULT_SIZE;
        }
        if (player1Type == null) {
            return new CommandLineArguments(USER, COMPUTER, level, size, delayInMillis);
        } else if (player2Type == null) {
            return new CommandLineArguments(USER, player1Type, level, size, delayInMillis);
        } else {
            return new CommandLineArguments(player1Type, player2Type, level, size, delayInMillis);
        }

    }

    private long getDelayInMillis(final String arg) {
        final String[] values = arg.split("=");
        if (values.length != 2) {
            System.err.printf(
                    "Invalid command line argument: '%s', because it must be follow the next pattern: 'delay=${DELAY_IN_MILLIS}'!%n",
                    arg
            );
            return -1;
        }
        try {
            final long result = Long.parseLong(values[1]);
            if (result <= 0) {
                System.err.printf(
                        "Invalid command line argument: '%s', because delay value must be positive!%n",
                        arg
                );
                return 0;
            }
            return result;
        } catch (final NumberFormatException exception) {
            System.err.printf(
                    "Invalid command line argument: '%s', because delay value must be a long value!%n",
                    arg
            );
            return 0;
        }
    }

    private boolean isSizeArg(final String arg) {
        for (final Size value : Size.values()) {
            if (value.name().equals(arg)) {
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
        private final long delayInMillis;

        private CommandLineArguments(final PlayerType player1Type,
                                     final PlayerType player2Type,
                                     final Level level,
                                     final Size size,
                                     final long delayInMillis) {
            this.size = size;
            this.player1Type = player1Type;
            this.player2Type = player2Type;
            this.level = level;
            this.delayInMillis = delayInMillis;
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
        public long getDelayInMillis() {
            return this.delayInMillis;
        }
    }
}
