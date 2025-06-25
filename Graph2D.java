import javax.swing.*;
import java.awt.*;

public class Graph2D extends JFrame {
    private double[] xValues;
    private double[] yValues;

    public Graph2D(double[] xValues, double[] yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
        setTitle("Graph2D");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        int padding = 50;
        int width = getWidth() - 2 * padding;
        int height = getHeight() - 2 * padding;

        g2d.drawLine(padding, getHeight() - padding, getWidth() - padding, getHeight() - padding);
        g2d.drawLine(padding, getHeight() - padding, padding, padding);

        double xMin = getMin(xValues);
        double xMax = getMax(xValues);
        double yMin = getMin(yValues);
        double yMax = getMax(yValues);

        g2d.setColor(Color.BLUE);
        for (int i = 0; i < xValues.length - 1; i++) {
            int x1 = padding + (int) ((xValues[i] - xMin) / (xMax - xMin) * width);
            int y1 = getHeight() - padding - (int) ((yValues[i] - yMin) / (yMax - yMin) * height);
            int x2 = padding + (int) ((xValues[i + 1] - xMin) / (xMax - xMin) * width);
            int y2 = getHeight() - padding - (int) ((yValues[i + 1] - yMin) / (yMax - yMin) * height);
            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    private double getMin(double[] array) {
        double min = array[0];
        for (double value : array) {
            if (value < min) min = value;
        }
        return min;
    }

    private double getMax(double[] array) {
        double max = array[0];
        for (double value : array) {
            if (value > max) max = value;
        }
        return max;
    }
}