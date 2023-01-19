import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class
CowPoetry {
    static String s = "poetry";
    static long mod(long a){
        return (a+1000000007)%1000000007;
    }
    static class word{
        int r, s;
        // r is rhyme, s is syllables
        public word(int s1, int r1){
            s = s1;
            r = r1;
        }
    }
    static long exp(long f, int pwr){
        if(pwr == 0) return 1;
        if(pwr == 1) return (f + 1000000007) % 1000000007;
        long ans = exp(f,pwr/2);
        ans = (ans*ans + 1000000007) % 1000000007;
        if(pwr%2 == 1) ans = (ans*f + 1000000007) % 1000000007;
        return (ans + 1000000007) % 1000000007;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        HashMap<Character, Integer> freq = new HashMap<>();
        word[] words = new word[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            words[i] = new word(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for(int i = 0; i<m; i++){
            Character c = br.readLine().charAt(0);
            if(!freq.containsKey(c)) freq.put(c, 1);
            else freq.put(c, freq.get(c)+1);
        }
        long[] rhyme = new long[n+1];
        long[] dp = new long[k+1];
        dp[0] = 1;
        for(int i = 0; i<=k; i++){
            for(int j = 0; j<n; j++){
                if(words[j].s+i<=k){
                    if(words[j].s+i==k) rhyme[words[j].r] = mod(rhyme[words[j].r]+dp[i]);
                    dp[words[j].s+i] = mod(dp[words[j].s+i]+dp[i]);
                }
            }
        }
        long ans = 1;
        for(char c: freq.keySet()){
            int cur = freq.get(c);
            long cursum = 0;
            for(int i = 0; i<=n; i++){
                if(rhyme[i]!=0)
                    cursum = mod(cursum+exp(rhyme[i], cur));
            }
            ans = mod(ans*cursum);
        }
        pw.println(ans);
        pw.close();
    }
}