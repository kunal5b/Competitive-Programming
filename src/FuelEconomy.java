import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FuelEconomy {
    static class station implements Comparable<station>{
        int x, y;
        public station(int x1, int y1) {
            x = x1;
            y = y1;
        }
        public int compareTo(station s) {
            return x - s.x;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fuel.in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File("fuel.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), g = Integer.parseInt(st.nextToken()), cur = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
        station[] stations = new station[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            stations[i] = new station(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(stations);
        cur-=stations[0].x;
        int[] next = new int[n];
        int stacklen = 0;
        int[] stack = new int[n];
        for (int i = n-1; i >= 0; i--) {
            while (stacklen>0 && stations[stack[stacklen-1]].y>=stations[i].y) {
                stacklen--;
            }
            next[i] = (stacklen == 0 ? -1 : stack[stacklen-1]);
            stack[stacklen] = i;
            stacklen++;
            System.out.println(next[i]);
        }
        long ans = 0;
        for(int i = 0; i<n; i++){
            if(cur<0){
                break;
            }
            int need = Math.min(g, (next[i] == -1 ? d : stations[next[i]].x)-stations[i].x);
            if(cur<need){
                ans+=((long) stations[i].y* (long)(need-cur));
                cur = need;
            }
            cur-=((i<n-1 ? stations[i+1].x : d)-stations[i].x);
        }
        if(cur<0) pw.println(-1);
        else pw.println(ans);
        pw.close();
    }
}
