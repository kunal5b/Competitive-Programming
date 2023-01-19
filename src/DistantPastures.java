import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DistantPastures {
    static class pasture implements Comparable<pasture>{
        int x, y, dist;
        public pasture(int x1, int y1, int d){
            x = x1;
            y = y1;
            dist = d;
        }
        public int compareTo(pasture p){
            return dist-p.dist;
        }
    }
    static void add(int x, int y, pasture p){
        if(x<0 || x>=n || y<0 || y>=n || visited[x][y]) return;
        if(grid[x][y] == grid[p.x][p.y]) q.add(new pasture(x, y, p.dist+a));
        else q.add(new pasture(x, y, p.dist+b));
    }
    static int n, a, b;
    static char[][] grid;
    static boolean[][] visited;
    static PriorityQueue<pasture> q;
    static String s = "distant";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        grid = new char[n][n];
        for(int i = 0; i<n; i++){
            grid[i] = br.readLine().toCharArray();
        }
        int max = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                q = new PriorityQueue<>();
                visited = new boolean[n][n];
                q.add(new pasture(i, j, 0));
                while(!q.isEmpty()){
                    pasture p = q.poll();
                    if(visited[p.x][p.y]) continue;
                    max = Math.max(p.dist, max);
                    visited[p.x][p.y] = true;
                    max = Math.max(max, p.dist);
                    add(p.x+1, p.y, p);
                    add(p.x-1, p.y, p);
                    add(p.x, p.y+1, p);
                    add(p.x, p.y-1, p);
                }
            }
        }
        pw.println(max);
        pw.close();
    }
}