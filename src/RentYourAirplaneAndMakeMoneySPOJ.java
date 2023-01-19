import java.util.Arrays;
import java.util.Scanner;

public class RentYourAirplaneAndMakeMoneySPOJ {
    static rent[] arr;
    static int n;
    static class rent implements Comparable<rent>{
        int s, e, p;
        public rent(int s1, int d1, int p1){
            s = s1;
            e = s1+d1;
            p = p1;
        }
        public int compareTo(rent r){
            return e-r.e;
        }
    }
    static int bin(int s){
        int lo = 0, hi = n-1;
        while(lo<hi){
            int mid = (lo+hi)/2;
            if(arr[mid].e>s) hi = mid-1;
            else lo = mid;
        }
        return lo;
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for(int ct = 0; ct<t; ct++){
            n = s.nextInt();
            arr = new rent[n+1];
            arr[0] = new rent(0, 0, 0);
            for(int i = 1; i<=n; i++){
                arr[i] = new rent(s.nextInt(), s.nextInt(), s.nextInt());
            }
            Arrays.sort(arr);
            int[] prev = new int[n+1];
            for(int i = 1; i<=n; i++){
                prev[i] = bin(arr[i].s);
            }
            int[] dp = new int[n+1];
            for(int i = 1; i<=n; i++){
                dp[i] = Math.max(dp[i-1], dp[prev[i]]+arr[i].p);
            }
            System.out.println(dp[n]);
        }
    }
}
