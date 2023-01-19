import java.io.*;
import java.util.*;
public class Cowpatibility {
     static HashMap<ArrayList<Integer>, Integer> map = new HashMap<>();
    static void subset(int c, int[] a, ArrayList<Integer> cur){
        if(c == 5){
            if(!map.containsKey(cur)) map.put(cur, 1);
            else map.put(cur, map.get(cur)+1);
            return;
        }
        subset(c+1, a, new ArrayList<>(cur));
        cur.add(a[c]);
        subset(c+1, a, new ArrayList<>(cur));
    }
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowpatibility.in"));
        PrintWriter pw = new PrintWriter(new File("cowpatibility.out"));
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] flavors = new int[5];
            for (int j = 0; j < 5; j++) {
                flavors[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(flavors);
            subset(0, flavors, new ArrayList<Integer>());
        }
        int[] arr = new int[6];
        long ans = n*(n-1)/2;
        int[] d = {-1, +1, -1, +1, -1, +1};
        for(ArrayList<Integer> key: map.keySet()){
            if(key.size() != 0) ans-=(d[key.size()]*map.get(key)*(map.get(key)-1)/2);
        }
        pw.println(ans);
        pw.close();
    }
}