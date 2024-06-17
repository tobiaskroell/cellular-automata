import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main extends JPanel {
    static final int MAX_CELLS_PER_ROW = 100;
    static final int CELL_SIZE = 10;
    static final int MAX_ROWS = (MAX_CELLS_PER_ROW / 2);
    static final int WINDOW_WIDTH = CELL_SIZE * MAX_CELLS_PER_ROW + 30;
    static final int WINDOW_HEIGHT = CELL_SIZE * MAX_ROWS + 50;
    static int ruleNumber = 0;
    static int[] ruleset = new int[8];

    public static void main(String[] args) {
        ruleNumber = readUserInput();
        ruleset = intToBitArray(ruleNumber);
        System.out.print("Ruleset " + ruleNumber + ": ");
        for (int bit : ruleset) {
            System.out.print(bit);
        }
        createCanvas();
    }

    private static int readUserInput() {
        Scanner sc = new Scanner(System.in);
        int input = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter rule number: ");
            try {
                input = sc.nextInt();
                if (input >= 0 && input <= 255) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid integer between 0 and 255.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer between 0 and 255.");
                sc.next();  // Clear the invalid input from scanner buffer
            }
        }

        sc.close();
        return input;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Ensures the panel is properly rendered
        Graphics2D cnv = (Graphics2D) g; // Cast to Graphics2D for more advanced control

        // Enable anti-aliasing for smoother lines
        cnv.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        cnv.setColor(Color.BLACK); // Set drawing color

        int x = 0;
        int y = 0;
        int cellWidth = 40;
        int cellHeight = 40;
        int currentCellsInRow = 0;
        int[] cells = new int[MAX_CELLS_PER_ROW];
        cells[MAX_CELLS_PER_ROW / 2] = 1; // Placing the 1 in the middle of the array

        for (int i = 0; i < MAX_ROWS; i++) {

            // Initial generation of the cells
            for (int j = 0; j < cells.length; j++) {
                if (currentCellsInRow == MAX_CELLS_PER_ROW) {
                    x = 0;
                    y += CELL_SIZE;
                    currentCellsInRow = 0;
                }

                if (cells[j] == 1) {
                    cnv.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                    cnv.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                }
                x += CELL_SIZE;
                currentCellsInRow++;
            }

            // Next generation of the cells
            int left = 0;
            int right = 0;
            int state = 0; // middle cell
            int newState = 0;
            int[] nextCells = new int[cells.length];
            nextCells[0] = cells[0];
            nextCells[cells.length - 1] = cells[cells.length - 1];

            for (int k = 1; k < cells.length - 1; k++) {
                left = cells[k - 1];
                right = cells[k + 1];
                state = cells[k];
                newState = calculateState(left, state, right);
                nextCells[k] = newState;
            }
            cells = nextCells;
        }
    }

    /**
     * Calculates the state of the given cell and returns the new state of the cell for the next generation.
     *
     * @param left  The state of the cell to the left of the current cell
     * @param state The state of the current cell
     * @param right The state of the cell to the right of the current cell
     * @return The new state of the cell for the next generation
     */
    private int calculateState(int left, int state, int right) {
        int value = left * 4 + state * 2 + right;
        return ruleset[7 - value];
    }

    private static void createCanvas() {
        JFrame frame = new JFrame("Cellular Automata");
        Main main = new Main();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.add(main);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }

    /**
     * Returns the bit array representation of the given number.
     *
     * @param number The number to convert to a bit array
     * @return The bit array of the given number
     */
    public static int[] intToBitArray(int number) {
        int[] bitArray = new int[8];
        // number >> i: shifts the binary representation of number i places to the right.
        // & 1: extracts the least significant bit of the shifted number.
        //
        // example: number = 13 = 00001101 in binary
        //          i = 0
        //          number >> i = 00001101
        //          (number >> i) & 1 = 00001101 & 00000001 = 1
        //
        //          i = 1
        //          number >> i = 00000110
        //          (number >> i) & 1 = 00000110 & 00000001 = 0
        //
        //          i = 2
        //          number >> i = 00000011
        //          (number >> i) & 1 = 00000011 & 00000001 = 1
        //
        //          i = 3
        //          number >> i = 00000001
        //          (number >> i) & 1 = 00000001 & 00000001 = 1
        for (int i = 0; i < 8; i++) {
            bitArray[7 - i] = (number >> i) & 1;
        }
        return bitArray;
    }

}
