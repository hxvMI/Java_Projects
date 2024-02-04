package prog03;

public class LinearFib extends Fib{

    public double fib(int n) {
        double a = 0;           //is always 1 instance behind b
        double b = 1;

        for (int i = 2; i <= n; i++) {
            double temp = a+b;  // stores new a+b value
            a = b;              // stores old b value
            b = temp;           // updates b with new value
        }

        return b;
    }

    @Override
    public double O(int n) {
        return n;
    }


}
