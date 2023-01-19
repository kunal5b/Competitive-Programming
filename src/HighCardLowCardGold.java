import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighCardLowCardGold {
    static int lowerbound(int num, int[] arr) {
        int lo = 0, hi = arr.length;
        while (lo<hi) {
            int mid = (lo+hi)/2;
            if (num<=mid) hi = mid;
            else lo = mid+1;
        }
        return lo;
    }
    static int upperbound(int num, int[] arr) {
        int lo = 0, hi = arr.length;
        while (lo<hi) {
            int mid = (lo+hi)/2;
            if (num<arr[mid]) lo = mid;
            else hi = mid-1;
        }
        return lo;
    }
    static class comp implements Comparator<Integer>{
        public int compare(Integer x, Integer y){
            return y-x;
        }
    }
    static String s = "cardgame";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s+".in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = Integer.parseInt(br.readLine());
        int[] elsie = new int[n];
        int[] bessie = new int[n];
        boolean[] visited = new boolean[n*2+2];
        for(int i = 0; i<n; i++){
            elsie[i] = Integer.parseInt(br.readLine());
            visited[elsie[i]] = true;
        }
        int cur = 0;
        for(int i = 1; i<=2*n; i++){
            if(!visited[i]){
                bessie[cur++] = i;
            }
        }
        Arrays.sort(bessie);
        Integer[] bessin = new Integer[n/2], elsin = new Integer[n/2];
        for(int i = 0; i<n/2; i++) {
            bessin[i] = bessie[n/2+i];
            elsin[i] = elsie[i];
        }
        Arrays.sort(elsin);
        int l = 0, cnt = 0;
        for(int i = 0; i<n/2 && l<n/2; i++, l++){
            while(l<(n/2) && bessin[l]<elsin[i]) l++;
            if(l == n/2) break;
            cnt++;
        }
        for(int i = 0; i<n/2; i++) {
            bessin[i] = bessie[n/2-i-1];
            elsin[i] = elsie[i+n/2];
        }
        Arrays.sort(bessin, new comp());
        Arrays.sort(elsin, new comp());
        l = 0;
        for(int i = 0; i<n/2 && l<n/2; i++, l++){
            while(l<(n/2) && bessin[l]>elsin[i]) l++;
            if(l == n/2) break;
            cnt++;
        }
        pw.println(cnt);
        pw.close();
    }
}