import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GasPipeline {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int ct = 0; ct<t; ct++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()), a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            String s = br.readLine();
            long[][] dp = new long[n+1][2];
            dp[0][0] = b;
            dp[0][1] = (long) Math.pow(10, 13);
            for(int i = 0; i<n; i++){
               if(s.charAt(i) == '0'){
                   dp[i+1][0] = Math.min(dp[i][0]+a+b, dp[i][1]+a+b+a);
                   dp[i+1][1] = Math.min(dp[i][0]+2*(a+b), dp[i][1]+a+2*b);
               }
               else{
                   dp[i+1][0] = (long) Math.pow(10, 13);
                   dp[i+1][1] = dp[i][1]+a+b+b;
               }
            }
            System.out.println(dp[n][0]);
        }
    }
}