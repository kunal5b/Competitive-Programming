import java.io.*;
import java.util.Arrays;

public class AngryCowsGold {
    static String s = "angry";
    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = Integer.parseInt(br.readLine());
        double[] dpR = new double[n], dpL = new double[n], bales = new double[n];
        for(int i = 0; i<n; i++){
            bales[i] = Integer.parseInt(br.readLine())*2;
        }
        Arrays.sort(bales);
        dpL[0] = -1;
        int idx = 0;
        for(int i = 1; i<n; i++){
            while(idx+1<i && Math.abs(bales[i]-bales[idx-1])>dpL[idx]+1) idx++;
            dpL[i] = Math.min(bales[i]-bales[idx-1], dpL[idx]+1);
        }
        idx = 1;
        dpR[0] = -1;
        for(int i = 0; i<n/2; i++){
            double temp = bales[i];
            bales[i] = n-i-1;
            bales[n-i-1] = temp;
        }
        for(int i = 1; i<n; i++){
            while(idx<i && Math.abs(bales[i]-bales[idx-1])>dpR[idx]+1) idx++;
            dpR[i] = Math.min(bales[i]-bales[idx-1], dpR[idx]+1);
        }
        double ans = Integer.MAX_VALUE;
        int i = 0, j = n-1;
        while(i<j){
            ans = Math.min(ans, Math.max((bales[j] - bales[i])/2, Math.max(dpL[i], dpR[j])+1));
            if (dpL[i+1] < dpR[j-1]) {
                i++;
            } else {
                j--;
            }
        }
        System.out.println(ans);
    }
}