import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
public class BessiesBirthdayBuffet {
    static String s = "buffet";
    static ArrayList<Integer>[] adj;
    static class pair implements Comparable<pair>{
        int a, b;
        public pair(int a1, int b1){
            a = a1;
            b = b1;
        }
        public int compareTo(pair p){return b-p.b;}
    }
    static int n, e;
    static int[] dij(int start){
         int[] dist = new int[n];
         Arrays.fill(dist, Integer.MAX_VALUE);
         PriorityQueue<pair> q = new PriorityQueue<>();
         q.add(new pair(start, 0));
         while(!q.isEmpty()){
             pair p = q.poll();
             if(dist[p.a] != Integer.MAX_VALUE) continue;
             dist[p.a] = p.b;
             for(int i = 0; i<adj[p.a].size(); i++){
                 q.add(new pair(adj[p.a].get(i), p.b+e));
             }
         }
         return dist;
    }
    public static void main(String[] args) throws IOException {
        // Scanner scan = new Scanner(new File(s+".in"));
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        n = scan.nextInt();
        e = scan.nextInt();
        pair[] quality = new pair[n];
        adj = new ArrayList[n+1];
        for(int i = 0; i<n; i++){
            quality[i] = new pair(i, scan.nextInt());
            int d = scan.nextInt();
            adj[i] = new ArrayList<>();
            for(int j = 0; j<d; j++){
                adj[i].add(scan.nextInt()-1);
            }
           //  System.out.println(adj[i]);
        }
        int[] dp = new int[n];
        int ans = 0;
        Arrays.sort(quality);
        Arrays.fill(dp, -1);
        for(int i = 0; i<n; i++){
            int[] dist = dij(quality[i].a);
            int cur = quality[quality[i].a].b;
            for(int j = 0; j<n; j++){
                if(dist[j]!=Integer.MAX_VALUE) cur = Math.max(cur,  dp[j]+quality[quality[i].a].b-dist[j]);
            }
            dp[quality[i].a] = cur;
            ans = Math.max(ans, cur);
        }
        System.out.println(ans);
        pw.close();
    }
}