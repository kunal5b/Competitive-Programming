import java.io.*;
public class TwoFourEight {
static String s = "248";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = Integer.parseInt(br.readLine());
        int[] seq = new int[n];
        for(int i = 0; i<n; i++){
            seq[i] = Integer.parseInt(br.readLine());
        }
        int ans = -1;
        int[][] dp = new int[n][n];
        for(int l = 1; l<=n; l++){
            for(int i = 0; i<=n-l; i++){
                int j = i+l-1;
                dp[i][j] = -1;
                if(l == 1) dp[i][j] = seq[i];
                for(int k = i; k<j; k++){
                    if(dp[i][k]>0 && dp[i][k] == dp[k+1][j]){
                        dp[i][j] = Math.max(dp[i][k]+1, dp[i][j]);
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}