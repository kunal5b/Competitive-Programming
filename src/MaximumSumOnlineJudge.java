import java.io.*;
import java.util.StringTokenizer;

public class MaximumSumOnlineJudge {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] prefix = new int[n+1][n+1];
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++) {
                prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + arr[i-1][j-1];
            }
        }
        int max = 0;
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=n; j++){
                for(int a = i; a<=n; a++){
                    for(int b = j; b<=n; b++){
                        max = Math.max(max, prefix[a][b]-prefix[a][j]-prefix[i][b]+prefix[i][j]);
                    }
                }
            }
        }
        System.out.println(max);
    }
}
