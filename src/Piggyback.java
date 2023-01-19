import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Piggyback {
    static String s = "piggyback";
    static int n, m;
    static ArrayList<Integer>[] adj;
    static class state implements Comparable<state>{
        int a, dist;
        public state(int a1, int d1){
            a = a1;
            dist = d1;
        }
        public int compareTo(state s){
            return dist-s.dist;
        }
    }
    static int[] dij(int cost, int start){
        int[] dist = new int[n];
        PriorityQueue<state> q = new PriorityQueue<>();
        boolean[] visited = new boolean[n];
        q.add(new state(start, 0));
        while(!q.isEmpty()){
            state s = q.poll();
            if(visited[s.a]) continue;
            dist[s.a] = s.dist;
            visited[s.a] = true;
            for(int i = 0; i<adj[s.a].size(); i++){
                q.add(new state(adj[s.a].get(i), s.dist+cost));
            }
        }
        return dist;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken()), p = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1, l = Integer.parseInt(st.nextToken())-1;
            adj[a].add(l);
            adj[l].add(a);
        }
        int[] distB = dij(b, 0);
        int[] distE = dij(e, 1);
        int[] distN = dij(p, n-1);
        int min = distB[n-1]+distE[n-1];
        for(int i = 0; i<n; i++){
            min = Math.min(distB[i]+distE[i]+distN[i], min);
        }
        pw.println(min);
        pw.close();
    }
}