package com.example;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.image.BufferedImage;

public class BesselPlot3D extends Application {
    
    @Override
    public void start(Stage stage) {
        // 1. Создаем данные
        XYSeriesCollection dataset = createBesselDataset(0, 2); // Порядки от 0 до 2
        
        // 2. Создаем график
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Функции Бесселя первого рода",
                "x", 
                "J_n(x)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        
        // 3. Конвертируем в JavaFX Image
        ImageView imageView = createImageView(chart, 800, 600);
        
        // 4. Настраиваем сцену
        stage.setScene(new Scene(new StackPane(imageView), 800, 600));
        stage.setTitle("Bessel Functions Visualization");
        stage.show();
    }
    
    private XYSeriesCollection createBesselDataset(int minOrder, int maxOrder) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (int n = minOrder; n <= maxOrder; n++) {
            XYSeries series = new XYSeries("J_" + n + "(x)");
            for (double x = 0; x <= 20; x += 0.1) {
                series.add(x, BesFunc.simpson(x, 0, Math.PI, n, 1000));
            }
            dataset.addSeries(series);
        }
        return dataset;
    }
    
    private ImageView createImageView(JFreeChart chart, int width, int height) {
        BufferedImage image = chart.createBufferedImage(width, height);
        return new ImageView(SwingFXUtils.toFXImage(image, null));
    }

    public static void main(String[] args) {
        launch(args); // Стандартный запуск JavaFX
    }
}
