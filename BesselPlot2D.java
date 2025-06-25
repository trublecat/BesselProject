package com.example;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.Scanner;

public class BesselPlot2D{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите порядок функции Бесселя (n): ");
        int n = scanner.nextInt();

        XYSeries series = new XYSeries("J_" + n + "(x)");

        double start = 0.0;
        double end = 20.0;
        int points = 200;

        for (int i = 0; i <= points; i++) {
            double x = start + (end - start) * i / points;
            double y = BesFunc.simpson(x, 0, Math.PI, n, 1000);
            series.add(x, y);
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Функция Бесселя первого рода J_" + n + "(x)",
                "x",
                "J_" + n + "(x)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartFrame frame = new ChartFrame("Bessel Function J_" + n, chart);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
