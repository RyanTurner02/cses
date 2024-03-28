import java.util.*;
import java.io.*;

public class removingdigits {
    private static final int oo = Integer.MAX_VALUE;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.valueOf(br.readLine());
            // System.out.println(solve_rec(n));
            System.out.println(solve_memo(n));
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

    public static int solve_memo(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, oo);
        memo[0] = 0;
        int ret = solve_memo(memo, n);
        // System.out.println(Arrays.toString(memo));
        return ret;
    }

    private static int solve_memo(int[] memo, int n) {
        if (n == 0) {
            return 0;
        }

        if (memo[n] != oo) {
            return memo[n];
        }

        int tempN = n;

        while (tempN != 0) {
            int getLast = tempN % 10;
            tempN /= 10;

            if (getLast == 0) {
                continue;
            }

            memo[n] = Math.min(memo[n], solve_memo(memo, n - getLast) + 1);
        }
        return memo[n];
    }
}
