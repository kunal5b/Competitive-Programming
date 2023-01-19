import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class CowLineup {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lineup.in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File("lineup.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        int[] breeds = new int[n];
        for(int i = 0; i<n; i++) breeds[i] = Integer.parseInt(br.readLine());
        int l = 0, r = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(;r<n; r++){
            if(map.containsKey(breeds[r])) map.put(breeds[r], map.get(breeds[r])+1);
            else map.put(breeds[r], 1);
            max = Math.max(map.get(breeds[r]), max);
            while(map.size()>k+1){
                map.put(breeds[l], map.get(breeds[l])-1);
                if(map.get(breeds[l]) == 0) map.remove(breeds[l]);
                l++;
            }
        }
        pw.println(max);
        pw.close();
    }
}
