import java.io.*;
import java.util.*;
public class searchingforsoulmates {
    static int min = 8;
    static HashSet<Long> visited = new HashSet<>();
    static void solve(int x, int y, int op){
        if(visited.contains(x)) return;
        if(x == y){
            if(min == 1000 || op<min) min = op;
            return;
        }
        visited.add((long) x);
        if(op<min){
            if(x % 2 == 0) solve(x/2, y, op+1);
            solve(x*2, y, op+1);
            solve(x+1, y, op+1);
        }
    }
    static class state{
        long x;
        int m;
        ArrayList<Integer> operations = new ArrayList<>();
        public state(long x1, int m1, ArrayList<Integer> op){
            x = x1;
            m = m1;
            operations = op;
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for(int i = 0; i<n; i++){
            long x = scan.nextLong(), y = scan.nextLong();
            Queue<state> bfs = new LinkedList<state>();
            bfs.add(new state(x, 0, new ArrayList<>()));
            HashSet<Long> visited = new HashSet<>();
            while(!bfs.isEmpty()){
                state cur = bfs.poll();
                if(cur.x == y){
                    System.out.println(cur.m);
                    System.out.println(cur.operations);
                    break;
                }
                visited.add(cur.x);
                cur.operations.add((int) cur.x);
                if(!visited.contains(cur.x*2)) bfs.add(new state(cur.x*2, cur.m+1, cur.operations));
                if(!visited.contains(cur.x+1)) bfs.add(new state(cur.x+1, cur.m+1, cur.operations));
                if(cur.x % 2 == 0){
                    if(!visited.contains(cur.x/2)) bfs.add(new state(cur.x/2, cur.m+1, cur.operations));
                }
            }
        }
    }
}