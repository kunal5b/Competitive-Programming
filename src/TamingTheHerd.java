import java.io.*;
import java.util.StringTokenizer;

public class TamingTheHerd {
    static String s = "taming";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        int[][][] dp = new int[n][n][n+1];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<=i; j++){
                for(int k = 0; k<n+1; k++){
                    dp[i][j][k] = 1000000000;
                }
            }
        }
        dp[0][0][1] = 0;
        if(arr[0] != 0) dp[0][0][1]++;
        for(int i = 1; i<n; i++){
            for(int j = 0; j<=i; j++){
                for(int k = 1; k<=i+1; k++){
                    if(j == i){
                        for(int a = 0; a<i; a++){
                            dp[i][j][k] = Math.min(dp[i][j][k], dp[i-1][a][k-1]);
                        }
                    }
                    else{
                        dp[i][j][k] = dp[i-1][j][k];
                    }
                    dp[i][j][k]+=(i-j != arr[i] ? 1 : 0);
                }
            }
        }
        for(int k = 1; k<=n; k++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j<n; j++){
                min = Math.min(min, dp[n-1][j][k]);
            }
            pw.println(min);
        }
        pw.close();
    }
}