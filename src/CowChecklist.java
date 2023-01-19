import java.io.*;
import java.util.StringTokenizer;

public class CowChecklist {
    static class pt{
        int x, y;
        public pt(int x1, int y1){
            x = x1;
            y = y1;
        }
    }
    static int dist(pt x, pt y){
        return (x.x-y.x)*(x.x-y.x)+(x.y-y.y)*(x.y-y.y);
    }
    static String s = "checklist";
    static pt[] h, g;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s + ".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        h = new pt[n];
        g = new pt[m];
        long[][][] dp = new long[n+1][m+1][2];
        // The first state of the dp is the distance along the Holsteins
        // second is distance along Guernseys
        // third is whether we just processed a Guernsey or Holstein
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            h[i] = new pt(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            g[i] = new pt(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k < 2; k++) {
                    dp[i][j][k] = 2000000000;
                }
            }
        }
        dp[1][0][0] = dp[0][0][0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if(i == 1 && j == 0) continue;
                if (i > 1) dp[i][j][0] = Math.min(dp[i][j][0], dp[i - 1][j][0] + dist(h[i - 2], h[i - 1]));
                if (j > 1) dp[i][j][1] = Math.min(dp[i][j][1], dp[i][j - 1][1] + dist(g[j - 2], g[j - 1]));
                if (i > 0 && j > 0) {
                    dp[i][j][0] = Math.min(dp[i][j][0], dp[i - 1][j][1] + dist(g[j - 1], h[i - 1]));
                    dp[i][j][1] = Math.min(dp[i][j][1], dp[i][j - 1][0] + dist(h[i - 1], g[j - 1]));
                }
            }
        }
        pw.println(dp[n][m][0]);
        pw.close();
    }
}