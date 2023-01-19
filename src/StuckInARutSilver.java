import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StuckInARutSilver {
    static boolean[] done;
    static ArrayList<Integer>[] add;
    static int[] nstop;
    static int dfs(int i){
        if(done[i]) return nstop[i];
        for(int x = 0; x<add[i].size(); x++){
            nstop[i]+=dfs(add[i].get(x));
        }
        done[i] = true;
        return nstop[i];
    }
    static class event implements Comparable<event>{
        int a, b, t;
        // A STOPS B
        public event(int a1, int b1, int t1){
            a = a1;
            b = b1;
            t = t1;
        }
        public int compareTo(event e){
            return t-e.t;
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        char[] c = new char[n];
        int[] x = new int[n], y = new int[n];
        for(int i = 0; i<n; i++){
            c[i] = scan.next().charAt(0);
            x[i] = scan.nextInt();
            y[i] = scan.nextInt();
        }
        PriorityQueue<event> q = new PriorityQueue<>();
        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                if(c[i] == c[j]) continue;
                int ie = (c[i] == 'E' ? i : j);
                int in = (c[i] == 'N' ? i : j);
                if(y[in]>y[ie] || x[ie]>x[in] || x[in]-x[ie]==y[ie]-y[in]) continue;
                // System.out.println(c[i] + " " + x[i] + " " + y[i] + " " + c[j] + " " + x[j] + " " + y[j]);
                int t1 = x[in]-x[ie];
                int t2 = y[ie]-y[in];
                if(t1>t2) {
                    q.add(new event(in, ie, t1));
                }
                if(t2>t1){
                    q.add(new event(ie, in, t2));
                }
            }
        }
        boolean[] stopped = new boolean[n];
        nstop = new int[n];
        add = new ArrayList[n];
        done = new boolean[n];
        int[] tstop = new int[n];
        for(int i = 0; i<n; i++) add[i] = new ArrayList<>();
        while(!q.isEmpty()){
            event e = q.poll();
            if(stopped[e.b]) continue;
            if(stopped[e.a] && ((c[e.a] == 'E' && x[e.b]>x[e.a]+tstop[e.a]) || (y[e.b]>y[e.a]+tstop[e.a]))) continue;
            nstop[e.a]+=1;
            add[e.a].add(e.b);
            stopped[e.b] = true;
            tstop[e.b] = e.t;
        }

        for(int i = 0; i<n; i++){
            if(done[i]) continue;
            dfs(i);
        }
        for(int i = 0; i<n; i++){
            System.out.println(nstop[i]);
        }
    }
}