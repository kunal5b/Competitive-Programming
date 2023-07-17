import java.util.Scanner;

public class cfnoprimedifferences {
    static boolean isPrime(int n) {
        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t-->0){
            int n = scan.nextInt(), m = scan.nextInt();
            int odd = m*(n/2)+1, even = 1;
            if(isPrime(m)){
                for(int i = 0; i<n; i++){
                    for(int j = 0; j<m; j++){
                        if(i%2 == 0){
                            System.out.print(odd++ + " ");
                        }
                        else{
                            System.out.print(even++ + " ");
                        }
                    }
                    System.out.println();
                }
            }
            else {
                int val = 1;
                for(int i = 0; i<n; i++){
                    for(int j = 0; j<m; j++){
                        System.out.print(val++ + " ");
                    }
                    System.out.println();
                }
            }
        }
    }
}
