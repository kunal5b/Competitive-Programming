import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CrazyFencesSilver {
    static String filename = "crazy";
    static int n, cur = 0;
    static fence[] arr;
    static boolean[] v;
    static ArrayList<fence>[] components;
    static class fence{
        long x1, y1, x2, y2;
        public fence(long x, long y, long x3, long y3){
            x1 = x;
            y1 = y;
            x2 = x3;
            y2 = y3;
        }
    }
    static class point{
        int x, y;
        public point(int x1, int u1){
            x = x1;
            y = u1;
        }
    }
    static boolean connected(int i, int ind){
        return ((arr[i].x1 == arr[ind].x1 && arr[i].y1 == arr[ind].y1
                || arr[i].x1 == arr[ind].x2 && arr[i].y1 == arr[ind].y2) ||
                (arr[i].x2 == arr[ind].x2 && arr[i].y2 == arr[ind].y2
                        || arr[i].x2 == arr[ind].x1 && arr[i].y2 == arr[ind].y1));
    }
    static void component(int ind){
        v[ind] = true;
        components[cur].add(arr[ind]);
        for(int i = 0; i<n; i++){
            if(!v[i] && connected(i, ind)) component(i);
        }
    }
    static boolean hits(fence f, int x, int y){
        if ((f.y1 > y) ^ (f.y2 > y)) {
            return (f.y1 - f.y2 < 0) ^ (f.x2 * (f.y1 - y) + f.x1 * (y - f.y2) > x * (f.y1 - f.y2));
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(filename+".in"));
        // Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(filename + ".out"));
        n = scan.nextInt();
        int c = scan.nextInt();
        arr = new fence[n];
        v = new boolean[n];
        components = new ArrayList[n];
        for(int i = 0; i<n; i++){
            arr[i] = new fence(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt());
        }
        for(int i = 0; i<n; i++){
            if(!v[i]){
                components[cur] = new ArrayList<>();
                component(i);
                cur++;
            }
        }
        int ans = 0;
        HashMap<ArrayList<Integer>, Integer> freq = new HashMap<>();
        for(int i = 0; i<c; i++){
            int x = scan.nextInt(), y = scan.nextInt();
            ArrayList<Integer> arr = new ArrayList<>();
            for(int j = 0; j<cur; j++){
                int cnt = 0;
                for(fence f: components[j]){
                    cnt+=hits(f, x, y) ? 1 : 0;
                }
                arr.add(cnt%2 == 1 ? 1 : -1);
            }
            if(!freq.containsKey(arr)){
                freq.put(arr, 0);
            }
            freq.put(arr, freq.get(arr)+1);
            ans = Math.max(ans, freq.get(arr));
        }
        pw.println(ans);
        pw.close();
    }
}