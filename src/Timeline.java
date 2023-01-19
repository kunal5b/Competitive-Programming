import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Timeline {
    static String filename = "timeline";
    static int[] dp;
    static boolean[] v;
    static int dfs(int cur){
        if(v[cur]){
            return dp[cur];
        }
        v[cur] = true;
        for(int i = 0; i<adj[cur].size(); i++){
            dp[cur] = Math.max(dp[cur], dfs(adj[cur].get(i))+len[cur].get(i));
        }
        return dp[cur];
    }
    static ArrayList<Integer>[] adj, len;
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(filename+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(filename + ".out"));
        int n = scan.nextInt(), m = scan.nextInt(), c = scan.nextInt();
        dp = new int[n];
        v = new boolean[n];
        for(int i = 0; i<n; i++) dp[i] = scan.nextInt();
        adj = new ArrayList[n];
        len = new ArrayList[n];
        int[] into = new int[n];
        for(int i = 0; i<n; i++){
            adj[i] = new ArrayList<>();
            len[i] = new ArrayList<>();
        }
        for(int i = 0; i<c; i++){
            int b = scan.nextInt()-1, a = scan.nextInt()-1, l = scan.nextInt();
            adj[a].add(b);
            len[a].add(l);
            into[b]++;
        }
        for(int i = 0; i<n; i++){
            if(into[i] == 0 && !v[i]){
                dfs(i);
            }
        }
        for(int i = 0; i<n; i++) pw.println(dp[i]);
        pw.close();
    }
}