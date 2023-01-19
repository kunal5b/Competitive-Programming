import java.io.*;
import java.util.Scanner;

public class LoanRepayment {
    static String filename = "loan";
    static boolean works(long x, long n, long k, long m){
        long g = 0;
        while(g<n && k>0){
            long next = Math.max((n-g)/x, m);
            if(next == m){
                long leftover = (n-g) / m;
                return leftover <= k;
            }
            g+=next;
            k--;
        }
        return g>=n;
    }

    public static void main(String[] args) throws IOException {
        // Scanner scan = new Scanner(new File(filename+".in"));
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(filename + ".out"));
        long n = scan.nextLong(), k = scan.nextLong(), m = scan.nextLong();
        long lo = 0, hi = n;
        while(lo<hi){
            long mid = (lo+hi)/2;
            if(works(mid, n, k, m)){
                lo = mid;
            }
            else hi = mid-1;
        }
        System.out.println(lo);
        pw.close();
    }
}