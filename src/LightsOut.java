import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LightsOut {
    static String filename = "lightsout";
    static int n;
    static pt[] vert;
    static class pt{
        int x, y;
        public pt(int c, int k){x = c; y = k;}
    }
    static int dark(int start){
        ArrayList<Integer> cur = new ArrayList<>();
        int cd = 0;
        for(int i = start; i<=n; i++){
            int j = (i+1) % n;
            int k = (i+2) % n;
            if ((vert[i].x - vert[j].x) * (vert[k].y - vert[j].y)-(vert[k].x - vert[j].x) * (vert[i].y - vert[j].y) > 0) {
                cur.add(-1);
            } else {
                cur.add(-2);
            }
            if(count(cur) == 1){
                return cd+shortest[i];
            }
            cd+=(Math.abs(vert[i].x - vert[j].x)+Math.abs(vert[i].y - vert[j].y));
            cur.add(Math.abs(vert[i].x - vert[j].x)+Math.abs(vert[i].y - vert[j].y));
        }
        return cd;
    }
    static int dist(int i, int j){
        return (int) Math.sqrt((long) (vert[i].x-vert[j].x)*(vert[i].x-vert[j].x)+ (long) (vert[i].y-vert[j].y)*(vert[i].y-vert[j].y));
    }
    static int count(ArrayList<Integer> cur){
        int cnt = 0;
        for(int i = 0; i<unique.size()-cur.size(); i++){
            if(unique.subList(i, i+cur.size()).equals(cur)) cnt++;
        }
        return cnt;
    }
    static int[] shortest;
    static ArrayList<Integer> unique = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(filename+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(filename + ".out"));
        n = scan.nextInt();
        vert = new pt[n+1];
        shortest = new int[n+1];
        for(int i = 0; i<n; i++){vert[i] = new pt(scan.nextInt(), scan.nextInt());}
        vert[n] = vert[0];
        for(int i = 1; i<=n; i++){
            shortest[i] = shortest[i-1]+dist(i, i-1);
        }
        shortest[n] = 0;
        for(int i = n-1; i>=0; i--){
            shortest[i] = Math.min(shortest[i], shortest[i+1]+dist(i, i+1));
        }
        for(int i = 0; i<n; i++){
            int j = (i + 1) % n;
            int k = (i + 2) % n;
            /* Use a cross product to determine which way the polygon turned. */
            if ((vert[i].x - vert[j].x) * (vert[k].y - vert[j].y)-(vert[k].x - vert[j].x) * (vert[i].y - vert[j].y) > 0) {
                unique.add(-1);
            } else {
                unique.add(-2);
            }
            unique.add(Math.abs(vert[i].x - vert[j].x)+Math.abs(vert[i].y - vert[j].y));
        }
        int ans = 0;
        for(int i = 1; i<n; i++){
            ans = Math.max(dark(i)-shortest[i], ans);
        }
        pw.println(ans);
        pw.close();
    }
}