import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class SnowBootsGold {
    static String s = "snowboots";
    static class tile implements Comparable<tile>{
        int a, b;
        public tile(int a1, int b1){
            a = a1;
            b = b1;
        }
        public int compareTo(tile p){return p.a-a;}
    }
    static class boot implements Comparable<boot>{
        int ind, snow, dist;
        public boot(int i, int s, int d){
            ind = i;
            snow = s;
            dist = d;
        }
        public int compareTo(boot b){return b.snow-snow;}
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(s+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = scan.nextInt(), b = scan.nextInt(), max = 1;
        tile[] depth = new tile[n];
        boot[] boots = new boot[b];
        TreeSet<Integer> deep = new TreeSet<>();
        for(int i = 0; i<n; i++){
            int a = scan.nextInt();
            depth[i] = new tile(a, i);
            deep.add(i);
        }
        for(int i = 0; i<b; i++){
            boots[i] = new boot(i, scan.nextInt(), scan.nextInt());
        }
        Arrays.sort(boots);
        Arrays.sort(depth);
        int[] ans = new int[b];
        for(int i = 0, c = 0; i<n && c<b;){
            if(depth[i].a>boots[c].snow){
                deep.remove(depth[i].b);
                if(deep.higher(depth[i].b)!=null && deep.lower(depth[i].b)!=null) max = Math.max(deep.higher(depth[i].b)-deep.lower(depth[i].b), max);
                i++;
                continue;
            }
            if(max<=boots[c].dist) ans[boots[c].ind] = 1;
            c++;
        }
        for(int i = 0; i<b; i++) pw.println(ans[i]);
        pw.close();
    }
}