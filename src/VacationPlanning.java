import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
public class VacationPlanning {
    static String s = "vacation";
    static int n;
    static class state implements Comparable<state>{
        int cur, d;
        public state(int c1, int dist){
            cur = c1;
            d = dist;
        }
        public int compareTo(state s){return d-s.d;}
    }
    static ArrayList<Integer>[] adj, len;
    static int[] dij(int start){
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<state> q = new PriorityQueue<>();
        q.add(new state(start, 0));
        while(!q.isEmpty()){
            state s = q.poll();
            if(dist[s.cur]!=Integer.MAX_VALUE) continue;
            dist[s.cur] = s.d;
            for(int i = 0; i<adj[s.cur].size(); i++){
                q.add(new state(adj[s.cur].get(i), s.d+len[s.cur].get(i)));
            }
        }
        return dist;
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        n = scan.nextInt();
        int m = scan.nextInt(), k = scan.nextInt(), q = scan.nextInt();
        adj = new ArrayList[n];
        len = new ArrayList[n];
        for(int i = 0; i<n; i++){
            adj[i] = new ArrayList<>();
            len[i] = new ArrayList<>();
        }
        for(int i = 0; i<m; i++){
            int u = scan.nextInt()-1, v = scan.nextInt()-1, d = scan.nextInt();
            adj[u].add(v);
            len[u].add(d);
        }
        int[][] dist = new int[n][n];
        for(int i = 0; i<n; i++) dist[i] = dij(i);
        long ans = 0, cnt = 0;
        for(int i = 0; i<q; i++){
            int a = scan.nextInt()-1, b = scan.nextInt()-1, min = Integer.MAX_VALUE;
            for(int j = 0; j<k; j++){
                if(dist[a][j]!=Integer.MAX_VALUE && dist[j][b]!=Integer.MAX_VALUE){
                    min = Math.min(min, dist[a][j]+dist[j][b]);
                }
            }
            if(min!=Integer.MAX_VALUE){
                cnt++;
                ans+=min;
            }
        }
        pw.println(cnt + "\n" + ans);
        pw.close();
    }
}