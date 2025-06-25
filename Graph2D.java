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
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int padding = 50;
        int width = getWidth() - 2 * padding;
        int height = getHeight() - 2 * padding;

        // Рисуем оси
        g2d.setColor(Color.BLACK);
        g2d.drawLine(padding, getHeight() - padding, getWidth() - padding, getHeight() - padding); // Ось X
        g2d.drawLine(padding, getHeight() - padding, padding, padding); // Ось Y

        // Подписи осей
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString("X", getWidth() - padding - 15, getHeight() - padding + 25);
        g2d.drawString("Jn(X)", padding - 40, padding - 10);

        double xMin = Math.floor(getMin(xValues)/0.5)*0.5;
        double xMax = Math.ceil(getMax(xValues)/0.5)*0.5;
        double yMin = Math.floor(getMin(yValues)/0.2)*0.2;
        double yMax = Math.ceil(getMax(yValues)/0.2)*0.2;

        // Деления и подписи на оси X с шагом 0.5
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        for (double x = xMin; x <= xMax; x += 0.5) {
            int xPos = padding + (int)((x - xMin) / (xMax - xMin) * width);
            // Деления
            g2d.drawLine(xPos, getHeight() - padding - 5, xPos, getHeight() - padding + 5);
            // Подписи (только целые и половины)
            if (x % 1 == 0 || x % 1 == 0.5) {
                String label = x % 1 == 0 ? String.format("%.0f", x) : String.format("%.1f", x);
                g2d.drawString(label, xPos - 10, getHeight() - padding + 20);
            }
        }

        // Деления и подписи на оси Y с шагом 0.2
        for (double y = yMin; y <= yMax; y += 0.2) {
            int yPos = getHeight() - padding - (int)((y - yMin) / (yMax - yMin) * height);
            // Деления
            g2d.drawLine(padding - 5, yPos, padding + 5, yPos);
            // Подписи (только каждое 0.5)
            if (Math.abs(y % 0.2) < 0.001) {
                String label = y % 1 == 0 ? String.format("%.0f", y) : String.format("%.1f", y);
                g2d.drawString(label, padding - 35, yPos + 5);
            }
        }

        if (yMin <= 0 && yMax >= 0) {
            int yZeroPos = getHeight() - padding - (int)((0 - yMin) / (yMax - yMin) * height);
            g2d.setColor(new Color(150, 150, 150));
            g2d.drawLine(padding, yZeroPos, getWidth() - padding, yZeroPos);
        }

        if (xMin <= 0 && xMax >= 0) {
            int xZeroPos = padding + (int)((0 - xMin) / (xMax - xMin) * width);
            g2d.setColor(new Color(150, 150, 150));
            g2d.drawLine(xZeroPos, padding, xZeroPos, getHeight() - padding);
        }

        g2d.setColor(new Color(0, 100, 200));
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < xValues.length - 1; i++) {
            int x1 = padding + (int)((xValues[i] - xMin) / (xMax - xMin) * width);
            int y1 = getHeight() - padding - (int)((yValues[i] - yMin) / (yMax - yMin) * height);
            int x2 = padding + (int)((xValues[i + 1] - xMin) / (xMax - xMin) * width);
            int y2 = getHeight() - padding - (int)((yValues[i + 1] - yMin) / (yMax - yMin) * height);
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