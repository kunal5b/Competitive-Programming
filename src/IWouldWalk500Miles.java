import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;
public class IWouldWalk500Miles {
    static String s = "walk";
    static class state implements Comparable<state>{
        int cur;
        long dist;
        public state(int x1, long d1){
            cur = x1;
            dist = d1;
        }
        public int compareTo(state s){return (int) ((int) dist-s.dist);}
    }
    static long dist(int x, int y){
        long cur = (((long) 2019201913*Math.min(x, y))+((long)2019201949*Math.max(x, y)))%2019201997;
        return cur;
    }

    public static void main(String[] args) throws IOException {
        // Scanner scan = new Scanner(new File(s+".in"));
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = scan.nextInt(), k = scan.nextInt();
        PriorityQueue<state> q = new PriorityQueue<>();
        PriorityQueue<Long> ans = new PriorityQueue<>();
        q.add(new state(1, Long.MAX_VALUE));
        boolean[] vis = new boolean[n+1];
        while(!q.isEmpty()){
            state s = q.poll();
            if(vis[s.cur]) continue;
            vis[s.cur] = true;
            ans.add(s.dist);
            for(int i = 1; i<=n; i++){
                if(!vis[i]) q.add(new state(i, dist(s.cur, i)));
            }
        }
        while(ans.size()>k) ans.poll();
        System.out.println(ans.poll());
        pw.close();
    }
}