import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
public class OutOfSortsGold {
    static String s = "sort";
    static int n;
    static class pair implements Comparable<pair>{
        int a, b;
        public pair(int a1, int b1){a = a1; b = b1;}
        public int compareTo(pair p){return b-p.b;}
    }
    static class BIT{
        int[] ft;
        public BIT(int n){ft = new int[n+1];}
        public void update(int i, int v){while(i<=n){ft[i]+=v; i+=(i&-i);}}
        public int query(int i){return i>0 ? ft[i]+query(i-(i&-i)) : 0;}
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        n = scan.nextInt();
        pair[] arr = new pair[n+1];
        arr[0] = new pair(-1, -1);
        for(int i = 1; i<=n; i++) arr[i] = new pair(i, scan.nextInt());
        Arrays.sort(arr);
        BIT bit = new BIT(n);
        int ans = 1;
        for(int i = 1; i<n; i++){
            bit.update(arr[i].a, 1);
            ans = Math.max(ans, i-bit.query(i));
        }
        pw.println(ans);
        pw.close();
    }
}