import java.util.ArrayList;
import java.util.Scanner;

public class CowntagionSilver {
    static ArrayList<Integer>[] adj;
    static long days = 0;
    static boolean[] visited;
    static long cnt(int pow){
        long c = 0;
        long cur = 1;
        while(cur<=pow){
            cur*=2;
            c++;
        }
        return c;
    }
    static void dfs(int x, int sz){
        if(visited[x] || sz==0) return;
        visited[x] = true;
        days+=cnt(sz)+sz;
        for(int i = 0; i<adj[x].size(); i++){
            dfs(adj[x].get(i), adj[adj[x].get(i)].size()-1);
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        adj = new ArrayList[n];
        visited = new boolean[n];
        for(int i = 0; i<n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i<n-1; i++){
            int a = scan.nextInt()-1, b = scan.nextInt()-1;
            adj[a].add(b);
            adj[b].add(a);
        }
        dfs(0, adj[0].size());
        System.out.println(days);
    }
}