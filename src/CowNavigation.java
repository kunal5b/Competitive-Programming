import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class CowNavigation {
    static String s = "cownav";
    static class state{
        int x, y, x1, y1, dir, dist;
        boolean f, s;
        /*
        Key for dir:
        - 0 -
        3 C 1
        - 2 -
         */
        public state(int xa, int ya, int x2, int y2, int d, int d1, boolean first, boolean second){
            x = xa;
            y = ya;
            x1 = x2;
            y1 = y2;
            dir = d;
            dist = d1;
            f = first;
            s = second;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = Integer.parseInt(br.readLine());
        char[][] grid = new char[n][n];
        for(int i = 0; i<n; i++){
            grid[i] = br.readLine().toCharArray();
        }
        Queue<state> q = new LinkedList<>();
        state s1 = null;
        boolean[][][][][] visited = new boolean[20][20][20][20][5];
        q.add(new state(n-1, 0, n-1, 0, 1, 0, false, false));
        while(!q.isEmpty()){
            state s = q.poll();
            if(visited[s.x][s.y][s.x1][s.y1][s.dir]) continue;
            visited[s.x][s.y][s.x1][s.y1][s.dir] = true;
            if(s.x == 0 && s.y == n-1) s.f = true;
            if(s.x1 == 0 && s.y1 == n-1) s.s = true;
            if(s.f && s.s){
                pw.println(s.dist);
                break;
            }
            q.add(new state(s.x, s.y, s.x1, s.y1, (s.dir+1)%4, s.dist+1, s.f, s.s));
            q.add(new state(s.x, s.y, s.x1, s.y1, (s.dir-1>=0 ? s.dir-1 : 3), s.dist+1, s.f, s.s));
            if(s.dir == 0){
                if(s.y1-1>=0 && grid[s.x1][s.y1-1] == 'E' && !s.s) s.y1--;
                if(s.x-1>=0 && grid[s.x-1][s.y] == 'E' && !s.f) s.x--;
            }
            if(s.dir == 1){
                if(s.y+1<n && grid[s.x][s.y+1] == 'E' && !s.f) s.y++;
                if(s.x1-1>=0 && grid[s.x1-1][s.y1] == 'E' && !s.s) s.x1--;
            }
            if(s.dir == 2){
                if(s.x+1<n && grid[s.x+1][s.y] == 'E' && !s.f) s.x++;
                if(s.y1+1<n && grid[s.x1][s.y1+1] == 'E' && !s.s) s.y1++;
            }
            if(s.dir == 3){
                if(s.y-1>=0 && grid[s.x][s.y-1] == 'E' && !s.f) s.y--;
                if(s.x1+1<n && grid[s.x1+1][s.y1] == 'E' && !s.s) s.x1++;
            }
            q.add(new state(s.x, s.y, s.x1, s.y1, s.dir, s.dist+1, s.f, s.s));
        }
        pw.close();
    }
}