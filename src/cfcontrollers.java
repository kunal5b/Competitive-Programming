import java.util.Scanner;

public class cfcontrollers {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String s = scan.next();
        int plus = 0;
        for(int i = 0; i<s.length(); i++){
            plus+=(s.charAt(i) == '+' ? 1:0);
        }
        int minus = n-plus;
        int q = scan.nextInt();
        while(q-->0){
            long  a = scan.nextInt(), b = scan.nextInt();
            long maximumPossible = (Math.max(a, b)*plus)-(Math.min(a, b)*minus);
            long minimumPossible = (Math.min(a, b)*plus)-(Math.max(a, b)*minus);
            if(maximumPossible<0 || minimumPossible>0){
                System.out.println("NO");
                continue;
            }
            long diff = Math.abs(a-b);
            if(diff == 0){
                if(n%2 == 0) {
                    System.out.println("YES");
                    continue;
                }
                else {
                    System.out.println("NO");
                    continue;
                }
            }
            if(maximumPossible%diff == 0) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
