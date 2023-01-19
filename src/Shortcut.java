import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
public class Shortcut {
    static String s = "shortcut";
    static int[] parent, cnt, cows, dist;
    static void update(int i, int o){
        if(i == -1){
            return;
        }
        cnt[i]+=cows[o];
        update(parent[i], o);
    }
    static class state implements Comparable<state>{
        int par, d, cur;
        public state(int p1, int cur1, int d1){
            par = p1;
            d = d1;
            cur = cur1;
        }
        public int compareTo(state s) {
            return s.d!=d ? d-s.d : par-s.par;
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = scan.nextInt(), m = scan.nextInt(), t = scan.nextInt();
        cows = new int[n];
        dist = new int[n];
        parent = new int[n];
        cnt = new int[n];
        ArrayList<Integer>[] adj = new ArrayList[n], len = new ArrayList[n];
        for(int i = 0; i<n; i++){
            adj[i] = new ArrayList<>();
            len[i] = new ArrayList<>();
        }
        for(int i = 0; i<n; i++) cows[i] = scan.nextInt();
        for(int i = 0; i<m; i++){
            int a = scan.nextInt()-1, b = scan.nextInt()-1, d = scan.nextInt();
            adj[a].add(b);
            adj[b].add(a);
            len[a].add(d);
            len[b].add(d);
        }
        Arrays.fill(parent, -1);
        PriorityQueue<state> q = new PriorityQueue<>();
        Arrays.fill(dist, -1);
        q.add(new state(-1, 0, 0));
        while(!q.isEmpty()){
            state s = q.poll();
            if(dist[s.cur]!=-1) continue;
            dist[s.cur] = s.d;
            parent[s.cur] = s.par;
            for(int i = 0; i<len[s.cur].size(); i++){
                if(len[s.cur].get(i) > 0) q.add(new state(s.cur, adj[s.cur].get(i), s.d+len[s.cur].get(i)));
            }
        }
        for(int i = 0; i<n; i++){
            update(i, i);
        }
        long ans = 0;
        for(int i = 0; i<n; i++){
            if(dist[i]>t){
                ans = Math.max(ans, (long) (dist[i] - t) *cnt[i]);
            }
        }
        pw.println(ans);
        pw.close();
    }
}