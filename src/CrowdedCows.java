import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class CrowdedCows {
    static class cow implements Comparable<cow>{
        int x, h;
        public cow(int x1, int h1){
            x = x1;
            h = h1;
        }
        public int compareTo(cow c){
            return x-c.x;
        }
    }
    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new FileReader("crowded.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File("crowded.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Long d = Long.parseLong(st.nextToken());
        cow[] cows = new cow[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            cows[i] = new cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(cows);
        int cnt = 0, l = 0, r = 0;
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        for(int i = 0; i<n; i++){
            while(r<n && cows[r].x-cows[i].x<=d) {
                if (!right.containsKey(cows[r].h)) right.put(cows[r].h, 1);
                else right.put(cows[r].h, right.get(cows[r].h) + 1);
                r++;
            }
            if(right.get(cows[i].h) == 1) right.remove(cows[i].h);
            else right.put(cows[i].h, right.get(cows[i].h)-1);
            while(cows[i].x-cows[l].x>=d){
                if(left.get(cows[l].h) == 1) left.remove(cows[i].h);
                else left.put(cows[l].h, left.get(cows[l].h)-1);
                l++;
            }
            if(left.size() > 0 && right.size() > 0 && left.lastKey()>=cows[i].h*2 && right.lastKey()>=cows[i].h*2) cnt++;
            if (!left.containsKey(cows[i].h)) left.put(cows[i].h, 1);
            else left.put(cows[i].h, left.get(cows[i].h) + 1);
        }
        System.out.println(cnt);
        pw.close();
    }
}
