import java.io.*;
import java.util.Scanner;

public class BalancedCowBreeds {
    static String filename = "bbreeds";

    public static void main(String[] args) throws IOException {
        // Scanner scan = new Scanner(new File(filename+".in"));
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(filename + ".out"));
        String s = scan.next();
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = 0; i<=s.length(); i++){
            for(int j = 0; j<=s.length(); j++){
                dp[i][j] = -1;
            }
        }
        int[] prefix = new int[n+1];
        for(int i = 1; i<=n; i++){
            prefix[i] = prefix[i-1] + s.charAt(i-1) == '(' ? 1 : -1;
        }
        for(int i = n; i>=0; i++){
            for(int j = 0; j<n; j++){
                if(i == n) dp[i][j] = 1;
            }
        }
        pw.close();
    }
}