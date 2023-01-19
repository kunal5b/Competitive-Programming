import java.io.*;
import java.util.StringTokenizer;

public class TheLazyCowSilver {
    static String s = "lazy";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Math.min(Integer.parseInt(st.nextToken())*2+1, 2*n);
        int[][] sq = new int[n*2+1][n*2+1];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                sq[i+j+1][n-i+j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i<sq.length; i++){
            for(int j = 1; j<sq[i].length; j++){
                sq[i][j]+=sq[i][j-1];
            }
        }
        for(int i = 0; i<sq.length; i++){
            for(int j = 1; j<sq[i].length; j++){
                sq[j][i]+=sq[j-1][i];
            }
        }
        int ans = 0;
        for(int i = 0; i+k<sq.length; i++){
            for(int j = 0; j+k<sq[i].length; j++){
                ans = Math.max(ans, sq[i+k][j+k]-sq[i][j+k]-sq[i+k][j]+sq[i][j]);
            }
        }
        pw.println(ans);
        pw.close();
    }
}