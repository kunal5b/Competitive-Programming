import java.util.Scanner;

public class CircularBarn {
    static class v{
        int t, rem;
        public v(int t1, int r1){
            t = t1;
            rem = r1;
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scan.nextInt();
            v[] arr = new v[n];
            String ans = "";
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                int a = scan.nextInt();
                v val = new v(a/4, a%4);
                if(val.t<min){
                    ans = val.rem == 0 ? "Farmer Nhoj" : "Farmer John";
                    min = val.t;
                }
            }
            System.out.println(ans);
        }
    }
}