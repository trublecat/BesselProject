import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Graph3D extends Application {
    private static double[] xData;
    private static double[] yData;
    private static double[] zData;

    public static void setData(double[] x, double[] y, double[] z) {
        xData = x;
        yData = y;
        zData = z;
    }

    @Override
    public void start(Stage primaryStage) {
        // Создаем группу для 3D сцены
        Group root = new Group();

        // Создаем сферы для каждой точки данных
        for (int i = 0; i < xData.length; i++) {
            Sphere sphere = new Sphere(0.2);
            sphere.setTranslateX(xData[i]);
            sphere.setTranslateY(yData[i]);
            sphere.setTranslateZ(zData[i]);

            // Цвет в зависимости от Z
            double hue = zData[i] / (getMaxValue(zData) + 1);
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(Color.hsb(hue * 360, 1.0, 1.0));
            sphere.setMaterial(material);

            root.getChildren().add(sphere);
        }

        // Создаем камеру
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-20);
        camera.setTranslateY(-5);
        camera.setRotationAxis(Rotate.X_AXIS);
        camera.setRotate(-30);

        // Создаем сцену
        Scene scene = new Scene(root, 800, 600, true);
        scene.setFill(Color.LIGHTGRAY);
        scene.setCamera(camera);

        // Добавляем обработчики для вращения сцены
        initMouseControl(root, scene, primaryStage);

        primaryStage.setTitle("3D Graph Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double getMaxValue(double[] array) {
        double max = Double.MIN_VALUE;
        for (double value : array) {
            if (value > max) max = value;
        }
        return max;
    }

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
    private final Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);

    private void initMouseControl(Group root, Scene scene, Stage stage) {
        root.getTransforms().addAll(xRotate, yRotate);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = xRotate.getAngle();
            anchorAngleY = yRotate.getAngle();
        });

        scene.setOnMouseDragged(event -> {
            xRotate.setAngle(anchorAngleX - (anchorY - event.getSceneY()));
            yRotate.setAngle(anchorAngleY + anchorX - event.getSceneX());
        });

        stage.addEventHandler(javafx.scene.input.ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            root.translateZProperty().set(root.getTranslateZ() + delta);
        });
    }
}