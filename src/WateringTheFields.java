import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class WateringTheFields {
    static String s = "irrigation";
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
    static int[] parent, size;
    static int sum;
    static void add(edge e){
        int a = root(e.a);
        int b = root(e.b);
        if(a == b) return;
        sum+=e.l;
        parent[b] = a;
        size[a]+=size[b];
    }
    static int root(int a){
        if(parent[a] == a) return a;
        return parent[a] = root(parent[a]);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
        PriorityQueue<edge> q = new PriorityQueue<>();
        int[] x = new int[n], y = new int[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
            for(int j = 0; j<i; j++){
                if(Math.pow(x[i]-x[j], 2)+Math.pow(y[i]-y[j], 2)>=c) q.add(new edge(i, j, (int) ((int) Math.pow(x[i]-x[j], 2)+Math.pow(y[i]-y[j], 2))));
            }
        }
        parent = new int[n];
        size = new int[n];
        for(int i = 0; i<n; i++){
            parent[i] = i;
            size[i] = 1;
        }
        int ans = -1;
        while (!q.isEmpty()){
            edge e = q.poll();
            add(e);
            if(size[e.a] == n){
                ans = sum;
                break;
            }
        }
        pw.println(ans);
        pw.close();
    }
}