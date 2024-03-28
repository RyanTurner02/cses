import java.util.*;
import java.io.*;

public class dicecombinations {
    private static final int MOD = (int) 1e9 + 7;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.valueOf(br.readLine());
            // System.out.println(solve_rec1(n));
            // System.out.println(solve_rec2(0, n));
            System.out.println(solve_memo1(n));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int solve_rec1(int n) {
        if (n == 0) {
            return 1;
        }

        int counter = 0;

        for (int i = 1; i <= 6; i++) {
            if (n - i < 0) {
                break;
            }
            counter += solve_rec1(n - i) % MOD;
        }
        return counter;
    }

    public static int solve_rec2(int num, int n) {
        if (num == n) {
            return 1;
        }

        int counter = 0;

        for (int i = 1; i <= 6; i++) {
            if (num + i > n) {
                break;
            }
            counter += solve_rec2(num + i, n) % MOD;
        }
        return counter;
    }

    public static long solve_memo1(int n) {
        long[] memo = new long[n + 1];
        Arrays.fill(memo, -1);

        long ret = solve_memo1(memo, n);
        // System.out.println(Arrays.toString(memo));
        return ret;
    }

    private static long solve_memo1(long[] memo, int n) {
        if (n == 0) {
            return 1;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        long counter = 0;

        for (int i = 1; i <= 6; i++) {
            if (n - i < 0) {
                break;
            }
            counter = (counter + solve_memo1(memo, n - i)) % MOD;
        }
        return memo[n] = counter;
    }
}
