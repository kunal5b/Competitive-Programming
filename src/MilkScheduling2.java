import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class MilkScheduling2 {
    static String s = "msched";
    static ArrayList<Integer>[] adj;
    static int[] dp, t;
    static int n;
    static int solve(int x){
        if(dp[x]>0) return dp[x];
        dp[x] = t[x];
        for(int i = 0; i<adj[x].size(); i++){
            solve(adj[x].get(i));
            dp[x] = Math.max(dp[x], dp[adj[x].get(i)]+t[x]);
        }
        return dp[x];
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        n = scan.nextInt();
        int m = scan.nextInt();
        dp = new int[n];
        t = new int[n];
        adj = new ArrayList[n];
        for(int i = 0; i<n; i++){
            t[i] = scan.nextInt();
            adj[i] = new ArrayList<>();
        }
        int[] con = new int[n];
        for(int i = 0; i<m; i++){
            int a = scan.nextInt()-1,  b = scan.nextInt()-1;
            adj[b].add(a);
            con[a]++;
        }
        int ans = 0;
        for(int i = 0; i<n; i++){
            if(con[i] == 0){
                ans = Math.max(solve(i), ans);
            }
        }
        pw.println(ans);
        pw.close();
    }
}