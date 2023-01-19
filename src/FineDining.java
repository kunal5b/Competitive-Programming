import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
public class FineDining {
    static String s = "dining";
    static int n;
    static ArrayList<Integer>[] adj, len;
    static class state implements Comparable<state>{
        int node, dist;
        public state(int n1, int d1){
            node = n1;
            dist = d1;
        }
        public int compareTo(state s){return dist-s.dist;}
    }
    static int[] dijkstra(int start){
        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);
        PriorityQueue<state> q = new PriorityQueue<>();
        q.add(new state(start, 0));
        while(!q.isEmpty()){
            state s = q.poll();
            if(dist[s.node] != -1) continue;
            dist[s.node] = s.dist;
            for(int i = 0; i<adj[s.node].size(); i++){
                q.add(new state(adj[s.node].get(i), s.dist+len[s.node].get(i)));
            }
        }
        return dist;
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        n = scan.nextInt();
        int m = scan.nextInt(), k = scan.nextInt();
        adj = new ArrayList[n+1];
        len = new ArrayList[n+1];
        for(int i = 0; i<=n; i++){
            adj[i] = new ArrayList<>();
            len[i] = new ArrayList<>();
        }
        for(int i = 0; i<m; i++){
            int a = scan.nextInt()-1, b = scan.nextInt()-1, t = scan.nextInt();
            adj[a].add(b);
            adj[b].add(a);
            len[b].add(t);
            len[a].add(t);
        }
        int[] original = dijkstra(n-1);
        for(int i = 0; i<k; i++){
            int a = scan.nextInt()-1, b = scan.nextInt();
            adj[n].add(a);
            len[n].add(original[a]-b);
        }
        int[] newdist = dijkstra(n);
        for(int i = 0; i<n-1; i++){
            if(newdist[i]<=original[i]) pw.println(1);
            else pw.println(0);
        }
        pw.close();
    }
}