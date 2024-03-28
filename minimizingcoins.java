import java.util.*;
import java.io.*;

public class minimizingcoins {
    private static boolean flag;

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
            flag = false;
            System.out.println(solve_rec(c, n, x));
            System.out.println(solve_memo(c, n, x));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long solve_rec(int[] c, int n, int x) {
        if (x == 0) {
            flag = true;
            return 0;
        }

        long counter = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int diff = x - c[i];

            if (diff < 0) {
                break;
            }

            counter = Math.min(counter, solve_rec(c, n, diff) + 1);
        }

        if (flag) {
            return counter;
        } else {
            return -1;
        }
    }

    public static long solve_memo(int[] c, int n, int x) {
        long[] memo = new long[x + 1];
        Arrays.fill(memo, -1);
        long ret = solve_memo(memo, c, n, x);
        // System.out.println(Arrays.toString(memo));
        return ret;
    }

    private static long solve_memo(long[] memo, int[] c, int n, int x) {
        if (x == 0) {
            flag = true;
            return 0;
        }

        if (memo[x] != -1) {
            return memo[x];
        }

        long counter = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int diff = x - c[i];

            if (diff < 0) {
                break;
            }

            counter = Math.min(counter, solve_memo(memo, c, n, diff) + 1);
        }

        if (flag) {
            return memo[x] = counter;
        } else {
            return -1;
        }
    }
}
