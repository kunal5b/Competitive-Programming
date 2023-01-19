import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class BarnPainting {
    static String s = "barnpainting";
    static ArrayList<Integer>[] adj;
    static int[] color;
    static long[][] dp;
    static long solve(int cur, int cc, int par, int pc) {
        if ((color[cur] >= 0 && cc != color[cur]) || cc == pc){
            return 0;
        }
        if (dp[cur][cc] >= 0) return dp[cur][cc];
        dp[cur][cc] = 1;
        for (int i : adj[cur]) {
            if (i == par) continue;
            long cnt = 0;
            for (int c = 0; c < 3; c++) {
                cnt = mod((cnt + solve(i, c, cur, cc)));
            }
            dp[cur][cc] = mod(dp[cur][cc]*cnt);
        }
        return dp[cur][cc];
    }

    static long mod(long a){
        return a%1000000007;
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = scan.nextInt(), k = scan.nextInt();
        adj = new ArrayList[n];
        color = new int[n];
        dp = new long[n][3];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();
        for(int i = 0; i<n-1; i++){
            int a = scan.nextInt()-1, b = scan.nextInt()-1;
            adj[a].add(b);
            adj[b].add(a);
        }
        Arrays.fill(color, -1);
        for(int i = 0; i<n; i++) Arrays.fill(dp[i], -1);
        for(int i = 0; i<k; i++) color[scan.nextInt()-1] = scan.nextInt()-1;
        long ans = 0;
        for(int i = 0; i<3; i++){
            ans = mod(ans+solve(0, i, -1, -1));
        }
        pw.println(ans);
        pw.close();
    }
}