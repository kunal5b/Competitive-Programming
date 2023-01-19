import java.io.*;
import java.util.Arrays;

public class TheCowRun {
static String s = "cowrun";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int[][][] dp = new int[n][n][2];
        for(int i = 0; i<n; i++){
            dp[i][i][0] = dp[i][i][1] = Math.abs(arr[i])*n;
        }
        for(int i = n-1; i>=0; i--){
            for(int j = i+1; j<n; j++){
                dp[i][j][0] = Math.min(dp[i+1][j][1]+(arr[j]-arr[i])*(n-(j-i)), dp[i+1][j][0]+(arr[i+1]-arr[i])*(n-(j-i)));
                dp[i][j][1] = Math.min(dp[i][j-1][1]+(arr[j]-arr[j-1])*(n-(j-i)), dp[i][j-1][0]+(arr[j]-arr[i])*(n-(j-i)));
            }
        }
        pw.println(Math.min(dp[0][n-1][1], dp[0][n-1][0]));
        pw.close();
    }
}