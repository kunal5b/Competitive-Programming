import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class WorldPeaceIOIPractice {
    static class pair{
        int a, b;
        public pair(int a1, int b1){
            a = a1;
            b = b1;
        }
    }
    static class edge implements Comparable<edge>{
        int a, b, l;
        public edge(int a1, int b1, int l1){
            a = a1;
            b = b1;
            l = l1;
        }
        public int compareTo(edge e){
            return l-e.l;
        }
    }
    static int root(int a){
        if(parent[a] == a) return a;
        return parent[a] = root(parent[a]);
    }
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        PriorityQueue<edge> q = new PriorityQueue<>();
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            q.add(new edge(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
        }
        int queries = Integer.parseInt(br.readLine());
        pair[] p = new pair[queries];
        parent = new int[n];
        for(int i = 0; i<n; i++) parent[i] = i;
        for(int i = 0; i<queries; i++){
            st = new StringTokenizer(br.readLine());
            p[i] = new pair(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
        }
        int idx = 0, ans = 0;
        while (!q.isEmpty()){
            edge e = q.poll();
            parent[root(e.a)] = root(e.b);
            if(parent[root(p[idx].a)] == parent[root(p[idx].b)]){
                ans = e.l;
                idx++;
            }
            if(idx == queries) break;
        }
        if(idx == queries) System.out.println(ans);
        else System.out.println("MISSION IMPOSSIBLE");
    }
}