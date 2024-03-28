import java.util.*;
import java.io.*;

public class removingdigits {
    private static final int oo = Integer.MAX_VALUE;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.valueOf(br.readLine());
            System.out.println(solve_rec(n));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int solve_rec(int n) {
        if (n == 0) {
            return 0;
        }

        int counter = oo;
        int tempN = n;

        while (tempN != 0) {
            int getLast = tempN % 10;
            tempN /= 10;

            if (getLast == 0) {
                continue;
            }

            counter = Math.min(counter, solve_rec(n - getLast) + 1);
        }
        return counter;
    }
}
