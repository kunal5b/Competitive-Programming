import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MilkingOrderGold {
    static String s = "milkorder";
    static int[] order;
    static ArrayList<Integer>[] obs;
    static int n, m;
    static boolean works(int x){
        ArrayList<Integer>[] adj = new ArrayList[n];
        int[] pre = new int[n];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();
        for(int i = 0; i<=x; i++){
            for(int j = 1; j<obs[i].size(); j++){
                adj[obs[i].get(j-1)].add(obs[i].get(j));
                pre[obs[i].get(j)]++;
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i<n; i++){
            if(pre[i] == 0){
                pq.add(i);
            }
        }
        for(int i = 0; i<n; i++){
            if(pq.isEmpty()) return false;
            int cur = pq.poll();
            order[i] = cur;
            for(int j: adj[cur]){
                pre[j]--;
                if(pre[j] == 0) pq.add(j);
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        obs = new ArrayList[m];
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            obs[i] = new ArrayList<>();
            int c = Integer.parseInt(st.nextToken());
            for(int j = 0; j<c; j++){
                obs[i].add(Integer.parseInt(st.nextToken())-1);
            }
        }
        int x = 0, hi = m-1;
        order = new int[n];
        while(x+1<hi){
            int mid = (x+hi)/2;
            if(works(mid)) x = mid;
            else hi = mid;
        }
        works(x);
        for(int i = 0; i<n-1; i++){
            pw.print(order[i]+1 + " ");
        }
        pw.print(order[n-1]+1);
        pw.close();
    }
}