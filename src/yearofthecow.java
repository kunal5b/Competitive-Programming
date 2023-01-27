import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class yearofthecow {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), k = scan.nextInt();
        Double[] arr = new Double[n];
        for(int i = 0; i<n; i++){
            arr[i] =  Math.ceil(scan.nextInt()/12)*12;
        }
        Arrays.sort(arr, Comparator.reverseOrder());
        Double[] dist = new Double[n];
        dist[n-1] = arr[n-1]-12;
        for(int i = 1; i<n; i++){
            System.out.print(arr[i] + " " );
            dist[i-1] = Math.abs(arr[i]-arr[i-1]);
        }
        Arrays.sort(dist, Comparator.reverseOrder());
        double ans = arr[0];
        for(int i = 0; i<k-1; i++){
            ans-=dist[i];
        }
        System.out.println(ans);
    }
}
