import java.io.*;
import java.util.HashMap;

public class SecretCodeSilver {
    static String s = "scode";
    static HashMap<String, Integer> mto = new HashMap<>();
    static int solve(String s){
        if(mto.containsKey(s)) return mto.get(s);
        int cnt = 1;
        int l = s.length();
        for(int i = 1; i*2<l; i++) {
            if (s.substring(0, i).equals(s.substring(i, 2 * i))) {
                cnt += solve(s.substring(i));
            }
            if (s.substring(0, i).equals(s.substring(l - i))) {
                cnt += solve(s.substring(i));
            }
            if (s.substring(l-i).equals(s.substring(0, i))) {
                cnt+=solve(s.substring(0, l-i));
            }
            if (s.substring(l-2*i, l-i).equals(s.substring(l - i))) {
                cnt+=solve(s.substring(0, l-i));
            }
        }
        cnt%=2014;
        mto.put(s, cnt);
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        pw.println(solve(br.readLine())-1);
        pw.close();
    }
}