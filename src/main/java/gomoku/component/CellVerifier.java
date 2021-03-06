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

import static gomoku.Constants.*;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public class CellVerifier {
    public boolean allCellsFilled(final GameTable gametable) {
        for (int i = 0; i < gametable.getSize(); i++) {
            for (int j = 0; j < gametable.getSize(); j++) {
                if (gametable.isEmpty(new Cell(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
