import java.io.*;
import java.util.*;

public class LasersAndMirrors {
    static class line{
        int num;
        boolean d;
        public line(int n1, boolean d1){
            num = n1;
            d = d1;
        }
    }
    static String s = "lasers";
    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), xl = Integer.parseInt(st.nextToken()), yl = Integer.parseInt(st.nextToken()), xb = Integer.parseInt(st.nextToken()), yb = Integer.parseInt(st.nextToken());
        HashMap<Integer, ArrayList<Integer>> xadj = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> yadj = new HashMap<>();
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            if(!xadj.containsKey(x)) xadj.put(x, new ArrayList<>());
            xadj.get(x).add(y);
            if(!yadj.containsKey(y)) yadj.put(y, new ArrayList<>());
            yadj.get(y).add(x);
        }
        Queue<line> q = new LinkedList<>();
        q.add(new line(xl, false));
        q.add(new line(yl, true));
        HashMap<line, Integer> dist = new HashMap<>();
        dist.put(new line(xl, false), 0);
        dist.put(new line(yl, true), 0);
        int ans = -1;
        while(!q.isEmpty()){
            line l = q.poll();
            if((l.num == xb && !l.d) || (l.num == yb && l.d)) {
                ans = dist.get(l);
                break;
            }
            HashMap<Integer, ArrayList<Integer>> adj = l.d ? yadj : xadj;
            if(adj.containsKey(l.num)){
                for(int i: adj.get(l.num)){
                    line next = new line(i, !l.d);
                    if(!dist.containsKey(next)){
                        dist.put(next, dist.get(l)+1);
                        q.add(next);
                    }
                }
            }
        }
        System.out.println(ans);
        pw.close();
    }
}