import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main extends JPanel {
    static final int MAX_CELLS_PER_ROW = 100;
    static final int CELL_SIZE = 20;
    static final int MAX_ROWS = (MAX_CELLS_PER_ROW / 2);
    static final int WINDOW_WIDTH = CELL_SIZE * MAX_CELLS_PER_ROW + 30;
    static final int WINDOW_HEIGHT = CELL_SIZE * MAX_ROWS + 50;
    static int ruleNumber = 0;
    static int[] ruleset = new int[8];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter rule number: ");
        ruleNumber = sc.nextInt();
        sc.close();
        ruleset = intToBitArray(ruleNumber);
        System.out.print("Ruleset " + ruleNumber + ": ");
        for (int bit : ruleset) {
            System.out.print(bit);
        }
        createCanvas();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Ensures the panel is properly rendered
        Graphics2D cnv = (Graphics2D) g; // Cast to Graphics2D for more advanced control

        // Enable anti-aliasing for smoother lines
        cnv.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        cnv.setColor(Color.BLACK); // Set drawing color
        cnv.setStroke(new BasicStroke(1)); // Set line thickness

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
    }

    public static int[] intToBitArray(int number) {
        int[] bitArray = new int[8];

        // number >> i shifts the least significant bit (most right bit) of the binary representation of number
        // i places to the right.
        // & 1 extracts the least significant bit
        //
        // example: number = 13 = 00001101
        // number >> i = 00001101 // i=0
        // number >> i & 1 = 00001101 & 00000001 = 1
        //
        // number >> i = 00000110 // i=1
        // number >> i & 1 = 00000110 & 00000110 = 0
        //
        // number >> i = 00000011 // i=2
        // number >> i & 1 = 00000011 & 00000011 = 0
        //...
        for (int i = 0; i < 8; i++) {
            bitArray[7 - i] = (number >> i) & 1;
        }
        return bitArray;
    }
}
