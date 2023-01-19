import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MoooMoo {
    static String s = "mooomoo";
    static int mincnt(int sum){
        int[] dp = new int[sum+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i<=sum; i++){
            if(dp[i]!=Integer.MAX_VALUE) {
                for (int j : breed) {
                    if(i+j<=sum) dp[i + j] = Math.min(dp[i] + 1, dp[i + j]);
                }
            }
        }
        return dp[sum];
    }
    static int[] breed;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
        breed = new int[b];
        int[] fields = new int[n];
        boolean works = true;
        for(int i = 0; i<b; i++){
            breed[i] = Integer.parseInt(br.readLine());
        }
        int[] prev = new int[n];
        for(int i = 0; i<n; i++){
            fields[i] = Integer.parseInt(br.readLine());
            prev[i] = fields[i];
            if(i>0 && prev[i-1]!=0){
                fields[i]-=(prev[i-1]-1);
                if(fields[i]<0){
                    works = false;
                }
            }
        }
        int ans = 0;
        if(works) {
            for (int i = 0; i < n; i++) {
                int cur = mincnt(fields[i]);
                if(cur == Integer.MAX_VALUE){
                    works = false;
                    break;
                }
                ans += cur;

            }
        }
        if(works) pw.println(ans);
        else pw.println(-1);
        pw.close();
    }
}