import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MilkScheduling {
    static class cow implements Comparable<cow> {
        int g, d;
        public cow(int g1, int d1){
            g = g1;
            d = d1;
        }
        public int compareTo(cow c){
            return c.g-g;
        }
    }
    static String s = "msched";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = Integer.parseInt(br.readLine());
        cow[] cows = new cow[n];
        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            cows[i] = new cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(cows);
        boolean[] visited = new boolean[10005];
        int cnt = 0;
        for(int i = 0; i<n; i++){
            for(int j = cows[i].d-1; j>=0; j--){
                if(!visited[j]){
                    cnt+=cows[i].g;
                    visited[j] = true;
                    break;
                }
            }
        }
        pw.println(cnt);
        pw.close();
    }
}