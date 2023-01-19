import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
public class FairPhotographySilver {
    static String s = "fairphoto";
    static class cow implements Comparable<cow>{
        int v, x;
        public cow(int x1, int t){x = x1; v = t;}
        public int compareTo(cow c){return x-c.x;}
    }
    public static void main(String[] args) throws IOException {
        // Scanner scan = new Scanner(new File(s+".in"));
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File(s+".out"));
        int n = scan.nextInt();
        cow[] cows = new cow[n];
        for(int i = 0; i<n; i++) cows[i] = new cow(scan.nextInt(), (scan.next().charAt(0) == 'W') ? 1 : -1);
        Arrays.sort(cows);
        int ps = 0;
        LinkedList<cow> prefix = new LinkedList<>();
        prefix.add(new cow(cows[0].x, 0));
        int last = 0;
        for(int i = 0; i<n; i++){
            ps+=cows[i].v;
            if(ps>=last){
                prefix.add(new cow(cows[i].x, ps));
            }
            last = Math.max(last, ps);
        }
        for(int i = 0; i<prefix.size(); i++) System.out.println(prefix.get(i).x + " " + prefix.get(i).v);
    }
}