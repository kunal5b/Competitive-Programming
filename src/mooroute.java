import java.util.Scanner;

public class mooroute {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] a = new int[n];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<n; i++){
            a[i] = scan.nextInt()-1;
            sb.append('R');
        }
        int i = n-1;
        while(i>=0){
            while(i>=0 && a[i]==1){
                sb.append('L');
                a[i]--;
                i--;
            }
            while (i >= 0 && a[i] != 1) {
                sb.append('L');
                a[i]--;
                i--;
            }
            i++;
           while(i<n && a[i]!=0){
               sb.append('R');
               a[i]--;
               i++;
           }
           i--;
        }
        System.out.println(sb);
    }
}
