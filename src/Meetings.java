import java.io.*;
import java.util.*;
public class Meetings {
    static class cow implements Comparable<cow>{
        int w, x, d;
        public cow(int a, int b, int c){
            w = a;
            x = b;
            d = c;
        }
        public int compareTo(cow c){return x-c.x;}
    }
    static class pair implements Comparable<pair>{
        int a, b;
        public pair(int a1, int b1){
            a = a1;
            b = b1;
        }
        public int compareTo(pair p){return a-p.b;}
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        // Scanner scan = new Scanner(new File("meetings.in"));
        // PrintWriter pw = new PrintWriter("meetings.out");
        int n = scan.nextInt(), l = scan.nextInt(), cur = 0, tot = 0;
        cow[] cows = new cow[n];

        PriorityQueue<pair> q = new PriorityQueue<>();
        for(int i = 0; i<n; i++){
            int w = scan.nextInt(), x = scan.nextInt(), d = scan.nextInt();
            cows[i] = new cow(w, x, d);
            if(d == -1) q.add(new pair(x, 0));
            else q.add(new pair(l-x, 1));
        }
        Arrays.sort(cows);
        int ans = 0, left = 0, r = n-1;
        while(cur<tot/2){
            pair p = q.poll();
            if(p.b == 0){
                cur+=cows[l].w;
                l++;
            }
            else{
                cur+=cows[r].w;
                r--;
            }

        }

    }
}
