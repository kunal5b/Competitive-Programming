import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RadioContact {
    static String s = "radio";
    static pt[] f, b;
    static class pt{
        int x, y;
        public pt(int x1, int y1){
            x = x1;
            y = y1;
        }
    }
    static long dist(int i, int j){
        return (long) (f[i].x-b[j].x)*(f[i].x-b[j].x)+ (long)(f[i].y-b[j].y)*(f[i].y-b[j].y);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        f = new pt[n+1];
        b = new pt[m+1];
        st = new StringTokenizer(br.readLine());
        f[0] = new pt(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        b[0] = new pt(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        String s = br.readLine();
        for(int i = 0; i<n; i++){
            if(s.charAt(i) == 'N'){
                f[i+1] = new pt(f[i].x, f[i].y+1);
            }
            if(s.charAt(i) == 'E'){
                f[i+1] = new pt(f[i].x+1, f[i].y);
            }
            if(s.charAt(i) == 'S'){
                f[i+1] = new pt(f[i].x, f[i].y-1);
            }
            if(s.charAt(i) == 'W'){
                f[i+1] = new pt(f[i].x-1, f[i].y);
            }
        }
        s = br.readLine();
        for(int i = 0; i<m; i++){
            if(s.charAt(i) == 'N'){
                b[i+1] = new pt(b[i].x, b[i].y+1);
            }
            if(s.charAt(i) == 'E'){
                b[i+1] = new pt(b[i].x+1, b[i].y);
            }
            if(s.charAt(i) == 'S'){
                b[i+1] = new pt(b[i].x, b[i].y-1);
            }
            if(s.charAt(i) == 'W'){
                b[i+1] = new pt(b[i].x-1, b[i].y);
            }
        }
        long[][] dp = new long[n+1][m+1];
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=m; j++){
                if(i!=0 || j!=0){
                    dp[i][j] = Long.MAX_VALUE;
                    if(i!=0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
                    if(j!=0) dp[i][j] = Math.min(dp[i][j], dp[i][j-1]);
                    if(i!=0 && j!=0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
                    dp[i][j]+=dist(i, j);
                }
            }
        }
        pw.println(dp[n][m]);
        pw.close();
    }
}