import java.io.*;
import java.util.StringTokenizer;

public class FruitFeast {
    static String s = "feast";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken()), a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
        boolean[][] dp = new boolean[t+1][2];
        dp[0][0] = true;
        for(int j = 0; j<2; j++){
            for(int i = 0; i<=t; i++){
                    if(!dp[i][j]) continue;
                    if(i+a <= t) dp[i+a][j] = true;
                    if(i+b <= t) dp[i+b][j] = true;
                    if(j!=1) dp[i/2][1] = true;
            }
        }
        int i;
        for(i = t; i>=0; i--) if(dp[i][1] || dp[i][0]) break;
        pw.println(i);
        pw.close();
    }
}