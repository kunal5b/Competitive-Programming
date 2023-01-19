import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class HaircutGold {
    static String s = "haircut";
    static int n;
    static class BIT{
        int[] ft;
        public BIT(int n){ft = new int[n+2];}
        public void update(int i, int v){while(i<=n+1){ft[i]+=v; i+=(i&-i);}}
        public int query(int i){return i>0 ? ft[i]+query(i-(i&-i)) : 0;}
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        n = scan.nextInt();
        long ans = 0;
        BIT bit = new BIT(n);
        int[] arr = new int[n+2];
        for(int i = 1; i<=n; i++){
            int a = scan.nextInt()+1;
            arr[a]+=i-1-bit.query(a);
            bit.update(a, 1);
        }
        for(int i = 1; i<arr.length-1; i++) {pw.println(ans); ans+=arr[i];}
        pw.close();
    }
}