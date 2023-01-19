import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class MeetingTimeSilver {
    static String s = "meeting";
    static ArrayList<Integer>[] adj;
    static int n;
    static boolean[] solve(ArrayList<Integer>[] len){
        boolean[][] dp = new boolean[105][10005];
        dp[0][0] = true;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<dp[i].length; j++){
                if(dp[i][j]) {
                    for(int cur = 0; cur<len[i].size(); cur++){
                        dp[adj[i].get(cur)][j+len[i].get(cur)] = true;
                    }
                }
            }
        }
        return dp[n-1];
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        n = scan.nextInt();
        int m = scan.nextInt();
        ArrayList<Integer>[] badj = new ArrayList[n], eadj = new ArrayList[n];
        adj = new ArrayList[n];
        for(int i = 0; i<n; i++) {
            adj[i] = new ArrayList<>();
            badj[i] = new ArrayList<>();
            eadj[i] = new ArrayList<>();
        }
        for(int i = 0; i<m; i++){
            int a = scan.nextInt()-1, b = scan.nextInt()-1, c = scan.nextInt(), d = scan.nextInt();
            adj[a].add(b);
            badj[a].add(c);
            eadj[a].add(d);
        }
        boolean[] bessie = solve(badj);
        boolean[] elsie = solve(eadj);
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i<bessie.length; i++){
            if(bessie[i] && elsie[i]){
                ans = i;
                break;
            }
        }
        pw.println(ans != Integer.MAX_VALUE ? ans : "IMPOSSIBLE");
        pw.close();
    }
}