import java.util.*;

public class cowfrisbee {
    static class pair implements Comparable<pair>{
        int x, y;
        public pair(int a, int b){
            x = a;
            y = b;
        }
        public int compareTo(pair p){return x-p.x;}
    }
    static class comp implements Comparator<pair>{
        public int compare(pair p1, pair p2){
            return p2.x-p1.x;

        }
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        pair[] h = new pair[n];
        for(int i = 0; i<n; i++) h[i] =  new pair(scan.nextInt(), i);
        Arrays.sort(h, new comp());
        TreeSet<pair> set = new TreeSet<>();
        long ans = 0;
        for(int i = 0; i<n; i++){
            pair p = new pair(h[i].y, h[i].x);
            set.add(p);
            if(set.higher(p) != null){
                ans+=(set.higher(p).x-p.x+1);
            }
            if(set.lower(p)!=null){
                ans+=(p.x-set.lower(p).x+1);
            }
        }
        System.out.println(ans);
    }
}
