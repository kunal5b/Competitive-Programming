import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class HaybaleFeast {
    static class bale{
        long f, s;
        public bale(int f1, int s1){
            f = f1;
            s = s1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hayfeast.in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File("hayfeast.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken()), cur = 0;
        bale[] bales = new bale[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            bales[i] = new bale(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        TreeMap<Long, Integer> spices = new TreeMap<>();
        Long min = Long.MAX_VALUE;
        int l = 0;
        for(int r = 0; r<n; r++){
            cur+=bales[r].f;
            if(!spices.containsKey(bales[r].s)){
                spices.put(bales[r].s, 1);
            }
            else{
                spices.put(bales[r].s, spices.get(bales[r].s)+1);
            }
            while(cur-bales[l].f>=m){
                spices.put(bales[l].s, spices.get(bales[l].s)-1);
                if(spices.get(bales[l].s) == 0) spices.remove(bales[l].s);
                cur-=bales[l].f;
                l++;
            }
            if(cur>=m) min = Math.min(min, spices.lastKey());
        }
        pw.println(min);
        pw.close();
    }
}
