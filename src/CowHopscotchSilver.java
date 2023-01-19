import java.io.*;
import java.util.StringTokenizer;

public class CowHopscotchSilver {
static String s = "hopscotch";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()), p = Integer.parseInt(st.nextToken());
        int[][] arr = new int[r][c];
        for(int i = 0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<c; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] dp = new int[r][c];
        dp[0][0] = 1;
        for(int i = 0; i<r; i++){
            for(int j = 0; j<c; j++){
                for(int k = 0; k<i; k++){
                    for(int a = 0; a<j; a++){
                        if(arr[k][a]!=arr[i][j]){
                            dp[i][j]+=dp[k][a];
                            dp[i][j]%=1000000007;
                        }
                    }
                }
            }
        }
        pw.println(dp[r-1][c-1]);
        pw.close();
    }
}