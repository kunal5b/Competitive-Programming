import java.io.*;
import java.util.*;

public class Perimeter {
    static String filename = "perimeter";
    static int cx, cy, dir = 2, ans = 0;
    static TreeMap<Integer, TreeSet<Integer>> loc = new TreeMap<>();
    static int[] dx = {1/*right*/, -1/*left*/, 0/*up*/, 0/*down*/}, dy = {0/*right*/, 0/*left*/, 1/*up*/, -1/*down*/}, to = {3, 2, 0, 1}, unto = {2, 3, 1, 0};
    static boolean right(){
        if((loc.containsKey(cx+dx[dir]) && loc.get(cx+dx[dir]).contains(cy+dy[dir])) || loc.get(cx+dx[dir]+dx[to[dir]]).contains(cy+dy[dir]+dy[to[dir]]) || loc.get(cx+dx[dir]-dx[to[dir]]).contains(cy+dy[dir]-dy[to[dir]])){
            return false;
        }
        cx+=dx[dir]+dx[to[dir]];
        cy+=dy[dir]+dy[to[dir]];
        ans+=1;
        return true;
    }
    static boolean up(){
        if(loc.get(cx+dx[dir]).contains(cy+dy[dir]) || !loc.get(cx+dx[dir]+dx[to[dir]]).contains(cy+dy[dir]+dy[to[dir]]) || loc.get(cx+dx[dir]-dx[to[dir]]).contains(cy+dy[dir]-dy[to[dir]])){
            return false;
        }
        cx+=dx[dir];
        cy+=dy[dir];
        ans+=1;
        return true;
    }
    static boolean left(){
        dir = unto[dir];
        ans+=1;
        return true;
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(filename+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(filename + ".out"));
        int n = scan.nextInt();
        for(int i = 0; i<n; i++){
            int x = scan.nextInt(), y = scan.nextInt();
            if(!loc.containsKey(x)){
                loc.put(x, new TreeSet<>());
            }
            loc.get(x).add(y);
        }
        int sx = loc.firstKey()-1;
        int sy = loc.get(loc.firstKey()).first();
        cx = sx;
        cy = sy;
        do{
            if(right()){

            }
            else if(up()){

            }
            else if(left()){

            }

        } while(cx!= sx && cy!=sy);
        System.out.println(cx + " " + cy);
        System.out.println(cx + " " + cy + " " + dir);
        System.out.println(ans);
        pw.close();
    }
}