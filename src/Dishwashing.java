import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Dishwashing {
    static String s = "dishes";
    static int[] base;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = Integer.parseInt(br.readLine()), max = 0, ans = n;
        base = new int[n+1];
        ArrayList<Integer>[] stacks = new ArrayList[n+1];
        for(int i = 0; i<=n; i++){
            stacks[i] = new ArrayList<>();
        }
        for(int i = 0; i<n; i++){
            int cur = Integer.parseInt(br.readLine());
            if(cur<max){
                ans = i;
                break;
            }
            for(int j = cur; j>0 && base[j] == 0; j--){
                base[j] = cur;
            }
            while (stacks[base[cur]].size() != 0 && stacks[base[cur]].get(stacks[base[cur]].size()-1) < cur) {
                max = stacks[base[cur]].get(stacks[base[cur]].size()-1);
                stacks[base[cur]].remove(stacks[base[cur]].size()-1);
            }
            stacks[base[cur]].add(cur);
        }
        pw.println(ans);
        pw.close();
    }
}