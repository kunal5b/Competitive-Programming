import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class Teamwork {
    static String s = "teamwork";
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = scan.nextInt(), k = scan.nextInt();
        int[] arr = new int[n+1], dp = new int[n+1];
        for(int i = 1; i<=n; i++) arr[i] = scan.nextInt();
        for(int i = 1; i<=n; i++){int cm = 0; for(int r = i; i-r<k && r>=1; r--){cm = Math.max(cm, arr[r]); dp[i] = Math.max(dp[i], (i-r+1)*cm+dp[r-1]);}}
        pw.println(dp[n]);
        pw.close();
    }
}