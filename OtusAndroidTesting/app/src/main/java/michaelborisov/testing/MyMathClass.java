package michaelborisov.testing;

public class MyMathClass {

    public int sum(int a, int b) {
        return a + b;
    }

    public double devise(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException();
        }

        return a / b;
    }
}
