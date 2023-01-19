import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Superbull {
    static String s = "superbull";
    static int[] parent, size;
    static long ans;
    static class edge implements Comparable<edge>{
        int a, b, l;
        public edge(int a1, int b1, int l1){
            a = a1;
            b = b1;
            l = l1;
        }
        public int compareTo(edge e){
            return e.l-l;
        }
    }
    static void add(edge e){
        int a = root(e.a), b = root(e.b);
        if(a == b) return;
        parent[b] = a;
        size[a]+=size[b];
        ans+=e.l;
    }
    static int root(int a){
        if(parent[a] == a) return a;
        return parent[a] = root(parent[a]);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = Integer.parseInt(br.readLine());
        int[] id = new int[n];
        PriorityQueue<edge> q = new PriorityQueue<>();
        for(int i = 0; i<n; i++){
            id[i] = Integer.parseInt(br.readLine());
            for(int j = 0; j<i; j++){
                q.add(new edge(i, j, id[i]^id[j]));
            }
        }
        parent = new int[n];
        size = new int[n];
        for(int i = 0; i<n; i++){
            parent[i] = i;
            size[i] = 1;
        }
        while(!q.isEmpty()){
            edge e = q.poll();
            add(e);
            if(size[root(e.a)] == n){
                pw.println(ans);
                break;
            }
        }
        pw.close();
    }
}