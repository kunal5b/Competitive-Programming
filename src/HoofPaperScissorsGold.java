import java.io.*;
import java.util.StringTokenizer;

public class HoofPaperScissorsGold {
    static String s = "hps";
    static int won(int a, int b){
        if(a == b) return 1;
        return 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        int[] fj = new int[n];
        for(int i = 0; i<n; i++){
            char c = br.readLine().charAt(0);
            if(c == 'H') fj[i] = 1;
            if(c == 'P') fj[i] = 2;
            if(c == 'S') fj[i] = 3;
        }
        int[][][] dp = new int[n+1][k+1][4];
        for(int i = 1; i<=n; i++){
            for(int j = 0; j<=k; j++){
                for(int l = 1; l<=3; l++){
                    if(j == 0) dp[i][j][l] = dp[i-1][j][l]+won(l, fj[i-1]);
                    else{
                        int a = 0, b = 0;
                        if(l == 1){
                            a = 2;
                            b = 3;
                        }
                        if(l == 2){
                            a = 1;
                            b = 3;
                        }
                        if(l == 3){
                            a = 1;
                            b = 2;
                        }
                        dp[i][j][l] = Math.max(Math.max(dp[i-1][j][l], dp[i-1][j-1][a]), dp[i-1][j-1][b])+won(l, fj[i-1]);
                    }
                }
            }
        }
        pw.println(Math.max(Math.max(dp[n][k][1], dp[n][k][2]), dp[n][k][3]));
        pw.close();
    }
}