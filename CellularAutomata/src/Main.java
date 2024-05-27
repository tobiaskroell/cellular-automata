import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    public static void main(String[] args) {
        createCanvas();

    }

    private static void createCanvas() {
        JFrame frame = new JFrame("Cellular Automata");
        Main main = new Main();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 400);
        frame.add(main);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Ensures the panel is properly rendered
        Graphics2D cnv = (Graphics2D) g; // Cast to Graphics2D for more advanced control

        // Enable anti-aliasing for smoother lines
        cnv.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        cnv.setColor(Color.BLACK); // Set drawing color

        int[] cells = {1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0};
        int x = 0;
        int y = 0;
        int cellWidth = 40;
        int cellHeight = 40;
        int cellsInRow = 0;

        for (int i = 0; i < cells.length; i++) {
            if (cellsInRow == 10) {
                x = 0;
                y += cellHeight;
                cellsInRow = 0;
            }

            if (cells[i] == 1) {
                cnv.fillRect(x, y, cellWidth, cellHeight);
            } else {
                cnv.drawRect(x, y, cellWidth, cellHeight);
            }
            x += cellWidth;
            cellsInRow++;
        }
    }
}








