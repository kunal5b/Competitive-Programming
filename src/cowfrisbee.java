import java.util.Scanner;

public class cowfrisbee {
    static class pair implements Comparable<pair>{
        int x, y;
        public pair(int a, int b){
            x = a;
            y= b;
        }
        public int compareTo(pair p){return x-p.x;}
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] h = new int[n];
        for(int i = 0; i<n; i++) h[i] = scan.nextInt();

    }
}
