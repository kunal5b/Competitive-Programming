import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ClosingTheFarmGold {
    static String s = "closing";
    static int[] parent, size;
    static void add(int a, int b){
        a = root(a);
        b = root(b);
        if(a == b) return;
        if(size[a]>size[b]) {
            parent[b] = a;
            size[a] += size[b];
        }
        else{
            parent[a] = b;
            size[b] += size[a];
        }
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
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] adj = new ArrayList[n];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();
        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1, b = Integer.parseInt(st.nextToken()) - 1;
            adj[a].add(b);
            adj[b].add(a);
        }
        int[] order = new int[n];
        parent = new int[n];
        size = new int[n];
        boolean[] open = new boolean[n];
        for(int i = 0; i<n; i++){
            parent[i] = i;
            size[i] = 1;
        }
        for(int i = 0; i<n; i++){
            order[i] = Integer.parseInt(br.readLine()) - 1;
        }
        ArrayList<String> ans = new ArrayList<>();
        for(int i = n-1; i>=0; i--){
            open[order[i]] = true;
            for(int j: adj[order[i]]){
                if(open[j]){
                    add(order[i], j);
                }
            }
            if(size[root(order[i])] == n-i) ans.add("YES");
            else ans.add("NO");
        }
        Collections.reverse(ans);
        for(String s: ans){
            pw.println(s);
        }
        pw.close();
    }
}