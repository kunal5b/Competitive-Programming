import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class WhatsUpWithGravity {
    static String s = "gravity";
    static class state implements Comparable<state>{
        int d, dir;
        point p;
        public state(point p1, int dl, int d1){
            p = p1;
            dir = dl;
            d = d1;
        }
        public int compareTo(state s){return d-s.d;}
    }
    static int n, m;
    static class point{
        int x, y;
        public point(int x1, int y1){
            x = x1;
            y = y1;
        }
    }
    static char[][] grid;
    static point fall(point p, int i){
       while(true){
           if(grid[p.x][p.y] == 'D') break;
           if(p.x+i<0 || p.x+i>=n) return new point(-1, -1);
           if(grid[p.x+i][p.y] == '#') break;
           p.x+=i;
       }
       return p;
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        n = scan.nextInt();
        m = scan.nextInt();
        point st = null, en = null;
        grid = new char[n][m];
        int[][] index = new int[n][m];
        for(int i = 0; i<n; i++){
            String s = scan.next();
            for(int j = 0; j<s.length(); j++){
                grid[i][j] = s.charAt(j);
                if(grid[i][j] == 'C'){
                    st = new point(i, j);
                }
                else if(grid[i][j] == 'D'){
                    en = new point(i, j);
                }
            }
        }
        int ans = -1;
        point start = fall(st, 1);
        point end = en;
        PriorityQueue<state> q = new PriorityQueue<>();
        q.add(new state(fall(st, 1), 1, 0));
        boolean[][] v = new boolean[n][m];
        while(!q.isEmpty()){
            state s = q.poll();
            point cur = s.p;
            if(cur.x == -1) continue;
            if(cur.x == end.x && cur.y == end.y){
                ans = s.d;
                break;
            }
            v[cur.x][cur.y] = true;
            if(cur.y+1<m && !v[cur.x][cur.y+1] && grid[cur.x][cur.y+1]!='#'){
                q.add(new state(fall(new point(cur.x, cur.y+1), s.dir), s.dir, s.d));
            }
            if(cur.y-1>=0  && !v[cur.x][cur.y-1] && grid[cur.x][cur.y-1]!='#'){
                q.add(new state(fall(new point(cur.x, cur.y-1), s.dir), s.dir, s.d));
            }
            point p = fall(cur, -s.dir);
            if(p.x!=-1 && !v[p.x][p.y]) q.add(new state(fall(cur, -s.dir), -s.dir, s.d+1));
        }
        pw.println(ans);
        pw.close();
    }
}