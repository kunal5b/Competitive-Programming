import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Roadblock {
    static class state implements Comparable<state>{
        int cur, dist, p;
        public state(int x, int d, int p1){
            cur = x;
            dist = d;
            p = p1;
        }
        public int compareTo(state s){
            return dist-s.dist;
        }
    }
    static String s = "rblock";
    static int n, m;
    static int[][] len;
    static int[] prev;
    static int path(){
        PriorityQueue<state> pq = new PriorityQueue<>();
        int[] arr = new int[n];
        boolean[] vis = new boolean[n];
        pq.add(new state(0, 0, -1));
        while (!pq.isEmpty()){
            state s = pq.poll();
            if(vis[s.cur]) continue;
            prev[s.cur] = s.p;
            vis[s.cur] = true;
            arr[s.cur] = s.dist;
            if(s.cur == n-1){
                break;
            }
            for(int i = 0; i<n; i++){
                if(len[s.cur][i]>0) {
                    pq.add(new state(i, len[i][s.cur]+s.dist, s.cur));
                }
            }
        }
        return arr[n-1];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        len = new int[n][n];
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1, b = Integer.parseInt(st.nextToken())-1, l = Integer.parseInt(st.nextToken());
            len[a][b] = len[b][a] = l;
        }
        prev = new int[n];
        int initial = path();
        ArrayList<Integer> allpath = new ArrayList<>();
        for(int i = n-1; i!=-1; i = prev[i]){
            allpath.add(i);
        }
        int ans = 0;
        for(int i = 0; i<allpath.size()-1; i++){
            int a = allpath.get(i), b = allpath.get(i+1);
            len[a][b]*=2;
            len[b][a]*=2;
            ans = Math.max(ans, path());
            len[a][b]/=2;
            len[b][a]/=2;
        }
        pw.println(ans-initial);
        pw.close();
    }
}