import java.awt.font.ShapeGraphicAttribute;
import java.io.*;
import java.util.*;

public class FencedInGold {
    static String s = "fencedin";
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
    static long ans = 0;
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int[] v = new int[n+1], h = new int[m+1];
        for(int i = 0; i<n; i++) v[i] = Integer.parseInt(br.readLine());
        for(int i = 0; i<m; i++) h[i] = Integer.parseInt(br.readLine());
        Arrays.sort(v);
        Arrays.sort(h);
        for(int i = 0; i<n; i++){
            v[i] = v[i+1]-v[i];
        }
        v[n] = a-v[n];
        for(int i = 0; i<m; i++){
            h[i] = h[i+1]-h[i];
        }
        h[m] = b-h[m];
        PriorityQueue<edge> q = new PriorityQueue<>();
        int[][] mat = new int[n+1][m+1];
        int c = 0;
        for(int i = 0; i<n+1; i++){
            for(int j = 0; j<m+1; j++){
                mat[i][j] = c;
                c++;
            }
        }
        parent = new int[c];
        size = new int[c];
        for(int i = 0; i<n+1; i++){
            for(int j = 0; j<m+1; j++){
                if(i>0){
                    q.add(new edge(mat[i][j], mat[i-1][j], h[j]));
                }
                if(j>0){
                    q.add(new edge(mat[i][j], mat[i][j-1], v[i]));
                }
            }
        }
        for(int i = 0; i<c; i++){
            parent[i] = i;
            size[i] = 1;
        }
        while(!q.isEmpty()){
            edge e = q.poll();
            add(e);
            if(size[root(e.a)] == c){
                pw.println(ans);
                break;
            }
        }
        pw.close();
    }
}