import java.io.BufferedReader;
import java.util.Scanner;

public class Vacations {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = scan.nextInt();
        }
        int[] dp = new int[n+1];
        int prev = 2;
        for(int i = 1; i<=n; i++){
            dp[i] = dp[i-1];
            if(arr[i-1] == 0){
                dp[i]++;
                prev = 2;
            }
            else if (arr[i-1] == 1 && prev == 1){
                prev = 2;
                dp[i]++;
            }
            else if (arr[i-1] == 1 && prev!=1) {
                prev = 1;
            }
            else if (arr[i-1] == 2 && prev == 0){
                dp[i]++;
                prev = 2;
            }
            else if (arr[i-1] == 2 && prev != 0){
                prev = 0;
            }
            else if (arr[i-1] == 3) {
                if(prev == 1){
                    prev = 0;
                }
                else prev = 1;
            }
        }
        System.out.println(dp[n]);
    }
}
