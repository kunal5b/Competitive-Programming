import java.util.Scanner;

public class cfwalkingboy {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t-->0){
            int n = scan.nextInt();
            int last = 0, cnt = 0;
            for(int i = 0; i<n; i++) {
                int cur = scan.nextInt();
                cnt+=((cur-last)/120);
                last = cur;
            }
            cnt+=((1440-last)/120);
            if(cnt>=2) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
