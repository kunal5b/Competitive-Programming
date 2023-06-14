import java.util.Scanner;

public class cfblackboardlist {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t-->0){
            int n = scan.nextInt(), max = 0, min = 0;
            for(int i = 0; i<n; i++){
                int l = scan.nextInt();
                max = Math.max(max, l);
                min = Math.min(min, l);
            }
            if(min<0){
                System.out.println(min);
            }
            else System.out.println(max);
        }
    }
}
