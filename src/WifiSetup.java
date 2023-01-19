import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WifiSetup {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("wifi.in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File("wifi.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
        int[] arr = new int[n+1];
        for(int i = 0; i<n; i++){
            arr[i+1] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        double[] dp = new double[n+1];
        for(int i = 1; i<=n; i++){
            dp[i] = Integer.MAX_VALUE;
            for(int j = 1; j<=i; j++){
                dp[i] = Math.min(dp[i], dp[j-1]+((double) b*(arr[i]-arr[j])/2)+a);
            }
        }
        if(dp[n]%1 == 0){
            pw.println((int) dp[n]);
        }
        else pw.println(dp[n]);
        pw.close();
    }
}
