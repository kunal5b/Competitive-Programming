import java.io.*;
import java.util.Scanner;

public class Cereal1 {
    static String filename = "cereal";

    public static void main(String[] args) throws IOException {
        // Scanner scan = new Scanner(new File(filename+".in"));
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(filename + ".out"));
        int n = scan.nextInt(), m = scan.nextInt();
        int[] p1 = new int[n], p2 = new int[n];
        for(int i = 0; i<n; i++){
            p1[i] = scan.nextInt()-1;
            p2[i] = scan.nextInt()-1;
        }
        int[] ans = new int[n];
        boolean[] cereal = new boolean[m];
        int cnt = 0;
        for(int i = n-1; i>=0; i--){
            if(!cereal[p1[i]]){
                cereal[p1[i]] = true;
                cnt++;
            }
            else if (!cereal[p2[i]]){
                cereal[p2[i]] = true;
                cnt++;
            }
            ans[i] = cnt;
        }
        for(int i = 0; i<n; i++) System.out.println(ans[i]);
        pw.close();
    }
}