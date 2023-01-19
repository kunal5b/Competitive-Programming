import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CowRouting {
    static String filename = "cowroute";
    static class state implements Comparable<state>{
        long cur, dist, city;
        public state(long c, long d, long c1){
            cur = c;
            dist = d;
            city = c1;
        }
        public int compareTo(state s){
            if(dist!=s.dist) return Long.compare(dist, s.dist);
            return Long.compare(city, s.city);
        }
    }
    static int[][] len = new int[1005][1005], cities = new int[1005][1005];
    static void solve(int a, int b, PrintWriter pw){
        PriorityQueue<state> q = new PriorityQueue<>();
        q.add(new state(a, 0, 0));
        boolean[] v = new boolean[1005];
        while(!q.isEmpty()){
            state s = q.poll();
            if(v[(int) s.cur]) continue;
            if(s.cur == b){
                pw.println(s.dist + " " + s.city);
                return;
            }
            v[(int) s.cur] = true;
            for(int i = 0; i<len[(int) s.cur].length; i++){
                if(len[(int) s.cur][i] == -1 || v[i]) continue;
                q.add(new state(i, s.dist+len[(int) s.cur][i], s.city+cities[(int) s.cur][i]));
            }
        }
        pw.println(-1 + " " + -1);
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(filename+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(filename + ".out"));
        int a = scan.nextInt()-1, b = scan.nextInt()-1, n = scan.nextInt();
        for(int i = 0; i<1005; i++) {
            for(int j = 0; j<1005; j++) {
                len[i][j] = -1;
                cities[i][j] = -1;
            }
        }
        for(int i = 0; i<n; i++){
            int cost = scan.nextInt(), length = scan.nextInt();
            int[] cur = new int[length];
            for(int j = 0; j<cur.length; j++){
                cur[j] = scan.nextInt()-1;
            }
            for(int j = 0; j<length; j++){
                for(int k = j+1; k<length; k++){
                    if((len[cur[j]][cur[k]] == cost && (k-j)<cities[cur[j]][cur[k]]) || len[cur[j]][cur[k]]>cost || len[cur[j]][cur[k]] == -1){
                        len[cur[j]][cur[k]] = cost;
                        cities[cur[j]][cur[k]] = k-j;
                    }
                }
            }
        }
        solve(a, b, pw);
        pw.close();
    }
}