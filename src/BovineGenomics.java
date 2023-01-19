import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BovineGenomics {
    static int n, m;
    static String[] spotty, plain;
    static boolean works(int len){
        for(int i = 0; i<=m-len; i++){
            HashSet<String> set = new HashSet<>();
            for(int j = 0; j<n; j++){
                set.add(spotty[j].substring(i, i+len));
            }
            for(int j = 0; j<n; j++){
                if(set.contains(plain[j].substring(i, i+len))) break;
                if(j == n-1) return true;
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter pw = new PrintWriter(new File("cownomics.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        spotty = new String[n];
        plain = new String[n];
        for(int i = 0; i<n; i++) spotty[i] = br.readLine();
        for(int i = 0; i<n; i++) plain[i] = br.readLine();
        int lo = 0, hi = m;
        while(lo<hi){
            int mid = (lo+hi)/2;
            if(works(mid)){
                hi = mid;
            }
            else{
                lo = mid+1;
            }
        }
        pw.println(lo);
        pw.close();
    }
}
