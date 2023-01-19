import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class TrappedInTheHaybalesSilver {
    static String s = "trapped";
    static class bale implements Comparable<bale>{
        int s, x;
        public bale(int s1, int x1){
            s = s1;
            x = x1;
        }
        public int compareTo(bale b){
            return x-b.x;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
        bale[] bales = new bale[n];
        int max = 0;
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            bales[i] = new bale(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            max = Math.max(max, bales[i].x);
        }
        System.out.println(max);
        Arrays.sort(bales);
        int min = Integer.MAX_VALUE, start = 0;
        for(int i = 1; i<n; i++){
            System.out.println(bales[i].x + " " + bales[i].s);
            if(bales[i].x>=b && bales[i-1].x<=b){
                start = i;
            }
        }
        int r = start;
        for(int i = start-1; i>=0; i--){
            while(r<n && bales[r].x <= bales[i].x+bales[i].s){
                min = Math.min(min, bales[r].x-bales[i].x-bales[r].s);
                r++;
            }
        }
        r = start-1;
        for(int i = start; i<n; i++){
            while(r>=0 && bales[i].x-bales[i].s<=bales[r].x) {
                min = Math.min(min, bales[i].x-bales[r].x-bales[r].s);
                r--;
            }
        }
        pw.println((min!=Integer.MAX_VALUE) ? Math.max(min, 0) : -1);
        pw.close();
    }
}