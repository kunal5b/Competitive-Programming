import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
public class WhyDidTheCowCrossTheRoadGold {
    static String s = "visitfj";
    static class state implements Comparable<state>{
        int x, y, d;
        public state(int x1, int y1, int d1){
            x = x1;
            y = y1;
            d = d1;
        }
        public int compareTo(state s){return d-s.d;}
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = scan.nextInt(), t = scan.nextInt();
        int[][] arr = new int[n][n];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                arr[i][j] = scan.nextInt();
            }
        }
        int[] dx = {-3, -2, -1, 0, 1, 2, 3, 2, 1, 0, -1, -2, 0, 0, -1, 1};
        int[] dy = {0, 1, 2, 3, 2, 1, 0, -1, -2, -3, -2, -1, -1, 1, 0, 0};
        PriorityQueue<state> q = new PriorityQueue<>();
        q.add(new state(0, 0, 0));
        int[][] vis = new int[n][n];
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i<n; i++) Arrays.fill(vis[i], Integer.MAX_VALUE);
        while(!q.isEmpty()){
            state s = q.poll();
            if(vis[s.x][s.y] != Integer.MAX_VALUE) continue;
            else vis[s.x][s.y] =  s.d;
            int dist = n-1-s.x+n-1-s.y;
            if(dist<=2){
                ans = Math.min(s.d+(dist*t), ans);
            }
            for(int i = 0; i<dx.length; i++){
                if(s.x+dx[i]>=0 && s.x+dx[i]<n && s.y+dy[i]>=0 && s.y+dy[i]<n) q.add(new state(s.x+dx[i], s.y+dy[i], s.d+3*t+arr[s.x+dx[i]][s.y+dy[i]]));
            }
        }
        pw.println(ans);
        pw.close();
    }
}