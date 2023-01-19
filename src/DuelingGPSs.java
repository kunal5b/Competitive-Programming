import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DuelingGPSs {
    static class pair implements Comparable<pair>{
        int a, b;
        public pair(int a1, int b1){
            a = a1;
            b = b1;
        }
        public int compareTo(pair p){
            return a-p.a;
        }
    }
    static String s = "gpsduel";
    static int n, m;
    static int[] dist;
    static int[] path(ArrayList<pair>[] adj, int s){
        dist = new int[n];
        PriorityQueue<pair> pq = new PriorityQueue<>();
        pq.add(new pair(0, s));
        boolean[] visited = new boolean[n];
        while (!pq.isEmpty()){
            pair p = pq.poll();
            if(visited[p.b]) continue;
            visited[p.b] = true;
            dist[p.b] = p.a;
            for(int i = 0; i<adj[p.b].size(); i++){
                pq.add(new pair(adj[p.b].get(i).b+p.a, adj[p.b].get(i).a));
            }
        }
        return dist;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ArrayList<pair>[] gps1 = new ArrayList[n];
        ArrayList<pair>[] gps2 = new ArrayList[n];
        ArrayList<pair>[] fin = new ArrayList[n];
        for(int i = 0; i<n; i++){
            gps1[i] = new ArrayList<>();
            gps2[i] = new ArrayList<>();
            fin[i] = new ArrayList<>();
        }
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1, b = Integer.parseInt(st.nextToken())-1, p = Integer.parseInt(st.nextToken()), q = Integer.parseInt(st.nextToken());
            gps1[b].add(new pair(a, p));
            gps2[b].add(new pair(a, q));
        }
        int[] arr = path(gps1, n-1);
        int[] arr2 = path(gps2, n-1);
        for(int i = 0; i<n; i++){
            for(int j = 0; j<gps1[i].size(); j++){
                int c = 0;
                if(arr[gps1[i].get(j).a]-arr[i]!=gps1[i].get(j).b) c++;
                if(arr2[gps1[i].get(j).a]-arr2[i]!=gps2[i].get(j).b) c++;
                fin[gps1[i].get(j).a].add(new pair(i, c));
            }
        }
        pw.println(path(fin, 0)[n-1]);
        pw.close();
    }
}