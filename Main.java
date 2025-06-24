import java.util.ArrayList;
import java.util.List;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        int steps = 100; //кол-во шагов для метода Симпсона (чем больше, тем точнее)
        int n = 1; // порядок, который задаёт пользователь
        int x = 1;
        System.out.println(BesFunc.simpson(x,0,Math.PI,n,steps));
    }
}