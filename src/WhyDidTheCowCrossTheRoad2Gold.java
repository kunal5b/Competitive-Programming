import java.io.*;
public class WhyDidTheCowCrossTheRoad2Gold {
    static String s = "nocross";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n], b = new int[n];
        for(int i = 0; i<n; i++) t[i] = Integer.parseInt(br.readLine());
        for(int i = 0; i<n; i++) b[i] = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][n];
        dp[0][0] = (Math.abs(t[0]-b[0])<=4 ? 1 : 0);
        for(int i = 1; i<n; i++){
            dp[i][0] = Math.max(dp[i-1][0], (Math.abs(t[i]-b[0])<=4 ? 1 : 0));
            dp[0][i] = Math.max(dp[0][i-1], (Math.abs(t[0]-b[i])<=4 ? 1 : 0));
        }
        for(int i = 1; i<n; i++) {
            for(int j = 1; j<n; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1]+(Math.abs(t[i]-b[j])<=4 ? 1 : 0), Math.max(dp[i-1][j], dp[i][j-1]));
            }
        }
        pw.println(dp[n-1][n-1]);
        pw.close();
    }
}