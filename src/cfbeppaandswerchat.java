import java.util.Scanner;

public class cfbeppaandswerchat {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t-->0){
            int n = scan.nextInt();
            int[] a = new int[n];
            int[] map = new int[n];
            for(int i = 0; i<n; i++){
                a[i] = scan.nextInt()-1;
                map[a[i]] = i;
            }
            
        }
    }
}
// 1 3 0 2 4