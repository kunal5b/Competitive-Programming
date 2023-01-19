import java.io.*;
import java.util.StringTokenizer;

public class SleepyCowSortingGold {
    static String s = "sleepy";
    static int n;
    static class BIT{
        int[] ft;
        public BIT(int n){ft = new int[n+1];}
        public void update(int i, int v){while(i<=n){ft[i]+=v; i+=(i&-i);}}
        public int query(int i){return i>0 ? ft[i]+query(i-(i&-i)) : 0;}
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=n; i++) arr[i] = Integer.parseInt(st.nextToken());
        int j = n;
        while(j>0 && arr[j]>arr[j-1]){
            j--;
        }
        pw.println(j-1);
        BIT bit = new BIT(n);
        for(int i = j; i<=n; i++){
            bit.update(arr[i], 1);
        }
        for(int i = 1; i<j; i++){
            pw.print(bit.query(arr[i])+(j-i-1));
            if(i != j-1) pw.print(" ");
            bit.update(arr[i], 1);
        }
        pw.close();
    }
}