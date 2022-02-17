/*
 * Copyright 2022 dogmax296
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package gomoku.component;

import gomoku.model.game.Cell;
import gomoku.model.game.GameTable;
import gomoku.model.game.Player;
import gomoku.model.game.Sign;

import static gomoku.Constants.GAME_TABLE_SIZE;
import static gomoku.Constants.WIN_COMBINATION_SIZE;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public class WinnerVerifier {

    public boolean isWinner(final GameTable gametable, final Player player) {
        return isWinByRows(gametable, player.getSign()) ||
                isWinByCols(gametable, player.getSign()) ||
                isWinByMainDiagonal(gametable, player.getSign()) ||
                isWinBySecondDiagonal(gametable, player.getSign());
    }

    private boolean isWinByRows(final GameTable gameTable, final Sign sign) {
        return isWinUsingLambda(gameTable, sign, (i, j, k) -> new Cell(i, j + k));
    }

    private boolean isWinByCols(final GameTable gameTable, final Sign sign) {
        return isWinUsingLambda(gameTable, sign, (i, j, k) -> new Cell(i + k, j));
    }

    private boolean isWinByMainDiagonal(final GameTable gameTable, final Sign sign) {
        return isWinUsingLambda(gameTable, sign, (i, j, k) -> new Cell(i + k, j + k));
    }

    private boolean isWinBySecondDiagonal(final GameTable gameTable, final Sign sign) {
        return isWinUsingLambda(gameTable, sign, (i, j, k) -> new Cell(i + k, j - k));
    }


    private boolean isWinUsingLambda(final GameTable gameTable, final Sign sign, final Lambda lambda) {
        for (int i = 0; i < GAME_TABLE_SIZE; i++) {
            for (int j = 0; j < GAME_TABLE_SIZE; j++) {
                int countSignCells = 0;
                for (int k = 0; k < WIN_COMBINATION_SIZE; k++) {
                    final Cell cell = lambda.convert(i, j, k);
                    if (gameTable.isValid(cell)) {
                        if (gameTable.getSign(cell) == sign) {
                            countSignCells++;
                        } else {
                            break;
                        }
                    }
                }

                if (countSignCells == WIN_COMBINATION_SIZE) {
                    return true;
                }

            }
        }
        return false;
    }


    @FunctionalInterface
    private interface Lambda {

        Cell convert(int i, int j, int k);
    }


}
