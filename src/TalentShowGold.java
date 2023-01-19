import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
public class TalentShowGold {
    static String s = "talent";
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = scan.nextInt(), w = scan.nextInt();
        int[] talent = new int[n], weight = new int[n];
        int[] dp = new int[250*1000+2];
        for(int i = 0; i<n; i++){
            weight[i] = scan.nextInt();
            talent[i] = scan.nextInt();
        }
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int i = 0; i<n; i++){
            for(int j = dp.length-1; j>=0; j--){
                if(dp[j] != -1 && j+weight[i]<dp.length){
                    dp[j+weight[i]] = Math.max(dp[j+weight[i]], dp[j]+talent[i]);
                }
            }
        }
        int ans = 0;
        for(int i = w; i<dp.length; i++) ans = Math.max(ans, (dp[i]*1000)/i);
        pw.println(ans);
        pw.close();
    }
}