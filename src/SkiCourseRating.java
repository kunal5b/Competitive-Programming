 import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;
 import java.util.Queue;
 import java.util.StringTokenizer;

public class SkiCourseRating {
    static String s = "skilevel";
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
    static void add(int a, int b){
        a = root(a);
        b = root(b);
        size[a]+=size[b];
        parent[b] = a;
        start[a]+=start[b];
    }
    static class point{
        int x, y;
        public point(int x1, int y1){
            x = x1;
            y = y1;
        }
    }
    static int[] size, parent, start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), cur = 0, t = Integer.parseInt(st.nextToken());
        int[][] mat = new int[n][m];
        int[][] ind = new int[n][m];
        PriorityQueue<edge> q = new PriorityQueue<>();
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                mat[i][j] = Integer.parseInt(st.nextToken());
                ind[i][j] = cur;
                if(j-1>=0) q.add(new edge(cur, ind[i][j-1], Math.abs(mat[i][j]-mat[i][j-1])));
                if(i-1>=0) q.add(new edge(cur, ind[i-1][j], Math.abs(mat[i][j]-mat[i-1][j])));
                cur++;
            }
        }
        size = new int[cur];
        parent = new int[cur];
        start = new int[cur];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                int c = Integer.parseInt(st.nextToken());
                if(c == 1) start[ind[i][j]] = 1;
            }
        }
        long ans = 0;
        for(int i = 0; i<cur; i++){
            parent[i] = i;
            size[i] = 1;
        }
        while(!q.isEmpty()){
            edge e = q.poll();
            int a = root(e.a), b = root(e.b);
            if(a == b) continue;
            if(size[a]+size[b]>=t){
                if(size[a]<t){
                    ans+=((long) start[a] * e.l);
                }
                if(size[b]<t){
                    ans+=((long) start[b] * e.l);
                }
            }
            add(a, b);
        }
        pw.println(ans);
        pw.close();
    }
}