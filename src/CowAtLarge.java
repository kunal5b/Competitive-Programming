import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CowAtLarge {
    static String s = "atlarge";
    static class state{
        int dist, a;
        public state(int a1, int d1){
            a = a1;
            dist = d1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken())-1;
        ArrayList<Integer>[] adj = new ArrayList[n];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();
        for(int i = 0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1, b = Integer.parseInt(st.nextToken())-1;
            adj[a].add(b);
            adj[b].add(a);
        }
        Queue<state> bfs = new LinkedList<>();
        bfs.add(new state(k, 0));
        boolean[] vis = new boolean[n];
        int[] distB = new int[n];
        while(!bfs.isEmpty()){
            state s = bfs.poll();
            if(vis[s.a]) continue;
            vis[s.a] = true;
            distB[s.a] = s.dist;
            for(int i = 0; i<adj[s.a].size(); i++){
                bfs.add(new state(adj[s.a].get(i), s.dist+1));
            }
        }
        for(int i = 0; i<n; i++){
            if(adj[i].size() == 1){
                bfs.add(new state(i, 0));
            }
        }
        vis = new boolean[n];
        int[] distF = new int[n];
        while(!bfs.isEmpty()){
            state s = bfs.poll();
            if(vis[s.a]) continue;
            vis[s.a] = true;
            distF[s.a] = s.dist;
            for(int i = 0; i<adj[s.a].size(); i++){
                bfs.add(new state(adj[s.a].get(i), s.dist+1));
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(k);
        vis = new boolean[n];
        int ans = 0;
        while(!q.isEmpty()){
            int cur = q.poll();
            if(distF[cur]<=distB[cur]){
                ans++;
                continue;
            }
            if(vis[cur]) continue;
            vis[cur] = true;
            for(int i = 0; i<adj[cur].size(); i++){
                q.add(adj[cur].get(i));
            }
        }
        pw.println(ans);
        pw.close();
    }
}