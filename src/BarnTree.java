import java.util.*;

public class BarnTree {
    static class state{
        int f, t;
        public state(int f1, int t2){
            f = f1;
            t = t2;
        }
    }
    static int[] need, bale;
    static boolean[] vis;
    static int targ = 0;
    static ArrayList<Integer>[] adj;
    static ArrayList<Integer> a = new ArrayList<>(), b = new ArrayList<>(), c = new ArrayList<>();
    static int dp(int cur){
        if(vis[cur]) return 0;
        need[cur]+=(targ-bale[cur]);
        vis[cur] = true;
        for(int i = 0; i<adj[cur].size(); i++){
            need[cur]+=dp(adj[cur].get(i));
        }
        return need[cur];
    }
    static int cnt = 0;
    static void dfs(int cur, int prev){

        if(need[cur]<0){
            a.add(cur+1);
            b.add(prev+1);
            c.add(-need[cur]);
            cnt++;
            bale[prev]+=need[prev];
            bale[cur]-=need[prev];
        }
        else if (need[cur]>0){
            a.add(prev+1);
            b.add(cur+1);
            c.add(need[cur]);
            cnt++;
            bale[cur]+=need[cur];
            bale[prev]-=need[cur];

        }
        for(int i: adj[cur]){
            if(i == prev) continue;
            dfs(i, cur);
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        bale = new int[n];
        need = new int[n];
        vis = new boolean[n];
        for(int i = 0; i<n; i++){
            bale[i] = scan.nextInt();
            targ+=bale[i];
        }
        targ/=n;
        adj = new ArrayList[n];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();
        for(int i = 0; i<n-1; i++){
            int a = scan.nextInt(), b = scan.nextInt();
            adj[a-1].add(b-1);
            adj[b-1].add(a-1);
        }
        int start = 0, cur = 0;
        for(int i = 0; i<n; i++){
            if(bale[i]>cur){
                start = i;
                cur = bale[i];
            }
        }
        dp(start);
        dfs(start, start);
        System.out.println(cnt);
        for(int i = 0; i<a.size(); i++){
            System.out.println(a.get(i) + " " + b.get(i) + " " + c.get(i));
        }
    }
}