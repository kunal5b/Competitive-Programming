import java.util.Arrays;
import java.util.Scanner;

public class ucfj {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] b = new int[n];
        int[] lastelement = new int[n];
        int[] curlast = new int[n];
        Arrays.fill(curlast, -1);
        for(int i = 0; i<n; i++){
            b[i] = scan.nextInt();
            lastelement[i] = curlast[b[i]];
            curlast[b[i]] = i;
            System.out.println(lastelement[i]);
        }
    }
}
