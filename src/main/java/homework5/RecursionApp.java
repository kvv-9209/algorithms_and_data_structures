package homework5;

public class RecursionApp {

    public static int power (int value, int pow) {
        if (pow == 1){
            return value;
        } else {
            return value * power(value, pow - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(power (2, 10));;
    }
}
