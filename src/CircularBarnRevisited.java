import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class CircularBarnRevisited {
    static String s = "cbarn2";
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = scan.nextInt(), k = scan.nextInt();
        int[] r = new int[n];
        for(int i = 0; i<n; i++) r[i] = scan.nextInt();
        long ans = Long.MAX_VALUE;
        for(int i = 0; i<n; i++){
            long[][] dp = new long[k+1][n+1];
            for(int l = 0; l<=k; l++) for(int j = 0; j<=n; j++) dp[l][j] = Integer.MAX_VALUE;
            dp[0][n] = 0;
            for(int l = 1; l<=k; l++){
                for(int j = 0; j<n; j++){
                    long cur = 0;
                    for(int a = j+1; a<=n; a++){
                        cur+=r[a-1]*(a-j-1);
                        dp[l][j] = Math.min(dp[l][j], cur+dp[l-1][a]);
                    }
                }
            }
            ans = Math.min(ans, dp[k][0]);
            int[] ne = new int[n];
            for(int l = 0; l<n; l++) ne[l] = r[(l+1)%n];
            r = ne;
        }
        pw.println(ans);
        pw.close();
    }
}