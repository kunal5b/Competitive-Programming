import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SquareOverlap {
    static String s = "squares";
    static class pair implements Comparable<pair>{
        int a, b;
        public pair(int a1, int b1){
            a = a1;
            b = b1;
        }
        public int compareTo(pair p){
            return a-p.a;
        }
    }
    static boolean equals(pair p, pair p1){
        return (p.a == p1.b && p.b == p1.a);
    }
    static long overlap(pair p, pair e, int k){
        int dx = Math.abs(p.a-e.b), dy = Math.abs(p.b-e.a);
        return (long) (k-dx)*(k-dy);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        pair[] centers = new pair[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            centers[i] = new pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(centers);
        TreeSet<pair> set = new TreeSet<>();
        int j = 0;
        ArrayList<Long> ans = new ArrayList<>();
        for(int i = 0; i<n && ans.size()<2; i++){
            set.add(new pair(centers[i].b, centers[i].a));
            while(centers[i].a-centers[j].a>=k){
                set.remove(new pair(centers[j].b, centers[j].a));
                j++;
            }
            for(pair p: set) if(Math.abs(p.a-centers[i].b)<k && !equals(p, centers[i])) ans.add(overlap(centers[i], p, k));
        }
        if(ans.size() == 1){
            pw.println(ans.get(0));
        }
        if(ans.size() >= 2){
            pw.println(-1);
        }
        if(ans.size() == 0){
            pw.println(0);
        }
        pw.close();
    }
}