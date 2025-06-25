public class Main {
    public static void main(String[] args) {
        int n = 2;
        int steps = 1000;
        double[] xValues = new double[80];
        double[] yValues = new double[80];
        double x = 0;
        for (int i = 0; i<xValues.length;i++){
            xValues[i] = x;
            yValues[i] = Math.round(BesFunc.simpson(x, n, steps) * 1000.0) / 1000.0;
            x += 0.25;
        }
        Graph2D graph = new Graph2D(xValues, yValues);
        graph.setVisible(true);
    }
}