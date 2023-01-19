import java.io.*;
import java.util.*;

public class Stampede {
    static String s = "stampede";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = Integer.parseInt(br.readLine());
        TreeMap<Integer, ArrayList<Integer>> process = new TreeMap<>();
        TreeSet<Integer> active = new TreeSet<>();
        HashSet<Integer> seen = new HashSet<>();
        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
            int start = Math.abs(x+1)*r, end = start + r;
            if(!process.containsKey(start)){
                process.put(start, new ArrayList<>());
            }
            if(!process.containsKey(end)){
                process.put(end, new ArrayList<>());
            }
            process.get(start).add(y);
            process.get(end).add(y);
        }
        active.add(1000005);
        for(int key: process.keySet()){
            for(int cur: process.get(key)){
                if(active.contains(cur)) active.remove(cur);
                else active.add(cur);
            }
            seen.add(active.first());
        }
        pw.println(seen.size()-1);
        pw.close();
    }
}