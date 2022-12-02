import java.util.Scanner;

public class Scan {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double sum = 0;
        while (true) {
            String value = scan.next();
            if(value.equals("exit")) break;
            if(value.matches("\\d+\\.\\d+") || value.matches("\\.\\d+") || value.matches("\\d+\\."))
                sum += Double.parseDouble(value);
        }
        System.out.printf("%.6f %n", sum);
    }
}
