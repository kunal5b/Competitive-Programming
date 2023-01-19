import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BessiesDream {
    static String filename = "dream";
    static class state implements Comparable<state>{
        int dist, x, y;
        boolean oranges;
        public state(int d, int x1, int y1, boolean or){
            dist = d;
            x = x1;
            y = y1;
            oranges = or;
        }
        public int compareTo(state s){return dist-s.dist;}
    }
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] grid;
    static int n, m;
    static state slide(int x, int y, int a, int dis){
        int d = dis+1;
        while(x+dx[a]<n && x+dx[a]>=0 && y+dy[a]<m && y+dy[a]>=0 && grid[x][y] == 4){
            if(grid[x+dx[a]][y+dy[a]] == 3 || grid[x+dx[a]][y+dy[a]] == 0) break;
            x+=dx[a];
            y+=dy[a];
            d++;
        }
        if(grid[x][y]!=2) return new state(d, x, y, false);
        return new state(d, x, y, true);
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(filename+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(filename + ".out"));
        n = scan.nextInt();
        m = scan.nextInt();
        int ans = -1;
        grid = new int[n][m];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                grid[i][j] = scan.nextInt();
            }
        }
        PriorityQueue<state> q = new PriorityQueue<>();
        q.add(new state(0, 0, 0, false));
        boolean[][][] v = new boolean[n][m][2];
        while(!q.isEmpty()){
            state s = q.poll();
            if((grid[s.x][s.y] == 3 && !s.oranges) || v[s.x][s.y][s.oranges ? 1 : 0]) continue;
            v[s.x][s.y][s.oranges ? 1 : 0] = true;
            if(s.x == n-1 && s.y == m-1){
                ans = s.dist;
                break;
            }
            for(int i = 0; i<dx.length; i++){
                int cx = s.x, cy = s.y;
                cx+=dx[i];
                cy+=dy[i];
                if(cx>=0 && cx<n && cy<m && cy>=0 && grid[cx][cy]!=0){
                    if(grid[cx][cy] == 2 && !v[cx][cy][1]){
                        q.add(new state(s.dist+1, cx, cy, true));
                    }
                    else if(grid[cx][cy] == 3 && s.oranges && !v[cx][cy][1]){
                        q.add(new state(s.dist+1, cx, cy, true));
                    }
                    else if(grid[cx][cy] == 4){
                        state l = slide(cx, cy, i, s.dist);
                        if(v[l.x][l.y][0]) continue;
                        q.add(l);
                    }
                    else if(grid[cx][cy] == 1 && !v[cx][cy][s.oranges ? 1 : 0]){
                        q.add(new state(s.dist+1, cx, cy, s.oranges));
                    }
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}