import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MooTubeGold {
    static String s = "mootube";
    static class triple implements Comparable<triple>{
        int p, q, r;
        public triple(int p1, int q1, int r1){
            p = p1;
            q = q1;
            r = r1;
        }
        public int compareTo(triple c){
            return c.r-r;
        }
    }
    static int[] parent, size;
    static void add(int a, int b){
        size[root(b)]+=size[root(a)];
        parent[root(a)] = root(b);
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
        int n = Integer.parseInt(st.nextToken()), q = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        size = new int[n];
        triple[] rel = new triple[n-1];
        for(int i = 0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            rel[i] = new triple(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(rel);
        triple[] queries = new triple[q];
        for(int i = 0; i<q; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken())-1;
            queries[i] = new triple(i, b, a);
        }
        Arrays.sort(queries);
        for(int i = 0; i<n; i++){
            parent[i] = i;
            size[i] = 1;
        }
        int[] ans = new int[q];
        int ind = 0;
        for(int i = 0; i<q; i++){
            while(ind<n-1 && queries[i].r<=rel[ind].r){
                add(rel[ind].p, rel[ind].q);
                ind++;
            }
            ans[queries[i].p] = size[root(queries[i].q)]-1;
        }
        for(int i = 0; i<q; i++){
            pw.println(ans[i]);
        }
        pw.close();
    }
}