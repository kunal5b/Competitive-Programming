import java.io.*;
import java.util.TreeSet;

public class CircularBarnGold {
    static String s = "cbarn";
    static class state implements Comparable<state>{
        int pos, cnt;
        public state(int p1, int c1){
            pos = p1;
            cnt = c1;
        }
        public int compareTo(state s){return pos-s.pos;}
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = Integer.parseInt(br.readLine());
        int[] cows = new int[n];
        int cur = 0;
        for(int i = 0; i<n; i++){
            cows[i] = Integer.parseInt(br.readLine());
            cur = Math.max(0, cur+cows[i]-1);
        }
        int startpt = 0;
        for(int i = 0; i<n; i++){
            if(cur == 0){
                startpt = i;
                break;
            }
            cur = Math.max(0, cur+cows[i]-1);
        }
        int[] barn = new int[n];
        cur = 0;
        for(int i = startpt; i<n; i = (i+1)%n){
            if(cur == n) break;
            barn[cur] = cows[i];
            cur++;
        }
        long ans = 0;
        TreeSet<state> active = new TreeSet<>();
        for(int i = 0; i<n; i++){
            if(barn[i]!=0){
                active.add(new state(i, barn[i]));
            }
            ans+=((long)(i-active.first().pos)*(i-active.first().pos));
            active.first().cnt--;
            if(active.first().cnt == 0) active.pollFirst();
        }
        pw.println(ans);
        pw.close();
    }
}