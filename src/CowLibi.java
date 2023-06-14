import java.util.*;

public class CowLibi {
    static class triple implements Comparable<triple>{
        long x, y, t;
        public triple(long x1, long y1, long t1){
            x = x1;
            y = y1;
            t = t1;
        }
        public int compareTo(triple l){
            return (int) (t-l.t);
        }
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        long p = scan.nextInt(), n = scan.nextInt(), cnt = 0;
        ArrayList<triple> grazings = new ArrayList<>();
        for(int i = 0; i<p; i++){
            grazings.add(new triple(scan.nextInt(), scan.nextInt(), scan.nextInt()));
        }
        Collections.sort(grazings);
        ArrayList<triple> alibis = new ArrayList<>();
        for(int i = 0; i<n; i++) {
            alibis.add(new triple(scan.nextInt(), scan.nextInt(), scan.nextInt()));
        }
        for(triple a: alibis){
            boolean all = true;
            for(triple g: grazings){
                if(Math.abs(g.x-a.x)+Math.abs(g.y-a.y)>Math.abs(g.t-a.t)) all = false;
            }
            if(all) cnt++;
        }
        System.out.println(cnt);
    }
}
