import java.io.*;
import java.util.Arrays;

public class BalancedPhoto {
    static String s = "bphoto";
    static class compress implements Comparable<compress>{
        int h, i;
        public compress(int h1, int y1){
            h = h1;
            i = y1;
        }
        public int compareTo(compress c){
            return h-c.h;
        }
    }
    static int n;
    static class BIT{
        public int[] ft;
        public BIT(int n){ ft = new int[n+1];}
        public void update(int x, int v) {while(x<=n) {ft[x]+=v; x+=(x&-x);} }
        public int query (int x) {return x>0 ? ft[x]+query(x-(x&-x)):0;}
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        n = Integer.parseInt(br.readLine());
        compress[] o = new compress[n];
        for(int i = 0; i<n; i++) {
            o[i] = new compress(Integer.parseInt(br.readLine()), i+1);
        }
        Arrays.sort(o);
        int[] heights = new int[n+1];
        for(int i = 0; i<n; i++){
            heights[o[i].i] = i+1;
        }
        int ans = 0;
        int[] l = new int[n+1], r = new int[n+1];
        BIT bit = new BIT(n);
        for(int i = 1; i<=n; i++){
            l[i] = bit.query(n)-bit.query(heights[i]);
            bit.update(heights[i], 1);
        }
        bit.ft = new int[n+1];
        for(int i = n; i>=1; i--){
            r[i] = bit.query(n)-bit.query(heights[i]);
            bit.update(heights[i], 1);
        }
        for(int i = 1; i<=n; i++){
            if(l[i]*2<r[i] || l[i]>r[i]*2) ans++;
        }
        pw.println(ans);
        pw.close();
    }
}