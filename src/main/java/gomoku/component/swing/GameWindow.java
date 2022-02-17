package gomoku.component.swing;

import gomoku.component.DataPrinter;
import gomoku.component.UserInputReader;
import gomoku.model.game.Cell;
import gomoku.model.game.GameTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static gomoku.Constants.*;

/**
 * @author dogmax296
 * @link https://github.com/dogmax296
 */
public final class GameWindow extends JFrame implements DataPrinter, UserInputReader {

    private final int size;

    private static final int FONT_SIZE = 25;

    private static final int CELL_SIZE = 40;

    private final JLabel[][] cells;

    private Cell clickedCell;

    public GameWindow(final int size) {
        super("Gomoku");
        this.size = size;
        this.cells = new JLabel[size][size];
        setSystemLookAndFeel();
        setLayout(new GridLayout(size, size));
        createGameTable();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        displayInTheMiddleOfTheScreen();

    }

    private void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (final ClassNotFoundException | UnsupportedLookAndFeelException |
                IllegalAccessException | InstantiationException ex) {
            ex.printStackTrace();
        }
    }

    private void createGameTable() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JLabel label = new JLabel();
                cells[i][j] = label;
                label.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setFont(new Font(Font.SERIF, Font.PLAIN, FONT_SIZE));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(label);
                final Cell cell = new Cell(i, j);
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(final MouseEvent unused) {
                        synchronized (GameWindow.this) {
                            clickedCell = cell;
                            GameWindow.this.notifyAll();
                        }
                    }
                });
            }
        }
    }

    private void displayInTheMiddleOfTheScreen() {
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
        setVisible(true);
    }


    @Override
    public void printInfoMessage(final String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void printErrorMessage(final String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void printInstructions() {
        // do nothing
    }

    @Override
    public void printGameTable(final GameTable gameTable) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].setText(String.valueOf(gameTable.getSign(new Cell(i, j))));
            }
        }
    }

    @Override
    public Cell getUserInput() {
        synchronized (this) {
            try {
                wait();
            } catch (final InterruptedException exception) {
                exception.printStackTrace();
                System.exit(2);
            }
        }
        return clickedCell;
    }
}
