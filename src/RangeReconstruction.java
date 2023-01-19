import java.util.Scanner;

public class RangeReconstruction {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] a = new int[n];
        for(int i = 0; i<n; i++){
            a[i] = scan.nextInt();
        }
        System.out.print(a[0] + " ");
        for(int i = 1; i<n; i++){
            for(int j = i; j<n; j++){
                int min = Integer.MAX_VALUE;
                int v = scan.nextInt();
                for(int m = i; m<=j; m++){
                    min = Math.min(min, a[i]);
                }
                for(int m = j; m>=i; m--){
                    if(a[m]>=v+min){
                        a[m]-=(a[m]-(v+min));
                        break;
                    }
                    else{
                        a[m] = min-v;
                        break;
                    }
                }
            }
            System.out.print(a[i]);
            if(i<n-1) System.out.print(" ");
        }
    }
}