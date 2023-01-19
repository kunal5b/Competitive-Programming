import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
public class Snakes {
    static String s = "snakes";
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = scan.nextInt(), k = scan.nextInt(), max = 0, sum = 0;
        int[] snakes = new int[n+1];
        for(int i = 1; i<=n; i++){
            snakes[i] = scan.nextInt();
            sum+=snakes[i];
        }
        int[][] dp = new int[n+1][k+1];
        for(int i = 0; i<=n; i++) Arrays.fill(dp[i], 1000000000);
        /*
        dp[i][j] is the minimum sum of nets needed to capture the first i groups with j changes.
        Transitions:
        a from 0 to i: dp[a][j-1]+max(from range a to i)
        Base Case:
         */
        for(int i = 1; i<=n; i++){
            max = Math.max(max, snakes[i]);
            dp[i][0] = max*i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                int m = snakes[i];
                for (int a = i - 1; a >= 0; a--) {
                    dp[i][j] = Math.min(dp[a][j - 1] + (m * (i - a)), dp[i][j]);
                    m = Math.max(snakes[a], m);
                }
            }
        }
        pw.println(dp[n][k]-sum);
        pw.close();
    }
}