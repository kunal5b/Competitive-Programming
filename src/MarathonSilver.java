import java.io.*;
import java.util.StringTokenizer;

public class MarathonSilver {
    static String s = "marathon";
    static class cp{
        int a, b;
        public cp(int x1, int y1){
            a = x1;
            b = y1;
        }
    }
    static int dist(cp p, cp p2){
        return Math.abs(p.a-p2.a)+Math.abs(p.b-p2.b);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        cp[] cps = new cp[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            cps[i] = new cp(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        int[][] dp = new int[n+1][k+1];
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=k; j++){
                dp[i][j] = 1000000000;
            }
        }
        dp[0][0] = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<=k; j++){
                for(int a = i+1; a<n && j+a-i-1<=k; a++){
                    dp[a][j+a-i-1] = Math.min(dp[i][j]+dist(cps[i], cps[a]), dp[a][j+a-i-1]);
                }
            }
        }
        pw.println(dp[n-1][k]);
        pw.close();
    }
}