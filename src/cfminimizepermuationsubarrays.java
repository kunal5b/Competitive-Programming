import java.util.Scanner;

public class cfminimizepermuationsubarrays {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t-->0){
            int n = scan.nextInt();
            int one = -1, two = -1, npos = -1;
            for(int i = 0; i<n; i++){
                int val = scan.nextInt();
                if(val == 1) one = i;
                if(val == 2) two = i;
                if(val == n) npos = i+1;
            }
            int l = Math.min(one, two)+1, r = Math.max(one, two)+1;
            if(npos>l && npos<r){
                System.out.println(l + " " + l);
            }
            else if(npos<l){
                System.out.println(l + " " + npos);
            }
            else{
                System.out.println(r + " " + npos);
            }
        }
    }
}
