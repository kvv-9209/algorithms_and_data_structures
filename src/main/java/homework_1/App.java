package homework_1;

public class App {

    public static void main(String[] args) {
        sum(5);

    }

    public static int exponentiation(int value, int degree) {
        int result = value;
        if (degree == 0) {
            result = 1;
        } else {
            for (int i = 0; i < degree - 1; i++) {
                result *= value;
            }
        }
        return result;
    }

    public static int exponentiationParity(int value, int degree) {
        int result = 1;
        while (degree > 0) {
            if (degree % 2 == 1) {
                result *= value;
                --degree;
            }
            value *= value;
            degree /= 2;
        }
        return result;
    }

    public static int sum(int value) {
        int result = 0;
        for (int i = 0; i < value + 1; i++) {
            result += i;
        }
        return result;
    }
}

