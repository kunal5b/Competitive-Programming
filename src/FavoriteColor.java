import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class FavoriteColor {
    static String s = "fcolor";
    static ArrayList<Integer>[] adj, adj2;
    static int[] parent;
    static Queue<Integer> q = new LinkedList<>();
    static void combine(int a, int b) {
        a = root(a);
        b = root(b);
        if (a == b) return;
        if (adj2[b].size() > adj2[a].size()) {
            int t = a;
            a = b;
            b = t;
        }
        for (int i : adj2[b]) {parent[i] = a; adj2[a].add(i);}
        adj[a].addAll(adj[b]);
        adj[b].clear();
        if (adj[a].size() > 1) q.add(a);
    }
    static int root(int a){
        if(a == parent[a]) return a;
        return parent[a] = root(parent[a]);
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = scan.nextInt(), m = scan.nextInt();
        adj = new ArrayList[n];
        adj2 = new ArrayList[n];
        for(int i = 0; i<n; i++) {
            adj[i] = new ArrayList<>();
            adj2[i] = new ArrayList<>();
        }
        for(int i = 0; i<m; i++){
            int a = scan.nextInt()-1, b = scan.nextInt()-1;
            adj[a].add(b);
        }
        for(int i = 0; i<n; i++){
            if(adj[i].size()>1) q.add(i);
        }
        parent = new int[n];
        for(int i = 0; i<n; i++) {parent[i] = i; adj2[i].add(i);}
        while(!q.isEmpty()){
            int i = q.peek();
            if(adj[i].size()<=1){q.poll(); continue;}
            int a = adj[i].get(adj[i].size()-1);
            adj[i].remove(adj[i].size()-1);
            combine(a, adj[i].get(adj[i].size()-1));
        }
        int[] ans = new int[n];
        int cnt = 1;
        System.out.println();
        for(int i = 0; i<n; i++){
            if(ans[parent[i]] == 0){
                ans[parent[i]] = cnt;
                cnt++;
            }
            pw.println(ans[parent[i]]);
        }
        pw.close();
    }
}