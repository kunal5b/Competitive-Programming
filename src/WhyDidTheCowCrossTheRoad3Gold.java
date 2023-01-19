import java.io.*;
import java.util.Arrays;

public class WhyDidTheCowCrossTheRoad3Gold {
    static String s = "circlecross";
    static int n;
    static class pair implements Comparable<pair>{
        int a, b;
        public pair(int a1, int b1){
            a = a1;
            b = b1;
        }
        public int compareTo(pair p){return a-p.a;}
    }
    static class BIT{
        int[] ft;
        public BIT(int n){ft = new int[2*n+1];}
        public void update(int i, int v){while(i<=2*n){ft[i]+=v; i+=(i&-i);}}
        public int query(int i){return i>0 ? ft[i]+query(i-(i&-i)) : 0;}
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        n = Integer.parseInt(br.readLine());
        pair[] cross = new pair[n];
        for(int i = 0; i<n; i++) cross[i] = new pair(0, 0);
        for(int i = 1; i<=2*n; i++){
            int l = Integer.parseInt(br.readLine())-1;
            if(cross[l].a == 0) cross[l].a = i;
            else cross[l].b = i;
        }
        Arrays.sort(cross);
        long ans = 0;
        BIT bit = new BIT(n);
        for(int i = 0; i<n; i++){
            ans+=bit.query(cross[i].b)-bit.query(cross[i].a);
            bit.update(cross[i].b, 1);
        }
        pw.println(ans);
        pw.close();
    }
}