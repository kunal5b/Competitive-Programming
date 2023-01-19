import java.io.*;
import java.util.*;

public class MilkRouting {
    static class state implements Comparable<state>{
        int dist, node;
        public state(int d1, int n1){
            dist = d1;
            node = n1;
        }
        public int compareTo(state s){
            return dist-s.dist;
        }
    }
    static String s = "mroute";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] adj = new ArrayList[n];
        ArrayList<Integer>[] cap = new ArrayList[n];
        ArrayList<Integer>[] l = new ArrayList[n];
        for(int i = 0; i<n; i++){
            adj[i] = new ArrayList<>();
            cap[i] = new ArrayList<>();
            l[i] = new ArrayList<>();
        }
        ArrayList<Integer> allcap = new ArrayList<>();
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1, b = Integer.parseInt(st.nextToken())-1, la = Integer.parseInt(st.nextToken()), ca = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
            cap[a].add(ca);
            cap[b].add(ca);
            l[a].add(la);
            l[b].add(la);
            allcap.add(ca);
        }
        int ans = Integer.MAX_VALUE;
        for(int c: allcap){
            PriorityQueue<state> pq = new PriorityQueue<>();
            pq.add(new state(0, 0));
            int[] arr = new int[n];
            Arrays.fill(arr, Integer.MAX_VALUE);
            while(!pq.isEmpty()){
                state s = pq.poll();
                if(arr[s.node] != Integer.MAX_VALUE) continue;
                if(s.node == n-1){
                    ans = Math.min(ans, s.dist+x/c);
                    break;
                }
                arr[s.node] = s.dist;
                for(int i = 0; i<cap[s.node].size(); i++){
                    if(cap[s.node].get(i)>=c){
                        pq.add(new state(s.dist+l[s.node].get(i), adj[s.node].get(i)));
                    }
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}