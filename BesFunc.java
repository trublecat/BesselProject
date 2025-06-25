public class BesFunc {
    public static double besselIntegrand(double x, double tau, int n) {
        return Math.cos(n * tau - x * Math.sin(tau)) / Math.PI;
    }

    public static double simpson(double x, double a, double b, int n, int steps) {
        double h = (b - a) / steps;
        double sum = besselIntegrand(x, a, n) + besselIntegrand(x, b, n);

        for (int i = 1; i < steps; i++) {
            double tau = a + i * h;
            sum += besselIntegrand(x, tau, n) * (i % 2 == 0 ? 2 : 4);
        }

        return sum * h / 3;
    }
}