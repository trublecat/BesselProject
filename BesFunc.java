public class BesFunc {
    private static double besselIntegrand(double x, double tau, int n) { // подынтегральная функция
        return Math.cos(n * tau - x * Math.sin(tau)) / Math.PI;
    }
    public static double simpson(double x, int n, int steps) { // вычисление интеграла методом Симпсона
        double h = (Math.PI - 0) / steps;
        double sum = besselIntegrand(x, 0, n) + besselIntegrand(x, Math.PI, n);
        for (int i = 1; i < steps; i++) {
            double tau = 0 + i * h;
            sum += besselIntegrand(x, tau, n) * (i % 2 == 0 ? 2 : 4);
        }
        return sum * h / 3;
    }
    /*
                          / \
                         | |
                         | (о)(о)
                         С .---_)
                          | |.___|
                          | \__/
                          /_____\
                         /_____/ \
                        / \
     */
}