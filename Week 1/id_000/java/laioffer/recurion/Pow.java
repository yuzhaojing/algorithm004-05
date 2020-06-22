package laioffer.recurion;

public class Pow {

    public static void main(String[] args) {
        System.out.println(myPow(6.0, -3));
    }

    private static double myPow(double x, int y) {
        return y > 0 ? pow(x, y) : 1.0 / pow(x, y);
    }

    private static double pow(double x, int y) {
        // Base Case
        if (x == 0) return 0;
        if (y == 0) return 1;

        double half = pow(x, y / 2);
        if (y % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
