import java.util.*;
import java.io.*;

public class minimizingcoins {
    private static final int oo = Integer.MAX_VALUE;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] nx = br.readLine().split(" ");
            String[] cS = br.readLine().split(" ");
            int n = Integer.valueOf(nx[0]);
            int x = Integer.valueOf(nx[1]);
            int[] c = new int[n];
            for (int i = 0; i < n; i++)
                c[i] = Integer.valueOf(cS[i]);

            // long sol = solve_rec(c, x);
            // long sol = solve_memo(c, x);
            long sol = solve_dp(c, x);

            if (sol == oo) {
                sol = -1;
            }

            System.out.println(sol);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long solve_rec(int[] c, int x) {
        if (x == 0) {
            return 0;
        }

        long counter = oo;

        for (int i = 0; i < c.length; i++) {
            int diff = x - c[i];

            if (diff < 0) {
                continue;
            }

            counter = Math.min(counter, solve_rec(c, diff) + 1);
        }
        return counter;
    }

    public static long solve_memo(int[] c, int x) {
        long[] memo = new long[x + 1];
        Arrays.fill(memo, oo);
        long ret = solve_memo(memo, c, x);
        // System.out.println(Arrays.toString(memo));
        return ret;
    }

    private static long solve_memo(long[] memo, int[] c, int x) {
        if (x == 0) {
            return 0;
        }

        if (memo[x] != oo) {
            return memo[x];
        }

        for (int i = 0; i < c.length; i++) {
            int diff = x - c[i];

            if (diff < 0) {
                continue;
            }

            memo[x] = Math.min(memo[x], solve_memo(memo, c, diff) + 1);
        }
        return memo[x];
    }

    public static long solve_dp(int[] c, int x) {
        long[] dp = new long[x + 1];
        Arrays.fill(dp, oo);
        dp[0] = 0; // base case: sum of 0 requires 0 coins
        long ret = solve_dp(dp, c, x);
        // System.out.println(Arrays.toString(dp));
        return ret;
    }

    private static long solve_dp(long[] dp, int[] c, int x) {
        for (int i = 1; i <= x; i++) {
            for (int j = 0; j < c.length; j++) {
                int diff = i - c[j];

                if (diff < 0) {
                    continue;
                }

                dp[i] = Math.min(dp[i], dp[diff] + 1);
            }
        }
        return dp[x];
    }
}
